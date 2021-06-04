package JMM.Lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1;i<=6;i++){
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"：被灭国");
            countDownLatch.countDown();
            System.out.println("asfasf");
        },CountryEnum.Foreach(i).getCountry()).start();}
        try {
            //让主线程等待上面的线程完成任务才开始执行,即阻塞主线程
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("秦国一统天下");
    }
}
