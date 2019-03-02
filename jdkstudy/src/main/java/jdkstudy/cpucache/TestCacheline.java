package jdkstudy.cpucache;

/**
 * this demo is used to show the cache line's influence
 * @author zhaowei
 *
 */
public class TestCacheline {

    private static void addByStep(int step) {
        int num = 64 * 1024 * 1024;
        int[] a = new int[num];
        long start = System.currentTimeMillis();
        for (int i = 0; i < a.length; i += step) {
            a[i] = a[i] * 3;
        }
        long end = System.currentTimeMillis();
        System.out.println("duration for step(" + step + ") is:" + (end - start));
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 2048;i = i * 2) {
            addByStep(i);
        }
    }
}