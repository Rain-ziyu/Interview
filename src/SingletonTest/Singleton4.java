package SingletonTest;

public class Singleton4 {
    private static  Singleton4 singleton4;

    private Singleton4() { }

    public static Singleton4 getSingleton4(){
        synchronized (singleton4){
        if(singleton4==null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            singleton4 = new Singleton4();

        }}
        return singleton4;
    }
}
