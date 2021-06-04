package JMM.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Data{
    private int nummber = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void increment(){
        lock.lock();
        try {
            //多线程的判断必须用loop即循环
            while (nummber!=0){
                //等待不生产
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            nummber++;
            System.out.println(Thread.currentThread().getName()+"\t"+nummber);
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }
    public void decrement(){
        lock.lock();
        try {
            while (nummber==0){
                //等待不生产
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            nummber--;
            System.out.println(Thread.currentThread().getName()+"\t"+nummber);
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }

}

public class ProductConsumer_TraditionDemo {
    public static void main(String[] args) {
Data data = new Data();
new Thread(()->{
    for (int i =1 ;i<=5;i++){
      data.increment();
  }
},"AAA").start();
        new Thread(()->{
            for (int i =1 ;i<=5;i++){
                data.decrement();
            }
        },"BBB").start();
        new Thread(()->{
            for (int i =1 ;i<=5;i++){
                data.increment();
            }
        },"CCC").start();
        new Thread(()->{
            for (int i =1 ;i<=5;i++){
                data.decrement();
            }
        },"DDD").start();
    }
}
