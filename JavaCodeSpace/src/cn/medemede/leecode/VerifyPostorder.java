package cn.medemede.leecode;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 */
public class VerifyPostorder {
    public boolean verifyPostorder(int[] postorder) {
        return isVerify(postorder, 0, postorder.length - 1);
    }

    private boolean isVerify(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int p = left;
        while (postorder[p] < postorder[right]) {
            p++;
        }
        int rL = p;
        while (postorder[p] > postorder[right]) {
            p++;
        }

        return p == right && isVerify(postorder, left, rL - 1) && isVerify(postorder, rL, right - 1);
    }
}
