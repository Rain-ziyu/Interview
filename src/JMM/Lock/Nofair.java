package JMM.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
* 可重入锁：
* 指的是外层函数获取该锁之后，内层函数仍然能够获取该锁的代码
* 同一线程中外层代码获取锁之后内层代码会自动获取锁
* 也就是说线程可以进入任意一个已获取的锁的同步着的代码块
* synchronized与ReentrantLock都是独占锁*/
class Phone{
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getId()+":sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getId()+":sendEmail");
    }

//    ===================================================================
    Lock lock = new ReentrantLock();
    public void  get(){
//        无论写多少个lock都可以，但必须有其对应的unlock，如果有缺少编译器也不会抛出异常，只是该线程一直无法退出
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+":sendSMS");
            set();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public void  set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+":sendEmail");
        }finally {
            lock.unlock();
        }
    }
}
public class Nofair {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Lock版本可重入锁======================================");
        new Thread(()->{
            try {
                phone.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t3").start();
        new Thread(()->{
            try {
                phone.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t4").start();
    }
}
