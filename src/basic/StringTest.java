package basic;

public class StringTest {
    public static void main(String[] args) {
        //创建了两个对象，一份存在字符串常量池中，一份存在堆中
        String s = new String("aa");
        //检查常量池中是否存在字符串aa，此处存在则直接返回
        String s1 = s.intern();
        String s2 = "aa";

        System.out.println(s == s2);  //①
        System.out.println(s1 == s2); //②

        String s3 = new String("b") + new String("b");
        //常量池中没有bb，在jdk1.7之后会把堆中的引用放到常量池中，故引用地址相等
        String s4 = s3.intern();
        String s5 = "bb";

        System.out.println(s3 == s5 ); //③
        System.out.println(s4 == s5);  //④

    }
}