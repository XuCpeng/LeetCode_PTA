//
// Created by xcp on 18-11-1.
//
// Sort.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
typedef int ElemType;

//---------------QuickSort----------------------
int Partition(ElemType a[], int l, int h) {

	//随机取枢值
	int ri = l + rand() % (h - l + 1);
	std::swap(a[l], a[ri]);

	int p = a[l];
	while (l < h) {
		while (l < h&&a[h] >= p) {
			--h;
		}
		a[l] = a[h];
		while (l < h&&a[l] <= p) {
			++l;
		}
		a[h] = a[l];
	}
	a[l] = p;
	return l;
}

void QuickSort(ElemType a[], int l, int h) {
	if (l < h) {
		int p = Partition(a, l, h);
		QuickSort(a, l, p - 1);
		QuickSort(a, p + 1, h);
	}
}

//-----------------InsertSort----------------
//a[0] not use
void InsterSort(ElemType a[], int n) {
	int j;
	for (int i = 2; i <= n; ++i) {
		if (a[i] < a[i - 1]) {
			a[0] = a[i];
			for (j = i - 1; a[j] > a[0]; --j) {
				a[j + 1] = a[j];
			}
			a[j + 1] = a[0];
		}
	}
}

//a[0] not use
void InsterSortHalf(ElemType a[], int n) {
	int low, hight, mid;
	for (int i = 2; i <= n; ++i) {
		if (a[i] < a[i - 1]) {
			a[0] = a[i];
			low = 1; hight = i - 1;
			while (low <= hight) {
				mid = (low + hight) / 2;
				if (a[mid] > a[0]) {
					hight = mid - 1;
				}
				else
					low = mid + 1;
			}
			low = i - 1;
			while (low > hight) {
				a[low + 1] = a[low];
				--low;
			}
			a[hight + 1] = a[0];

		}
	}

}

//--------------------ShellSort--------------------
//a[0] not use
void ShellInsertSort(ElemType a[], int n, int span) {
	for (int i = n / span; i >= 1; i = i / span) {
		for (int j = 1 + i; j <= n; ++j) {
			if (a[j] < a[j - i]) {
				a[0] = a[j];
				int k;
				for (k = j - i; k > 0 && a[k] > a[0]; k -= i) {
					a[k + i] = a[k];
				}
				a[k + i] = a[0];
			}
		}
	}
}


//----------------BubbeSort-------------------
void BubbleSort(ElemType a[], int n) {
	int l = 1, h = n;
	bool flag = true;
	while (l < h&&flag) {
		flag = false;
		for (int i = l; i < h; ++i) {
			if (a[i] > a[i + 1]) {
				std::swap(a[i], a[i + 1]);
				flag = true;
			}
		}

		--h;

		for (int j = h; j > l; --j) {
			if (a[j] < a[j - 1]) {
				std::swap(a[j], a[j - 1]);
				flag = true;
			}
		}

		++l;
	}

}

//------------------SimpleSelect------------------

void SimpleSelect(ElemType a[], int n) {
	int min;
	//a[0] not use
	for (int i = 1; i < n; ++i) {
		min = i;
		for (int j = i + 1; j <= n; ++j) {
			if (a[j] < a[min]) {
				min = j;
			}
		}
		if (i != min)
			std::swap(a[min], a[i]);
	}
}

//--------------MergeSort-----------------------
int b[100001];
void Merge(ElemType a[], int l, int h, int mid) {

	for (int i = l; i <= h; ++i) {
		b[i] = a[i];
	}

	int p, q, k;
	for (p = l, q = mid + 1, k = l; p <= mid && q <= h; ++k) {
		if (b[p] > b[q]) {
			a[k] = b[q++];
		}
		else {
			a[k] = b[p++];
		}
	}
	while (p <= mid) {
		a[k++] = b[p++];
	}
	while (q <= h) {
		a[k++] = b[q++];
	}

}

void MergeSort(ElemType a[], int l, int h) {
	if (l < h) {
		int mid = (l + h) / 2;
		MergeSort(a, l, mid);
		MergeSort(a, mid + 1, h);
		Merge(a, l, h, mid);
	}

}


//--------------HeapSort-----------------------

//大顶堆
void adjustHeap(int a[], int i, int n) { //调整i及其子树
	int p = a[i]; //暂存
	for (int k = i * 2+1; k < n; k = k * 2+1) //i的左子树或其子节点的左子树
	{
		if (k+1 < n&&a[k] < a[k + 1]) {
			k++; //k指向子节点中大的那个
		}
		if (a[k] > p) {  //若子节点中大的那个大于父节点
			a[i] = a[k];  //将该节点赋值给父节点(父节点已暂存)
			i = k; //用i指向父节点该存放的位置
			//由于当前节点会被父节点替换，所以需要对其子节点再进行调整->继续for循环
		}
		else
		{
			break;//如果子节点都不大于父节点，不用调整(因为下面已经是堆)
		}
	}
	a[i] = p; //将父节点放到最终的位置(父节点及其子树全部是堆)
}

void HeapSort(int a[], int n) {

	for (int i = n / 2-1; i >= 0; i--) //从最下非叶节点开始
	{
		adjustHeap(a, i, n);
	}
	//到这里a已经是大顶堆
	
	//a[0]是a[0~n-1]的最大值，将其置换到a[n-1],a[n-1]有序
	//调整a[0~n-2]为小顶堆,再将次大值a[0]置换到a[n-2],(a[n-2]~a[n-1])有序
	//...最终得到递增序列a[0]~a[n-1]
	for (int i = n-1; i > 0; i--)
	{
		std::swap(a[0], a[i]);
		adjustHeap(a, 0, i);
	}
}

int main() {
	int  n= 1000;
	int a[100001];
	std::cin >> n;

	//堆排需要a[0]
	for (int i = 0; i < n; i++)
	{
		std::cin >> a[i];
	}
	HeapSort(a, n);

	std::cout << a[0];
	for (int j = 1; j < n; ++j) {
		std::cout << " " << a[j];
	}

	//下列排序方式均不使用a[0]
	/*
	for (int i = 1; i <= n; ++i) {
		std::cin >> a[i];
	}*/
	//InsterSort(a,n);
	//ShellInsertSort(a,n,2);
	//InsterSortHalf(a,n);
	//QuickSort(a,1,n);
	//SimpleSelect(a,n);
	//BubbleSort(a,n);
	//MergeSort(a, 1, n);
	/*
	std::cout << a[1];
	for (int j = 2; j <= n; ++j) {
		std::cout << " " << a[j];
	}*/
	
	return 0;
}
