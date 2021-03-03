package com.project.demo;

import com.project.demo.pojo.Person;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class StreamTest {
    /**
     * 排序
     * list遍历转map
     */
    @Test
    public void demo1() {
        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Person person = new Person();
            person.setName("名字" + i);
            person.setAge("年龄" + i + 5);
            person.setSort(i);
            list.add(person);
        }
        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getSort() - o1.getSort() == 0 ? o1.getName().compareTo(o2.getName()) : o2.getSort() - o1.getSort();
            }
        });
        //list.stream().map(Person::getName).forEach(System.out::println);
        list.stream().collect(Collectors.toMap(Person::getName, Person::getAge)).forEach((x, y) -> System.out.println("x = " + x + "y=" + y));
    }

    /**
     * list遍历
     * 自然排序
     */
    @Test
    public void demo2() {
        ArrayList<Person> list = new ArrayList<>();
        for (int i = 10; i <= 10 && i >= 0; i--) {
            Person person = new Person();
            person.setName("名字" + i);
            person.setAge("年龄" + i + 5);
            person.setSort(i);
            list.add(person);
        }
        list.stream().map(Person::getSort).forEach(System.out::println);
        list.stream().sorted(Comparator.comparing(Person::getSort)).map(Person::getSort).forEach(System.out::println);
    }

    /**
     * list聚合
     */
    @Test
    public void demo3() {
        List<String> one = new ArrayList<>();
        one.add("迪丽热巴");
        one.add("宋远桥");
        one.add("苏星河");
        one.add("老子");
        one.add("庄子");
        one.add("孙子");
        one.add("洪七公");

        List<String> two = new ArrayList<>();
        two.add("古力娜扎");
        two.add("张无忌");
        two.add("张三丰");
        two.add("赵丽颖");
        two.add("张二狗");
        two.add("张天爱");
        two.add("张三");

        Stream stream = one.stream();
        Stream stream1 = two.stream();
        /*List<String> collect = (List<String>) Stream.concat(stream, stream1).collect(Collectors.toList());
        System.out.println(collect);*/
        String collect1 = (String) Stream.concat(stream, stream1).collect(Collectors.joining("--"));
        System.out.println("collect1 = " + collect1);
    }

    /**
     * 并行流无序遍历
     */
    @Test
    public void demo4() {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.add("" + i);
        }
        //parallelStream是并行流，内部以多线程并行执行的方式对流进行操作，需要注意使用并行流的前提是流中的数据处理没有顺序要求（会乱序，即使用了forEachOrdered）
        arrayList.parallelStream().forEach(System.out::println);
    }

    /**
     * 过滤
     */
    @Test
    public void demo5() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.add(i);
        }
        //parallelStream是并行流，内部以多线程并行执行的方式对流进行操作，需要注意使用并行流的前提是流中的数据处理没有顺序要求（会乱序，即使用了forEachOrdered）
        arrayList.stream().filter(s -> s > 1).forEach(System.out::println);
    }

    /**
     * 转换
     */
    @Test
    public void demo6() {
        Stream.of("张三,22").map(s -> {
            String[] split = s.split(",");
            Person person = new Person();
            person.setName(split[0]);
            person.setAge(split[1]);
            return person;
        }).forEach(System.out::println);
    }

    /**
     * 转换聚合
     */
    @Test
    public void demo7() {
        List<String> list = Arrays.asList("k,l,s,x,z","h,n,z,y","1,5,2,4,8");
        List<String> newList = list.stream().flatMap(s -> {
            String[] str = s.split(",");
            Stream<String> stream = Arrays.stream(str);
            return stream;
        }).collect(Collectors.toList());
        System.out.println("处理前的集合"+list);
        System.out.println("处理后的集合"+newList);
    }
}
