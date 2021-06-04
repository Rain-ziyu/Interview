package JMM.Lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });
        for (int i = 1 ;i<=7;i++){
            new Thread(()->{
                System.out.println("第"+Thread.currentThread().getName()+"颗龙珠被找到");
                try {
                    //让先完成的在那里等待其他线程完成后再执行后续任务
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("被使用了");
            },String.valueOf(i)).start();
        }
    }
}
