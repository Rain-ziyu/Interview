package JMM;

import java.util.concurrent.atomic.AtomicReference;

class User{
    private String name;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    int age;
}
/*原子引用*/
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User user = new User("zhangsan",19);
        User user1 = new User("lisi",20);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user);
        atomicReference.compareAndSet(user,user1);
        System.out.println(atomicReference.get());
    }
}
