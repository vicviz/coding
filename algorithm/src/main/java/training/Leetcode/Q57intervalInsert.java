package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 给出一个无重叠的 ，按照区间起始端点排序的区间列表。

 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

 示例 1:

 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 输出: [[1,5],[6,9]]
 示例 2:

 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 输出: [[1,2],[3,10],[12,16]]
 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 */
public class Q57intervalInsert {
    public static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        intervals.add(newInterval);
        return merge(intervals);
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
        Q57intervalInsert test = new Q57intervalInsert();
        List<Interval> list = new ArrayList<>();
        Interval a = new Interval(1,2);
        list.add(a);
        a = new Interval(3,5);
        list.add(a);
        a = new Interval(6,7);
        list.add(a);
        a = new Interval(8,10);
        list.add(a);
        a = new Interval(12,16);
        list.add(a);
        List<Interval> result = test.insert(list, new Interval(4, 8));
        for (Interval interval: result) {
            System.out.println(interval.start + "," + interval.end);
        }
    }
}
