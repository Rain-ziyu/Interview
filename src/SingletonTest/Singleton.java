package SingletonTest;
/*
* 饿汉式：有可能不需要该对象无需创建
* （1） 构造器私有化
* （2） 自行创建，并且用静态变量保存
* （3） 向外提供这个实例
* （4） 强调这是一个单例对象，使用final修饰
* */
public class Singleton {
    public static final Singleton singleton = new Singleton();
    private Singleton (){ }
}
