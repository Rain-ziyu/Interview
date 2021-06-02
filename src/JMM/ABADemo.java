package JMM;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
   static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
   static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {
        System.out.println("ABA问题的产生===================");
        new Thread(()->{
           atomicReference.compareAndSet(100,101);
           atomicReference.compareAndSet(101,100);
        },"t1").start();
     new Thread(()->{
         try {
             Thread.sleep(1);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         atomicReference.compareAndSet(100,9);
         System.out.println(atomicReference.get());
     },"t2").start();
        try {
            Thread.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ABA问题的解决===================");
        new Thread(()->{
           int tamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"第一次版本号"+tamp);
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"第二次版本号"+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"第三次版本号"+atomicStampedReference.getStamp());
        },"t3").start();
        new Thread(()->{
            int tamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"第一次版本号"+tamp);
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicStampedReference.compareAndSet(100, 103, tamp, tamp + 1);
            System.out.println(Thread.currentThread().getName()+"是否修改成功:"+b+"当前版本号为"+atomicStampedReference.getStamp());
            System.out.println("最终的值为:"+atomicStampedReference.getReference());
        },"t4").start();
    }
}
