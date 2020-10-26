#include <queue>
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution
{
public:
    int minDepth(TreeNode *root)
    {
        if (root == nullptr)
        {
            return 0;
        }

        std::queue<TreeNode *> q;                 
        q.push(root);
        TreeNode *b = root;
        TreeNode *p = root;
        int n = 1;
        while (!q.empty())
        {
            p = q.front();
            q.pop();
            if (p->left == nullptr && p->right == nullptr)
            {
                break;
            }
            if (p->left != nullptr)
            {
                q.push(p->left);
            }
            if (p->right != nullptr)
            {
                q.push(p->right);
            }

            if (p == b)
            {
                n += 1;
                b = q.back();
            }
        }
        return n;
    }

    int getDepth(TreeNode *root, int n)
    {

        if (root->left == nullptr && root->right == nullptr)
        {
            return n;
        }
        int a = 100000, b = 100000;
        if (root->left != nullptr)
        {
            a = getDepth(root->left, n + 1);
        }

        if (root->right != nullptr)
        {
            b = getDepth(root->right, n + 1);
        }

        return std::min(a, b);
    }

    int minDepth2(TreeNode *root)
    {
        if (root == nullptr)
        {
            return 0;
        }

        return getDepth(root, 1);
    }
};