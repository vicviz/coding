package training.Leetcode;

public class Q27RemoveElement {
    public int removeElement(int[] nums, int val) {
        int tail = 0;
        for (int nowIndex = 0 ; nowIndex < nums.length;nowIndex++) {
            if (nums[nowIndex] != val) {
                if (tail != nowIndex) {
                    nums[tail] = nums[nowIndex];
                }
                tail++;
            }
        }
        return tail;
    }

    public static void main(String[] args) {
//        int[] num = {1,2,1,3,1,4};
        int[] num = {0,1,2,2,3,0,4,2};
        Q27RemoveElement q27RemoveElement = new Q27RemoveElement();
        int len = q27RemoveElement.removeElement(num, 2);
        for (int i = 0 ; i < len; i++) {
            System.out.print(num[i] + ",");
        }
    }
}
