package cn.medemede.leecode;

import java.util.*;

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if (n < 4) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            if (i != 0) {
                continue;
            }
            int target2 = target - nums[i];
            for (int j = i + 1; j < n - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int target3 = target2 - nums[j];
                int l = n - 1;
                for (int k = j + 1; k < l; k++) {
                    if (k != j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    int target4 = target3 - nums[k];
                    for (; l > k; l--) {
                        if (l != nums.length - 1 && nums[l] == nums[l + 1]) {
                            continue;
                        }
                        if (nums[l] < target4) {
                            break;
                        }
                        if (nums[l] == target4) {
                            result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[l])));
                        }
                    }
                }
            }
        }
        return result;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

    private TreeNode getFlatten(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left != null) {
            root.right = left;
            root.left = null;
            root = getFlatten(left);
        }
        if (right != null) {
            root.right = right;
            return getFlatten(right);
        } else {
            return root;
        }
    }

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        getFlatten(root);
    }

    private void getConnect(Node node1, Node node2) {
        if (node1 == null)
            return;
        node1.next = node2;
        getConnect(node1.left, node1.right);
        getConnect(node2.left, node2.right);
        getConnect(node1.right, node2.left);
    }

    public Node connect(Node root) {
        if (root == null)
            return null;
        getConnect(root.left, root.right);
        return root;
    }

    public boolean canPartition(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count += num;
        }
        if (count % 2 != 0) {
            return false;
        }

        count = count / 2;

        boolean[] dp = new boolean[count + 1];
        if (nums[0] <= count)
            dp[nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = count; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[count];
    }

    public ListNode reverseList(ListNode head) {
        if (head.next == null)
            return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        return newHead;
    }

    ListNode successor = null;

    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode newHead = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return newHead;
    }

    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right - left + 1);
        }
        int i = 1;
        ListNode p = head;
        while (i < left - 1) {
            i++;
            p = p.next;
        }
        p.next = reverseN(p.next, right - left + 1);
        return head;
    }

    private ListNode getReverseBetween(ListNode node, ListNode q) {
        if (node == q)
            return node;
        getReverseBetween(node.next, q).next = node;
        return node;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (right - left + 1 < 2) {
            return head;
        }

        int i = 0;
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode p = newHead;

        while (i < left - 1) {
            i++;
            p = p.next;
        }
        ListNode q = p;
        while (i < right) {
            q = q.next;
            i++;
        }
        ListNode r = q.next;
        getReverseBetween(p.next, q).next = r;
        p.next = q;
        return newHead.next;
    }

    public ListNode reverseN2(ListNode head, int n, int k) {
        if (n == 1) {
            successor = reverseN2(head.next, k, k);
            return head;
        }
        ListNode newHead = reverseN2(head.next, n - 1, k);
        head.next.next = head;
        head.next = successor;
        return newHead;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode h = newHead, p, q, r;
        int nums = 0;
        while (h.next != null) {
            nums++;
            h = h.next;
        }
        h = newHead;

        for (int j = 0; j < nums / k; j++) {
            p = h.next;
            q = p.next;
            for (int i = 0; i < k - 1 && q != null; i++) {
                r = q.next;
                q.next = p;
                p = q;
                q = r;
            }
            h.next.next = q;
            q = h.next;
            h.next = p;
            h = q;
        }
        return newHead.next;
    }

    private ListNode reverseKGroup2(ListNode head, int k) {
        ListNode p, q, r;
        p = head;
        for (int i = 0; i < k; i++) {
            if (p == null) {
                return head;
            }
            p = p.next;
        }
        p = head;
        q = p.next;
        for (int i = 0; i < k - 1 && q != null; i++) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        head.next = reverseKGroup2(q, k);
        return p;
    }

    private boolean getIsPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        if (getIsPalindrome(head.next) && p.val == head.val) {
            p = p.next;
            return true;
        } else {
            return false;
        }
    }

    private ListNode p;

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        p = head;
        return getIsPalindrome(head.next);
    }

    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode q;
        if (fast == null) {
            fast = slow;
        } else {
            fast = slow.next;
        }
        q = fast.next;
        fast.next = null;
        while (q != null) {
            ListNode r = q.next;
            q.next = fast;
            fast = q;
            q = r;
        }
        while (head != slow) {
            if (head.val != fast.val) {
                return false;
            }
            head = head.next;
            fast = fast.next;
        }
        return true;
    }

    private TreeNode getConstructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        }
        int index = start;
        int max = nums[start];
        for (int i = start + 1; i < end; i++) {
            if (nums[i] > max) {
                index = i;
                max = nums[i];
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = getConstructMaximumBinaryTree(nums, start, index);
        root.right = getConstructMaximumBinaryTree(nums, index + 1, end);
        return root;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return getConstructMaximumBinaryTree(nums, 0, nums.length);
    }

    Map<Integer, Integer> indexHash = new HashMap<>();

    private TreeNode getBuildTree(int[] preorder, int index, int inStart, int inEnd) {
        if (inStart >= inEnd)
            return null;
        int val = preorder[index];
        TreeNode root = new TreeNode(val);
        int i = indexHash.get(val);
        root.left = getBuildTree(preorder, index + 1, inStart, i);
        root.right = getBuildTree(preorder, index + (i - inStart) + 1, i + 1, inEnd);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0)
            return null;
        for (int i = 0; i < inorder.length; i++) {
            indexHash.put(inorder[i], i);
        }
        return getBuildTree(preorder, 0, 0, inorder.length);
    }

    HashMap<String, Boolean> subTreeMap = new HashMap<>();
    List<TreeNode> result = new LinkedList<>();

    private String buildSubTree(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String subTree = buildSubTree(root.left) + "," + buildSubTree(root.right) + "," + root.val;
        if (subTreeMap.get(subTree) == null) {
            subTreeMap.put(subTree, true);
        } else if (subTreeMap.get(subTree)) {
            result.add(root);
            subTreeMap.put(subTree, false);
        }
        return subTree;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        buildSubTree(root);
        return result;
    }

    private void getDeleteNode(TreeNode root, TreeNode parent, int leftOrRight, int key) {
        if (root == null) {
            return;
        }
        if (root.val == key) {
            if (root.left != null || root.right != null) {
                TreeNode tmp;
                parent = root;
                if (root.right != null) {
                    tmp = root.right;
                    leftOrRight = 2;
                    while (tmp.left != null) {
                        parent = tmp;
                        tmp = tmp.left;
                        leftOrRight = 1;
                    }
                } else {
                    tmp = root.left;
                    leftOrRight = 1;
                    while (tmp.right != null) {
                        parent = tmp;
                        tmp = tmp.right;
                        leftOrRight = 2;
                    }
                }
                root.val = tmp.val;
            }
            if (leftOrRight == 1) {
                if (parent.left.right != null)
                    parent.left = parent.left.right;
                else
                    parent.left = parent.left.left;
            } else {
                if (parent.right.left != null) {
                    parent.right = parent.right.left;
                } else {
                    parent.right = parent.right.right;
                }
            }
        } else if (root.val > key) {
            getDeleteNode(root.left, root, 1, key);
        } else {
            getDeleteNode(root.right, root, 2, key);
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode newHead = new TreeNode();
        newHead.left = root;
        getDeleteNode(root, newHead, 1, key);
        return newHead.left;
    }

    TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode minNode = root.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }
            root.val = minNode.val;
            root.right = deleteNode2(root.right, minNode.val);
        } else if (root.val > key) {
            root.left = deleteNode2(root.left, key);
        } else {
            root.right = deleteNode2(root.right, key);
        }
        return root;
    }


    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    public boolean isValidBSTFf(TreeNode root) {
        return root == null ||
                (root.left != null ? root.left.val < root.val && isValidBSTFf(root.left)
                        : root.right == null || (root.right.val > root.val && isValidBSTFf(root.right)));
    }

    private boolean getIsValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
            return false;
        }
        return getIsValidBST(root.left, min, root.val) && getIsValidBST(root.right, root.val, max);
    }

    public boolean isValidBST2(TreeNode root) {
        return getIsValidBST(root, null, null);
    }


    /**
     * 二叉查找树中序遍历为升序
     */
    TreeNode pre = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (pre != null && root.val <= pre.val) {
            return false;
        }
        pre = root;
        return isValidBST(root.right);
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        if (head.next == head)
            return true;
        ListNode p = head.next;
        head.next = head;
        ListNode q;
        while (p.next != null) {
            if (p.next.next == head)
                return true;
            else {
                q = p.next;
                p.next = head;
                p = q;
            }
        }
        return false;
    }

    /**
     * HashMap
     * <p>
     * 单调栈：从顶部添加，逐个删除
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            valToIndex.put(nums1[i], i);
        }
        int[] res = new int[nums1.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int j = nums2.length - 1; j >= 0; j--) {
            while (!stack.isEmpty() && stack.peek() < nums2[j]) {
                stack.pop();
            }
            if (valToIndex.containsKey(nums2[j])) {
                res[valToIndex.get(nums2[j])] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums2[j]);
        }
        return res;
    }

    /**
     * 单调栈：从顶部添加，逐个删除
     * <p>
     * 数组长度翻倍
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            int trueI = i % nums.length;
            while (!stack.isEmpty() && nums[trueI] >= stack.peek()) {
                stack.poll();
            }
            if (i < nums.length) {
                res[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.addFirst(nums[trueI]);
        }
        return res;
    }


    /**
     * 单调栈：从顶部添加，逐个删除
     * <p>
     * 索引入栈
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.poll();
            }
            if (!stack.isEmpty()) {
                res[i] = stack.peek() - i;
            }
            stack.addFirst(i);
        }
        return res;
    }

    /**
     * 单调队列：从尾部添加，逐个删除
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k==1)
            return nums;
        int[] res=new int[nums.length-k+1];
        int i=0;
        int j=0;
        LinkedList<Integer> queue=new LinkedList<>();
        while(j<k){
            while(!queue.isEmpty()&&queue.peekLast()<nums[j]){
                queue.removeLast();
            }
            queue.addLast(nums[j]);
            j++;
        }
        res[i]=queue.peekFirst();
        while(j<nums.length){
            if(nums[i]==queue.peekFirst()){
                queue.removeFirst();
            }
            while(!queue.isEmpty()&&queue.peekLast()<nums[j]){
                queue.removeLast();
            }
            queue.addLast(nums[j]);
            i++;
            res[i]=queue.peek();
            j++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        ListNode p = head;
        for (int i = 2; i <= 5; i++) {
            p.next = new ListNode(i);
            p = p.next;
        }
        ListNode result = s.reverseKGroup2(head, 2);
        System.out.println(result);
    }
}
