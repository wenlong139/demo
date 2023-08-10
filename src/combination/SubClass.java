package combination;

/**
 * @Author qinwenlong
 * @Date 2023/6/8
 **/
public class SubClass extends Parent{
    @Override
    public void say() {
        super.say();
        System.out.println("sub");
    }

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.say();
    }
}
