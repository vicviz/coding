package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 */
public class Q56merge {
    public static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() == 0) {
            return Collections.emptyList();
        } else if (intervals.size() == 1) {
            return intervals;
        }
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start > o2.start) {
                    return 1;
                } else if (o1.start < o2.start) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        Interval now = intervals.get(0);
        List<Interval> result = new ArrayList<>();
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (now.end >= interval.start) {
                if (now.end < interval.end) {
                    now.end = interval.end;
                }
            } else {
                result.add(now);
                now = interval;
            }
        }
        result.add(now);
        return result;
    }

    public static void main(String[] args) {
        Q56merge test = new Q56merge();
        List<Interval> list = new ArrayList<>();
        Interval a = new Interval(1,3);
        list.add(a);
        a = new Interval(2,6);
        list.add(a);
        a = new Interval(8,10);
        list.add(a);
        a = new Interval(15,18);
        list.add(a);
        a = new Interval(18,129);
        list.add(a);
        List<Interval> result = test.merge(list);
        for (Interval interval: result) {
            System.out.println(interval.start + "," + interval.end);
        }
    }
}
