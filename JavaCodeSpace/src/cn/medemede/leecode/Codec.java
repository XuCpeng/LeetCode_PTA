package cn.medemede.leecode;

public class Codec {
    int i;
    StringBuilder sb = new StringBuilder();

    private void getSerialize(TreeNode root) {
        if (root == null) {
            sb.append("#").append(",");
            return;
        }
        sb.append(root.val).append(",");
        getSerialize(root.left);
        getSerialize(root.right);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        sb.delete(0, sb.length());
        getSerialize(root);
        return sb.toString();
    }

    private TreeNode getDeserialize(String[] dataChars) {
        i++;
        if (dataChars[i].equals("#")) {
            return null;
        }
        TreeNode newTreeNode = new TreeNode(Integer.parseInt(dataChars[i]));
        newTreeNode.left = getDeserialize(dataChars);
        newTreeNode.right = getDeserialize(dataChars);
        return newTreeNode;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataChars = data.split(",");
        i = -1;
        return getDeserialize(dataChars);
    }
}
