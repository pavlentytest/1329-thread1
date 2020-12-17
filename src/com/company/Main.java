package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // [+][-][+][-][+][-]

       // new TestThread("+").start();
       // new TestThread("-").start();
        TestThread thread1 = new TestThread("+");
        TestThread thread2 = new TestThread("-");
        thread1.start();
        thread2.start();
        Thread.sleep(500);
        thread1.flag = false; // команда на остановку
        thread1.join(); // ожидаем заверешения
        test("stopped!");
        //System.out.print("stopped!");
       // thread1.stop(); - не пользуемся, не заврешает работу корректно
    }
    public static Object key = new Object();

    public static void test(String message) {
        try {
            synchronized(key) {
                System.out.print("[");
                Thread.sleep(200);
                System.out.print(message);
                Thread.sleep(200);
                System.out.print("]");
                //key.notify(); // возобновляют поток у которого был wait
               // key.notifyAll(); // возобновляет все потоки в режиме wait
               // key.wait(); // вход в режим спячки - оба ждут возобновления!!!
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class TestThread extends Thread {
    private String mess;
    public boolean flag = true;
    public TestThread(String mess) {
        this.mess = mess;
    }
    @Override
    public void run() {
        while(flag) {
            Main.test(this.mess);
        }
    }
}

