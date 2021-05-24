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
                //物品i肯定能恰好装满重量为nums[i]的背包
                dp[i][nums[i]] = true;
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
        int i = removeElement(nums, 0) - 1;
        while (i < nums.length) {
            nums[i] = 0;
            i++;
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

    /**
     * 跳跃游戏 II
     * <p>贪心</p>
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        //可跳的最远位置
        int right = 0;
        //跳的步数
        int jumps = 0;
        //上一步可达的最远位置
        int end = 0;

        // 到达nums.length - 1就不再跳了，所以无需也不能计算nums.length - 1
        for (int i = 0; i < nums.length - 1; i++) {
            if (i + nums[i] > right) {
                right = i + nums[i];
            }
            if (i == end) {
                jumps++;
                end = right;
            }
        }
        return jumps;
    }

    /**
     * 最小路径和
     * <p>动态规划，递归，备忘录</p>
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        memo = new int[grid.length][grid[0].length];
        return getMinPathSum(grid, 0, 0);
    }

    private int getMinPathSum(int[][] grid, int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int val = grid[i][j];
        if (i != grid.length - 1 && j != grid[0].length - 1) {
            memo[i][j] = val + Math.min(getMinPathSum(grid, i + 1, j), getMinPathSum(grid, i, j + 1));
        } else if (i != grid.length - 1) {
            memo[i][j] = val + getMinPathSum(grid, i + 1, j);
        } else if (j != grid[0].length - 1) {
            memo[i][j] = val + getMinPathSum(grid, i, j + 1);
        } else {
            memo[i][j] = val;
        }
        return memo[i][j];
    }

    /**
     * 最小路径和
     * <p>动态规划，非递归，左上角正序</p>
     *
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 地下城游戏
     * <p>动态规划，右下角倒序</p>
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        if (dungeon[m - 1][n - 1] < 0) {
            dp[m - 1][n - 1] = 1 - dungeon[m - 1][n - 1];
        } else {
            dp[m - 1][n - 1] = 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            int need = dungeon[i][n - 1] - dp[i + 1][n - 1];
            if (need < 0) {
                dp[i][n - 1] = -need;
            } else {
                dp[i][n - 1] = 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            int need = dungeon[m - 1][i] - dp[m - 1][i + 1];
            if (need < 0) {
                dp[m - 1][i] = -need;
            } else {
                dp[m - 1][i] = 1;
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int need = dungeon[i][j] - Math.min(dp[i + 1][j], dp[i][j + 1]);
                if (need < 0) {
                    dp[i][j] = -need;
                } else {
                    dp[i][j] = 1;
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 地下城游戏
     * <p>动态规划，递归，备忘录</p>
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP2(int[][] dungeon) {
        this.memo = new int[dungeon.length][dungeon[0].length];
        return getCalculateMinimumHP2(dungeon, 0, 0);
    }

    private int getCalculateMinimumHP2(int[][] dungeon, int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int next = 1;
        if (i != dungeon.length - 1 && j != dungeon[0].length - 1) {
            next = Math.min(getCalculateMinimumHP2(dungeon, i + 1, j), getCalculateMinimumHP2(dungeon, i, j + 1));
        } else if (i != dungeon.length - 1) {
            next = getCalculateMinimumHP2(dungeon, i + 1, j);
        } else if (j != dungeon[0].length - 1) {
            next = getCalculateMinimumHP2(dungeon, i, j + 1);
        }
        int need = dungeon[i][j] - next;
        if (need < 0) {
            memo[i][j] = -need;
        } else {
            memo[i][j] = 1;
        }
        return memo[i][j];
    }

    /**
     * 自由之路
     * <p>动态规划，递归</p>
     */
    char[] rings;
    char[] keys;
    HashMap<Character, ArrayList<Integer>> charToIndex;

    public int findRotateSteps(String ring, String key) {
        this.rings = ring.toCharArray();
        this.keys = key.toCharArray();
        this.memo = new int[rings.length][keys.length];
        this.charToIndex = new HashMap<>();
        for (int[] x : memo) {
            Arrays.fill(x, -1);
        }
        for (int i = 0; i < rings.length; i++) {
            charToIndex.computeIfAbsent(rings[i], k -> new ArrayList<>());
            charToIndex.get(rings[i]).add(i);
        }
        return getFindRotateSteps(0, 0);
    }

    private int getFindRotateSteps(int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int minSteps = Integer.MAX_VALUE;
        ArrayList<Integer> targetIndex = charToIndex.get(keys[j]);

        if (j == keys.length - 1) {
            for (Integer index : targetIndex) {
                int dis = findRotateStepsDistance(i, index);
                if (dis < minSteps) {
                    minSteps = dis;
                }
            }
            memo[i][j] = minSteps + 1;
            return memo[i][j];
        }

        for (Integer index : targetIndex) {
            int dis = findRotateStepsDistance(i, index) + getFindRotateSteps(index, j + 1);
            if (dis < minSteps) {
                minSteps = dis;
            }
        }

        memo[i][j] = minSteps + 1;
        return memo[i][j];
    }

    private int findRotateStepsDistance(int i, int j) {
        return Math.min((i - j + rings.length) % rings.length, (j - i + rings.length) % rings.length);
    }


    /**
     * 正则表达式匹配
     * <p>递归，备忘录</p>
     * <p>重点在于对*的处理，需要遍历匹配0次到多次的情况</p>
     */
    char[] sChars;
    char[] pChars;

    public boolean isMatch(String s, String p) {
        this.sChars = s.toCharArray();
        this.pChars = p.toCharArray();
        this.memo = new int[sChars.length + 1][pChars.length + 1];
        return getIsMatch(0, 0) == 1;
    }

    private int getIsMatch(int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (i == sChars.length || j == pChars.length) {
            if (i != sChars.length) {
                return 2;
            }
            while (j + 1 < pChars.length && pChars[j + 1] == '*') {
                j += 2;
            }
            return j == pChars.length ? 1 : 2;
        }
        if (j + 1 < pChars.length && pChars[j + 1] == '*') {
            memo[i][j] = getIsMatch(i, j + 2);
            if (memo[i][j] == 2 && (sChars[i] == pChars[j] || pChars[j] == '.')) {
                memo[i][j] = getIsMatch(i + 1, j);
            }
        } else if (sChars[i] == pChars[j] || pChars[j] == '.') {
            memo[i][j] = getIsMatch(i + 1, j + 1);
        } else {
            memo[i][j] = 2;
        }
        return memo[i][j];
    }

    /**
     * 鸡蛋掉落
     * <p>最基本的方法：动态规划，递归，备忘录。超时！</p>
     * <p>可行的方法：动态规划，递归，备忘录，二分搜索。</p>
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop(int k, int n) {
        this.memo = new int[k + 1][n + 1];
        return getSuperEggDrop(k, n);
    }

    private int getSuperEggDrop(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        if (memo[k][n] != 0) {
            return memo[k][n];
        }
        int left = 1;
        int right = n;
        int steps = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            // 当前层碎了，鸡蛋-1，向下搜索
            int a = getSuperEggDrop(k - 1, mid - 1);
            // 当前层没碎，鸡蛋数不变，向上搜索
            int b = getSuperEggDrop(k, n - mid);
            if (a > b) {
                right = mid - 1;
                steps = Math.min(steps, a + 1);
            } else {
                left = mid + 1;
                steps = Math.min(steps, b + 1);
            }
        }
        memo[k][n] = steps;
        return memo[k][n];
    }

    /**
     * 鸡蛋掉落
     * <p>高级方法：动态规划，dp数组。</p>
     * <p>dp[k][m] = n，当前有 k 个鸡蛋，可以尝试扔 m 次鸡蛋，最坏情况下最多能确切测试一栋 n 层的楼。</p>
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop2(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        int i = 0;
        while (dp[k][i] < n) {
            i++;
            for (int j = 1; j <= k; j++) {
                dp[j][i] = dp[j][i - 1] + dp[j - 1][i - 1] + 1;
            }
        }
        return i;
    }

    /**
     * 鸡蛋掉落
     * <p>高级方法：动态规划，dp数组，状态压缩。</p>
     * <p>dp[k] = n，当前有 k 个鸡蛋，可以尝试扔"当前次"(下面代码中为i次)鸡蛋，最坏情况下最多能确切测试一栋 n 层的楼。</p>
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop3(int k, int n) {
        int[] dp = new int[k + 1];
        int i = 0;
        while (dp[k] < n) {
            i++;
            for (int j = k; j > 0; j--) {
                dp[j] = dp[j] + dp[j - 1] + 1;
            }
        }
        return i;
    }

    /**
     * 戳气球
     * <p>递归，备忘录，两侧添加虚拟气球</p>
     */
    int[] nums;

    public int maxCoins(int[] nums) {
        this.memo = new int[nums.length + 2][nums.length + 2];
        for (int[] x : memo) {
            Arrays.fill(x, -1);
        }
        this.nums = new int[nums.length + 2];
        this.nums[0] = 1;
        this.nums[nums.length + 1] = 1;
        System.arraycopy(nums, 0, this.nums, 1, nums.length);
        return getMaxCoins(0, nums.length + 1);
    }

    private int getMaxCoins(int i, int j) {
        int val = nums[i] * nums[j];
        if (i + 2 == j) {
            return val * nums[i + 1];
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int maxVal = 0;
        // 最后戳破气球k时最大
        for (int k = i + 1; k < j; k++) {
            int tmpVal = getMaxCoins(i, k) + getMaxCoins(k, j) + val * nums[k];
            if (tmpVal > maxVal) {
                maxVal = tmpVal;
            }
        }
        memo[i][j] = maxVal;
        return maxVal;
    }

    /**
     * 戳气球
     * <p>非递归，dp数组，两侧添加虚拟气球，完全根据上面的递归方法改写</p>
     */
    public int maxCoins2(int[] nums) {
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        int[] newNums = new int[nums.length + 2];
        newNums[0] = 1;
        newNums[nums.length + 1] = 1;
        for (int i = 0; i < nums.length; i++) {
            newNums[i + 1] = nums[i];
        }
        for (int i = nums.length; i >= 0; i--) {
            for (int j = i + 1; j <= nums.length + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    // i<k<j, 所以dp[i][k]在dp[i][j]同一行左侧，dp[k][j]在dp[i][j]同一列下侧
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNums[i] * newNums[k] * newNums[j]);
                }
            }
        }
        return dp[0][nums.length + 1];
    }

    /**
     * 石子游戏
     * <p>自己想的方法：递归，每次统计两个人的选择结果，返回先手优势最大的分数</p>
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        this.memo = new int[piles.length][piles.length];
        for (int[] x : memo) {
            Arrays.fill(x, Integer.MAX_VALUE);
        }
        return getStoneGame(piles, 0, piles.length - 1) > 0;
    }

    private int getStoneGame(int[] piles, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }
        int a = Math.abs(piles[i] - piles[j]) + getStoneGame(piles, i + 1, j - 1);
        int b = piles[i] - piles[i + 1] + getStoneGame(piles, i + 2, j);
        int c = piles[j] - piles[j - 1] + getStoneGame(piles, i, j - 2);
        memo[i][j] = Math.max(a, Math.max(b, c));
        return memo[i][j];
    }

    /**
     * 石子游戏
     * <p>自己想的方法：dp数组，每次统计两个人的选择结果，返回先手优势最大的分数。完全根据上面的递归修改</p>
     *
     * @param piles
     * @return
     */
    public boolean stoneGame2(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];

        for (int i = piles.length - 3; i >= 0; i--) {
            for (int j = 2; j < piles.length; j++) {
                int a = Math.abs(piles[i] - piles[j]) + dp[i + 1][j - 1];
                int b = piles[i] - piles[i + 1] + dp[i + 2][j];
                int c = piles[j] - piles[j - 1] + dp[i][j - 2];
                dp[i][j] = Math.max(a, Math.max(b, c));
            }
        }
        return dp[0][piles.length - 1] > 0;
    }

    /**
     * 石子游戏
     * <p>官方解法：dp数组，每次统计一个人的选择结果，返回石子数量之差。还可继续压缩状态</p>
     *
     * @param piles
     * @return
     */
    public boolean stoneGame3(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] > 0;
    }


    /**
     * 搜索旋转排序数组
     * <p>无重复元素，关键：确定[i,j]之间的有序部分，其他不要多想</p>
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length;
        if (target == nums[0]) {
            return 0;
        }
        if (target == nums[nums.length - 1]) {
            return nums.length - 1;
        }
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > nums[0]) {
                if (target > nums[0] && target < nums[mid]) {
                    j = mid;
                } else {
                    i = mid + 1;
                }
            } else {
                if (target < nums[0] && target > nums[mid]) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
        }
        return -1;
    }

    /**
     * 搜索旋转排序数组 II
     * <p>有重复元素，当nums[mid]==nums[i]==nums[j]时无法判断有序部分，i++,j--</p>
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search2(int[] nums, int target) {
        int i = 0;
        int j = nums.length;
        if (target == nums[0] || target == nums[nums.length - 1]) {
            return true;
        }

        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[i] == nums[mid] && nums[i] == nums[j - 1]) {
                i++;
                j--;
            } else if (nums[i] <= nums[mid]) {
                // 左侧有序
                if (target >= nums[i] && target < nums[mid]) {
                    j = mid;
                } else {
                    i = mid + 1;
                }
            } else {
                // 右侧有序
                if (target <= nums[j - 1] && target > nums[mid]) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
        }
        return false;
    }

    /**
     * 买卖股票的最佳时机，一次
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int minPrice = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - minPrice > max) {
                max = prices[i] - minPrice;
            } else if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
        }
        return max;
    }

    /**
     * 买卖股票的最佳时机，无限次
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int profit = 0;
        int i = 0;
        while (i < prices.length) {
            while (i + 1 < prices.length && prices[i + 1] <= prices[i]) {
                i++;
            }
            int j = i + 1;
            while (j + 1 < prices.length && prices[j + 1] >= prices[j]) {
                j++;
            }
            if (i < prices.length && j < prices.length) {
                profit += prices[j] - prices[i];
            }
            i = j + 1;
        }
        return profit;
    }

    /**
     * 买卖股票的最佳时机，两次
     * <p>
     * base case：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = dp[i][0][1] = -infinity
     * <p>
     * 状态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     * @param prices
     * @return
     */

    public int maxProfit30(int[] prices) {
        int[][][] dp = new int[prices.length][3][2];
        dp[0][1][1] = -prices[0];
        dp[0][2][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], -prices[i]);

            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }
        return dp[prices.length - 1][2][0];
    }

    public int maxProfit31(int[] prices) {
        int dpi10 = 0;
        int dpi11 = -prices[0];
        int dpi20 = 0;
        int dpi21 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int tmp = dpi10;
            dpi10 = Math.max(dpi10, dpi11 + prices[i]);
            dpi11 = Math.max(dpi11, -prices[i]);
            dpi20 = Math.max(dpi20, dpi21 + prices[i]);
            dpi21 = Math.max(dpi21, tmp - prices[i]);
        }
        return dpi20;
    }

    /**
     * 买卖股票的最佳时机，K次
     * <p>
     * 状态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int k, int[] prices) {
        if (prices.length < 2 || k < 1) {
            return 0;
        }
        int[][][] dp = new int[prices.length][k][2];

        // base case: i==0时（即第一天时），若持有股票只能是-prices[0]
        for (int j = 0; j < k; j++) {
            dp[0][j][1] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                if (j == 0) {
                    // base case: dp[i>0][0][1]时(非第一天，第一次买卖，且持有)
                    dp[i][0][1] = Math.max(dp[i - 1][0][1], -prices[i]);
                } else {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }
        return dp[prices.length - 1][k - 1][0];
    }

    /**
     * 买卖股票的最佳时机+冷冻期，无限次
     *
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int dpi0 = Math.max(0, prices[1] - prices[0]);
        int dpi1 = Math.max(-prices[0], -prices[1]);
        int dpi20 = 0;
        for (int i = 2; i < prices.length; i++) {
            int tmp = dpi0;
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);

            // 即dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            dpi1 = Math.max(dpi1, dpi20 - prices[i]);

            dpi20 = tmp;
        }
        return dpi0;
    }

    /**
     * 买卖股票的最佳时机+手续费，无限次
     *
     * @param prices
     * @return
     */
    public int maxProfit6(int[] prices, int fee) {
        if (prices.length < 2) {
            return 0;
        }

        int dpi0 = 0;
        int dpi1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dpi0 = Math.max(dpi0, dpi1 + prices[i] - fee);
            dpi1 = Math.max(dpi1, dpi0 - prices[i]);
        }
        return dpi0;
    }

    /**
     * 打家劫舍，相连房间触发警报
     * <p>
     * dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length < 2) {
            return nums[0];
        }

        int a = nums[0];
        int b = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int tmp = b;
            b = Math.max(b, a + nums[i]);
            a = tmp;
        }
        return b;
    }

    /**
     * 打家劫舍 II，环形
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length < 2) {
            return nums[0];
        }
        int res;

        int a = nums[0];
        int b = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length - 1; i++) {
            int tmp = b;
            b = Math.max(b, a + nums[i]);
            a = tmp;
        }
        res = b;

        a = 0;
        b = nums[1];
        for (int i = 2; i < nums.length; i++) {
            int tmp = b;
            b = Math.max(b, a + nums[i]);
            a = tmp;
        }
        res = Math.max(b, res);

        return res;
    }

    /**
     * 打家劫舍 Ⅲ，树形
     * <p>返回二元组，表示选择与非选择</p>
     */
    public int rob3(TreeNode root) {
        int[] res = getRob3(root);
        return Math.max(res[0], res[1]);
    }

    private int[] getRob3(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] a = getRob3(node.left);
        int[] b = getRob3(node.right);
        return new int[]{Math.max(a[0], a[1]) + Math.max(b[0], b[1]), node.val + a[0] + b[0]};
    }

    /**
     * 实现 strStr()
     * <p>KMP算法</p>
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        char[] haystackChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();
        int[] pat = new int[needleChars.length];
        int i;
        int j;
        for (i = 1, j = 0; i < needleChars.length; i++) {
            while (j > 0 && needleChars[i] != needleChars[j]) {
                j = pat[j - 1];
            }
            if (needleChars[i] == needleChars[j]) {
                j++;
            }
            pat[i] = j;
        }
        for (i = 0, j = 0; i < haystackChars.length && j < needleChars.length; i++) {
            while (j > 0 && haystackChars[i] != needleChars[j]) {
                j = pat[j - 1];
            }

            // 到达这个地方只有两种情况：1. haystackChars[i] == needleChars[j] 2. haystackChars[i] != needleChars[j],j==0
            // haystackChars[i] == needleChars[j]时，无论j是否为0是肯定要+1的，因为该字符已经匹配上了，所以匹配下一个字符
            // j=0时还不相等，haystackChars[i]就没有比较的必要了，所以下一个字符从头比较，j又恰好为0，所以无需处理
            if (haystackChars[i] == needleChars[j]) {
                j++;
            }
        }
        if (j == needleChars.length) {
            return i - needleChars.length;
        } else {
            return -1;
        }
    }

    /**
     * 让字符串成为回文串的最少插入次数
     * <p>递归，备忘录</p>
     */
    char[] ss;

    public int minInsertions(String s) {
        this.ss = s.toCharArray();
        this.memo = new int[ss.length][ss.length];
        return getMinInsertions(0, ss.length - 1);
    }

    private int getMinInsertions(int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (ss[i] == ss[j]) {
            memo[i][j] = getMinInsertions(i + 1, j - 1);
        } else {
            memo[i][j] = Math.min(getMinInsertions(i + 1, j), getMinInsertions(i, j - 1)) + 1;
        }

        return memo[i][j];
    }

    /**
     * 让字符串成为回文串的最少插入次数
     * <p>dp数组</p>
     */
    public int minInsertions2(String s) {
        char[] ss = s.toCharArray();
        int[][] dp = new int[ss.length][ss.length];
        for (int i = ss.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < ss.length; j++) {
                if (ss[i] == ss[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][ss.length - 1];
    }

    /**
     * 全排列
     * <p>回溯，标记数组，保序</p>
     *
     * @param nums
     * @return
     */
    List<List<Integer>> res;
    boolean[] flag;

    public List<List<Integer>> permute(int[] nums) {
        this.res = new LinkedList<>();
        this.flag = new boolean[nums.length];
        LinkedList<Integer> track = new LinkedList<>();
        getPermute(nums, track);
        return res;
    }

    private void getPermute(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!flag[i]) {
                track.addLast(nums[i]);
                flag[i] = true;
                getPermute(nums, track);
                track.removeLast();
                flag[i] = false;
            }
        }
    }

    /**
     * 全排列
     * <p>回溯，交换，不保序</p>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        this.res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        for (int x : nums) {
            track.add(x);
        }
        getPermute2(0, track);
        return res;
    }

    private void getPermute2(int i, LinkedList<Integer> track) {
        if (track.size() == i) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int j = i; j < track.size(); j++) {
            Collections.swap(track, i, j);
            getPermute2(i + 1, track);
            Collections.swap(track, i, j);
        }
    }

    /**
     * N 皇后
     *
     * @param n
     * @return
     */
    List<List<String>> queens;
    int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        this.queens = new ArrayList<>();
        this.flag = new boolean[n];
        getSolveNQueens(new ArrayList<>());
        return queens;
    }

    private void getSolveNQueens(ArrayList<String> queen) {
        if (queen.size() == n) {
            queens.add(new ArrayList<>(queen));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        for (int i = 0; i < n; i++) {
            if (canSetQueen(i, queen)) {
                sb.setCharAt(i, 'Q');
                flag[i] = true;
                queen.add(sb.toString());
                getSolveNQueens(queen);
                sb.setCharAt(i, '.');
                flag[i] = false;
                queen.remove(queen.size() - 1);
            }
        }
    }

    private boolean canSetQueen(int i, ArrayList<String> queen) {
        if (flag[i]) {
            return false;
        }
        int p = queen.size() - 1;
        int q = i + 1;
        while (p >= 0 && q < n) {
            if (queen.get(p).charAt(q) == 'Q') {
                return false;
            }
            p--;
            q++;
        }

        p = queen.size() - 1;
        q = i - 1;
        while (p >= 0 && q >= 0) {
            if (queen.get(p).charAt(q) == 'Q') {
                return false;
            }
            p--;
            q--;
        }
        return true;
    }

    /**
     * 划分为k个相等的子集
     * <p>官方解（错误）</p>
     */
    boolean search(int used, int todo, Result[] memo, int[] nums, int target) {
        if (memo[used] == null) {
            memo[used] = Result.FALSE;
            int targ = (todo - 1) % target + 1;
            for (int i = 0; i < nums.length; i++) {
                if ((((used >> i) & 1) == 0) && nums[i] <= targ) {
                    if (search(used | (1 << i), todo - nums[i], memo, nums, target)) {
                        memo[used] = Result.TRUE;
                        break;
                    }
                }
            }
        }
        return memo[used] == Result.TRUE;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;

        Result[] memo = new Result[1 << nums.length];
        memo[(1 << nums.length) - 1] = Result.TRUE;
        return search(0, sum, memo, nums, sum / k);
    }

    /**
     * 划分为k个相等的子集
     * <p>题解</p>
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets2(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;
        // 排序
        Arrays.sort(nums);

        int row = nums.length - 1;

        if (nums[row] > target) return false;

        return canPartitionKSubsetDFS(new int[k], row, nums, target);

    }

    public boolean canPartitionKSubsetDFS(int[] groups, int row, int[] nums, int target) {
        if (row < 0) {
            return true;
        }
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + nums[row] <= target) {
                groups[i] += nums[row];
                if (canPartitionKSubsetDFS(groups, row - 1, nums, target)) {
                    return true;
                }
                groups[i] -= nums[row];
            }
            // 注意
            if (groups[i] == 0) break;
        }

        return false;
    }

    /**
     * 子集
     * <p>旧子集添加新元素组成新的子集</p>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new LinkedList<>());
        for (int num : nums) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                LinkedList<Integer> newSubSet = new LinkedList<>(res.get(j));
                newSubSet.add(num);
                res.add(newSubSet);
            }
        }
        return res;
    }

    /**
     * 子集
     * <p>回溯，递归</p>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        res = new ArrayList<>();
        getSubsets(0, nums, new LinkedList<>());
        return res;
    }

    private void getSubsets(int i, int[] nums, LinkedList<Integer> track) {
        if (i == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        getSubsets(i + 1, nums, track);
        track.add(nums[i]);
        getSubsets(i + 1, nums, track);
        track.removeLast();
    }


    /**
     * [1..n]的所有k个数的组合
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        this.res = new ArrayList<>();
        getCombine(n, k, 1, new LinkedList<>());
        return res;
    }

    private void getCombine(int n, int k, int i, LinkedList<Integer> track) {
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }
        if (i > n || track.size() + (n - i) + 1 < k) {
            return;
        }

        track.addLast(i);
        getCombine(n, k, i + 1, track);
        track.removeLast();
        getCombine(n, k, i + 1, track);
    }


    /**
     * 数独
     * <p>递归，回溯</p>
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        getSolveSudoku(board, 0);
    }

    private boolean getSolveSudoku(char[][] board, int preI) {
        int i = preI;
        int j = 0;
        for (; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    break;
                }
            }
            if (j < 9 && board[i][j] == '.') {
                break;
            }
        }

        if (i == 9 && j == 9) {
            return true;
        }
        for (int k = '1'; k <= '9'; k++) {
            if (canSetSudoku(k, i, j, board)) {
                board[i][j] = (char) k;
                if (!getSolveSudoku(board, i)) {
                    board[i][j] = '.';
                } else {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean canSetSudoku(int k, int i, int j, char[][] board) {
        for (int l = 0; l < 9; l++) {
            if (board[i][l] == k || board[l][j] == k) {
                return false;
            }
        }

        int p = i;
        int q;
        while (p % 3 != 0) {
            p--;
            q = j;
            if (board[p][q] == k) {
                return false;
            }
            while (q % 3 != 0) {
                q--;
                if (board[p][q] == k) {
                    return false;
                }
            }
            q = j + 1;
            while (q % 3 != 0) {
                if (board[p][q] == k) {
                    return false;
                }
                q++;
            }
        }
        p = i + 1;
        while (p % 3 != 0) {
            q = j;
            if (board[p][q] == k) {
                return false;
            }
            while (q % 3 != 0) {
                q--;
                if (board[p][q] == k) {
                    return false;
                }
            }
            q = j + 1;
            while (q % 3 != 0) {
                if (board[p][q] == k) {
                    return false;
                }
                q++;
            }
            p++;
        }

        return true;
    }


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

    /**
     * 二叉树最小深度
     * <p>广度优先遍历BFS，遇到叶子返回结果</p>
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        TreeNode last = root;
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.pollFirst();
            if (tmp.left == null && tmp.right == null) {
                res++;
                return res;
            }
            if (tmp.left != null) {
                queue.addLast(tmp.left);
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            }
            if (tmp == last) {
                res++;
                last = queue.peekLast();
            }
        }
        return res;
    }


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


    /**
     * 滑动谜题
     * <p>DFS，数组表示，慢。建议用字符串。</p>
     * <p>预先处理并记录状态转移矩阵</p>
     *
     * @param board
     * @return
     */
    public int slidingPuzzle(int[][] board) {
        ArrayList<Integer> oneBoard = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            oneBoard.add(board[0][j]);
        }
        for (int j = 0; j < 3; j++) {
            oneBoard.add(board[1][j]);
        }
        LinkedList<ArrayList<Integer>> queue = new LinkedList<>();
        queue.add(oneBoard);

        ArrayList<ArrayList<Integer>> mapping = new ArrayList<>();
        mapping.add(new ArrayList<>(Arrays.asList(1, 3)));
        mapping.add(new ArrayList<>(Arrays.asList(0, 2, 4)));
        mapping.add(new ArrayList<>(Arrays.asList(1, 5)));
        mapping.add(new ArrayList<>(Arrays.asList(0, 4)));
        mapping.add(new ArrayList<>(Arrays.asList(1, 3, 5)));
        mapping.add(new ArrayList<>(Arrays.asList(2, 4)));

        ArrayList<ArrayList<Integer>> visited = new ArrayList<>();
        visited.add(oneBoard);

        int res = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> aBoard = queue.pollFirst();
                if (isPuzzle(aBoard)) {
                    return res;
                }
                int zero = zeroIndex(aBoard);
                for (int x : mapping.get(zero)) {
                    ArrayList<Integer> tmp = new ArrayList<>(aBoard);
                    Collections.swap(tmp, zero, x);
                    if (!containPuzzle(visited, tmp)) {
                        queue.addLast(tmp);
                        visited.add(tmp);
                    }
                }
            }
            res++;
        }

        return -1;
    }

    private boolean isPuzzle(ArrayList<Integer> oneBoard) {
        for (int i = 0; i < 5; i++) {
            if (oneBoard.get(i) != i + 1) {
                return false;
            }
        }
        return oneBoard.get(5) == 0;
    }

    private int zeroIndex(ArrayList<Integer> aBoard) {
        for (int i = 0; i < 6; i++) {
            if (aBoard.get(i) == 0) {
                return i;
            }
        }
        return 0;
    }

    private boolean containPuzzle(ArrayList<ArrayList<Integer>> visited, ArrayList<Integer> a) {
        for (ArrayList<Integer> x : visited) {
            if (equalPuzzle(x, a)) {
                return true;
            }
        }
        return false;
    }

    private boolean equalPuzzle(ArrayList<Integer> a, ArrayList<Integer> b) {
        for (int i = 0; i < 6; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 位1的个数
     * <p>n&(n-1)可消除n的最后一位1
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n = n & (n - 1);
        }
        return res;
    }

    /**
     * 2的幂数
     * <p>n&(n-1)可消除n的最后一位1
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

    /**
     * 只出现一次的数字
     * <p>a^a=0, a^0=a</p>
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int x : nums) {
            res ^= x;
        }
        return res;
    }

    /**
     * 两个只出现一次的数字
     * <p>对整个数组求异或值count，利用count为1的位，对数组进行分组。</p>
     * <p>分组结果可以满足：1. 相同数字在同一组；2.两个只出现一次的数字在不同组。</p>
     *
     * @param nums
     * @return
     */
    public int[] singleNumber2(int[] nums) {
        int count = 0;
        for (int x : nums) {
            count ^= x;
        }
        int i = 1;
        for (int j = 0; j < 32; j++) {
            if ((count & i) > 0) {
                break;
            }
            i = i << 1;
        }
        int res1 = 0;
        int res2 = 0;
        for (int x : nums) {
            if ((x & i) == 0) {
                res1 ^= x;
            } else {
                res2 ^= x;
            }
        }
        return new int[]{res1, res2};
    }

    /**
     * 阶乘后的零
     * <p>阶乘后的零的个数与因子5的个数有关。</p>
     * <p>fives = n/5 + n/25 + n/125 + n/625 + ... + 0</p>
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int res = 0;
        int i = 5;
        while (i <= n) {
            res += n / i;
            i *= 5;
        }
        return res;
    }

    public int trailingZeroes2(int n) {
        if (n == 0) {
            return 0;
        }
        return (n / 5) + trailingZeroes2(n / 5);
    }

    /**
     * 阶乘函数后 K 个零
     * <p>二分法</p>
     *
     * @param k
     * @return
     */
    public int preimageSizeFZF(long k) {
        return (int) (getPreimageSizeFZFRight(k) - getPreimageSizeFZFLeft(k));
    }

    private long getPreimageSizeFZFLeft(long k) {
        long left = 0;
        long right = Long.MAX_VALUE;
        while (left < right) {
            long mid = left + (right - left) / 2;
            long n = trailingZeroesLong(mid);
            if (n < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private long getPreimageSizeFZFRight(long k) {
        long left = 0;
        long right = Long.MAX_VALUE;
        while (left < right) {
            long mid = left + (right - left) / 2;
            long n = trailingZeroesLong(mid);
            if (n > k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public long trailingZeroesLong(long n) {
        long res = 0;
        long i = 5;
        while (i <= n) {
            res += n / i;
            i *= 5;
        }
        return res;
    }


    /**
     * 小于n的质数的个数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (!notPrime[i]) {
                ans += 1;
                if ((long) i * i < n) {
                    // 从x*x开始,而不是从2x开始
                    for (int j = i * i; j < n; j += i) {
                        notPrime[j] = true;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 超级次方
     * <p>递归</p>
     * <p>(a*b)%k=(a%k)*(b%k)%k</p>
     *
     * @param a
     * @param b
     * @return
     */
    public int superPow(int a, int[] b) {
        return getSuperPow(a, b, b.length - 1);
    }

    private int getSuperPow(int a, int[] b, int i) {
        if (i < 0) {
            return 1;
        }
        int p1 = myPow(a, b[i]);
        int p2 = myPow(getSuperPow(a, b, i - 1), 10);
        return (p1 * p2) % 1337;
    }

    /**
     * 快速求幂思想，取模
     * <p>a^b=(a^(b/2))^2, b为偶数</p>
     * <p>a^b=a*a^(b-1), b为奇数</p>
     *
     * @param a
     * @param k
     * @return
     */
    private int myPow(int a, int k) {
        if (k == 0) {
            return 1;
        }
        if (k % 2 == 0) {
            int sub = myPow(a, k / 2);
            return (sub * sub) % 1337;
        } else {
            return ((a % 1337) * myPow(a, k - 1)) % 1337;
        }
    }

    /**
     * 超级次方
     * <p>非递归</p>
     * <p>(a*b)%k=(a%k)*(b%k)%k</p>
     *
     * @param a
     * @param b
     * @return
     */
    public int superPow2(int a, int[] b) {
        int res = 1;
        for (int j : b) {
            res = myPow(res, 10) * myPow(a, j) % 1337;
        }
        return res;
    }

    /**
     * 找到所有数组中消失的数字
     * <p>利用原数组及其索引做 hash 表，用数组长度做标记</p>
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int x : nums) {
            nums[(x - 1) % n] += n;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }


    /**
     * 错误的集合
     * <p>利用原数组及其索引做 hash 表，用数组长度做标记</p>
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int a = 0;
        int b = 0;
        int n = nums.length;
        for (int x : nums) {
            nums[(x - 1) % n] += n;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                a = i + 1;
            } else if (nums[i] > 2 * n) {
                b = i + 1;
            }
        }
        return new int[]{b, a};
    }

    /**
     * Nim 游戏
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    /**
     * 和为K的子数组（中等）
     * <p>HashMap记录前缀和及其数量</p>
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> subSums = new HashMap<>();
        subSums.put(0, 1);
        int res = 0;
        for (int num : nums) {
            count += num;
            res += subSums.getOrDefault(count - k, 0);
            subSums.put(count, subSums.getOrDefault(count, 0) + 1);
        }
        return res;
    }

    /**
     * 航班预订统计
     * <p>差分数组</p>
     * <p>diff[i] 就是 nums[i] 和 nums[i-1] 之差</p>
     *
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        int[] diff = new int[n + 1];

        for (int[] x : bookings) {
            diff[x[0] - 1] += x[2];
            diff[x[1]] -= x[2];
        }
        res[0] = diff[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + diff[i];
        }

        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
