package Practic3;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static Random random = new Random();
    static Set<Integer> set1 = new HashSet<>();
    private static int a = 0;
    private static final Lock lock = new ReentrantLock();
    private static List<Integer> list = Collections.synchronizedList(new ArrayList<>());
    synchronized static void addEl(){
        set1.add(a++);
    }
    static void getElement(int a){
        lock.lock();
        System.out.println("Элемент под номером " + a + ": " + list.get(a));
        lock.unlock();
    }




    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(()->{
            for(int i = 0; i < 10; i++){

                addEl();
            }
        });

        Thread two = new Thread(()->{
            for(int i = 10; i < 20; i++){

                addEl();
            }
        });
        one.start();
        two.start();
        Thread.sleep(3000);
        for (int asd : set1) {
            System.out.println(asd);
        }




        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        one = new Thread(() -> {
            for (int i = 0; i < list.size(); i++) getElement(i);
        });
        two = new Thread(() -> {
            for (int i = 0; i < list.size(); i++) getElement(i);
        });
        System.out.println();
        one.start();
        two.start();
        Thread.sleep(3000);

    }
}
