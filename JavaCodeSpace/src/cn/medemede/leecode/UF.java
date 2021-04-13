package cn.medemede.leecode;

public class UF {
    int count;
    int[] parent;
    int[] wight;

    public UF(int n) {
        this.count = n;
        this.parent = new int[n];
        this.wight = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            wight[i] = 1;
        }
    }

    public int find(int p) {
        while (parent[p] != p) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootQ == rootP) {
            return;
        }
        if (wight[rootP] > wight[rootQ]) {
            parent[rootQ] = rootP;
            wight[rootP] = wight[rootP] + wight[rootQ];
        } else {
            parent[rootP] = rootQ;
            wight[rootQ] = wight[rootQ] + wight[rootP];
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);

    }

    public int getCount() {
        return count;
    }
}
