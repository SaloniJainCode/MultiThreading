package org.example;

import java.util.function.Consumer;

class SharedData{
    int data;
    boolean isProduce = false;

    public synchronized void produce(int value) throws InterruptedException {
        if(isProduce){
            wait();
        }else{
            data = value;
            System.out.println("Produces :" +value);
            isProduce = true;
            notify();
        }
    }

    public synchronized void consumer() throws InterruptedException {
        if(!isProduce){
            wait();
        }else{
            System.out.println("Consumes:" + data);
            isProduce = false;
            notify();
        }
    }
}

class Producer implements Runnable{
    SharedData data;

    public Producer(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        for (int i=1 ; i<10 ;i++){
            try {
                data.produce(i);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consume implements Runnable{
    SharedData data;

    public Consume(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 1 ; i < 10 ; i++){
            try {
                data.consumer();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class ProducerConsumer {
    public static void main(String[] args) {
        SharedData data = new SharedData();
        Thread pro = new Thread(new Producer(data));
        Thread con = new Thread(new Consume(data));
        pro.start();
        con.start();
    }
}
