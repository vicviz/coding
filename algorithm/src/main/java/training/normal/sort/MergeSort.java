/**
 *
 * ChooseSort.java, 2017—01-23.
 *
 *
 */
package training.normal.sort;

/**
 * the time cost is terrible for lenthg of array is 40
 * 递归调用在小数组很多时会反复调用,这时造成了时间的浪费
 * 快速排序也有类似的问题,建议是在小数组时,进行插入排序
 * @author zhaowei
 */
public class MergeSort extends Sorter {

    private void merge(Comparable[] a, int start, int mid, int end, Comparable[] tempSpace) {
        for (int i = start;i <= end;i++) {
            tempSpace[i] = a[i];//just copy
        }
        int i = start;
        int j = mid + 1;
        for (int k = start;k <= end;k++) {
            if (i > mid) {
                a[k] = tempSpace[j++];
            } else if (j > end) {
                a[k] = tempSpace[i++];
            } else if (tempSpace[i].compareTo(tempSpace[j]) > 0) {
                a[k] = tempSpace[j++];
            } else {
                a[k] = tempSpace[i++];
            }
        }
    }


    @Override
    public long sort(Comparable[] a) {
        long startTime = System.currentTimeMillis();
        Comparable[] temp = new Comparable[a.length];
        sort(a, 0, a.length - 1, temp);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private void sort(Comparable[] a, int start , int end, Comparable[]temp) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(a, 0, mid, temp);
        sort(a, mid + 1, end, temp);
        merge(a, 0, mid, end, temp);
    }

    public static void main(String[] args) {
        int len = 40;
        Sorter sorter = new MergeSort();
        Comparable[] a = mock(len);
        show(a, len);
        System.out.println("time cost:" + sorter.sort(a));
        show(a, len);
    }
}
