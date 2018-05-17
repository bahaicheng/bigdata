package test;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
    public static final String format1 = "yyyy-MM-dd HH:mm:ss";
    public static void main(String[] args) {
        Timer1();
    }

    public static void printWord(String format){
        System.out.println("现在时间为："+format);
    }

    public static void  Timer1(){
        Timer time=new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat(format1);
                String format = sdf.format(l);
                System.out.println("================================="+format);
                printWord(format);
            }
        },1000,2000);
    }



}
