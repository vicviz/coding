/**
 * ContainerWithMostWater_11.java, 2016—10-01.
 */
package training.Leetcode;

/**
 * https://leetcode.com/problems/container-with-most-water/
 Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 Find two lines, which together with x-axis forms a container, such that the container contains the most water.

 Note: You may not slant the container.
 需要注意:两边不一定是相邻的，有可能隔了很远
 * @author zhaowei
 */
public class ContainerWithMostWater_11 {
    /**
     * 由于水面是由较低的挡板决定的，所以移动较高的那块，肯定是面积减小的，但移动较低的，有可能增加
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int start  = 0;
        int length = height.length;
        int end = length -1;
        int max = 0;
        while (start < end) {
            int low = Math.min(height[start], height[end]);
            int area =(end - start) * low;
            if (max < area) {
                max = area;
            }
            if (height[start] > height[end]) {
                end--;
            } else {
                start ++;
            }
        }
        return max;
    }
    /**
     * 暴力
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int max = 0;
        int length = height.length;
        for (int i = 0 ; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                int low = height[i] > height[j]? height[j]: height[i];
                int area = (j - i) * low;
                if (max < area) {
                    max = area;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        ContainerWithMostWater_11 solution = new ContainerWithMostWater_11();
        System.out.println(solution.maxArea(new int[]{1, 2, 1}));
    }
}
