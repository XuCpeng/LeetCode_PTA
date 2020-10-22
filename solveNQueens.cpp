#include <iostream>
#include <vector>
using namespace std;

class Solution
{
public:
    
    vector<vector<string>> solveNQueens(int n)
    {
        vector<int> f(n, 0);
        vector<vector<string>> res_s;
        vector<string> track;
        string raw_s(n, '.');
        getNQueens(res_s,f, track, raw_s, n);
        return res_s;
    }
    void getNQueens(vector<vector<string>> &res_s,vector<int> &f, vector<string> &track, string &raw_s, int n)
    {
        if (n == track.size())
        {
            res_s.push_back(track);
            return;
        }

        for (int i = 0; i < n; i++)
        {
            if (f[i])
                continue;

            int f_r = i;
            int f_l = i;
            int f_tmp = 0;
            for (int j = track.size() - 1; j >= 0; j--)
            {
                if (++f_r < n && track[j][f_r] == 'Q')
                {
                    f_tmp = 1;
                    break;
                }
                if (--f_l >= 0 && track[j][f_l] == 'Q')
                {
                    f_tmp = 1;
                    break;
                }
            }

            if (f_tmp)
                continue;

            f[i] = 1;
            string tmp_s = raw_s;
            tmp_s[i] = 'Q';
            track.push_back(tmp_s);
            getNQueens(res_s, f, track, raw_s, n);
            track.pop_back();
            f[i] = 0;
        }
    }
};