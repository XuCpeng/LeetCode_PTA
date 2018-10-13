#include <stdio.h>
#include <stdlib.h>
#include <iostream>

typedef int ElementType;
typedef struct Node *PtrToNode;
struct Node {
    ElementType Data;
    PtrToNode   Next;
};
typedef PtrToNode List;

List Reverse(List L){
    List p,q,w;
    p=L;
    q=p;
    if (p!=NULL&&p->Next!=NULL) {
        q=p->Next;
        p->Next=NULL;
        while (q->Next != NULL) {
            w = q->Next;
            q->Next = p;
            p = q;
            q = w;
        }
        q->Next=p;
    }
    return q;
}