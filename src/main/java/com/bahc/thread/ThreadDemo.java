package com.bahc.thread;

class Thread1 extends Thread {
    private String name;
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Thread1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程运行开始！");
        int j = 0;
        for (int i = 0; i < 5; i++) {
            j++;
            System.out.println("子线程" + name + "运行：" + i);
        }
        System.out.println(Thread.currentThread().getName() + "线程运行结束！");
        this.num = j;
    }

}

public class ThreadDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "主线程运行开始!");
        Thread1 mTh1 = new Thread1("A");
        Thread1 mTh2 = new Thread1("B");
        mTh1.start();
        mTh2.start();

        try {
            mTh1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            mTh2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(mTh1.getNum()+"===="+mTh2.getNum());
        int i = mTh1.getNum() + mTh2.getNum();
        System.out.println("++++:"+i);

        System.out.println(Thread.currentThread().getName() + "主线程运行结束!");
    }
}
