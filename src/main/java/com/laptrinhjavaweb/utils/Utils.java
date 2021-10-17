package com.laptrinhjavaweb.utils;

import org.apache.commons.lang.StringUtils;

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

public class Utils{

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

    /**
     * Generic method to create condition clause with values have typed is String become like this.
     * Example: " AND name LIKE '%value%' "
     *
     * @param fieldName
     * @param value
     * @return
     */
    public static StringBuilder createConditionForStringByLike(String fieldName, String value) {
        if (StringUtils.isNotEmpty(value)) {
            return new StringBuilder(" AND ")
                    .append(fieldName)
                    .append(" LIKE '%")
                    .append(value)
                    .append("%' ");
        }
        return new StringBuilder();
    }

    /**
     * Generic method to create a condition with values have typed is Integer become like this.
     * Example: " AND age = 23 "
     *
     * @param fieldName
     * @param value
     * @return
     */
    public static StringBuilder createConditionForNumber(String fieldName, Number value) {
        if (value != null) {
            return new StringBuilder(" AND ")
                    .append(fieldName)
                    .append("=")
                    .append(value).append(" ");
        }
        return new StringBuilder();
    }

//    public static void main(String[] args) {
//
//        long then = System.currentTimeMillis();
//        long now = System.currentTimeMillis();
//        System.out.println("Elapsed time: " + (now - then));
//        Map<String, String> hashtable = new Hashtable<>();
//        int  i = 0;
//        while (true){
//            Map a = new HashMap(300);
//            hashtable.put("nhu", "sadsa"+ i++);
//
//        }
//    }
}
