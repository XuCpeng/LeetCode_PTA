// SemilarTree.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>

struct Tree
{
	char a;
	int lc, rc;
};

bool isSemilar(Tree trees1[], Tree trees2[], int tree1, int tree2,int n) {
	if (tree1 == '-' || tree2 == '-') {
		if (tree1 != tree2)
			return false;
		else
		{
			return true;
		}
	}
	if (trees1[tree1].a != trees2[tree2].a) {
		return false;
	}
	else
	{
		if (n > 1) {
			return ((isSemilar(trees1, trees2, trees1[tree1].lc, trees2[tree2].lc, n - 1) && isSemilar(trees1, trees2, trees1[tree1].rc, trees2[tree2].rc, n - 1)) ||
				(isSemilar(trees1, trees2, trees1[tree1].lc, trees2[tree2].rc, n - 1) && isSemilar(trees1, trees2, trees1[tree1].rc, trees2[tree2].lc, n - 1)));
		}
		else
		{
			return true;
		}
	}
}

int main()
{
	int n1, n2;
	Tree trees1[10],trees2[10]; 
	int tree1, tree2;
	int inDegree[10] = { 0 };
	std::cin >> n1;
	for (int i = 0; i < n1; i++)
	{
		char a,b;
		std::cin >> trees1[i].a >> a >> b;
		if (a != '-') {
			inDegree[a - 48]++;
			trees1[i].lc = a - 48;
		}
		else
		{
			trees1[i].lc = a;
		}
		if (b != '-') {
			inDegree[b - 48]++;
			trees1[i].rc = b - 48;
		}
		else
		{
			trees1[i].rc = b;
		}
	}
	for (int i = 0; i < n1; i++)
	{
		if (inDegree[i] == 0)
			tree1 = i;
	}
	std::cin >> n2;
	for (int i = 0; i < n2; i++)
	{
		inDegree[i] = 0;
	}
	for (int i = 0; i < n2; i++)
	{
		char a,b;
		std::cin >> trees2[i].a >> a >> b;
		if (a != '-') {
			inDegree[a - 48] ++;
			trees2[i].lc = a - 48;
		}
		else
		{
			trees2[i].lc = a;
		}
		if (b != '-') {
			inDegree[b - 48] ++;
			trees2[i].rc = b-48;
		}
		else
		{
			trees2[i].rc = b;
		}
	}
	for (int i = 0; i < n2; i++)
	{
		if (inDegree[i] == 0)
			tree2 = i;
	}
	
	bool flag;
	if (n1 != n2) {
		flag = false;
	}
	else if (n1==0)
	{
		flag = true;
	}
	else
	{
		flag = isSemilar(trees1, trees2, tree1, tree2, n1);
	}
	std::cout << (flag ? "Yes" : "No" )<< std::endl;

	return 0;

}