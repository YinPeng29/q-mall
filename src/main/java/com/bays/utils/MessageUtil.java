package com.bays.utils;

import com.bays.model.resp.Article;
import com.bays.model.resp.MusicMessage;
import com.bays.model.resp.NewsMessage;
import com.bays.model.resp.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinpeng on 2018/5/11.
 */
public class MessageUtil {
    /**
     * 解析微信发来的请求信息
     */
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception{
        Map<String,String> map = new HashMap<String,String>();
        //从request中取得输入流
        InputStream inputStream = request.getInputStream();
        //读取输入流
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        Element root = document.getRootElement();

        @SuppressWarnings("unchecked")
        List<Element> elementList = root.elements();
        //遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(),e.getText());
        }
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 扩展xstream，使其支持CDATA块
     *
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * 消息转换
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        xstream.alias("xml",textMessage.getClass());
        return xstream.toXML(textMessage);
    }
    public static String musicMessageToXml(MusicMessage musicMessage){
        xstream.alias("xml",musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }
    public static String newsMessageToXml(NewsMessage newsMessage){
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }

}