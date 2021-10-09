package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.AbstractDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;

import java.text.SimpleDateFormat;
import java.util.*;

interface Song{
    String name = "20";
    String time = "90";
}

class Student extends Person{
    public Student(String name){

        super((name));
    }
}
class Person {
    String name;

    public Person(){}

    public Person(String name){
        this.name = name;
    }

    public <T extends Person> void getPerson1(ArrayList<T> para){
        for(Person a: para) {
            a.name = "232";
        }
    }

    public void getPerson2(ArrayList<? extends  Person> para){ // the same
        for(Person a: para) {
            a.name = "232";
        }
    }

//    @Override
//    public int compareTo(Person o) {
//        return this.name.compareTo(o.name);
//    }

    @Override
    public String toString() {
        return this.name;
    }
}

class test{
    private static final Father INSTANCE = new Father();

    public static Father getInstance(){
        return INSTANCE;
    }
}


class Father implements Song{
    protected String name;
    private static final Father INSTANCE = new Father();

    public Father(){}

    public static Father getInstance(){
        return INSTANCE;
    }

    public Father(String name) {
        name.equals("le");
        this.name = name;
        System.out.println("this is father");
    }

    public void testGC(){
        AbstractDTO a = new AbstractDTO();
    }
//    @Override
//    public int hashCode(){
//        //return 5;
//        return this.hashCode(); // tìm duplicate thông qua hashcode của 1 trường trong object
//    }
//
//    @Override
//    public boolean equals(Object newObject) {
//        Father a = (Father)newObject;
//        return a.name.equals(a.name);
//    }

}

public class Utils extends Father implements Comparator<Utils>{

    /**
     * make for object đủ đk để Garbage collection bằng cách hủy tham chiếu đến đối tượng
     * @param param
     * @param <T>
     */
    public static <T> void destroyReference(T ...param){
        for(T item: param){
            item =  null;
        }
        //System.gc();// call Garbage collection
    }

    public static <T extends Song> void hehe(T a){};
    static String[] listSongs = {"Pink Moon", "Somersault",
    "Shiva Moon", "Circles",
    "Deep Channel", "Passenger",
    "Listen","Listen","Listen","Listen","Listen","Somersault"};
    static int[] listInts = {66,9,22,35,8,66, 233, 432, 33};

    public static void main(String[] args) {
Father father = new Father();
father.testGC();
        //String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Date timeStamp = new Date();
        Map<String, Integer> map1 = new HashMap<>(15);
        map1.equals(null);
        Map<String, String> hashtable = new Hashtable<>();
        while (true){
            map1.put("nhu", 23);

        }



//        System.out.println(map1.size());
//        Map<String, String> map2 = new LinkedHashMap<>();

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





//list.contains();
        //list.contains(new String());
//        for(String item: listSongs){
//            list.add(item);
//        }
//        Collections.sort(list);
//        set.addAll(list);
//        System.out.println(set);
//        Set<Father> set1 = new TreeSet<>();
//        Set<Father> set3 = new HashSet<>();
//        set3.add(new Father());
//        System.out.println(set3.add(new Father("le quang nhu")));
//        set3.contains(new Father());
//        System.out.println(set3.add(new Father("le quang nhu")));
//        String str1 = new String("le quang nhu");
//        String str2 = new String("le quang nhu 2");
//        set3.add(str1);
//        set3.add(str2);
//        System.out.println(str1.hashCode());
//        System.out.println(str2.hashCode());

//        set2.addAll(list);
//        System.out.println(set2);
//        for (String item: set2){
//            System.out.println(item.hashCode());
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
