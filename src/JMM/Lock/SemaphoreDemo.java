package JMM.Lock;

import java.util.concurrent.Semaphore;
/*
Semaphore会记录已被占用的资源，对可用的资源减一，并且在资源被释放的时候会对值加一
* */
public class SemaphoreDemo
{
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(6);
        for (int i = 1;i<=10;i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢占到了车位");
                    Thread.sleep(50);
                    System.out.println(Thread.currentThread().getName()+"停车了50ms，释放停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放资源
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
