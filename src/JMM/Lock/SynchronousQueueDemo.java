package JMM.Lock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        //只能是生产一个消费一个
        BlockingQueue<String> blockingQueue = new SynchronousQueue();
        new Thread(()->{

            try {
                System.out.println(Thread.currentThread().getName()+"put a");
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName()+"put b");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName()+"put c");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        new Thread(()->{
            try {
                Thread.sleep(500);
                System.out.println("取出"+blockingQueue.take());
                Thread.sleep(500);
                System.out.println("取出"+blockingQueue.take());
                Thread.sleep(500);
                System.out.println("取出"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
