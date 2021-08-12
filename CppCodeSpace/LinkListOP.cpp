#include <stdio.h>
#include <stdlib.h>
#define ERROR NULL
typedef int ElementType;
typedef struct LNode *PtrToLNode;
struct LNode
{
    ElementType Data;
    PtrToLNode Next;
};
typedef PtrToLNode Position;
typedef PtrToLNode List;

Position Find(List L, ElementType X)
{
    PtrToLNode p = L;
    while (p != NULL && p->Data != X)
    {
        p = p->Next;
    }
    if (p != NULL && p->Data == X)
    {
        return p;
    }
    else
    {
        return ERROR;
    }
}
List Insert(List L, ElementType X, Position P)
{
    if (L == NULL && P != NULL)
    {
        printf("%s", "Wrong Position for Insertion");
        return ERROR;
    }

    if (P == L)
    {
        Position x = (Position)malloc(sizeof(struct LNode));
        x->Data = X;
        x->Next = L;
        return x;
    }

    PtrToLNode p = L, q = L->Next;
    while (q != NULL && q != P)
    {
        p = q;
        q = q->Next;
    }
    if (q == P)
    {
        Position x = (Position)malloc(sizeof(struct LNode));
        x->Data = X;
        x->Next = q;
        p->Next = x;
        return L;
    }
    else
    {
        printf("%s", "Wrong Position for Insertion");
        return ERROR;
    }
}
List Delete(List L, Position P)
{
    if (L == NULL)
    {
        printf("%s", "Wrong Position for Deletion");
        return ERROR;
    }

    if (P == L)
    {
        PtrToLNode p = L->Next;
        L->Next = NULL;
        free(L);
        return p;
    }

    PtrToLNode p = L, q = L->Next;
    while (q != NULL && q != P)
    {
        p = q;
        q = q->Next;
    }
    if (q == P)
    {
        p->Next = q->Next;
        free(q);
        return L;
    }
    else
    {
        printf("%s", "Wrong Position for Deletion");
        return ERROR;
    }
}

int main()
{
    List L;
    ElementType X;
    Position P, tmp;
    int N;

    L = NULL;
    scanf("%d", &N);
    while (N--)
    {
        scanf("%d", &X);
        L = Insert(L, X, L);
        if (L == ERROR)
            printf("Wrong Answer\n");
    }
    scanf("%d", &N);
    while (N--)
    {
        scanf("%d", &X);
        P = Find(L, X);
        if (P == ERROR)
            printf("Finding Error: %d is not in.\n", X);
        else
        {
            L = Delete(L, P);
            printf("%d is found and deleted.\n", X);
            if (L == ERROR)
                printf("Wrong Answer or Empty List.\n");
        }
    }
    L = Insert(L, X, NULL);
    if (L == ERROR)
        printf("Wrong Answer\n");
    else
        printf("%d is inserted as the last element.\n", X);
    P = (Position)malloc(sizeof(struct LNode));
    tmp = Insert(L, X, P);
    if (tmp != ERROR)
        printf("Wrong Answer\n");
    tmp = Delete(L, P);
    if (tmp != ERROR)
        printf("Wrong Answer\n");
    for (P = L; P; P = P->Next)
        printf("%d ", P->Data);

    system("pause");
    return 0;
}