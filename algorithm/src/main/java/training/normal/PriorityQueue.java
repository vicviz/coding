package training.normal;

import java.util.Scanner;

/** 题目描述：
     把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     输入：
     输入可能包含多个测试样例，对于每个测试案例，
     输入的第一行为一个整数n(1<= n<=1000000)：代表旋转数组的元素个数。
     输入的第二行包括n个整数，其中每个整数a的范围是(1<=a<=10000000)。
     输出：
     对应每个测试案例，
     输出旋转数组中最小的元素。
     样例输入：
     5
     3 4 5 12
     样例输出：
     1
 */
public class PriorityQueue {
    private static int findMinBaoli(int [] array, int start ,int end, int len)  {
        int small = array[start];
        for (int i = start ; i <= end ;i++) {
            if (array[i] < small) {
                small = array[i];
            }
        }
        return small;
    }
    private static int findMin(int [] array, int start ,int end, int len) {
        int value0 = array[0];//the first element is a flag for localizing
        while (start < end) {
            if (start + 1 == end) {
                return array[start] > array[end]? array[end]:array[start];
            }
            int index = start + (end - start) / 2;

            int valueIndex = array[index];
            if (valueIndex > value0) {
                //the smallest is in right part.
                start = index + 1;
            } else if (valueIndex < value0) {
                //the smallest is in left part.
                if (valueIndex < array[index - 1]) {
                    return valueIndex;
                }
                end = index - 1;
            }  else {
                return findMinBaoli( array, start , end, len);
            }
        }
        return array[end];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            int array[] = new int[num];
            for (int i = 0; i < num; i++) {
                array[i] = scanner.nextInt();
            }
            //如果第一个比最后一个小，说明没有旋转。
            if (num == 1 || array[0] < array[num - 1]) {
                System.out.println(array[0]);
            } else {
                System.out.println(findMin(array, 0, num - 1,num));
            }
        }
    }
}
