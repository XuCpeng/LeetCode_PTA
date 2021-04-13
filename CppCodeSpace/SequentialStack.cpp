// SequentialStack.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include "iostream"
using namespace std;

const int StackSize = 10;

template<class DataType>
class SeqStack
{
public:
	SeqStack() { top = -1; }
	~SeqStack() {};

	void Push(DataType x);
	DataType Pop();
	DataType GetTop();
	int Empty();

private:
	DataType data[StackSize];
	int top;
};

template<class DataType>
void SeqStack<DataType>::Push(DataType x)
{
	if (top == StackSize - 1)
		cout<< "上溢"<<endl;
	else
	data[++top] = x;
}

template<class DataType>
DataType SeqStack<DataType>::Pop()
{
	if (top == -1)
		throw "下溢";
	DataType x = data[top--];
	return x;
}

template<class DataType>
DataType SeqStack<DataType>::GetTop()
{
	if (top == -1)
		throw "栈为空";
	return data[top];
}

template<class DataType>
int SeqStack<DataType>::Empty()
{
	if (top == -1)
		return 1;
	else
		return 0;
}


int main()
{
	SeqStack<int> s;
	int a[11] = { 5,3,5,89,9,4,6,4,4,654,56 };
	for (int i = 0; i < 11; i++) {
		s.Push(a[i]);
	}
	cout << "栈顶元素："<< s.GetTop()<<endl;
	cout << "出栈：" << s.Pop()<<endl;
	cout << "栈顶元素："<<s.GetTop()<<endl;
	cout << "是否为空？ " << s.Empty() << endl;

	system("pause");
	return 0;
}
