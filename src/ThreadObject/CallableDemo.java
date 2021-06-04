package ThreadObject;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}
class MyThread1 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"运行成功");
        TimeUnit.SECONDS.sleep(5);
        return 1024;
    }
}
public class CallableDemo {
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread1());
//        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread1());
//        当共用同一个FutureTask时对于重复的任务不会重复计算，而复用上一个计算结果
        Thread thread = new Thread(futureTask,"AAA");
        Thread thread1 = new Thread(futureTask,"BBB");
        thread.start();
        thread1.start();
//        while (!futureTask.isDone())
//        {
//
//        }
        try {
//            一般情况下对线程的计算结果放在最后获取即（futureTask.get()），否则会阻塞主线程的计算
            System.out.println("Result:"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
