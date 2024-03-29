#include <stdio.h>
#include <stdlib.h>

#define ERROR -1
typedef int ElementType;
typedef struct LNode *PtrToLNode;
struct LNode
{
    ElementType Data;
    PtrToLNode Next;
};
typedef PtrToLNode List;

List Read(); /* 细节在此不表 */

ElementType FindKth(List L, int K);

int main()
{
    int N, K;
    ElementType X;
    List L = Read();
    scanf("%d", &N);
    while (N--)
    {
        scanf("%d", &K);
        X = FindKth(L, K);
        if (X != ERROR)
            printf("%d ", X);
        else
            printf("NA ");
    }
    return 0;
}

/* 你的代码将被嵌在这里 */
ElementType FindKth(List L, int K)
{
    if (K < 1)
        return ERROR;
    int t = 1;
    PtrToLNode p = L;
    while (p != NULL && t != K)
    {
        p = p->Next;
        t++;
    }
    if (p != NULL)
    {
        return p->Data;
    }
    else
    {
        return ERROR;
    }
}
