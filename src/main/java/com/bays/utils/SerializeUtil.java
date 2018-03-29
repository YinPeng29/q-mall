package com.bays.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by yinpeng on 2018/3/26.
 * 序列化/反序列化工具类
 */
public class SerializeUtil {
    static final Class<?> CLASS = SerializeUtil.class;
    private static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);
    public static byte[] serialize(Object obj){
        if(obj == null ){
            throw new NullPointerException("can't serialize obj(null)");
        }
        byte[] bt = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try{
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(obj);
            os.close();
            bos.close();
            bt = bos.toByteArray();
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("serialize error...",e);
        }
        return bt;
    }

    public static <T> T deserialize(byte[] in){
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try{
            if(in != null){
                bis = new ByteArrayInputStream(in);
                ois = new ObjectInputStream(bis);
                obj = ois.readObject();
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("deserialize error ... ",e);
        }
        return (T)obj;
    }
}