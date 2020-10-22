#include <iostream>
#include <vector>

class Solution
{
public:
    int run_fib(std::vector<int> &memo, int n)
    {
        if (memo[n] != 0)
        {
            return memo[n];
        }
        if (n == 1 || n == 2)
        {
            return 1;
        }
        memo[n] = run_fib(memo, n - 1) + run_fib(memo, n - 2);
        return memo[n];
    }

    int fib(int N)
    {
        if (N < 1)
        {
            return 0;
        }
        std::vector<int> memo(N + 1, 0);
        return run_fib(memo, N);
    }

    int fib2(int N)
    {
        if (N < 1)
        {
            return 0;
        }
        int memo[3] = {1, 1, 0};
        int p = 0;
        for (int i = 1; i < N; i++)
        {
            memo[(p + 2) % 3] = memo[p] + memo[(p + 1) % 3];
            p = (p + 1) % 3;
        }
        return memo[p];
    }

    struct Node
    {
        int v;
        Node *next;
    };

    int fib3(int N)
    {
        if (N < 1)
        {
            return 0;
        }
        Node a = Node();
        Node b = Node();
        Node c = Node();
        a.next = &b;
        a.v = 1;
        b.next = &c;
        b.v = 1;
        c.next = &a;
        Node *p = &a;
        for (int i = 2; i < N; i++)
        {
            p->next->next->v = p->next->v + p->v;
            p = p->next;
        }
        return p->next->v;
    }

    int fib4(int N)
    {
        if (N < 1)
        {
            return 0;
        }
        if (N < 3)
        {
            return 1;
        }
        int a = 1, b = 1, c = 1;
        for (int i = 2; i < N; i++)
        {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
};

int main()
{
    Solution s = Solution();
    int r = s.fib4(29);
    std::cout << r << std::endl;
    return 0;
}
