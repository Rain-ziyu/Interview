package JMM.Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
* 多个线程同时读取同一资源没有任何问题，所以为了满足并发量，读取共享资源应该同时进行
* 但是如果有一个线程进行写操作，不应该再有其他线程对该资源进行读和写
* 结果就是
*   读读可以共存
* 读写不可以共存
* 写写不可共存
* 写操作：写操作应该是原子性+独占性的，总线程应该是不可分割的*/
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
//    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void put(String key,Object value){
//        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入："+key);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入成功");
        }finally {
//            lock.writeLock().unlock();
        }

    }
    public void get(String key){
//        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取："+key);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取成功"+o);
        }finally {
//            lock.readLock().unlock();
        }

    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i=0;i<50;i++){
            int putint = i;
        new Thread(()->{
            myCache.put(putint+"",putint+"");
        },String.valueOf(i)).start();}
        for (int i=0;i<50;i++){
            int putint = i;
            new Thread(()->{
                myCache.get(putint+"");
            },String.valueOf(i)).start();}

    }
}
