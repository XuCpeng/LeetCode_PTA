//验证二叉排序树
#include <iostream>
using namespace std;

typedef struct BTnode
{
    int data;
    struct BTnode *rchild, *lchind;
};

//主程序
int Degth(BTnode *t)
{
    int flag = Subdegr(t, t->lchind, t->rchild);
}

//递归子程序
int Subdegr(BTnode *t, BTnode *lB, BTnode *rB)
{
    //如果左右子树都不空
    if (lB != NULL && rB != NULL)
    {
        if (t->data > lB->data && t->data < rB->data)
        {
            //若t的值合法，检查左右子树
            return Subdegr(lB, lB->lchind, lB->rchild) && Subdegr(rB, rB->lchind, rB->rchild);
        }
        else
        {
            //若t的值不合法，返回false，不再检查t的子树
            return 0;
        }

        //t只有左子树
    }
    else if (lB != NULL)
    {
        if (t->data > lB->data)
        {
            return Subdegr(lB, lB->lchind, lB->rchild);
        }
        else
        {
            return 0;
        }

        //t只有右子树
    }
    else if (rB != NULL)
    {
        if (t->data < rB->data)
        {
            return Subdegr(rB, rB->lchind, rB->rchild);
        }
        else
        {
            return 0;
        }

        //t左右子树为空，即t为叶子节点，该子树全部合法，返回true
    }
    else
    {
        return 1;
    }
}