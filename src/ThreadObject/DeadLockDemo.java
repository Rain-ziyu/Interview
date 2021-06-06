package ThreadObject;
class ThreadLock implements Runnable{
    String LockA;
    String LockB;

    public ThreadLock(String lockA, String lockB) {
        LockA = lockA;
        LockB = lockB;
    }
    @Override
    public void run() {
        synchronized (LockA){
            System.out.println(Thread.currentThread().getName()+"持有"+LockA+"尝试获取"+LockB);
            synchronized (LockB){
                System.out.println(Thread.currentThread().getName()+"持有"+LockA+"获取"+LockB);
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        new Thread(new ThreadLock("LockA","LockB"),"AAA").start();
        new Thread(new ThreadLock("LockB","LockA"),"BBB").start();
    }
}
