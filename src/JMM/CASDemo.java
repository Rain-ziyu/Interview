package JMM;

import java.util.concurrent.atomic.AtomicInteger;

/*
* CAS:比较并交换*/
public class CASDemo {
    public static void main(String[] args) {
//        seevolatileDemo();
//        Atomicvolatile();
        AtomicInteger integer = new AtomicInteger(5);
        integer.compareAndSet(5,10);
        integer.compareAndSet(5,11);
        System.out.println(integer);
    }
    private static void Atomicvolatile() {
        Data data = new Data();
        for(int i = 0;i<100;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"come in .....");
                for (int index=0;index<2000;index++){
                    data.addplusplus();
                    data.addplusplus1();}
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"最终的结果为"+"number="+data.number+" number1="+data.number1);
    }
}
