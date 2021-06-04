package JMM.Lock;

import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource{
private int number = 1;
private Lock lock = new ReentrantLock();
Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();
public void printf5(){
    lock.lock();
    try {
        while (number!=1){
            try {
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 1 ;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+"\t"+i);
        }
        number=2;
        c2.signal();
    }finally {
        lock.unlock();
    }
}
    public void printf10(){
        lock.lock();
        try {
            while (number!=2){
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1 ;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }
    public void printf15(){
        lock.lock();
        try {
            while (number!=3){
                try {
                    c3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1 ;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=1;
            c1.signal();
        }finally {
            lock.unlock();
        }
    }
}
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(()->{
            for (int i = 1;i<=10;i++)
                resource.printf5();
        },"AAA").start();
        new Thread(()->{
            for (int i = 1;i<=10;i++)
                resource.printf10();
        },"BBB").start();
        new Thread(()->{
            for (int i = 1;i<=10;i++)
                resource.printf15();
        },"CCC").start();
    }
}
