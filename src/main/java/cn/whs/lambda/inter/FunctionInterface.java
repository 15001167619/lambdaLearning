package cn.whs.lambda.inter;

/**
 * @author 武海升
 * @version 2.0
 * @description 函数式接口
 * @date 2018-03-30 16:57
 */
@FunctionalInterface
public interface FunctionInterface {

    /**
     *  函数式接口：有且仅有一个抽象方法的接口,
     *              其中默认方法、静态方法以及Object类的方法不算在内，
     *              校验是否为函数式接口 可通过注解 @FunctionalInterface 来测试
     */
    int get();

    public int hashCode();

    default String defaultMethod(){
        return null;
    }

    static int test(){
        return 0;
    }



}
