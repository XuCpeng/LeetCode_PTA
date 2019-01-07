// SearchTree.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//由于限定了最大深度为10，所以直接使用了1024的数组
#include <iostream>

void getTree(int a[],int i, int x) {
	if (a[i] == -1) {
		a[i] = x;
	}
	else if (a[i]>x)
	{
		getTree(a, 2 * i + 1, x);
	}
	else
	{
		getTree(a, 2 * i + 2, x);
	}
}

int main()
{
	int n=1,l;
	int a[1024], x,y;
	
	
	while (true)
	{
		std::cin >> n;
		if (n == 0)
			break;
		std::cin >> l;
		for (int i = 0; i < 1024; i++)
		{
			a[i] = -1;
		}
		for (int k = 0; k < n; k++)
		{
			std::cin >> x;
			getTree(a, 0, x);
		}
		
		for (int h = 0; h < l; h++)
		{
			int b[1024];
			for (int i = 0; i < 1024; i++)
			{
				b[i] = -1;
			}
			for (int j = 0; j < n; j++)
			{
				std::cin >> y;
				getTree(b, 0, y);
			}
			bool flag = true;
			for (int i = 0; i < 1024; i++)
			{
				if (a[i] != b[i]) {
					flag = false;
					break;
				}
			}
			std::cout << (flag ? "Yes" : "No") << std::endl;
		}

	}
}
