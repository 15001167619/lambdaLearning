package cn.whs.lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 武海升
 * @version 2.0
 * @description Stream 流创建
 * @date 2018-04-03 9:41
 */
public class StreamApiStudy {

    //Stream 流的创建
    //(一)通过数组创建流
    static void streamCreatOne(){
        String[] arr = {"1","2","3","3","5"};
        Stream<String> arrStream = Stream.of(arr);
        arrStream
                .distinct()//中间操作
                .filter((a) ->!"5".equals(a))//中间操作
                .forEach(System.out :: println);//终止操作
    }
    //(二)通过集合创建流
    static void creatStreamTwo(){
        List<Integer> list = Arrays.asList(1,2,7,9);
        Stream<Integer> stream = list.stream();
        stream.forEach(System.out :: println);//终止操作
    }
    //(三)通过 Stream.generate() 创建流
    static void streamCreatThree(){
        Stream<Integer> stream = Stream.generate(() -> 1);
        stream
                .limit(5)//中间操作
                .forEach(System.out :: println);//终止操作
    }
    //(四)通过集合创建流
    static void streamCreatFour(){
        Stream<Integer> stream = Stream.iterate(0, (x) -> x + 2);
        stream
                .limit(5)//中间操作
                .forEach(System.out :: println);//终止操作
    }
    //(五)通过 其它Api 创建流
    static void streamCreatFive(){
        String  str = "abc";
        IntStream stream = str.chars();
        stream.forEach(System.out :: println);//终止操作
        try {
            Stream<String> streamString = Files.lines(Paths.get("F:\\xf\\vm1\\mobile_project_template_1.1.0.vm"));
            streamString.forEach(System.out :: println);//终止操作
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
//        streamCreatOne();
//        creatStreamTwo();
//        streamCreatThree();
//        streamCreatFour();
        streamCreatFive();
    }

}
