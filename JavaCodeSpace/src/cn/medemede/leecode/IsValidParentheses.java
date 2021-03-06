package cn.medemede.leecode;

import java.util.LinkedList;

public class IsValidParentheses {

    /**
     * 有效的括号
     *
     * @param s
     * @return
     */
    public boolean isValidParentheses(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.addFirst(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pollFirst();
                if (c == ')' && top != '(') {
                    return false;
                }
                if (c == ']' && top != '[') {
                    return false;
                }
                if (c == '}' && top != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}

