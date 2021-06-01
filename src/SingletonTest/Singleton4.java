package SingletonTest;

public class Singleton4 {
    private static volatile Singleton4 singleton4;

    private Singleton4() { }

    public static Singleton4 getSingleton4(){
        //双端检锁,并不一定安全
        //synchronized并不能防止指令重排，可能存在某一线程取到地址不为空的singleton4，即return singleton4的singleton4的实际内容为空
        //为了解决双端检锁可以使用volatile来进行防止指令重排
        //双检锁的优势在于如果提前知道singleton4对象不为空，可以跳过获取锁的等待阶段直接返回所需对象
        if(singleton4==null){
        synchronized (singleton4){
        if(singleton4==null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            singleton4 = new Singleton4();

        }}}
        return singleton4;
    }
}
