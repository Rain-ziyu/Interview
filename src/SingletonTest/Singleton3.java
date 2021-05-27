package SingletonTest;
/*
*懒汉式：
* 多线程模式是不安全的
*   延迟创建这个实例对象
* （1）构造器私有化
* （2）用一个静态变量保存这个唯一的实例
*  (3)提供一个静态方法来获取这个实例对象
*/
public class Singleton3 {
   private static  Singleton3 singleton3;

   private Singleton3() { }

    public static Singleton3 getSingleton3(){
       if(singleton3==null){
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           singleton3 = new Singleton3();
       }
       return singleton3;
    }

}
