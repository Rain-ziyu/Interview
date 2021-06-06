package ThreadObject;

import java.util.concurrent.*;

//线程池的底层就是ThreadPoolExecutor
public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        ThreadPooltest();
//        ThreadPoolBlockingDeque();
        ExecutorService ThreadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            for (int i = 1;i<=10;i++){
                ThreadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
//                try {
//                    TimeUnit.MILLISECONDS.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }finally {
            ThreadPool.shutdown();
        }
    }


    private static void ThreadPoolBlockingDeque() {
        ExecutorService executorService1 = new ThreadPoolExecutor(2,
                5,
                100L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 0; i < 8; i++) {
                  final  int tmp = i;
                  executorService1.execute(()->{
                      System.out.println(Thread.currentThread().getName()+"\t"+tmp);

                          try {
                              TimeUnit.SECONDS.sleep(4);
                          } catch (InterruptedException e) {
                              e.printStackTrace();
                          }
                  });
            }
        }finally {
            executorService1.shutdown();
        }
    }

    private static void ThreadPooltest() {
        //        System.out.println(Runtime.getRuntime().availableProcessors());
//        ExecutorService executorService = Executors.newFixedThreadPool(5);//一池五个线程
//        ExecutorService executorService = Executors.newSingleThreadExecutor();//一池一个线程
        ExecutorService executorService = Executors.newCachedThreadPool();//一个池子处理n个线程
//       模拟十个用户来办理业务，每一个请求就是一个来自外部的请求线程
        try {
            for (int i = 1;i<=10;i++){
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
//                try {
//                    TimeUnit.MILLISECONDS.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }finally {
                executorService.shutdown();
        }
    }
}
