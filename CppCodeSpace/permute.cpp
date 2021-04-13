#include <iostream>
#include <vector>
using namespace std;

class Solution
{
public:
    vector<vector<int>> permute(vector<int> &nums)
    {
        vector<vector<int>> res;
        vector<int> track;
        vector<int> f(nums.size(), 0);
        getPermute(res, f,nums, track);
        return res;
    }

    void getPermute(vector<vector<int>> &res, vector<int> &f, vector<int> &nums, vector<int> &track)
    {
        if (track.size() == nums.size())
        {
            res.push_back(track);
            return;
        }
        
        for (int i = 0; i < nums.size(); i++)
        {
            if (f[i])
            {
                continue;
            }
            track.push_back(nums[i]);
            f[i] = 1;
            getPermute(res,f, nums, track);
            f[i] = 0;
            track.pop_back();
        }
    }
};

int main()
{
    Solution s;
}