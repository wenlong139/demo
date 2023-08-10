package mulityTheads;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author qinwenlong
 * @Date 2021/11/16
 **/
public class ThreadDemo {

    public static String testCatchException(Integer integer) {
        try {
            System.out.println("1111");
            throw new RuntimeException("error");
        } catch (Throwable e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        String s ="a";
        char c = s.charAt(2);
        int num=1;
        foo(num);
        System.out.println(num);
        /*List<Integer> list = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            list.add(1);
        }
        Object pass =
                list.parallelStream().map(ThreadDemo::testCatchException).filter(s -> !s.equals("pass")).collect(Collectors.toList());
        System.out.println(pass);*/
    }


    public static void foo(int num) {
        num=100;
    }

}
