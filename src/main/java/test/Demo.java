package test;

import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import java.io.*;

public class Demo {
    public static void main(String[] args) {
        demo7();
    }


    public static void demo7(){
        int[] arr = new int[5];
        System.out.println(arr.length);
    }

    public static void demo6(){
        String filePath = "D:\\data\\linern.txt";
        String str= null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            int i = 0;
            while((str=br.readLine())!=null) {
                i++;
                if(str.contains("\r\n")){
                    System.out.print(str);
                }

                System.out.print(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void demo5() {

        try {
            File file = new File("D:\\data\\Hello1.txt");
            FileWriter fw = new FileWriter(file);
            String n = "abc\rd";
            fw.write("This"+n+"!is!an!example\r\n");
            fw.write("This"+n+"!is!an!example");
            fw.flush();
            fw.close();

            FileReader fr = new FileReader(file);
            char [] a = new char[1024];
            fr.read(a); // 读取数组中的内容
            for(char c : a) {
                System.out.println(c);
            }
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void demo4() {
        byte[] buffer = new byte[1024];
        String content = null;
        int size = 0;
        try {
            FileInputStream fis = new FileInputStream(new File("D:\\data\\testfile.txt"));
            while ((size = fis.read(buffer)) != -1) {
                System.out.println("size:" + size);
                content = new String(buffer, 0, size);
                if (content.contains("\r")) {
                    content = content.replace("\r", "^");
                }
                System.out.println(content);
            }
            System.out.println("size:" + size);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void demo3() {
        String a = "aaaaa\n!";
        String b = "bbbbb!";
        String c = "ccccc";
        String line = a + b + c;
        System.out.println(a + b + c);
        String[] split = line.split("!", 5);
        System.out.println("length:" + split.length);
        String newline = "";
        for (String s : split) {
            System.out.println(s);
            if (s.contains("\n")) {
                s = s.replace("\n", "^");
                System.out.println("=======");
            }
            newline += s;

        }
        System.out.println("newline:" + newline);
    }

    public static void demo2() {
        String a = "中国";
        String s = demo1(a);
        byte[] bytes = a.getBytes();
        System.out.println(s);

        System.out.println(a.getBytes().length);
        for (byte b : a.getBytes()) {
            System.out.println(b);
        }
    }

    public static String demo1(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }
}
