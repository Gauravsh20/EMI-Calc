package com.emicalc;

import java.util.*;

public class Test {

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        // Sort edges in descending order of reward
        public int compareTo(Edge other) {
            return Integer.compare(other.w, this.w);
        }
    }

    static class UnionFind {
        int[] parent, size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (x == parent[x]) {
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (size[rootX] < size[rootY]) {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1; // 0-based indexing
            int v = sc.nextInt() - 1; // 0-based indexing
            int w = sc.nextInt();
            edges[i] = new Edge(u, v, w);
        }
        Arrays.sort(edges); // Sort edges in descending order of reward
        UnionFind uf = new UnionFind(n);
        int maxReward = 0;
        for (Edge e : edges) {
            if (!uf.connected(e.u, e.v)) { // If adding edge e does not create a cycle
                uf.union(e.u, e.v);
                maxReward += e.w;
            }
        }
        for (Edge e : edges) {
            if (e.w < 0 && maxReward >= -e.w && !uf.connected(e.u, e.v)) {
                // If removing edge e does not disconnect the graph
                uf.union(e.u, e.v);
                maxReward += e.w;
            }
        }
        System.out.println(maxReward);
    }
}
