package org.example;

public class ByImplementingRunnable implements Runnable{
    @Override
    public void run() {
        for(int i = 0 ; i < 20 ; i++){
            System.out.println("Multi");
        }
    }

    public static void main(String[] args) {
        ByImplementingRunnable thread = new ByImplementingRunnable();
        Thread t = new Thread(thread);
        t.start();
        for(int i = 0 ; i < 20 ; i++){
            System.out.println("Threading");
        }
    }
}
