package cn.medemede.leecode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PathInZigZagTree {

    /**
     * 纯数学方法，完全自主实现：
     * 1. 求得 label 的正常顺序完全二叉树下标 index
     * 2. 求得 label 的正常顺序父节点下标 index/2
     * 3. 根据下标求得非正常顺序值 val
     * 4. 将 val 插入结果集
     * 5. 重复2-4，直到 index==0
     */
    public List<Integer> pathInZigZagTree(int label) {
        int n = log2(label + 1);
        int above = pow2(n) - 1;
        if (above >= label) {
            n--;
            above = pow2(n) - 1;
        }
        n++;
        int index = label;
        if (n % 2 == 0) {
            index = above + pow2(n) - label;
        }
        List<Integer> res = new LinkedList<>();
        res.add(label);
        index = index / 2;
        while (index > 0) {
            res.add(0, getValByIndex(index));
            index = index / 2;
        }
        return res;
    }

    private int getValByIndex(int index) {
        int n = log2(index + 1);
        int above = pow2(n) - 1;
        if (above >= index) {
            n--;
            above = pow2(n) - 1;
        }

        n++;
        if (n % 2 == 0) {
            return pow2(n) - (index - above);
        } else {
            return index;
        }
    }

    private int pow2(int n) {
        return 2 << (n - 1);
    }

    private int log2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }

    public List<Integer> pathInZigZagTree2(int label) {
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        int i = 1;
        int n = 1;
        boolean getLabel = false;
        while (!getLabel) {
            int num = (int) (Math.pow(2, n - 1));
            if (n % 2 == 1) {
                int l = i + num;
                int j = i;
                for (; j < l; j++) {
                    valToIndex.put(i, j);
                    if (j == label) {
                        getLabel = true;
                        break;
                    }
                    i++;
                }
            } else {
                int r = i;
                int j = i + num - 1;
                for (; j >= r; j--) {
                    valToIndex.put(i, j);
                    if (j == label) {
                        getLabel = true;
                        break;
                    }
                    i++;
                }
            }
            n++;
        }
        List<Integer> res = new LinkedList<>();
        while (i > 0) {
            res.add(0, valToIndex.get(i));
            i = i / 2;
        }
        return res;
    }

    public static void main(String[] args) {
        PathInZigZagTree pathInZigZagTree = new PathInZigZagTree();
        System.out.println(pathInZigZagTree.pathInZigZagTree2(14));
    }
}
