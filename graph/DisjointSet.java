package graph;

import java.util.Arrays;

class DisjointSet {
    int[] parent;
    int[] rank;
    int[] size;
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];
        for (int i =0 ; i< n;i++) {
            parent[i] = i;
        }
        Arrays.fill(size, 1);
    }

    public boolean find(int u, int v) {
        int parentOfU = findParent(u);
        int parentOfV = findParent(v);
        return parentOfU == parentOfV;
    }

    public int findParent(int u) {
        if (parent[u] == u) {
            return u;
        }

        int root = findParent(parent[u]);
        parent[u] = root;
        return root;
    }

    public void unionByRank(int u, int v) {
        int rootOfU = findParent(u);
        int rootOfV = findParent(v);

        if (rank[rootOfU] < rank[rootOfV]) {
            parent[rootOfU] = rootOfV;
        } else if (rank[rootOfU] > rank[rootOfV]) {
            parent[rootOfV] = rootOfU;
        } else {
            parent[rootOfV] = rootOfU;
            rank[rootOfU]++;
        }
    }

    public void unionBySize(int u, int v) {
        int rootOfU = findParent(u);
        int rootOfV = findParent(v);

        if (size[rootOfU] < size[rootOfV]) {
            parent[rootOfU] = rootOfV;
            size[rootOfV] = size[rootOfU] + size[rootOfV];
        } else  if (size[rootOfV] < size[rootOfU]) {
            parent[rootOfV] = rootOfU;
            size[rootOfU] = size[rootOfU] + size[rootOfV];
        } else {
            parent[rootOfV] = rootOfU;
            size[rootOfU] = size[rootOfU] + size[rootOfV];
        }

    }
}