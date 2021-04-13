class Solution(object):
    def get_num(self, l, s, index):
        sum_t = l[index - 1] + l[index - 2]
        if sum_t > 2147483647:
            return False
        num_l = len(str(sum_t))
        test_str = s[:num_l]
        if test_str[0] == '0' and not int(test_str) == 0:
            return False
        if not sum_t == int(test_str):
            return False
        else:
            if num_l == len(s):
                l.append(sum_t)
                return True
            else:
                l.append(sum_t)
                return self.get_num(l, s[num_l:], index + 1)

    def splitIntoFibonacci(self, S):
        """
        :type S: str
        :rtype: List[int]
        """
        for i in range(1, len(S)):
            for j in range(1, len(S) - i):
                l = []
                s_tmp = S[:i]
                if (s_tmp[0] == '0' and not int(s_tmp) == 0) or int(s_tmp) > 2147483647:
                    return []
                l.append(int(s_tmp))
                s_tmp2 = S[i:i + j]
                if (s_tmp2[0] == '0' and not int(s_tmp2) == 0) or int(s_tmp2) > 2147483647:
                    break
                l.append(int(s_tmp2))

                if self.get_num(l, S[i + j:], 2):
                    return l
        return []


solution = Solution()
print(solution.splitIntoFibonacci("1320581321313221264343965566089105744171833277577"))
