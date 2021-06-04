package JMM.Lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ShareResource{
    private volatile boolean FLAG=true;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    BlockingQueue<String> blockingQueue =null;
    public ShareResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(this.blockingQueue.getClass().getName());
    }
    public void myPod(){
        String data=null;
        boolean retValue;
        while (FLAG){
           data = atomicInteger.incrementAndGet()+"";
            try {
              retValue =   blockingQueue.offer(data,2L, TimeUnit.SECONDS);
              if(retValue){
                  System.out.println(Thread.currentThread().getName()+"插入"+data+"成功");
              }else {
                  System.out.println(Thread.currentThread().getName()+"插入"+data+"失败");
              }
              TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("停止所有生产操作，FLAG："+FLAG);
    }
    public void Consumer(){
        String retValue = null;
        while (FLAG)
        {
            try {
             retValue =   blockingQueue.poll(2L,TimeUnit.SECONDS);
             if(retValue==null||retValue.equalsIgnoreCase(""))
             {
                 FLAG=false;
                 System.out.println(Thread.currentThread().getName()+"消费队列超过两秒钟没有取到data");
                 return;
             }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("消费队列消费成功取出的值为"+retValue);
        }

    }
    public void stop(){
        this.FLAG = false;
    }
}
public class ProductConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource(new ArrayBlockingQueue<String>(3));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"生产者启动");
            shareResource.myPod();
        },"Product").start();

//        new Thread(()->{
//            System.out.println(Thread.currentThread().getName()+"消费者启动");
//            shareResource.Consumer();
//        },"Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程叫停");
        shareResource.stop();
    }
}
