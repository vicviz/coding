package training.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *994. 腐烂的橘子  显示英文描述
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 *
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 *
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * 示例 3：
 *
 * 输入：[[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *
 *
 * 提示：
 *
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] 仅为 0、1 或 2
 */
public class Q994tree {
    public int orangesRotting(int[][] grid) {
        List<Integer> listx1 = new ArrayList<>();
        List<Integer> listy1 = new ArrayList<>();

        List<Integer> listx2 = new ArrayList<>();
        List<Integer> listy2 = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    listx2.add(i);
                    listy2.add(j);
                } else if (grid[i][j] == 1) {
                    listx1.add(i);
                    listy1.add(j);
                }
            }
        }
        if (listx1.size() == 0) {
            return 0;
        }
        if (listx2.size() == 0) {
            return -1;
        }

        boolean isChanage = true;
        int nums = 0;
        while (listx1.size() > 0 && isChanage == true) {
            isChanage = false;
            ArrayList newxList = new ArrayList();
            ArrayList newyList = new ArrayList();
            ArrayList<Integer> recordx = new ArrayList<>();
            ArrayList<Integer> recordy = new ArrayList<>();

            for (int i = 0; i < listx1.size(); i++) {
                int x1 = listx1.get(i);
                int y1 = listy1.get(i);
                if (isNext2(grid, x1, y1)) {
                    isChanage = true;
                    recordx.add(x1);
                    recordy.add(y1);
                } else {
                    newxList.add(x1);
                    newyList.add(y1);
                }
            }
            listx1 = newxList;
            listy1 = newyList;
            for (int i = 0 ; i < recordx.size(); i++) {
                int x1 = recordx.get(i);
                int y1 = recordy.get(i);
                grid[x1][y1] = 2;
            }
            nums++;
        }
        if (listx1.size() == 0) {
            return nums;
        }
        return -1;
    }

    private boolean isNext2(int[][] grid, int x, int y) {
        if (x - 1 >= 0) {
            if (grid[x - 1][y] == 2) {
                return true;
            }
        }
        if (y - 1 >= 0) {
            if (grid[x][y - 1] == 2) {
                return true;
            }
        }
        if (x + 1 < grid.length) {
            if (grid[x + 1][y] == 2) {
                return true;
            }
        }
        if (y + 1 < grid[0].length) {
            if (grid[x][y + 1] == 2) {
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        Q994tree test = new Q994tree();
//        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
//        int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};
        int[][] grid = {{0,2}};
        System.out.println(test.orangesRotting(grid));
    }
}