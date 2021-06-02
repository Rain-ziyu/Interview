package UnSafeList;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/*
* 1.故障现象  java.util.ConcurrentModificationException
* 2.导致原因
* 3.解决方案
*   1. new Vector<>();
*   2.Collections.synchronizedList(new ArrayList<>());
*   3.new CopyOnWriteArrayList<>();
* 4.优化建议（同样错误不犯第二次）*/
public class UnSafeArrayList {
    public static void main(String[] args) {
//        noSafeArrayList();
//        SetNoSafe();
//        NoSafeMap();
    }

    private static void NoSafeMap() {
        Collections.synchronizedMap(new HashMap<>());
        new ConcurrentHashMap<>();
        Map<String,String> map = new HashMap<>();

        for (int i =0;i<=30;i++){
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,8),Thread.currentThread().getName());
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void SetNoSafe() {
        //        Collections.synchronizedSet(new HashSet() );
        Set<String> set =  Collections.synchronizedSet(new HashSet() );//new HashSet();
        for (int i =0;i<=30;i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void noSafeArrayList() {
        //尽管Vector可以解决该问题但高并发性能会下降
        new CopyOnWriteArrayList<>();
        Collections.synchronizedList(new ArrayList<>());
        List<String> list = new Vector<>();//new ArrayList<>();
        list.forEach(System.out::println);
//     并发修改异常   java.util.ConcurrentModificationException
        for (int i = 1;i<=400;i++ ){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
