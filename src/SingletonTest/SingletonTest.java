package SingletonTest;

import java.util.concurrent.*;

public class SingletonTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Singleton singleton = Singleton.singleton;
        Singleton singleton_ = Singleton.singleton;
        System.out.println(singleton==singleton_);
        System.out.println("-----------------------------------------------");
        Singleton1 singleton1 = Singleton1.INSTANCE;
        System.out.println(singleton1);
        System.out.println("-----------------------------------------------");
        Singleton2 singleton2 = Singleton2.singleton;
        System.out.println(singleton2);
        System.out.println("-----------------------------------------------");
//        Singleton3 singleton3_1_ = Singleton3.getSingleton3();
//        Singleton3 singleton3_ = Singleton3.getSingleton3();
//        System.out.println(singleton3_1_==singleton3_);
        System.out.println("----------------多线程方法----------------------");
        Callable<Singleton3> c = new Callable<Singleton3>() {
            @Override
            public Singleton3 call() throws Exception {
                return Singleton3.getSingleton3();
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Singleton3> submit1 = executorService.submit(c);
        Future<Singleton3> submit2 = executorService.submit(c);
        Singleton3 singleton3_1 = submit1.get();
        Singleton3 singleton3_2 = submit2.get();
        System.out.println(singleton3_1==singleton3_2);
        executorService.shutdown();
        System.out.println("-----------------------------------------------");
        Singleton5 singleton5 = Singleton5.getSingleton5();
        Singleton5 singleton5_ = Singleton5.getSingleton5();
        System.out.println(singleton5==singleton5_);
    }
}
