package com.boss.trainee.gateway.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/24 16:36
 */
@Slf4j
public class FileUtil {

    private final static String FILEPATH = "F:\\BossCode\\log\\test.txt";

    public static void read(StringBuilder stringBuilder) {

//        File file = new File(FILEPATH);
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            FileWriter fileWriter = new FileWriter(FILEPATH);
//            fileWriter.write(stringBuilder.toString());
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        FileOutputStream o = null;
        byte[] buff = new byte[]{};
        try {
            File file = new File(FILEPATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            buff = stringBuilder.toString().getBytes();
            o = new FileOutputStream(file, true);
            byte[] line = null;
            line = "\r\n".getBytes();
            o.write(line);
            o.write(buff);
            o.flush();
            o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
