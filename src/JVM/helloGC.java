package JVM;

public class helloGC {
    public static void main(String[] args) {
        System.out.println("Hello GC");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
