package training.Leetcode;

public class Q42trap {
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int nowMaxLeft = 0;
        int nowMaxRight = 0;
        for (int i = 0 ; i < height.length; i++) {
            if (height[i] > nowMaxLeft) {
                nowMaxLeft = height[i];
            }
            leftMax[i] = nowMaxLeft;
        }
        for (int i = height.length - 1 ; i >= 0; i--) {
            if (height[i] > nowMaxRight) {
                nowMaxRight = height[i];
            }
            rightMax[i] = nowMaxRight;
        }

        int result = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int num = height[i];
            if (num < leftMax[i - 1] && num < rightMax[i + 1]) {
                if (leftMax[i - 1] >= rightMax[i + 1]) {
                    result += rightMax[i + 1] - num;
                } else {
                    result += leftMax[i - 1] - num;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q42trap test= new Q42trap();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(test.trap(height));
    }
}
