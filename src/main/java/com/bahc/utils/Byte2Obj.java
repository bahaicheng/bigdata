package com.bahc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Byte2Obj {
    public static void main(String[] args) {

    }

    public static void ByteStreamTest(){

    }

    public static void byteArray(){
        int a = 0;
        int b = 1;
        int c = 2;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        bout.write(a);
        bout.write(b);
        bout.write(c);
        byte[] buff = bout.toByteArray();
        for (int i = 0; i < buff.length; i++)
            System.out.println(buff[i]);
        System.out.println("***********************");
        ByteArrayInputStream bin = new ByteArrayInputStream(buff);
        while ((b = bin.read()) != -1) {
            System.out.println(b);
        }
    }
}
