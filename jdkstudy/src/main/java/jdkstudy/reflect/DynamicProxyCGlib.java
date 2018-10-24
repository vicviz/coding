/**
 *   DynamicProxyTest.java, 2018—03-10.
 */
package jdkstudy.reflect;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhaowei
 */
public class DynamicProxyCGlib {

    public static class AnyClass {
        public void say() {
            System.out.println("hi");
        }
    }

    /**
     * 使用cglib 3.2.0+ 版本与asm有冲突，注意使用2.2的版本
     */
    public static class CGlibDemo implements MethodInterceptor {
        public Object intercept(Object obj, Method method, Object[] args,
                                MethodProxy proxy) throws Throwable {
            System.out.println("before invoke");
            proxy.invokeSuper(obj, args);
            System.out.println("said");
            return null;
        }
    }
    public static void main(String[] args) {
        AnyClass anyClass = new AnyClass();
        CGlibDemo demo = new CGlibDemo();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(anyClass.getClass());
        enhancer.setCallback(demo);
        AnyClass proxy = (AnyClass) enhancer.create();
        proxy.say();
    }
}
