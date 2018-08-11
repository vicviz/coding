package jdkstudy.classload;

/**
 * class load phase:
 * loading->verification->preparation->resolution->initialization->using->unloading
 *
 * loading:from disk or network
 *
 * verification:
 *   jvm:
 *   code verification
 *
 * preparation:
 *   alloc memory for class member variable
 *
 * resolution
 *
 * initialization:
 *   case1:init when get new、getstatic、putstatic、invokestatic
 *   case2:java.lang.reflex, init the father class
 *   case3:init class, init the father class
 *   case4:init main class
 *   case5:java.lang.invoke.MethodHandle after jdk1.7
 *
 * using
 *
 * unloading
 *
 */
public class TestClassLoader {
    static
    {
        System.out.println("TestClassLoader init");
    }

    {
        System.out.println("TestClassLoader member init");
    }

    static class Grandpa {
        static {
            System.out.println("Grandpa load");
        }
        public Grandpa ()
        {
            System.out.println("I'm grandpa.");
        }
    }
    static class Father extends Grandpa {
        public static int age = 40;
        static {
            System.out.println("father load");
        }

        public Father() {
            System.out.println("I'm father.");
        }
    }
    static class Son extends Father{
        static {
            System.out.println("son load");
        }
        public Son()
        {
            System.out.println("I'm son.");
        }
    }

    public static void main(String[] args) {
//        System.out.println("------without son class init ----");
//        // only father class is init
//        // so it will not print: "I'm son."
//        System.out.println("father.age:" + Son.age);

        System.out.println("\n\n\n------with son class init and constructor ----");
        Son son = new Son();

    }
}
