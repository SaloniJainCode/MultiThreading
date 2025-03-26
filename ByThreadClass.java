package org.example;

public class ByThreadClass extends Thread{
    @Override
    public void run(){
        for(int i = 0 ; i < 20 ; i++) {
            System.out.println("Multi");
        }
    }
    public static void main(String[] args) {
        ByThreadClass thread = new ByThreadClass();
        thread.start();
        for(int i = 0 ; i < 20 ; i++) {
            System.out.println("Threading");
        }
    }
}
