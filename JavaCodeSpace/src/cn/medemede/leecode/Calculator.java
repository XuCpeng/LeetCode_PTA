package cn.medemede.leecode;

import java.util.LinkedList;

/**
 * 简易计算器
 * <p>后缀表达式</p>
 */
public class Calculator {
    int n;

    public int calculate(String s) {
        this.n = s.length();
        LinkedList<String> queue = parse2suffix(s);
        LinkedList<String> stack = new LinkedList<>();
        for (String str : queue) {
            if (isNumber(str)) {
                stack.addFirst(str);
            } else {
                stack.addFirst(calculateCup(stack.pollFirst(), stack.pollFirst(), str));
            }
        }
        return Integer.parseInt(stack.getFirst());
    }

    private String calculateCup(String a, String b, String operator) {
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        int res = 0;
        switch (operator) {
            case "+":
                res = x + y;
                break;
            case "-":
                res = y - x;
                break;
            case "*":
                res = x * y;
                break;
            case "/":
                res = y / x;
                break;
            default:
                break;
        }
        return String.valueOf(res);
    }

    private boolean isNumber(String s) {
        return !("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s));
    }

    private LinkedList<String> parse2suffix(String s) {
        LinkedList<String> queue = new LinkedList<>();
        LinkedList<String> stack = new LinkedList<>();
        n = s.length();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            i++;
            if (c == ' ') {
                continue;
            }
            if (c == '(') {
                stack.addFirst("(");
                continue;
            }
            if (c == '+' || c == '-') {
                while (!stack.isEmpty() && !stack.peekFirst().equals("(")) {
                    queue.addLast(stack.pollFirst());
                }
                stack.addFirst(c + "");
                continue;
            }
            if (c == '*' || c == '/') {
                while (!stack.isEmpty() && (stack.peekFirst().equals("*") || stack.peekFirst().equals("/"))) {
                    queue.addLast(stack.pollFirst());
                }
                stack.addFirst(c + "");
                continue;
            }
            if (c == ')') {
                while (!stack.isEmpty() && !stack.peekFirst().equals("(")) {
                    queue.addLast(stack.pollFirst());
                }
                stack.removeFirst();
                continue;
            }
            int p = i;
            while (p < n && '0' <= s.charAt(p) && s.charAt(p) <= '9') {
                p++;
            }
            queue.addLast(s.substring(i - 1, p));
            i = p;
        }
        while (!stack.isEmpty()) {
            queue.addLast(stack.pollFirst());
        }
        return queue;
    }


    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int res = calculator.calculate("42");
        System.out.println(res);
    }
}
