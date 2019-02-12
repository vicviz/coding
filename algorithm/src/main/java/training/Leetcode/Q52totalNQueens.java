package training.Leetcode;

import java.util.*;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class Q52totalNQueens {
    private static class Point {
        private int x;
        private int y;
        private char c;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.c = '.';
        }
    }

    public int totalNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n == 1) {
            return 1;
        }
        if (n == 2 || n == 3) {
            return 0;
        }
        List<Point> remain = new LinkedList<>();
        List<Point> choosed = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j<= n; j++) {
                Point point = new Point(i,j);
                remain.add(point);
            }
        }
        Set<String> noRepeat = new HashSet<>();
        choose(remain, choosed, n, noRepeat);
        return noRepeat.size();
    }

    /**
     * 反复选择
     */
    private void choose(List<Point> remain, List<Point> choosed, int n, Set<String> noRepeatSet) {
        if (n == 0) {
            //输出结果
            String key = noRepeatKey(choosed);
            if (!noRepeatSet.contains(key)) {
                noRepeatSet.add(key);
            }
            return;
        }
        if (remain.size() == 0) {
            return;
        }
        for (int i = 0; i < remain.size(); i++) {
            Point now = remain.get(i);
            int nowSize = choosed.size();
            if (nowSize > 0) {
                if (now.x > choosed.size() + 1) {
                    break;
                }
            }
            List<Point> tryList = new LinkedList<>(remain);
            List<Point> tryChoosedList = new LinkedList<>(choosed);

            tryChoosedList.add(now);
            tryList = delete(now, tryList);
            choose(tryList, tryChoosedList, n - 1, noRepeatSet);
        }
    }

    private String noRepeatKey(List<Point> choosed) {
        Collections.sort(choosed, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.x > o2.x) {
                    return 1;
                } else if (o1.x < o2.x) {
                    return -1;
                } else {
                    if (o1.y > o2.y) {
                        return 1;
                    } else if (o1.y <= o2.y) {
                        return -1;
                    }
                    return 1;
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Point point: choosed) {
            sb.append(point.y).append("_");
        }
        return sb.toString();
    }


    /**
     * 移除这个点的可攻击点
     * @param point
     */
    private List<Point> delete(Point point, List<Point> remain) {
        Iterator<Point> iter = remain.iterator();
        List<Point> newRemain = new LinkedList<>();

        while (iter.hasNext()) {
            Point pointRemai = iter.next();
            if (point.x != pointRemai.x && point.y != pointRemai.y
                    && (Math.abs(point.x - pointRemai.x) != Math.abs(point.y - pointRemai.y))) {
                newRemain.add(pointRemai);
            }
        }
        return newRemain;
    }

    public static void main(String[] args) {
        Q52totalNQueens test = new Q52totalNQueens();
        System.out.println(test.totalNQueens(4));
    }
}
