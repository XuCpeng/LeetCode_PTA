package cn.medemede.leecode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class OpenLock {

    /**
     * 打开转盘锁
     * <p>广度优先遍历BFS，visited</p>
     * <p>注意：一次搜索一层，除了用指针记录每层最后一个节点的方法，还可以事先记录队列的长度n，然后for</p>
     *
     * @param deadends
     * @param target
     * @return
     */
    StringBuilder sb;

    public int openLock(String[] deadends, String target) {
        LinkedList<String> queue = new LinkedList<>();
        this.sb = new StringBuilder();
        HashSet<String> visited = new HashSet<>(Arrays.asList(deadends));
        if (visited.contains("0000")) {
            return -1;
        }
        queue.addLast("0000");
        int res = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String tmp = queue.pollFirst();
                visited.add(tmp);
                if (target.equals(tmp)) {
                    return res;
                }

                for (int j = 0; j < 4; j++) {
                    String newStr = charPlus(tmp, j);
                    if (!visited.contains(newStr)) {
                        queue.addLast(newStr);
                        visited.add(newStr);
                    }
                    newStr = charReduce(tmp, j);
                    if (!visited.contains(newStr)) {
                        queue.addLast(newStr);
                        visited.add(newStr);
                    }
                }
            }
            res++;
        }
        return -1;
    }

    private String charPlus(String s, int i) {
        String res;
        char c = s.charAt(i);
        sb.append(s);
        if (c + 1 > '9') {
            sb.setCharAt(i, '0');
        } else {
            sb.setCharAt(i, (char) (c + 1));
        }
        res = sb.toString();
        sb.delete(0, sb.length());
        return res;
    }

    private String charReduce(String s, int i) {
        String res;
        char c = s.charAt(i);
        sb.append(s);
        if (c - 1 < '0') {
            sb.setCharAt(i, '9');
        } else {
            sb.setCharAt(i, (char) (c - 1));
        }
        res = sb.toString();
        sb.delete(0, sb.length());
        return res;
    }

    /**
     * 打开转盘锁
     * <p>双向BFS，必须知道终点</p>
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock2(String[] deadends, String target) {
        HashSet<String> deadStrs = new HashSet<>(Arrays.asList(deadends));
        if (deadStrs.contains("0000")) {
            return -1;
        }
        int res = 0;
        this.sb = new StringBuilder();
        HashSet<String> visited = new HashSet<>();
        HashSet<String> queue = new HashSet<>();
        queue.add("0000");
        HashSet<String> queue2 = new HashSet<>();
        queue2.add(target);

        while (!queue.isEmpty() && !queue2.isEmpty()) {
            HashSet<String> temp = new HashSet<>();
            for (String s : queue) {
                if (deadStrs.contains(s)) {
                    continue;
                }
                if (queue2.contains(s)) {
                    return res;
                }
                visited.add(s);
                for (int j = 0; j < 4; j++) {
                    String newStr = charPlus(s, j);
                    if (!visited.contains(newStr)) {
                        temp.add(newStr);
                    }
                    newStr = charReduce(s, j);
                    if (!visited.contains(newStr)) {
                        temp.add(newStr);
                    }
                }
            }
            res++;
            queue = queue2;
            queue2 = temp;
        }
        return -1;
    }

}

