package cn.whs.lambda.lambda;

import java.util.function.Function;

/**
 * @author 武海升
 * @date 2018/12/12 17:28
 */
public class UnaryOperatorTest {

    public static void main(String[] args) {
        Function<Integer,User> function = (age) -> new User(age);
        System.out.println(function.apply(100));

        Function<Integer,Integer> function1 = (parameter) -> {
            int sum = 0;
            for (int i = 0; i <parameter ; i++) {
                sum+=i;
            }
            return sum;
        };
        System.out.println(function1.apply(5));
    }

}
