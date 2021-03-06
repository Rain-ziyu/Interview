package JMM;

import java.util.concurrent.atomic.AtomicInteger;

class Data{
   volatile int number=0;
   //原子类：底层依靠CAS进行实现
   AtomicInteger number1 = new AtomicInteger(0);
    public void addTo(){
        number+=60;
    }
    public  void addplusplus(){
        number++;
    }
 public void addplusplus1(){
        number1.getAndIncrement();
 }
        }
/*
* 验证volatile的可见性seevolatileDemo
* 验证volatile不保证原子性
*      ：即该事件是不可分割的完整性的，中间不可以被加塞，保证动作的完整性
* 验证volatile禁止指令重排
* */
public class VolatileDemo {
    public static void main(String[] args) {
//        seevolatileDemo();
//        Atomicvolatile();

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

    private static void seevolatileDemo() {
        Data data = new Data();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"come in.......");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addTo();
            System.out.println("data已经执行addto方法");
        },"AAAAA").start();
        while(data.number==0){}
        System.out.println(data.number);
    }

}
