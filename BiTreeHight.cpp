#include <stdio.h>
#include <stdlib.h>

typedef char ElementType;
typedef struct TNode *Position;
typedef Position BinTree;
struct TNode
{
    ElementType Data;
    BinTree Left;
    BinTree Right;
};

BinTree CreatBinTree(); /* 实现细节忽略 */
int GetHeight(BinTree BT);

int main()
{
    BinTree BT = CreatBinTree();
    printf("%d\n", GetHeight(BT));
    return 0;
}
/* 你的代码将被嵌在这里 */

int GetHeight(BinTree BT)
{
    return GetH(BT);
}

int GetH(BinTree t)
{
    if (t != NULL)
    {
        int a = GetH(t->Left) + 1;
        int b = GetH(t->Right) + 1;
        return (a > b ? a : b);
    }
    else
    {
        return 0;
    }
}