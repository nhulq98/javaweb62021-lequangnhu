package com.laptrinhjavaweb.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

// =============not apply design pattern================
interface Duck1{
    void swim();
    void quack();
}

interface FlyAble{
    void swim();
    void fly();
}

// 3 class: VitSim, VitTa, VitCaoSu
class VitSim1 implements Duck1, FlyAble{
    @Override
    public void swim() {}
    @Override
    public void fly() {}
    @Override
    public void quack() {}
}
class VitTa1 implements Duck1, FlyAble{
    @Override
    public void swim() {}
    @Override
    public void fly() {}
    @Override
    public void quack() {}
}
class VitCaoSu1 implements Duck1{
    @Override
    public void swim() {}
    @Override
    public void quack() {}
}

/*
* Nhược điểm của cách này là gì?
* 1. Nếu ta muốn thay đổi hành vi bay thì ta phải đi vào all các lớp implement hành vi bay đó để sửa lại, nó sẽ kéo theo lỗi
* trong quá trình sửa.
* 2. Can't reuse code
* */
//================END====================

// ==========Apply design pattern==============
/*
Tách các phần hay thay đổi(khách hàng hay đổi ý) ra với các phần chạy ổn, ít thay đổi.
Ở bài toán của ta: các phần thay đổi là fly.
Còn phần giữ nguyên là Swim, quack

*/

interface FlyBehavior{
    void fly();
}
class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() { }
}
class FlyNoWay implements FlyBehavior{

    @Override
    public void fly() { }
}



interface QuackBehavior{
    void quack();
}
class Quack implements QuackBehavior{
    @Override
    public void quack() {}
}
class Squeak implements QuackBehavior{
    @Override
    public void quack() {}
}
class MuteQuack implements QuackBehavior{
    @Override
    public void quack() {}
}


// apply
class Duck{
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public void swim(){}
}

class MallardDuck extends Duck{
    public MallardDuck(){
        quackBehavior = new Squeak();
        flyBehavior = new FlyWithWings();
    }
}

//==================================================

public class Utils implements Comparator<Utils> {

    /**
     * make for object đủ đk để Garbage collection bằng cách hủy tham chiếu đến đối tượng
     *
     * @param param
     * @param <T>
     */
    public static <T> void destroyReference(T... param) {
        for (T item : param) {
            item = null;
        }
        //System.gc();// call Garbage collection
    }

    static String[] listSongs = {"Pink Moon", "Somersault",
            "Shiva Moon", "Circles",
            "Deep Channel", "Passenger",
            "Listen", "Listen", "Listen", "Listen", "Listen", "Somersault"};
    static int[] listInts = {66, 9, 22, 35, 8, 66, 233, 432, 33};

    public static void main(String[] args) {

        long then = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (now - then));
        Map<String, String> hashtable = new Hashtable<>();
        int  i = 0;
        while (true){
            Map a = new HashMap(300);
            hashtable.put("nhu", "sadsa"+ i++);

        }


//        System.out.println(map1.size());
        //Map<String, String> map2 = new LinkedHashMap<>();

//        Map<String, String> map4 = new TreeMap<>();
//
//        Set<String> set = new HashSet<>();
//        Set<String> set2 = new TreeSet<>();
//        Set<Person> set3 = new LinkedHashSet<>();
//        //List<String> list = new ArrayList<>();
//        Person a = new Person();
//        set3.add(new Person("le quang B"));
//        set3.add(new Person("le quang A"));
//        Iterator iterator = set2.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().toString());
//        }

//        int n = 1000;
//
//        List<Song> list1 = new ArrayList<>();
//        //Collections.sort(list1);
//        long start1 = System. currentTimeMillis();
//        for(int i = 0; i < n; i++){
//            list1.add(new Song());
//        }
//        long end1 = System. currentTimeMillis();
//        System.out.println("Thêm Xong" + TimeUnit.MILLISECONDS.toSeconds(end1 - start1));
//        long start = System. currentTimeMillis();
//        list1.add(3, "hehe");
//        long end = System. currentTimeMillis();
//        System.out.println("Time: "+ TimeUnit.MILLISECONDS.toSeconds(end - start));
//        Utils a = new Utils();
//        String str = "le quang nhu";
//        a = null;
//        str = null;
//        System.gc();
//        System.out.println(str);
    }


    @Override
    public int compare(Utils o1, Utils o2) {
        return 0;
    }
}
