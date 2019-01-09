// LinkedQueue.cpp : 定义控制台应用程序的入口点。
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
class LinkQueue {
public:
	LinkQueue();
	~LinkQueue();
	void EnQueue(DataType x);
	DataType DeQueue();
	DataType GetQueue();
	int Empty() { if (front == rear) return 1; else return 0; }
private:
	Node<DataType> *front, *rear;
};

template<class DataType>
LinkQueue<DataType>::LinkQueue()
{
	Node<DataType> *s = new Node<DataType>;
	s->next = NULL;
	front = rear = s;
}

template<class DataType>
LinkQueue<DataType>::~LinkQueue()
{
	while (front != NULL) {
		Node<DataType> *p = front;
		front = front->next;
		delete p;
	}
}

template<class DataType>
void LinkQueue<DataType>::EnQueue(DataType x)
{
	Node<DataType> *s = new Node<DataType>;
	s->data = x;
	rear->next = s;
	rear = s;
}

template<class DataType>
DataType LinkQueue<DataType>::DeQueue()
{
	if (front == rear)
		throw "下溢";
	Node<DataType> *p = front->next;
	DataType x = p->data;
	front->next = p->next;
	if (p->next == NULL)
		rear = front;
	delete p;
	return x;
}

template<class DataType>
DataType LinkQueue<DataType>::GetQueue()
{
	if (front->next == NULL)
		cout << "队列为空" << endl;
	else {
		DataType x = front->next->data;
		return x;
	}
	return 0;
}


int main()
{
	LinkQueue<int> s;
	int a[5] = { 5,9,6,7,3 };
	for (int i = 0; i < 5; i++) {
		s.EnQueue(a[i]);
	}
	cout << "队头元素：" << s.GetQueue() << endl;
	cout << "出栈：" << s.DeQueue() << endl;
	cout << "队头元素：" << s.GetQueue() << endl;
	cout << "是否为空？ " << s.Empty() << endl;

	system("pause");
	return 0;
}
