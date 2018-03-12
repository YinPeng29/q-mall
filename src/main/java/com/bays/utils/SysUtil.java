package com.bays.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by yinpeng on 2017/11/1.
 * Generation of global unique UUID
 */
public class SysUtil {

    public static final String bar = "-";
    private static final String DES = "DES";
    private static final String signKey = "*Static#604*";
    /**
     * 产生随机UUID可充当ID 或者验证信息来使用
     * @return
     */
    public static String randomUUID(){
        String uid = UUID.randomUUID().toString();
        return uid.replaceAll(SysUtil.bar,"");
    }
    public static String strUUID(String str){
        String uid = UUID.nameUUIDFromBytes(str.getBytes()).toString();
        return uid.replaceAll(SysUtil.bar,"");
    }

    /**
     * 加密方法 token加签
     * @param data
     * @param key 长度>5
     * @return
     * @throws Exception
     */
    public static String encrypt(String data,String key) throws Exception {
        byte[] resource = encrypt(data.getBytes(Field.ENCODE),key.getBytes(Field.ENCODE));
        String str = new BASE64Encoder().encode(resource);
        return str;
    }

    /**
     * 解密方法 token验签
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String data,String key) throws Exception {
        if(data==null) return null;
        BASE64Decoder  decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf,key.getBytes());
        return new String(bt);
    }

    /**
     * 加密
     */
    private static byte[] encrypt(byte[] data,byte[] key) throws Exception {
        //生成可信任的随机数
        SecureRandom secureRandom = new SecureRandom();
        //原始秘钥数据创建 DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        //秘钥工厂 然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);  //秘钥
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,secureRandom);
        return cipher.doFinal(data);  //加密数据
    }

    /**
     * 解密
     */
    private static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        //生成可信任的随机数
        SecureRandom secureRandom = new SecureRandom();
        //原始秘钥数据创建 DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        //秘钥工厂 然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE,secretKey,secureRandom);
        return cipher.doFinal(data);
    }

    /**
     * BASE64 加密
     */
    public static String base64Encode(String data) throws UnsupportedEncodingException {
        return new BASE64Encoder().encode(data.getBytes(Field.ENCODE));
    }
    /**
     * BASE64 解密
     */
    public static String base64Decode(String data) throws IOException {
        BASE64Decoder  bd = new BASE64Decoder();
        byte[] bytes = bd.decodeBuffer(data);
        return new String(bytes,Field.ENCODE);
    }

    /**
     * 生成加密的签名
     * @return
     */
    public static String secretKey(){
        String str=null;
        try {
            str = base64Encode(signKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}