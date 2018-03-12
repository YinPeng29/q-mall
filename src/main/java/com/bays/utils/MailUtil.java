package com.bays.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by yinpeng on 2017/11/2.
 * 邮件发送工具类
 */
public class MailUtil {
    public static final String myEmailAccount = "yinpeng29@163.com";
    public static final String myEmailPwd = "YINpeng321";
    public static final String COM_FROM="XXX网";    //发件人名称
    public static final String MAIL_TITLE ="请激活您的账号";
    public static final String MAIL_TRANSPORT_PROCOTOL = "mail.transport.protocol";
    public static final String MAIL_SERVER_NAME = "smtp";
    public static final String MAIL_HOST_TYPE ="mail.smtp.host";
    public static final String MAIL_HOST_ADDRESS = "smtp.163.com";      //发件人邮箱服务器地址 这里使用网易服务器
    public static final String MAIL_AUTH = "mail.smtp.auth";
    public static final String MAIL_HEADER = "text/html;charset=UTF-8";
    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    /**
     *
     * @param body              邮件内容
     * @param receiveMailAddress    收件人邮箱
     * @param username              收件人姓名
     * @throws Exception
     */
    public static void sendMail(String body,String receiveMailAddress,String username) {
        Properties prop = new Properties();
        prop.setProperty(MAIL_TRANSPORT_PROCOTOL,MAIL_SERVER_NAME);  //邮箱协议 javaMail规范
        prop.setProperty(MAIL_HOST_TYPE,MAIL_HOST_ADDRESS);      //smtp
        prop.setProperty(MAIL_AUTH,Field.TRUE);     //请求认证

        //根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(prop);
        //设置为debug模式 true/false true 启动调试控制台可以看到调试信息
        session.setDebug(false);
        // 根据 Session 获取邮件传输对象
        try{
        Transport transport = session.getTransport();
        MimeMessage mimeMessage = createMail(session,myEmailAccount,receiveMailAddress,username,body);
        transport = session.getTransport();
        transport.connect(myEmailAccount,myEmailPwd);
        //发送邮件, 发到所有的收件地址, message.getAllRecipients()
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
        }catch(Exception e){
            logger.error("com.bays.utils.MailUtil.sendMail",e);
            e.printStackTrace();
        }
    }

    /**
     *
     * @param session
     * @param sendMail      发件人邮箱
     * @param receiveMail   收件人邮箱
     * @param username      收件人姓名
     * @param body          邮件内容
     * @return
     * @throws Exception
     */
    private static MimeMessage createMail(Session session,String sendMail,String receiveMail,String username,String body) throws Exception{
        //创建邮件
        MimeMessage message = new MimeMessage(session);

        //设置邮件来源
        InternetAddress from = new InternetAddress();
        from.setAddress(sendMail);
        from.setPersonal(COM_FROM,Field.ENCODE);
        message.setFrom(from);

        //设置收件人信息
        InternetAddress receiveAdress = new InternetAddress();
        receiveAdress.setAddress(receiveMail);
        receiveAdress.setPersonal(username,Field.ENCODE);
        message.setRecipient(MimeMessage.RecipientType.TO,receiveAdress);

        //邮件主题
        message.setSubject(MAIL_TITLE,Field.ENCODE);
        //邮件头 text/html;charset=UTF-8
        message.setContent(body,MAIL_HEADER);
        //发件日期
        message.setSentDate(new Date());
        //保存设置
        message.saveChanges();
        return message;
    }
}

