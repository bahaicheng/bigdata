package test;

import java.util.stream.Collector;

public class Demo {
    public static void main(String[] args) {
        String a = "中国";
        String s = demo1(a);
        byte[] bytes = a.getBytes();
        System.out.println(s);

        System.out.println(a.getBytes().length);
        for (byte b : a.getBytes()) {
            System.out.println(b);
        }
    }

    public static String demo1(String value){
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]).append(",");
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();
    }
}
