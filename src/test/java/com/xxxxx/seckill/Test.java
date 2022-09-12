package com.xxxxx.seckill;

/**
 * @author : jiabin
 * @date :2022/9/6 20:18
 */



import java.util.*;

public class Test {
    public static void main(String[] args) {
        Map<Integer, List<People>> map = new HashMap<>();
        People people1 = new People("小明",12);
        People people2 = new People("小明",13);
        List<People> list1 = new ArrayList<>();
        list1.add(people1);
        list1.add(people2);
        map.put(1,list1);

        //第1题：遍历map集合，找到其value的集合中，people的age等于12，并将其从list中移除；
        int i = 0;
        Set<Map.Entry<Integer, List<People>>> mp2 = map.entrySet();
        for (Map.Entry<Integer, List<People>> x:mp2){
            List<People> list = x.getValue();
            for (People p:list) {
                if (p.getAge().equals(12)){
                    list.remove(i);
                }
                i++;
            }
            System.out.println(list.size());
        }

        //----------------------------------分隔线--------------------------------------
        // 第2题：有多个People，根据name和age放到某种java集合快速去重

      /*  People people1 = new People("小明",12);
        People people2 = new People("小明",12);

        List<People> list = new ArrayList<>();
        list.add(people1);
        list.add(people2);

        Set<Integer> set = new HashSet<>();
        for (People pe:list) {
            set.add(pe.getAge());
        }

        for (Integer i:set) {
            System.out.println(i);
        }*/


    }
}

class People {

    public People (String name,Integer age){
        this.name = name;
        this.age = age;
    }
    public People (){
    }

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}