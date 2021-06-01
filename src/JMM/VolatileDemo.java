package JMM;
class Data{
   volatile int number=0;
    public void addTo(){
        number+=60;
    }
        }

public class VolatileDemo {
    public static void main(String[] args) {
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
