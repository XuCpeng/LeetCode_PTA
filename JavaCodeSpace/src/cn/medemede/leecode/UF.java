package cn.medemede.leecode;


/**
 * Union-Find 算法，并查集算法，主要是解决图论中「动态连通性」问题的。
 */
public class UF {
    int count;
    int[] parent;
    int[] wight;

    public UF(int n) {
        this.count = n;
        this.parent = new int[n];
        this.wight = new int[n];

        //初始化parent[i] = i，即为根结点
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            wight[i] = 1;
        }
    }

    /**
     * find时通过合并父节点进行路径压缩
     *
     * @param p
     * @return
     */
    public int find(int p) {
        while (parent[p] != p) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    /**
     * 合并时，将小树接到大树下面，平衡优化
     *
     * @param p
     * @param q
     */
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
