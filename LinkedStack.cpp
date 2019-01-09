// LinkedStack.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include "iostream"
using namespace std;

template<class DataType>
struct Node
{
	DataType data;
	Node<DataType> *next;
};

template<class DataType>
class LinkStack
{
public:
	LinkStack() { top = NULL; }
	~LinkStack();
	void Push(DataType x);
	DataType Pop();
	DataType GetTop() {
		if (top != NULL)
			return top->data;
	}
	int Empty() {
		if (top == NULL)
			return 1;
		else
			return 0;
	}
private:
	Node<DataType> *top;
};


template<class DataType>
LinkStack<DataType>::~LinkStack()
{
	while(top != NULL) {
		Node<DataType> *p = top;
		top = top->next;
		delete p;
	}
}

template<class DataType>
void LinkStack<DataType>::Push(DataType x)
{
	Node<DataType> *s = new Node<DataType>;
	s->data = x;
	s->next = top;
	top = s;
}

template<class DataType>
DataType LinkStack<DataType>::Pop()
{
	if (top == NULL)
		throw "下溢";
	DataType x = top->data;
	Node<DataType> *p = top;
	top = top->next;
	delete p;
	return x;
}

int main()
{
	LinkStack<int> s;
	int a[11] = { 5,3,5,89,9,4,6,4,4,654,56 };
	for (int i = 0; i < 11; i++) {
		s.Push(a[i]);
	}
	cout << "栈顶元素：" << s.GetTop() << endl;
	cout << "出栈：" << s.Pop() << endl;
	cout << "栈顶元素：" << s.GetTop() << endl;
	cout << "是否为空？ " << s.Empty() << endl;

	system("pause");
	return 0;
}
