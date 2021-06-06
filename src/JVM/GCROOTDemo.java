package JVM;
/*
JVM的主要垃圾处理是发生在堆和方法区
在Java中可以作为GCROOTS的对象有：
1.虚拟机栈（栈帧中的本地变量表）中引用的对象
2.方法区中的类静态属性所引用的对象
3.方法区中常量所引用的对象
4.本地方法栈中JNI（即一般所说的Native方法）中引用的对象
*/

public class GCROOTDemo {
    private byte[] array=new byte[10];
//    public static int a;方法区中的类静态属性所引用的对象
//    public static final int c = 0;方法区中常量所引用的对象
    public static void m1(){
        GCROOTDemo demo = new GCROOTDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
