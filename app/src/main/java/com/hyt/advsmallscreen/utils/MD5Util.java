package com.hyt.advsmallscreen.utils;

 

import com.google.common.hash.Hashing;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ${Tao} on 2017-11-1111.
 */

public class MD5Util {
    
    public  static  String   md5FromFile (String path, boolean upper) throws IOException {
        FileInputStream fis = new FileInputStream(path);
//        String md5 =DigestUtils.md5Hex(fis);

        String md5 = new String(Hex.encodeHex(DigestUtils.md5(fis)));

        return upper ? md5.toString().toUpperCase() : md5.toString();
        
    }

//    /**
//     * <pre>
//     * @param charSequence
//     * @param charset
//     * @param upper
//     * @return 生成的MD5
//     * </pre>
//     */
    public static String createMD5(CharSequence charSequence, Charset charset,
                                   boolean upper) {


        String md5 = Hashing.md5().newHasher().putString(charSequence, charset)
                .hash().toString();
        return upper ? md5.toUpperCase() : md5;
    }



    public  static  String creatRequest(){
       return createMD5(String.valueOf(Math.random()) , Charsets.UTF_8 ,false);
    }



    /**Md5字符串加密
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (string == null || string.isEmpty()) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 对字符串BASE64编码
     *
     * @param res
     *            源字符串
     * @return String
     */
    public static String encode(String res) {
        try {
            // android
//            Base64 base = new Base64();
            if ((res != null) && (!"".equals(res))) {
//                return new String(base.encode(res.getBytes()));
                // java
                return Base64Object.stringToBase64(res);
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * Base64编码
     * @param strSrc 需要编码的字符串
     * @return 编码后的字符串
     */
//       public static String encrypt(String strSrc){
//               String strBse64 ="";
//           //    byte[] bytes = Base64.encode(strSrc.getBytes(), Base64.NO_WRAP);
//
//               strBse64 = new String(bytes);
//               return strBse64;
//            }

    /**
     * BASE64字符串解码
     *
     * @param str
     *            BASE64字符串
     * @return String
     */
    public static String decode(String str) {
        try {
            byte[] bytes= new Base64().decode(str.getBytes());
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
