package com.bays.service.core.impl;


import com.bays.model.resp.Article;
import com.bays.model.resp.NewsMessage;
import com.bays.model.resp.TextMessage;
import com.bays.service.core.CoreService;
import com.bays.utils.Constant;
import com.bays.utils.MessageUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yinpeng on 2018/5/11.
 */
@Service
public class CoreServiceImpl implements CoreService {

    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        String respcontent="";
        try{
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号(open_id)
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0);
            List<Article> articleList = new ArrayList<Article>();
            if(msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)){
                String content = requestMap.get("Content");
                if("1".equals(content)){
                    textMessage.setContent("小猪佩奇身上纹");
                    System.out.println("回复用户: 小猪佩奇身上纹");
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }else if("2".equals(content)){
                    textMessage.setContent("掌声送给社会人");
                    System.out.println("回复用户: 掌声送给社会人");
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }else if(content.contains("常静")){
                    textMessage.setContent("一个美丽的咖啡店主");
                    System.out.println("回复用户: 一个美丽的咖啡店主");
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                } else if(content.contains("尹鹏")){
                    textMessage.setContent("爸爸爱你");
                    System.out.println("回复用户: 爸爸爱你");
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }else if(content.contains("郝伟")){
                    textMessage.setContent("没错郝伟就是个傻逼");
                    System.out.println("回复用户: 没错郝伟就是个傻逼");
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }else if(content.contains("咖啡") || content.contains("美式") || content.contains("拿铁") || content.contains("摩卡")){
                    Article article = new Article();
                    article.setTitle("好的,这位爷");
                    article.setDescription("咖啡马上就到,先看点图文吧");
                    article.setPicUrl("http://mpic.tiankong.com/235/b54/235b54fde7169de7c2f36e6c76f98168/FO-56953.jpg");
                    article.setUrl("https://www.jianshu.com/p/1c8f2fdb4c88");
                    articleList.add(article);
                    System.out.println("回复: 好的,这位爷");
                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                }else if("快点".equals(content)){
                    Article article1 = new Article();
                    article1.setTitle("这位爷,您别急");
                    article1.setDescription("再看几篇文章吧");
                    article1.setPicUrl("http://mpic.tiankong.com/d08/e2b/d08e2b8bac5adf34a07b02bdd7aae4a3/640.jpg");
                    article1.setUrl("https://www.jianshu.com/p/9e4a2b57d092");

                    Article article2 = new Article();
                    article2.setTitle("张雪峰老师，直男癌就直男癌，别拉普通人垫背");
                    article2.setDescription("这篇您一定想看");
                    article2.setPicUrl("https://upload-images.jianshu.io/upload_images/326721-05fa5b532a321a9c?imageMogr2/auto-orient/strip%7CimageView2/2/w/700");
                    article2.setUrl("https://www.jianshu.com/p/b5826cde6786");

                    Article article3 = new Article();
                    article3.setTitle("震惊,点开竟然有红包领");
                    article3.setDescription("最顶级的情商，是懂得他人说不出口的话");
                    article3.setPicUrl("https://upload-images.jianshu.io/upload_images/1767483-6da9939117c20771.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/320");
                    article3.setUrl("https://www.jianshu.com/p/8a27899c85f9");

                    Article article4 = new Article();
                    article4.setTitle("豆瓣9.1分神剧，迷人的不仅仅是少儿不宜");
                    article4.setDescription("迷人的不仅仅是少儿不宜");
                    article4.setPicUrl("https://upload-images.jianshu.io/upload_images/6126137-ef01fba6dc272d1c?imageMogr2/auto-orient/strip%7CimageView2/2/w/700");
                    article4.setUrl("https://www.jianshu.com/p/b511a37388e7");

                    articleList.add(article1);
                    articleList.add(article2);
                    articleList.add(article3);
                    articleList.add(article4);
                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    System.out.println("回复: 这位爷,您别急");
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                } else{
                    textMessage.setContent(Constant.TEXT_MESSAGE_ONE);
                    System.out.println("回复用户: "+Constant.TEXT_MESSAGE_ONE);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
            }else if(msgType.equals("event")){ // 事件类型

                String event = requestMap.get("Event");
                if(event.equals("subscribe")){
                    respcontent = "感谢关注 这里是研磨时光机呀！\n";
                    StringBuffer contentMsg = new StringBuffer();
                    contentMsg.append("您还可以回复下列数字，体验相应服务").append("\n\n");
                    contentMsg.append("1  回复测试").append("\n");
                    contentMsg.append("咖啡/美式/拿铁 图文").append("\n");
                    contentMsg.append("快点  我是多图文").append("\n");
                    respcontent = respcontent+contentMsg.toString();
                }else if(event.equals("unsubscribe")){
                    // 取消关注,用户接受不到我们发送的消息了，可以在这里记录用户取消关注的日志信息
                }else if(event.equals("CLICK")){
                    String eventKey = requestMap.get("EventKey");
                    if(eventKey.equals("shop")){
                        System.out.println("用户点击了店铺简介");
                        respcontent = "您点击了店铺简介";
                    }else if(eventKey.equals("self")){
                        System.out.println("用户点击了店主自制");
                        respcontent = "您想来一杯店主自制咖啡么";
                    }
                }
                textMessage.setContent(respcontent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return respMessage;
    }
}