package com.topwordanalysis.databaseOperation.encryption;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * MD5加密工具类
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */
public class MD5Util {
    private static String KEY=null;//加盐中用到的AES加密的秘钥

    /**
     * 加载encryption.properties
     * 获得KEY
     */
    static {
        try {
            Properties properties=new Properties();
            InputStream in=MD5Util.class.getClassLoader().getResourceAsStream("config/encryption.properties");
            properties.load(in);
            KEY=properties.getProperty("MD5KEY");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于进行MD5加密
     *
     * @param password 需要加密的密码
     * @return 加密后的密文
     */
    public static String md5Password(String password) {
        try {
            //密文AES加密后截取一部分用于加盐
            String encrypt=AESUtil.aesEncrypt(password,KEY);
            if (encrypt.length()>=8){
                password=password+encrypt.substring(0,8);
            } else {
                password=encrypt+password;
            }

            // 得到一个信息摘要器
            MessageDigest digest=MessageDigest.getInstance("md5");
            byte[] result=digest.digest(password.getBytes());
            StringBuffer buffer=new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b:result) {
                int number=b&0xff;
                String str=Integer.toHexString(number);
                if (str.length()==1)
                    buffer.append("0");
                buffer.append(str);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.md5Password("123456"));
    }
}
