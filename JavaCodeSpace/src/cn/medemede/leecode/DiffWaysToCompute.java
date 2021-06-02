package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.List;

public class DiffWaysToCompute {

    /**
     * 为运算表达式设计优先级
     * <p>分治算法</p>
     *
     * @param expression
     * @return
     */
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            switch (c) {
                case '+': {
                    List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                    List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                    for (Integer a : left) {
                        for (Integer b : right) {
                            res.add(a + b);
                        }
                    }
                    break;
                }
                case '-': {
                    List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                    List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                    for (Integer a : left) {
                        for (Integer b : right) {
                            res.add(a - b);
                        }
                    }
                    break;
                }
                case '*': {
                    List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                    List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                    for (Integer a : left) {
                        for (Integer b : right) {
                            res.add(a * b);
                        }
                    }
                    break;
                }
            }
        }

        if (res.isEmpty()) {
            res.add(Integer.valueOf(expression));
        }

        return res;
    }


}

