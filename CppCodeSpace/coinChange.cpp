//
// Created by XuChuanpeng on 2020/10/20.
//
#include <iostream>
#include <vector>
#include <map>
class Solution
{
public:
    int coinChange(std::vector<int> &coins, int amount)
    {
        std::vector<int> memo(amount + 1, -2);
        return getChanges(memo, coins, amount);
    }
    int getChanges(std::vector<int> &memo, std::vector<int> &coins, int balance)
    {
        if (balance < 0)
        {
            return -1;
        }
        if (memo[balance] != -2)
        {
            return memo[balance];
        }
        if (balance == 0)
        {
            return 0;
        }
        int res = 10001;
        for (int i = 0; i < coins.size(); i++)
        {
            int sub = getChanges(memo, coins, balance - coins[i]);
            if (sub == -1)
            {
                continue;
            }
            res = std::min(res, sub + 1);
        }
        if (res == 10001)
        {
            res = -1;
        }
        memo[balance] = res;
        return res;
    }

    int coinChange2(std::vector<int> &coins, int amount)
    {
        std::vector<int> memo(amount + 1, amount + 1);
        memo[0] = 0;
        for (int i = 1; i < amount + 1; i++)
        {
            for (int j = 0; j < coins.size(); j++)
            {
                if (i >= coins[j])
                {
                    memo[i] = std::min(memo[i], memo[i - coins[j]] + 1);
                }
            }
        }
        if (memo[amount] == amount + 1)
        {
            return -1;
        }
        else
        {
            return memo[amount];
        }
    }
};

int main()
{
    Solution s = Solution();
    std::vector<int> coins;
    coins.push_back(474);
    coins.push_back(83);
    coins.push_back(404);
    coins.push_back(3);
    int r = s.coinChange2(coins, 264);
    std::cout << r << std::endl;
    return 0;
}