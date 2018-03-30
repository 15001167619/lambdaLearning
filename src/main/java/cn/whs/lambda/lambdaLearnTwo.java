package cn.whs.lambda;

import java.util.function.Function;

/**
 * @author 武海升
 * @version 2.0
 * @description  函数式接口 方法的引用
 * @date 2018-03-30 16:18
 */
public class lambdaLearnTwo {

    //静态方法
    static Integer factorial(Integer parameter){
        if (parameter <= 1){
            return parameter;
        }else {
            return parameter * factorial(parameter-1);
        }
    }


    public static void main(String[] args) {
        /*
         *  静态方法引用
         *  语法：类名 :: staticMethodName
         *        lambda 表达式 (args) -> 类名.staticMethodName(args)
         */
        System.out.println("############# 静态方法引用 ################");
        Function<Integer,Integer> function = (a) -> lambdaLearnTwo.factorial(a);
        Function<Integer,Integer> function2 = lambdaLearnTwo :: factorial;
        System.out.println(function.apply(3));
        System.out.println(function2.apply(4));

    }




    /*
     *  实例方法引用
     *  语法：inst :: instMethodName
     *        lambda 表达式 (args) -> 类名.instMethodName(args)
     */
    /*
     *  对象方法引用
     *  语法：类名 :: instMethodName
     *        lambda 表达式 (args) -> 类名.instMethodName(args)
     */
    /*
     *  构造方法引用
     *  语法：类名 :: new
     *        lambda 表达式 (args) -> new 类名()
     */



}
