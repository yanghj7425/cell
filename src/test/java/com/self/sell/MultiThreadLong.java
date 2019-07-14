package com.self.sell;

/**
 * 多线程 long 型读写测试
 */
public class MultiThreadLong {
    public static Long aLong = 0L;

    public static class ChangeT implements Runnable {

        private long to;


        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true) {
                MultiThreadLong.aLong = to;
                Thread.yield();

            }
        }
    }

    public static class ReadT implements Runnable {


        @Override
        public void run() {
            while (true) {
                long tmp = MultiThreadLong.aLong;
                if (tmp != 111L && tmp != 112L && tmp != 113L && tmp != 114L) {
                    System.out.println(tmp);
                    System.exit(0);
                }
                Thread.yield();
            }
        }
    }


    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(112L)).start();
        new Thread(new ChangeT(113L)).start();
        new Thread(new ChangeT(114L)).start();

        new Thread(new ReadT()).start();


    }


}
