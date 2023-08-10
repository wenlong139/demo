package DesignPattern;

/**
 * @Author qinwenlong
 * @Date 2021/12/1
 **/
public class SingletonDemo {
    public static void main(String[] args) {

        Singleton singleton = Singleton.getSingleton();
    }

    static class Singleton {
        private static volatile Singleton singleton;

        private Singleton() {

        }

        public static Singleton getSingleton() {
            if(singleton==null) {
                synchronized (Singleton.class) {
                    if (singleton == null) {
                        singleton = new Singleton();
                    }
                }
            }
            return singleton;
        }
    }

}
