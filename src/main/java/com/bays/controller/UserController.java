package com.bays.controller;

import com.bays.model.User;
import com.bays.service.user.UserService;
import com.bays.utils.JwtBuilder.JwtBuilder;
import com.bays.utils.MailUtil;
import com.bays.utils.ResponseHandle;
import com.bays.utils.ReturnCode;
import com.bays.utils.SysUtil;
import com.bays.utils.redisTool.RedisUtil;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController extends ResponseHandle{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Value("${token.key}")
    private String tokenInfo;

    @Value("${mail.sender.account}")
    private String myEmail;

    @ResponseBody
    @RequestMapping("/info")
    public String findAll(){
//        System.out.println("jedis: "+jedisPool.getResource().toString());
//        RedisUtil redisUtil = new RedisUtil(jedisPool);
//        redisUtil.set("yin","peng");
        List<Map> all = userService.findAll();
        logger.info("index 运行成功..."+all.toString());
        long time = 60*60*24*1000;
        JwtBuilder jwtBuilder = new JwtBuilder("13223329",new Date(),time);
        try {
            String s = jwtBuilder.compactJWT();
            System.out.println("加密后: "+s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("tokenInfo: "+ tokenInfo+" emailAccount: "+ myEmail);
        return "hello world";
    }

    @RequestMapping(value = "/login",method= RequestMethod.POST)
    @ResponseBody
    public String login(User user, HttpServletRequest request){
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        User user1 = userService.selectUser(user);
        if(user1 != null){
            HttpSession session = request.getSession();
            session.setAttribute("user",userName);
            session.setAttribute("isLogin",true);
            session.setAttribute("user_info",user1);
            System.out.println(this.setResponse(ReturnCode.SUCCESS).toJSONString());
            com.alibaba.fastjson.JSONObject js = new com.alibaba.fastjson.JSONObject();
            js.put("type",user1.getType());
            return toJsonString(js);
        }
        return this.setResponse(ReturnCode.LOGIN_FAIL).toJSONString();
    }

    /**
     * 注册用户
     * @param user
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public String save(User user,HttpServletRequest request){
        logger.info("user info: "+ user.toString());

        String username = request.getParameter("username");
        user.setUserName(username);
        user.setPassWord(request.getParameter("password"));
        user.setAddTime(new Date());
        user.setEmail(request.getParameter("email"));
        user.setStatus(0); //新用户状态0 待激活
        user.setType("0");
        //唯一标识
        String uuid = SysUtil.randomUUID();
        String userid= SysUtil.randomUUID();
        user.setUserId(userid);
        user.setUuid(uuid);
        List<Map> userByName = userService.findUserByName(username);
        if(userByName.size() > 0){
            return this.setResponse(ReturnCode.USER_EXISTS).toJSONString();
        }
        int i = userService.saveUser(user);
        if(i>0){
            String userEmail = user.getEmail();
            String url = "http://localhost:8080/user/active?uuid="+uuid+"&userId="+userid;
            MailUtil.sendMail("<h2>请点击下面链接,激活您的帐号</h2>"+
                    "<a href='"+url+"'"+">"+url+"</a>",
                    userEmail,username);
            return this.setResponse(ReturnCode.EMAIL_VALID).toJSONString();

        }
        return this.setResponse(ReturnCode.REGISTER_FALI).toJSONString();
    }

    /**
     * 激活用户方法
     * 过期时间三天
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/active",method = RequestMethod.GET)
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public String Activation(HttpServletRequest request){
        String uuid = request.getParameter("uuid");
        String userId = request.getParameter("userId");
        boolean bool = userService.validMail(userId,uuid);
        if(!bool){
            return "邮件已经失效,请联系管理员";
        }
        int i = userService.updateUser(1, userId, uuid);
        if(i <= 0){
            return "激活失败";
        }
        return "激活成功,请前往登录";
    }

    @RequestMapping(value = "/queryCurrentUser",method= RequestMethod.POST)
    @ResponseBody
    public String queryCurrentUser(HttpServletRequest req){
        Object user = req.getSession().getAttribute("user");
        if(user!=null){
            User currentUser = (User) req.getSession().getAttribute("user_info");
            return setResponse(ReturnCode.SUCCESS,currentUser.toJson()).toJSONString();
        }
        return setResponse(ReturnCode.FAILD).toJSONString();
    }


}
