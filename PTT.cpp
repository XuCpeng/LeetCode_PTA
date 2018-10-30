#include <iostream>
using namespace std;
typedef struct TNode *List;
struct TNode
{
    int coef, expn;
    TNode *Next;
};

int main()
{
    List a = (List)malloc(sizeof(TNode));
    a->Next = NULL;
    List b = (List)malloc(sizeof(TNode));
    b->Next = NULL;
    List p = a, pa, pb; //工作指针
    int na, nb, count;

    cin >> na;

    for (int i = 0; i < na; i++)
    {
        List x = (List)malloc(sizeof(TNode));
        x->Next = NULL;
        cin >> x->coef;
        cin >> x->expn;

        p->Next = x;
        p = x;
    }

    cin >> nb;

    p = b;
    for (int i = 0; i < nb; i++)
    {
        List x = (List)malloc(sizeof(TNode));
        x->Next = NULL;
        cin >> x->coef;
        cin >> x->expn;

        p->Next = x;
        p = x;
    }

    //-------------------积---------------------------
    int max = 0;
    if (a->Next != NULL && b->Next != NULL)
        max = a->Next->expn + b->Next->expn;
    else if (b->Next != NULL)
        max = b->Next->expn;
    else if (a->Next != NULL)
        max = a->Next->expn;

    int d[max + 1];
    for (int i = 0; i < max + 1; i++)
    {
        d[i] = 0;
    }
    pa = a;
    while (pa->Next != NULL)
    {
        pb = b;
        while (pb->Next != NULL)
        {
            d[pa->Next->expn + pb->Next->expn] += pa->Next->coef * pb->Next->coef;
            pb = pb->Next;
        }
        pa = pa->Next;
    }

    count = 0;
    if (d[max] != 0)
    {
        cout << d[max] << " " << max;
        count++;
    }

    for (int i = max - 1; i >= 0; i--)
    {
        if (d[i] != 0)
        {
            cout << " " << d[i] << " " << i;
            count++;
        }
    }
    if (count == 0)
    {
        cout << 0 << " " << 0;
    }

    //------------------------------------------------

    cout << endl;

    //-------------------和---------------------------
    max = 0 + 1;
    if (a->Next != NULL && b->Next != NULL)
    {
        if (a->Next->expn > b->Next->expn)
        {
            max = a->Next->expn;
        }
        else
        {
            max = b->Next->expn;
        }
    }
    else if (b->Next != NULL)
    {
        max = b->Next->expn;
    }
    else if (a->Next != NULL)
    {
        max = a->Next->expn;
    }
    int c[max + 1];

    for (int i = 0; i < max + 1; i++)
    {
        c[i] = 0;
    }

    pa = a;
    pb = b;
    while (pa->Next != NULL)
    {
        c[pa->Next->expn] += pa->Next->coef;
        pa = pa->Next;
    }
    while (pb->Next != NULL)
    {
        c[pb->Next->expn] += pb->Next->coef;
        pb = pb->Next;
    }

    count = 0;
    if (c[max] != 0)
    {
        cout << c[max] << " " << max;
        count++;
    }

    for (int i = max - 1; i >= 0; i--)
    {
        if (c[i] != 0)
        {
            cout << " " << c[i] << " " << i;
            count++;
        }
    }
    if (count == 0)
    {
        cout << 0 << " " << 0;
    }
    //----------------------------------

    system("pause");
    return 0;
}