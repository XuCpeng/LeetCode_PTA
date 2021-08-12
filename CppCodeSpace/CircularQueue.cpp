// CircularQueue.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include "iostream"
using namespace std;

const int QueueSize = 10;
template <class DataType>
class CircularQue
{
public:
	CircularQue() { front = rear = QueueSize - 1; }
	~CircularQue() {}
	void EnQueue(DataType x);
	DataType DeQueue();
	DataType GetQueue();
	int Empty()
	{
		if (front == rear)
			return 1;
		else
			return 0;
	}

private:
	DataType data[QueueSize];
	int front, rear;
};

template <class DataType>
void CircularQue<DataType>::EnQueue(DataType x)
{
	if ((rear + 1) % QueueSize == front)
		throw "上溢";
	rear = (rear + 1) % QueueSize;
	data[rear] = x;
}

template <class DataType>
DataType CircularQue<DataType>::DeQueue()
{
	if (rear == front)
		throw "下溢";
	front = (front + 1) % QueueSize;
	return data[front];
}

template <class DataType>
DataType CircularQue<DataType>::GetQueue()
{
	if (rear == front)
		throw "下溢";
	int i = (front + 1) % QueueSize;
	return data[i];
}

int main()
{
	CircularQue<int> s;
	int a[5] = {5, 9, 6, 7, 3};
	for (int i = 0; i < 5; i++)
	{
		s.EnQueue(a[i]);
	}
	cout << "队头元素：" << s.GetQueue() << endl;
	cout << "出栈：" << s.DeQueue() << endl;
	cout << "队头元素：" << s.GetQueue() << endl;
	cout << "是否为空？ " << s.Empty() << endl;

	system("pause");
	return 0;
}
