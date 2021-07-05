package cn.medemede.leecode;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * 原子的数量
 */
public class CountOfAtoms {
    int n;
    int i;
    String formula;

    public String countOfAtoms(String formula) {
        this.n = formula.length();
        this.formula = formula;
        this.i = 0;
        LinkedList<TreeMap<String, Integer>> stack = new LinkedList<>();

        while (i < n) {
            char c = formula.charAt(i);

            if (c == '(') {
                stack.addFirst(new TreeMap<>());
                i++;
                continue;
            }

            if (c == ')') {
                int base = getNumber();
                TreeMap<String, Integer> top = stack.pollFirst();
                TreeMap<String, Integer> next = stack.pollFirst();
                if (next == null) {
                    next = new TreeMap<>();
                }
                for (Map.Entry<String, Integer> entry : top.entrySet()) {
                    next.put(entry.getKey(), next.getOrDefault(entry.getKey(), 0) + entry.getValue() * base);
                }
                stack.addFirst(next);
                continue;
            }

            TreeMap<String, Integer> top = stack.peekFirst();
            if (top == null) {
                stack.addFirst(new TreeMap<>());
                top = stack.peekFirst();
            }
            String element = getElement();
            int base = getNumber();
            top.put(element, top.getOrDefault(element, 0) + base);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : stack.getFirst().entrySet()) {
            sb.append(entry.getKey());
            if (entry.getValue() > 1)
                sb.append(entry.getValue());
        }

        return sb.toString();
    }

    private int getNumber() {
        int base = 1;
        int j = i;
        while (j + 1 < n && formula.charAt(j + 1) >= '0' && formula.charAt(j + 1) <= '9') {
            j++;
        }

        if (j > i) {
            base = Integer.parseInt(formula.substring(i + 1, j + 1));
        }
        i = j + 1;
        return base;
    }

    private String getElement() {
        int j = i;
        while (j + 1 < n && formula.charAt(j + 1) >= 'a' && formula.charAt(j + 1) <= 'z') {
            j++;
        }
        String element = formula.substring(i, j + 1);
        i = j;
        return element;
    }

    public static void main(String[] args) {
        CountOfAtoms countOfAtoms = new CountOfAtoms();
        System.out.println(countOfAtoms.countOfAtoms("Mg(OH)2"));
    }
}
