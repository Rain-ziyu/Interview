package JMM.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//        1、实现方式：
//
//        synchronized是关键字属天JVM层面，synchronized的语义底层是通过一个monitor的对象来完成，其实wait()、notify()等方法也依赖于monitor对象，这就是为什么只有在同步的块或者方法中才能调用wait()、notify()等方法，否则会抛出java.lang.IllegalMonitorStateException的异常的原因
//        Lock是具体类（java.util.concurrent.Locks.Lock）是api层面的锁
//        2、使用方法
//
//        synchronized 不需要用户去手动释放锁，当synchronized代码执行完后系统会自动让线程释放对锁的占用
//        ReentrantLock则需要用户去手动释放锁着没有主动放锁，就有可能导致出现死锁现象。需要lock()和unlock()方法配合try{ }finally{ }语句块来完成。
//        3、等待是否可中断
//
//        synchronized不可中断，除非抛出异常或者正常运行完成
//        ReentrantLock 可中断
//        设置超时方法 trylock(long timeout，TimeUnit unit)
//        或者
//        LockInterruptibly()放代码块中，调用interrupt()方法可中断
//        4、加锁是否公平
//
//        synchronized非公平锁
//        ReentrantLock两者都可以，默认公平锁，构造方法可以传入boolean值，true为公平锁，false为非公平锁
//        5、锁绑定多个条件condition
//
//        synchronized没有
//        ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程。

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
