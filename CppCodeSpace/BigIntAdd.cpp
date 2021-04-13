// BigIntAdd.cpp : 定义控制台应用程序的入口点。
//
#include "stdafx.h"
#include "iostream"
#include "algorithm"
using namespace std;

const int MaxSize = 1000;

template<class DataType>
class SeqList
{
public:
	SeqList() { length = 0; }
	SeqList(int a[], int n);
	~SeqList() {};
	int Length() { return length; }
	DataType Get(int i);
	int Locate(DataType x);
	void Insert(int i, DataType x);
	int Delete(int i);
	void PrintList();
	friend SeqList<int> Add(SeqList<int> A, SeqList<int> B);
private:
	int length;
	DataType data[MaxSize];
};

//有参构造函数
template<class DataType>
SeqList<DataType>::SeqList(int a[], int n) {
	if (n>MaxSize)
	{
		throw "参数非法！";
	}
	else
	{
		for (int i = 0; i < n; i++) {
			data[i] = a[i];
			length = n;
		}
	}
}

//按位查找函数
template<class DataType>
DataType SeqList<DataType>::Get(int i) {
	if (i<1 || i>length) {
		throw "查找位置非法！";
	}
	else
	{
		return data[i - 1];
	}
}

//按值查找
template<class DataType>
int SeqList<DataType>::Locate(DataType x) {
	for (int i = 0; i < length; i++)
	{
		if (data[i] == x) {
			return i + 1;
		}
	}
	return 0;
}

//插入函数
template<class DataType>
void SeqList<DataType>::Insert(int i, DataType x) {
	if (length >= MaxSize)
		throw "上溢";
	else
		if (i<1 || i>length + 1)
			throw "位置";
		else
		{
			for (int j = length; j >= i; j--)
				data[j] = data[j - 1];
			data[i - 1] = x;
			length++;
		}
}

//删除函数
template<class DataType>
int SeqList<DataType>::Delete(int i)
{
	if (length == 0)
		throw "下溢";
	else
		if (i<1 || i>length)
			throw "位置";
		else
		{
			DataType x = data[i - 1];
			for (int j = i; j < length; j++)
			{
				data[j - 1] = data[j];
			}
			length--;
			return x;
		}
	return 0;
}

//遍历函数
template<class DataType>
void SeqList<DataType>::PrintList()
{
	for (int i = 0; i < length; i++)
	{
		cout << data[i] << " ";
	}
	cout << endl;
}

SeqList<int> Add(SeqList<int> A, SeqList<int> B)
{
	int flag = 0;
	int i = 0;
	int n = A.length;
	int m = B.length;
	SeqList<int> C;
	while (i<n&&i<m)
	{
		C.data[i] = (A.data[i] + B.data[i] + flag) % 10;
		flag = (A.data[i] + B.data[i] + flag) / 10;
		i++;
	}
	for (; i < n; i++) {
		C.data[i] = (A.data[i] + flag) % 10;
		flag = (A.data[i] + flag) / 10;
	}
	for (; i<m; i++)
	{
		C.data[i] = (B.data[i] + flag) % 10;
		flag = (B.data[i] + flag) / 10;
	}
	C.length = max(m, n) + flag;
	if (flag == 1)
	{
		C.data[C.length] = 1;
		C.length++;
	}
	return C;
}

int main() {
	int a[41] = { 1,2,3,4,5,6,7,8,9,1,0,1,1,1,2,1,3,1,4,1,5,1,6,1,7,1,8,1,9,2,0,2,1,2,2,2,3,2,4,2,5 };
	int b[42] = { 1,2,3,4,5,6,7,8,9,1,0,1,1,1,2,1,3,1,4,1,5,1,6,1,7,1,8,1,9,2,0,2,1,2,2,2,3,2,4,2,5,8};
	SeqList<int> x(a, 41);
	SeqList<int> y(b, 42);
	SeqList<int> z = Add(x,y);
	z.PrintList();
	system("pause");
	return 0;
}
