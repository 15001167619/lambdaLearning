package cn.whs.lambda.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 武海升
 * @version 2.0
 * @description
 * @date 2018-04-09 11:29
 */
public class StreamApiStudy {

    public static void main(String[] args) {

       Arrays.asList(0,1,2,4,5,6).stream()
               .filter(x -> x%2 == 0)
               .forEach(System.out::println);
        int sum = Arrays.asList(0, 1, 2, 4, 5, 6).stream()
                .filter(x -> x % 2 == 0).mapToInt(x -> x)
                .sum();
        System.out.println("sum："+sum);

        Integer min = Arrays.asList(0, 1, 2, 4, 5, 6).stream()
                .min(Integer::compare).get();
        Integer max = Arrays.asList(0, 1, 2, 4, 5, 6).stream()
                .max(Integer::compare).get();
        OptionalDouble average = Arrays.asList(0, 1, 2, 4, 5, 6).stream()
                .mapToInt(x -> x)
                .average();
        System.out.println("min："+min);
        System.out.println("max："+max);
        System.out.println("average："+average.getAsDouble());
        // findAny() 从索引0开始找符合要求的数据，若没有 抛出NoSuchElementException异常
        Optional<Integer> any = Arrays.asList(3, 1, 21, 40, 5, 61).stream().filter(x -> x % 2 == 0).findAny();
        Integer integer = null;
        try {
            integer = any.get();
            System.out.println("integer"+integer);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("integer"+integer);
        }

        Optional<Integer> first = Arrays.asList(4, 1, 20, 40, 5, 61).stream()
                .filter(x -> x % 2 == 0)
                .findFirst();
        System.out.println(first.get());

        System.out.println("********* sort  *********");
        Arrays.asList(5,6,2,8,4,7).stream()
                .sorted()//默认从小到大
                .sorted((a, b) -> b - a)//从大到小
                .forEach(System.out::println);

        Arrays.asList("admin","root","password").stream()
                .sorted((a, b) -> b.length() - a.length())
                .forEach(System.out::println);

        System.out.println("********* 收集器  *********");
        System.out.println("********* collect  *********");

        List<Integer> integerList = Stream.iterate(1, a -> a + 1)
                .limit(100)
                .filter(a -> a % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(integerList);

        System.out.println("********* toArray  *********");

        Integer[] integerArray = Stream.iterate(1, a -> a + 1)
                .limit(100)
                .filter(a -> a % 2 == 0)
                .toArray(Integer[]::new);
        for (Integer i : integerArray) {
            System.out.println(i);
        }

        List<Integer> collect = Stream.iterate(1, a -> a + 1)
                .limit(100)                 //构造100个数字流
                .sorted((a, b) -> b - a)    //从大到小排序
                .filter(x -> x % 2 == 0)    //从大到小排序后取偶数
                .skip(10)                   //skip(n) 跳过第 n 个
                .limit(10)                  //从到到小排序后取偶数，最后取前10
                .collect(Collectors.toList());
        System.out.println(collect);

        String str = "1,2,3";
        int sum1 = Stream.of(str.split(",")).mapToInt(Integer::valueOf).sum();
        System.out.println("sum:"+sum1);

        String personStr = "admin,root,manager";
        Stream.of(personStr.split(","))
                .map(Person::new)
                .forEach(System.out::println);
        //设置线程数
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","2");
        Integer max_ = Arrays.asList(0, 1, 2, 4, 5, 6).stream()
                .peek(x -> {
                    System.out.println(Thread.currentThread().getName());
                })
                .parallel()//并行流
                //.sequential()//顺序流
                .max(Integer::compare).get();
        System.out.println("max_"+max_);


    }



}

class Person{

    private String name;

    public Person(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}