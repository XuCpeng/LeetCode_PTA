#include <stdio.h>
#include <stdlib.h>

typedef char ElementType;
typedef struct TNode *Position;
typedef Position BinTree;
struct TNode{
    ElementType Data;
    BinTree Left;
    BinTree Right;
};


void InorderTraversal(BinTree BT)
{
    if (BT != NULL)
    {
        InorderTraversal(BT->Left);
        printf(" ");
        printf("%c", BT->Data);
        InorderTraversal(BT->Right);
    }
}
void PreorderTraversal(BinTree BT)
{
    if (BT != NULL)
    {
        printf(" ");
        printf("%c", BT->Data);
        PreorderTraversal(BT->Left);
        PreorderTraversal(BT->Right);
    }
}
void PostorderTraversal(BinTree BT)
{
    if (BT != NULL)
    {
        PostorderTraversal(BT->Left);
        PostorderTraversal(BT->Right);
        printf(" ");
        printf("%c", BT->Data);
    }
}
void LevelorderTraversal(BinTree BT)
{
    int MaxSize = 10000;
    BinTree q[MaxSize];
    int qh = 0, qp = 0;
    BinTree p;
    if (BT != NULL)
    {
        q[qp] = BT;
        qp = (qp + 1) % MaxSize;
        while (qp != qh)
        {
            p = q[qh];
            qh = (qh + 1) % MaxSize;
            printf(" ");
            printf("%c", p->Data);
            if (p->Left != NULL)
            {
                q[qp] = p->Left;
                qp = (qp + 1) % MaxSize;
            }
            if (p->Right != NULL)
            {
                q[qp] = p->Right;
                qp = (qp + 1) % MaxSize;
            }
        }
    }
}