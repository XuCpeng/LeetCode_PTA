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

    /**
     * 翻转二叉树
     * <p>后序遍历进行翻转
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

    /**
     * 将二叉树展开为右叉树
     * <p>先展开左子树，并返回左子树的右下节点作为右子树的根节点
     *
     * @param root
     * @return
     */
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
        if (root == null) {
            return;
        }
        getFlatten(root);
    }

    /**
     * 连接二叉树的后继节点
     * <p> 使用辅助函数同时连接两个子树
     *
     * @param node1
     * @param node2
     */
    private void getConnect(Node node1, Node node2) {
        if (node1 == null) {
            return;
        }
        node1.next = node2;
        getConnect(node1.left, node1.right);
        getConnect(node2.left, node2.right);
        getConnect(node1.right, node2.left);
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        getConnect(root.left, root.right);
        return root;
    }

    /**
     * 背包问题：将数组分割成两个子集，使得两个子集的元素和相等。未进行状态压缩。
     * <p> 若可分割为两个子集，则子集大小恰好为 count / 2
     * <p> dp数组的大小为 nums.length, (count / 2) + 1
     * <p> 遍历整个数组，相当于依次添加每个物品，dp[i][j]表示前i个物品是否恰好可以装满重量为j的背包
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count += num;
        }
        if (count % 2 != 0) {
            return false;
        }
        count = count / 2;

        boolean[][] dp = new boolean[nums.length][count + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= count) {
                dp[i][nums[i]] = true; //物品i肯定能恰好装满重量为nums[i]的背包
            }
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= count; j++) {
                dp[i][j] = dp[i - 1][j] || (j - nums[i] > 0 && dp[i - 1][j - nums[i]]);
            }
        }

        return dp[nums.length - 1][count];
    }

    /**
     * 背包问题：将数组分割成两个子集，使得两个子集的元素和相等。进行状态压缩
     * <p> 若可分割为两个子集，则子集大小恰好为 count / 2
     * <p> dp数组的大小为 (count / 2) + 1
     * <p> 反向遍历，防止刚刚计算过的结果影响当前结果（因为当前结果需要计算dp[j - nums[i]]）
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            // 非常重要：此处需要倒序遍历，每个数字只能用一次
            for (int j = sum; j > 0; j--) {
                if (!dp[j] && j - nums[i - 1] >= 0) {
                    dp[j] = dp[j - nums[i - 1]];
                }
            }
        }
        return dp[sum];
    }


    public ListNode reverseList(ListNode head) {
        if (head.next == null) {
            return head;
        }
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
        if (node == q) {
            return node;
        }
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

    /**
     * 是否为回文串
     *
     * <p>全局p指针从前向后移动，head指针作为递归参数从后向前移动
     *
     * @param head
     * @return
     */
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

    /**
     * 是否为回文串
     *
     * <p>快慢指针，slow指向中心，将后串翻转，再对比
     *
     * @param head
     * @return
     */
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
        if (inStart >= inEnd) {
            return null;
        }
        int val = preorder[index];
        TreeNode root = new TreeNode(val);
        int i = indexHash.get(val);
        root.left = getBuildTree(preorder, index + 1, inStart, i);
        root.right = getBuildTree(preorder, index + (i - inStart) + 1, i + 1, inEnd);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
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
                if (parent.left.right != null) {
                    parent.left = parent.left.right;
                } else {
                    parent.left = parent.left.left;
                }
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


    /**
     * 链表是否有环
     * <p>指向头结点法，把所有已经检查过的节点指向头结点，如果有环的话p.next.next也指向头结点
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        if (head.next == head) {
            return true;
        }
        ListNode p = head.next;
        head.next = head;
        ListNode q;
        while (p.next != null) {
            if (p.next.next == head) {
                return true;
            } else {
                q = p.next;
                p.next = head;
                p = q;
            }
        }
        return false;
    }


    /**
     * 链表是否有环
     * <p>快慢指针法，如果有环的话fast.next.next==slow
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


    /**
     * 链表找环，返回环的起点
     * <p>快慢指针法，如果有环的话fast.next.next==slow
     * <p>快慢指针相遇后，将慢针回拨至起点，再次相遇时即为环的起点。
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                slow = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }


    /**
     * HashMap
     * <p>
     * 单调栈：从顶部添加，被挡住的逐个删除
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
     * 单调栈：从顶部添加，被挡住的逐个删除
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
     * 单调栈：从顶部添加，被挡住的逐个删除
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
     * 单调队列：从尾部添加，被挡住的逐个删除
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int[] res = new int[nums.length - k + 1];
        int i = 0;
        int j = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        while (j < k) {
            while (!queue.isEmpty() && queue.peekLast() < nums[j]) {
                queue.removeLast();
            }
            queue.addLast(nums[j]);
            j++;
        }
        res[i] = queue.peekFirst();
        while (j < nums.length) {
            if (nums[i] == queue.peekFirst()) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && queue.peekLast() < nums[j]) {
                queue.removeLast();
            }
            queue.addLast(nums[j]);
            i++;
            res[i] = queue.peek();
            j++;
        }
        return res;
    }

    /**
     * 吃香蕉速度
     * <p>使用二分查找左边界
     * <p>查找到目标后right=mid，而非return
     * <p>mid=left+(right-left)/2 防止溢出
     * <p>left=mid+1,right=mid
     *
     * @param piles 每堆香蕉数量
     * @param h     总时间限制
     * @return 每小时最少吃多少根香蕉
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1;
        for (int pile : piles) {
            if (pile > right) {
                right = pile;
            }
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            int hours = 0;
            for (int pile : piles) {
                hours += pile / mid;
                if (pile % mid > 0) {
                    hours++;
                }
            }
            if (hours <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 最小船载
     * <p>使用二分查找左边界
     * <p>查找到目标后right=mid，而非return
     * <p>mid=left+(right-left)/2 防止溢出
     * <p>left=mid+1,right=mid
     *
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int left = 0;
        int right = 0;
        for (int weight : weights) {
            if (weight > left) {
                left = weight;
            }
            right += weight;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            int days = 0;
            int tmpWeight = 0;

            for (int weight : weights) {
                tmpWeight += weight;
                if (tmpWeight > mid) {
                    days++;
                    tmpWeight = weight;
                }
            }
            days++;
            if (days <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 双指针
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] res = new int[2];
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                break;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    /**
     * 翻转字符串 char[]
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        char tmp;
        while (left < right) {
            tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }

    /**
     * 删除链表倒数第n个节点
     * <p>双指针</p>
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newNode = new ListNode();
        newNode.next = head;
        ListNode left = newNode;
        ListNode right = newNode;
        while (right != null && n > 0) {
            right = right.next;
            n--;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return newNode.next;
    }

    /**
     * 最小覆盖子串
     * <p>滑动窗口</p>
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        int resLeft;
        int resRight;
        int left = 0;
        int right = 0;
        int[] count = new int[128];
        for (char a : ts) {
            count[a]++;
        }
        while (right < ss.length && !isSubStr(count)) {
            count[ss[right]]--;
            right++;
        }
        if (!isSubStr(count)) {
            return "";
        }
        while (isSubStr(count)) {
            count[ss[left]]++;
            left++;
        }
        resLeft = left - 1;
        resRight = right;
        while (right < ss.length) {
            while (right < ss.length && !isSubStr(count)) {
                count[ss[right]]--;
                right++;
            }
            while (isSubStr(count)) {
                count[ss[left]]++;
                left++;
            }
            if (right - left + 1 < resRight - resLeft) {
                resLeft = left - 1;
                resRight = right;
            }
        }

        return s.substring(resLeft, resRight);
    }

    private boolean isSubStr(int[] count) {
        for (int c : count) {
            if (c > 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * s2是否包含s1一个排列，只有小写字母
     * <p>滑动窗口</p>
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || "".equals(s1)) {
            return true;
        }
        if (s2.length() < s1.length()) {
            return false;
        }
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        int[] count = new int[128];
        for (char a : s1Chars) {
            count[a]++;
        }
        int left = 0;
        int right = 0;
        for (; right < s1Chars.length; right++) {
            count[s2Chars[right]]--;
        }
        if (isSubStr2(count)) {
            return true;
        }
        while (right < s2Chars.length) {
            count[s2Chars[right]]--;
            count[s2Chars[left]]++;
            left++;
            if (isSubStr2(count)) {
                return true;
            }
            right++;
        }
        return false;
    }

    private boolean isSubStr2(int[] count) {
        for (int i = 96; i < 123; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 寻找字母异位词，并返回索引
     * <p>滑动窗口</p>
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int[] count = new int[128];
        for (char a : pChars) {
            count[a]++;
        }
        int left = 0;
        int right = 0;
        for (; right < pChars.length; right++) {
            count[sChars[right]]--;
        }
        if (isSubStr2(count)) {
            res.add(left);
        }
        while (right < sChars.length) {
            count[sChars[right]]--;
            count[sChars[left]]++;
            left++;
            if (isSubStr2(count)) {
                res.add(left);
            }
            right++;
        }
        return res;
    }

    /**
     * 最长无重复子串
     * <p>滑动窗口</p>
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int left = 0;
        int right = 0;
        boolean[] flag = new boolean[128];
        char[] ss = s.toCharArray();
        while (right < ss.length && !flag[ss[right]]) {
            flag[ss[right]] = true;
            right++;
        }
        if (right == ss.length) {
            return right;
        }
        result = right;
        while (right < ss.length) {
            while (ss[left] != ss[right]) {
                flag[ss[left]] = false;
                left++;
            }
            left++;
            right++;
            while (right < ss.length && !flag[ss[right]]) {
                flag[ss[right]] = true;
                right++;
            }
            if (right - left > result) {
                result = right - left;
            }
        }
        return result;
    }

    /**
     * 去除重复字母，且保持其他字母顺序不变，且字典序最小。
     * <p>基本思想：从前到后找到s[i]>s[i+1]，将s[i]删除；
     * <p>1. 若栈中不存在当前元素，则将比自己大且后面还有的元素弹出，入栈；
     * <p>2. 若栈中存在此元素，则什么也不做
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        int[] flag = new int[128];
        boolean[] inStack = new boolean[128];
        for (int i = 0; i < s.length(); i++) {
            flag[s.charAt(i)]++;
        }
        LinkedList<Character> s1 = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            while (!s1.isEmpty() && s1.peek() > x && flag[s1.peek()] > 1) {
                inStack[s1.peek()] = false;
                s1.pop();
            }
            if (!inStack[x]) {
                s1.push(x);
                inStack[x] = true;
            }

        }

        while (!s1.isEmpty()) {
            sb.append(s1.pop());
        }
        return sb.reverse().toString();
    }

    /**
     * 原地删除有序数组重复元素
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            while (j < nums.length - 1 && nums[j + 1] == nums[j]) {
                j++;
            }
            nums[i] = nums[j];
            i++;
            j++;
        }
        return i;
    }

    /**
     * 移除有序链表重复元素
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }

    /**
     * 原地删除指定元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == val) {
                break;
            }
            i++;
        }
        int j = i + 1;
        while (j < nums.length) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        return i + 1;
    }

    /**
     * 将0移动到末尾
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                break;
            }
            i++;
        }
        int j = i + 1;
        while (j < nums.length) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        while (i < nums.length) {
            nums[i] = 0;
        }
    }

    /**
     * 两数之和为target
     *
     * <p>使用HashMap存储value到index的映射。
     * <p>重点在于初始化hashmap的过程中就进行判断，可以避免重复值的影响。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> indexToVal = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexToVal.containsKey(target - nums[i])) {
                return new int[]{indexToVal.get(target - nums[i]), i};
            }
            indexToVal.put(nums[i], i);
        }
        return new int[]{};
    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        int a = 0;
        int b = 1;
        int res = a + b;
        while (n > 1) {
            res = a + b;
            a = b;
            b = res;
            n--;
        }
        return res;
    }

    /**
     * 零钱兑换
     * <p>计算可以凑成总金额所需的最少的硬币个数,每种硬币的数量是无限的</p>
     * <p>动态规划，正向dp数组</p>
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = amount + 1;
        }
        for (int i = 1; i < amount + 1; i++) {
            // 非常重要：此处不能倒序遍历，因为每种硬币数量是无限的，所以可重复使用
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * 零钱兑换
     * <p>给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     * <p>动态规划，正向dp数组</p>
     *
     * @param amount
     * @param coins
     * @return
     */
    public int coinChange2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            // 非常重要：此处不能倒序遍历，因为每种硬币数量是无限的，所以可重复使用
            for (int j = 1; j <= amount; j++) {
                if (j - coin >= 0) {
                    dp[j] += dp[j - coin];
                }
            }
        }
        return dp[amount];
    }

    /**
     * 下降路径最下和
     * <p>动态规划</p>
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i - 1][j];
                if (j - 1 >= 0) {
                    tmp = Math.min(tmp, matrix[i - 1][j - 1]);
                }
                if (j + 1 < n) {
                    tmp = Math.min(tmp, matrix[i - 1][j + 1]);
                }
                matrix[i][j] = matrix[i][j] + tmp;
            }
        }
        int res = matrix[n - 1][0];
        for (int x : matrix[n - 1]) {
            res = Math.min(res, x);
        }
        return res;
    }

    /**
     * 目标和
     * <p>动态规划,递推公式</p>
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int minvalue = 1000;
        int[] dp = new int[2001];
        dp[nums[0] + minvalue] = 1;
        dp[-nums[0] + minvalue] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] tmp = new int[2001];
            for (int j = -1000; j < 1001; j++) {
                if (dp[j + minvalue] > 0) {
                    tmp[j - nums[i] + minvalue] += dp[j + minvalue];
                    tmp[j + nums[i] + minvalue] += dp[j + minvalue];
                }
            }
            dp = tmp;
        }
        return dp[target + minvalue];
    }

    /**
     * 目标和
     * <p>动态规划，背包问题，反向dp数组</p>
     * <p>动态规划问题还是要分清“选择”</p>
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if ((sum + target) % 2 != 0) {
            return 0;
        }
        target = (sum + target) / 2;

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            // 非常重要：此处需要倒序遍历，j>=0
            for (int j = target; j >= 0; j--) {
                if (j >= nums[i - 1]) {
                    dp[j] += dp[j - nums[i - 1]];
                }
            }
        }
        return dp[target];
    }

    /**
     * 最长公共子序列
     * <p>动态规划，dp数组，非递归，自底向上</p>
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int[][] dp = new int[t1.length + 1][t2.length + 1];
        for (int i = 1; i <= t1.length; i++) {
            for (int j = 1; j <= t2.length; j++) {
                if (t1[i - 1] == t2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[t1.length][t2.length];
    }

    /**
     * 最长公共子序列
     * <p>动态规划，dp函数，递归，备忘录，自顶向下</p>
     * <p>之所以是自顶向下，是因为递归算法的真正开始计算是在退栈的时候，所以虽然递归算法的推进是从0开始不断+1，
     * 但实际计算是在==length之后开始，所以相当于--，所以与非递归算法在t1[i] != t2[j]时是-1，而递归算法是+1</p>
     *
     * @param text1
     * @param text2
     * @return
     */
    int[][] memo;

    public int longestCommonSubsequence2(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        memo = new int[t1.length][t2.length];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return getLongestCommonSubsequence(t1, 0, t2, 0);
    }

    private int getLongestCommonSubsequence(char[] t1, int i, char[] t2, int j) {
        if (i == t1.length || j == t2.length) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (t1[i] == t2[j]) {
            memo[i][j] = getLongestCommonSubsequence(t1, i + 1, t2, j + 1) + 1;
        } else {
            memo[i][j] = Math.max(
                    getLongestCommonSubsequence(t1, i + 1, t2, j),
                    getLongestCommonSubsequence(t1, i, t2, j + 1));
        }
        return memo[i][j];
    }


    /**
     * 最长递增子序列
     * <p>动态规划</p>
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 非常重要：此处需要倒序遍历，j>=0
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i]++;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 最长递增子序列
     * <p>二分查找，耐心排序，扑克牌堆</p>
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] top = new int[nums.length];
        top[0] = nums[0];
        int p = 1;
        for (int x : nums) {
            int left = 0;
            int right = p - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] >= x) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            if (top[right] >= x) {
                top[right] = x;
            } else {
                top[p] = x;
                p++;
            }
        }
        return p;
    }


    /**
     * 俄罗斯套娃信封问题
     * <p>先排序：先按a[0]递增排序，a[0]相同时再按a[1]递减排序</p>
     * <P>后等同于最长递增子序列</P>
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        int[] dp = new int[envelopes.length];
        dp[0] = envelopes[0][1];
        int res = 1;
        for (int[] x : envelopes) {
            int left = 0;
            int right = res - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (dp[mid] >= x[1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (dp[right] >= x[1]) {
                dp[right] = x[1];
            } else {
                dp[res] = x[1];
                res++;
            }
        }
        return res;
    }

    /**
     * 连续最大和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0) {
                nums[i] += nums[i - 1];
            }
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    /**
     * 两个字符串的删除操作
     * <p>动态规划，递归，备忘录<p/>
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        memo = new int[w1.length][w2.length];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return getMinDistance(w1, 0, w2, 0);
    }

    private int getMinDistance(char[] w1, int i, char[] w2, int j) {
        if (i == w1.length) {
            return w2.length - j;
        }
        if (j == w2.length) {
            return w1.length - i;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (w1[i] == w2[j]) {
            memo[i][j] = getMinDistance(w1, i + 1, w2, j + 1);
        } else {
            memo[i][j] = Math.min(getMinDistance(w1, i + 1, w2, j), getMinDistance(w1, i, w2, j + 1)) + 1;
        }
        return memo[i][j];
    }

    /**
     * 两个字符串的最小ASCII删除和
     * <p>动态规划，递归，备忘录</p>
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
        char[] w1 = s1.toCharArray();
        char[] w2 = s2.toCharArray();
        memo = new int[w1.length][w2.length];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return getMinimumDeleteSum(w1, 0, w2, 0);
    }

    private int getMinimumDeleteSum(char[] w1, int i, char[] w2, int j) {
        if (i == w1.length) {
            int sum = 0;
            while (j < w2.length) {
                sum += w2[j];
                j++;
            }
            return sum;
        }
        if (j == w2.length) {
            int sum = 0;
            while (i < w1.length) {
                sum += w1[i];
                i++;
            }
            return sum;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (w1[i] == w2[j]) {
            memo[i][j] = getMinimumDeleteSum(w1, i + 1, w2, j + 1);
        } else {
            memo[i][j] = Math.min(getMinimumDeleteSum(w1, i + 1, w2, j) + w1[i], getMinimumDeleteSum(w1, i, w2, j + 1) + w2[j]);
        }
        return memo[i][j];
    }

    /**
     * 最长回文子序列
     * <p>动态规划，递归</p>
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        memo = new int[s.length()][s.length()];
        for (int[] x : memo) {
            Arrays.fill(x, -1);
        }
        return getLongestPalindromeSubseq(s.toCharArray(), 0, s.length() - 1);
    }

    private int getLongestPalindromeSubseq(char[] s, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s[i] == s[j]) {
            memo[i][j] = getLongestPalindromeSubseq(s, i + 1, j - 1) + 2;
        } else {
            memo[i][j] = Math.max(getLongestPalindromeSubseq(s, i + 1, j), getLongestPalindromeSubseq(s, i, j - 1));
        }
        return memo[i][j];
    }

    /**
     * 最长回文子序列
     * <p>动态规划，非递归，dp数组</p>
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq2(String s) {
        char[] chars = s.toCharArray();
        int[][] dp = new int[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = 1;
        }
        for (int i = chars.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][chars.length - 1];
    }

    /**
     * 无重叠区间，返回需要删除的最小区间数量
     * <p>排序,贪心</p>
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int res = 0;
        int right = intervals[0][0];
        for (int[] x : intervals) {
            if (x[0] >= right) {
                right = x[1];
            } else {
                res++;
            }
        }
        return res;
    }

    /**
     * 用最少数量的箭引爆气球
     * <p>排序,贪心</p>
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int res = 0;
        int right = points[0][0];
        for (int[] x : points) {
            if (x[0] > right) {
                res++;
            }
            right = x[1];
        }
        return res;
    }

    /**
     * 跳跃游戏
     * <p>经典贪心算法！！！记录可到达的最远位置即可</p>
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length - 1;
        int right = 0;
        // 此处条件，right>=n时可直接返回ture，i<=right才可到达
        for (int i = 0; right < n && i <= right; i++) {
            right = Math.max(right, nums[i] + i);
        }
        return right >= n;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.canPartition2(new int[]{3, 3, 3, 4, 5}));
    }
}
