package SingletonTest;
/*懒加载
* 在静态内部类被加载和初始化时，才创建singleton5实例对象
* 静态内部类不会自动随外部类初始化而初始化，他是需要单独加载和初始化的
* */
public class Singleton5 {
    private Singleton5  (){}
    private static class Inner{
        private static final Singleton5 singleton5 = new Singleton5();
    }
    public static Singleton5 getSingleton5(){
        return Inner.singleton5;
    }
}
