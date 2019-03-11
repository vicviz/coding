package training.Leetcode;

import java.util.*;

/**
 *127. 单词接龙
 *
 *
 *
 *
 * 题目描述
 * 评论 (34)
 * 题解
 * 提交记录
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class Q127ladderLength {

    /**
     * 判断两个字符串是否相邻
     * @param a
     * @param b
     * @return
     */
    private boolean isNeighbor(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }

    /**
     * 单向加边
     * @param adjacentMatrix
     * @param source
     * @param target
     */
    private void addNeighbor(Map<Integer, List<Integer>> adjacentMatrix, int source, int target) {
        List<Integer> adjA = adjacentMatrix.get(source);
        if (adjA == null) {
            adjA = new ArrayList<>();
            adjacentMatrix.put(source, adjA);
        }
        adjA.add(target);
    }

    /**
     * 构建邻接矩阵
     * @param wordArrayList
     * @return
     */
    private Map<Integer, List<Integer>> buildAdjMatrix(List<String> wordArrayList) {
        Map<Integer, List<Integer>> adjacentMatrix = new HashMap<>();
        for (int i = 0; i < wordArrayList.size(); i++) {
            for (int j = i + 1; j < wordArrayList.size(); j++) {
                if (isNeighbor(wordArrayList.get(i), wordArrayList.get(j))) {
                    addNeighbor(adjacentMatrix, i, j);
                    addNeighbor(adjacentMatrix, j, i);
                }
            }
        }
//        printAdjMatrix(adjacentMatrix, wordArrayList);
        return adjacentMatrix;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> noRepeat = new HashSet<>();
        for (String word: wordList) {
            noRepeat.add(word);
        }
        if (!noRepeat.contains(endWord)) {
            return 0;
        }
        if (!noRepeat.contains(beginWord)) {
            wordList.add(beginWord);
        }
        Map<Integer, List<Integer>> adjacentMatrix = buildAdjMatrix(wordList);
        LinkedList<Integer> candidate = new LinkedList<>();
        //确定起点和终点的编号
        int beginInt = 0;
        int endInt = 0;

        for (int i = 0 ;i < wordList.size(); i++) {
            if (wordList.get(i).equals(beginWord)) {
                beginInt = i;
            } else if (wordList.get(i).equals(endWord)) {
                endInt = i;
            }
        }
        //初始化未访问的点
        int[] d = new int[wordList.size()];
        for (int i = 0 ;i < wordList.size(); i++) {
            if (i != beginInt) {
                d[i] = Integer.MAX_VALUE;
            } else {
                d[i] = 0;
            }
        }
        candidate.addLast(beginInt);
        int[] color = new int[wordList.size()];
        Map<Integer, List<Integer>> preNodeListMap = new HashMap<>();

        //使用最短路径算法
        boolean getEnd = false;
        while (true) {
            int min = Integer.MAX_VALUE;
            Integer choosedNode = null;
            for (Integer node : candidate) {
                if (d[node] < min) {
                    min = d[node];
                }
            }
            if (min == Integer.MAX_VALUE) {
                break;
            }
            for (Integer node : candidate) {
                if (d[node] == min) {
                    if (node == endInt) {
                        //如果已经找到了终点，则可以直接进行计算了
                        getEnd = true;
                        break;
                    }
                    choosedNode = node;
                    candidate.remove(node);
                    break;
                }
            }
            if (getEnd) {
                break;
            }

            color[choosedNode] = 2;//表示已经确定了最短值了
            //确定了到这些点为最短距离点,生成下一批最短的候选点
            List<Integer> list = adjacentMatrix.get(choosedNode);
            if (list != null) {
                for (Integer nextNode : list) {
                    if (color[nextNode] == 2) {
                        continue;
                    }
                    if (color[nextNode] == 0) {
                        candidate.addLast(nextNode);
                    }
                    color[nextNode] = 1;//表示后续可以成为候选的
                    if (d[nextNode] > d[choosedNode] + 1) {
                        List<Integer> preList = preNodeListMap.get(nextNode);
                        if (preList == null) {
                            preList = new ArrayList<>();
                            preNodeListMap.put(nextNode, preList);
                        } else {
                            preList.clear();
                        }
                        preList.add(choosedNode);
                        d[nextNode] = d[choosedNode] + 1;
                    } else if (d[nextNode] == d[choosedNode] + 1) {
                        //如果有多个点的距离是一样的，在这里需要都加上
                        List<Integer> preList = preNodeListMap.get(nextNode);
                        if (preList == null) {
                            preList = new ArrayList<>();
                            preNodeListMap.put(nextNode, preList);
                        }
                        preList.add(choosedNode);
                    }
                }
            }
        }
        if (d[endInt] != Integer.MAX_VALUE) {
            LinkedList<String> initResult = new LinkedList<>();
            search(endInt, beginInt, preNodeListMap, initResult, wordList, result);
            return result.get(0).size();
        }
        return 0;
    }


    /**
     * 递归的构造路径结果
     * @param node
     * @param beginInt
     * @param preNodeListMap
     * @param result
     * @param wordArrayList
     */
    private void search(Integer node, int beginInt,
                                      Map<Integer, List<Integer>> preNodeListMap,
                        LinkedList<String> result, List<String> wordArrayList,
                        List<List<String>> finalResult) {
        result.addFirst(wordArrayList.get(node));
        if (node == beginInt) {
            finalResult.add(new ArrayList<>(result));
            return;
        } else {
            List<Integer> preNodes = preNodeListMap.get(node);
            for (Integer preNode: preNodes) {
                LinkedList<String> org = new LinkedList<>(result);
                search(preNode, beginInt, preNodeListMap, org, wordArrayList, finalResult);
            }
        }
    }

    private void printAdjMatrix(Map<Integer, List<Integer>> adjMatrix, List<String> wordArrayList) {
        for (Integer key: adjMatrix.keySet()) {
            System.out.print(wordArrayList.get(key) + "->");
            List<Integer> neighbors = adjMatrix.get(key);
            for (Integer neighbor: neighbors) {
                System.out.print(wordArrayList.get(neighbor) + ",");
            }
            System.out.println();
        }
    }
}