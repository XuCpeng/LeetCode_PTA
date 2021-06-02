package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    /**
     * 括号生成
     * <p>递归，回溯。左右括号可用数量为参数</p>
     * <p>记录左右括号的可用数量，递归选择左右括号，左括号数量>=右括号数量。</p>
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        getGenerateParenthesis(n, 0, sb, res);
        return res;
    }

    private void getGenerateParenthesis(int left, int right, StringBuilder sb, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(sb.toString());
            return;
        }

        if (left > 0) {
            sb.append('(');
            getGenerateParenthesis(left - 1, right + 1, sb, res);
            sb.delete(sb.length() - 1, sb.length());
        }
        if (right > 0) {
            sb.append(')');
            getGenerateParenthesis(left, right - 1, sb, res);
            sb.delete(sb.length() - 1, sb.length());
        }
    }

}

