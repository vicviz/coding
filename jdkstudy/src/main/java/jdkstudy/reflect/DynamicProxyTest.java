/**
 *   DynamicProxyTest.java, 2018—03-10.
 */
package jdkstudy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhaowei
 */
public class DynamicProxyTest {

    private static interface Isay {
        void say();
    }

    private static class AnyClass implements Isay {
        @Override
        public void say() {
            System.out.println("hi");
        }
    }

    private static class MyProxy implements InvocationHandler {
        Object someOne;
        private Object proxy(Object someOne) {
            this.someOne = someOne;
            return Proxy.newProxyInstance(someOne.getClass().getClassLoader(), someOne.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("someone said:");
            return method.invoke(someOne, args);
        }
    }

    public static void main(String[] args) {
        MyProxy myProxy = new MyProxy();
        Isay proxyOne = (Isay) myProxy.proxy(new AnyClass());
        proxyOne.say();
    }
}
