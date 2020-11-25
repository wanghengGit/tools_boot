package com.kit.util;

import java.util.ArrayList;
import java.util.List;

/**
 * String处理工具类
 */
public class StringUtil {

    private static List<String> parseVariables(String expr) {
        List<String> vars = new ArrayList<>();
        int idx = expr.indexOf("{");
        while (idx > -1) {
            int idx2 = expr.indexOf("}", idx);
            if (idx2 > -1) {
                vars.add(expr.substring(idx + 1, idx2));
                idx = expr.indexOf("{", idx2);
            } else {
//                throw new SystemException("invalid expr:{}", expr);
            }
        }
        return vars;
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    /**
     * 字符串转16进制byte
     *
     * @param
     * @return
     * @throws Exception
     * @author hw
     * @date 2018/10/19 9:47
     */
    private static byte hexStr2Str(String hexStr) {
        String str = "0123456789abcdef";
        char[] hexs = hexStr.toCharArray();
        int n = 0;
        n = str.indexOf(hexs[0]) * 16;
        n += str.indexOf(hexs[1]);
        byte bytes = (byte) (n & 0xff);

        return bytes;
    }

    public static String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 字节数组转16进制
     *
     * @param bytes 需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    /**
     * 字节转十六进制
     *
     * @param b 需要进行转换的byte字节
     * @return 转换后的Hex字符串
     */
    public static String byteToHex(byte b) {
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static void Testbytes2Int() {
        byte[] bytes = {-23, -18, 51, 1};
        int i0 = bytes[0] & 0xFF;
        int i1 = (bytes[1] & 0xFF) << 8;
        int i2 = (bytes[2] & 0xFF) << 16;
        int i3 = (bytes[3] & 0xFF) << 24;
        System.out.println(i0 | i1 | i2 | i3); //输出20180713

    }

    private static String intToHex(int n) {
        StringBuffer s = new StringBuffer();
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            s = s.append(b[n%16]);
            n = n/16;
        }
        a = s.reverse().toString();
        return a;
    }
}
