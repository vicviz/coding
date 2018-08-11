/**
 * TeamConnect.java, 2016—09-20.
 * <p>
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package training.normal;

import java.io.*;
import java.util.*;

/**
 * 题目:
 * 某公司有多个团队,每个团队与外部的团队有联系人;
 * 一个团队有可能与多个团队进行联系;
 * 一个团队A与另一个团队B如果没有直接联系,例如开发与市场团队,那么他们可以通过共同联系团队C(例如产品团队)进行联系;
 * 也有可能A经过c1,c2,...等团队才联系上B;
 * 现在需要组织旅游,HR想知道最少有多少个团队旅游去了,会导致出现不能沟通的团队?
 *
 * @author zhaowei
 */
public class TeamConnect {
    public static final int WHITE = 0, GRAY = 1, BLACK = 2;
    private int[][] flow, capacity, res_capacity;
    private int[] parent, color, queue;
    private int[] min_capacity;
    private int size, source, sink, first, last;
    private static int MAXN = 200;
    int N, M;

    private int maxFlow() {
        flow = new int[MAXN][MAXN];
        res_capacity = new int[MAXN][MAXN];
        parent = new int[MAXN];
        min_capacity = new int[MAXN];
        color = new int[MAXN];
        queue = new int[MAXN];
        int max_flow = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res_capacity[i][j] = capacity[i][j];
            }
        }

        while (BFS(source)) {
            max_flow += min_capacity[sink];
            int v = sink, u;
            while (v != source) {
                u = parent[v];
                flow[u][v] += min_capacity[sink];
                flow[v][u] -= min_capacity[sink];
                res_capacity[u][v] -= min_capacity[sink];
                res_capacity[v][u] += min_capacity[sink];
                v = u;
            }
        }
        return max_flow;
    }

    private boolean BFS(int source) {
        for (int i = 0; i < size; i++) {
            color[i] = WHITE;
            min_capacity[i] = Integer.MAX_VALUE;
        }

        first = last = 0;
        queue[last++] = source;
        color[source] = GRAY;

        while (first != last) {
            int v = queue[first++];
            for (int u = 0; u < size; u++)
                if (color[u] == WHITE && res_capacity[v][u] > 0) {
                    min_capacity[u] = Math.min(min_capacity[v], res_capacity[v][u]);
                    parent[u] = v;
                    color[u] = GRAY;
                    if (u == sink) return true;
                    queue[last++] = u;
                }
        }
        return false;
    }

    /**
     * @throws IOException
     */
    public void solve() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] numbers = line.split(" ");
        N = Integer.parseInt(numbers[0]);
        M = Integer.parseInt(numbers[1]);
        source = Integer.parseInt(numbers[2]);
        sink = Integer.parseInt(numbers[3]);
        source--;
        sink--;
        int osource = source;
        int osink = sink;
        capacity = new int[N * 2][N * 2];

        //init
        for (int i = 0; i < N; i++) {
            capacity[i * 2][i * 2 + 1] = 1;
        }

        for (int i = 0; i < M; i++) {
            line = scanner.nextLine();
            numbers = line.split(" ");
            int x = Integer.parseInt(numbers[0]);
            int y = Integer.parseInt(numbers[1]);
            x--;
            y--;
            capacity[x * 2 + 1][y * 2] = Integer.MAX_VALUE;
            capacity[y * 2 + 1][x * 2] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < 2 * N; i++) {
            capacity[i][source * 2] = 0;
            capacity[sink * 2 + 1][i] = 0;
        }

        int[] ans = new int[N + 1];

        size = 2 * N;
        source = source * 2 + 1;
        sink = sink * 2;

        int max = maxFlow();

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (i != osource && i != osink) {
                capacity[i * 2][i * 2 + 1] = 0;
                int nowFlow = maxFlow();

                if (max - nowFlow == count + 1) ans[count++] = i;
                else capacity[i * 2][i * 2 + 1] = 1;
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {

        TeamConnect teamConnect = new TeamConnect();
        teamConnect.solve();
    }
}
