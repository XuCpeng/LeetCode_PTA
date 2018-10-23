Stack CreateStack(int MaxSize)
{
	Stack s = (Stack)malloc(sizeof(struct SNode));
	s->Data = (ElementType*)malloc(sizeof(ElementType)*MaxSize);
	s->MaxSize = MaxSize;
	s->Top1 = -1;
	s->Top2 = MaxSize;
	return s;
}

bool Push(Stack S, ElementType X, int Tag)
{
	if (S == NULL || S->Top1+1==S->Top2)
	{
		printf("%s", "Stack Full\n");
		return false;
	}

	switch (Tag)
	{
	case 1:
		S->Top1 = S->Top1 + 1;
		S->Data[S->Top1] = X;
		return true;
	case 2:
		S->Top2 = S->Top2 - 1;
		S->Data[S->Top2] = X;
		return true;
	default:
		break;
	}

	return false;
}

ElementType Pop(Stack S, int Tag)
{
	if (S == NULL)
	{
		printf("Stack %d Empty\n", Tag);
		return ERROR;
	}
	switch (Tag)
	{
	case 1:
		if (S->Top1 < 0)
		{
			printf("Stack %d Empty\n", Tag);
			return ERROR;
		}
		S->Top1 = S->Top1 - 1;
		return S->Data[S->Top1+1];
	case 2:
		if (S->Top2 > S->MaxSize-1)
		{
			printf("Stack %d Empty\n", Tag);
			return ERROR;
		}
		S->Top2 = S->Top2 + 1;
		return S->Data[S->Top2-1];
	default:
		break;
	}

	return ERROR;
}