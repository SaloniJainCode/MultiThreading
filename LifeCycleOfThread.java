package org.example;

public class LifeCycleOfThread extends Thread {
    @Override
    public void run(){
        System.out.println("RUNNING");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LifeCycleOfThread thread = new LifeCycleOfThread();
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(1000);
        System.out.println(thread.getState());
        thread.join();
        System.out.println(thread.getState());
    }
}
