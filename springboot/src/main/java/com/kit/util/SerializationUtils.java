package com.kit.util;

import java.io.*;

public class SerializationUtils {
    private static String FILE_NAME = "c:/data/test";

    //序列化  写的过程
    public static void write(Serializable s) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            objectOutputStream.writeObject(s);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //反序列化 读的过程
    public static Object read() {
        Object obj = null;
        // 反序列化
        try {
            ObjectInput input = new ObjectInputStream(new FileInputStream(FILE_NAME));
            obj = input.readObject();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}