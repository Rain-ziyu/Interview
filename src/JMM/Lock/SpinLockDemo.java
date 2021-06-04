package JMM.Lock;

import java.util.concurrent.atomic.AtomicReference;
//自旋锁
public class SpinLockDemo {
    //原子引用线程
    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();
    public void myLock(){
    Thread thread = Thread.currentThread();

        while (!threadAtomicReference.compareAndSet(null,thread)){
        }
        System.out.println(Thread.currentThread().getName()+"\t come in");
    }
    public void unlock(){
        Thread thread = Thread.currentThread();
       threadAtomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t come out");
    }
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
          new Thread(()->{
              spinLockDemo.myLock();
                  try {
                      Thread.sleep(500);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                   finally {
                  spinLockDemo.unlock();
                  }
          },"AAA").start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                spinLockDemo.unlock();
            }
        },"BBB").start();


    }
}
