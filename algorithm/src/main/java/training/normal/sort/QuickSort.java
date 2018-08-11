package training.normal.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 14-4-7
 * Time: 下午10:14
 */
public class QuickSort {

    private static void exchange(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private static void sort2(List<Integer>list, int left, int right) {
        if (list == null || list.size() == 0 || left >= right) {
            return ;
        }
        int value0 = list.get(left);
        int i = left + 1;
        int j = right;
        while (i <= j) {
            while (list.get(i) < value0 && i <= right) {
                i ++;
            }
            while (list.get(j) > value0 && j>= left) {
                j --;
            }
            if (i < j) {
                exchange(list, i, j);
            }
        }
    }


    private static void sort1(List<Integer>list, int left, int right) {
        if (list == null || list.size() == 0 || left >= right) {
            return ;
        }
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && list.get(i) < list.get(j)) {
                j--;
            }
            if (i < j) {
                exchange(list, i, j);
            }
            while (i < j && list.get(i) < list.get(j)) {
                i++;
            }
            if (i < j) {
                exchange(list, i, j);
            }
        }

        if (i - left > 1) {
            sort1(list, left, i - 1);
        }
        if (right - i > 1) {
            sort1(list, i + 1, right);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(8);
        list.add(6);
        list.add(3);
        list.add(4);
        list.add(2);
        sort1(list, 0 , list.size() - 1);
        System.out.println(list);

    }
}
