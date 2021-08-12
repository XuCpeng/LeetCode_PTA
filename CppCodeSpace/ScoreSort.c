//
// Created by Peng on 2019/1/5.
//
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
typedef struct Stu
{
    char name[20];
    int score;
} Stu;

void mySwap(Stu a, Stu b)
{
    char name[20];
    strcpy(name, a.name);

    int score = a.score;
    strcpy(a.name, b.name);
    a.score = b.score;
    strcpy(b.name, name);
    b.score = score;
}

void aFromB(Stu &a, Stu &b)
{
    strcpy(a.name, b.name);
    a.score = b.score;
}

//---------------QuickSort----------------------
int Partition(Stu a[], int l, int h)
{

    //随机取枢值
    int ri = l + rand() % (h - l + 1);
    mySwap(a[l], a[ri]);

    Stu p = a[l];
    while (l < h)
    {
        while (l < h && a[h].score >= p.score)
        {
            --h;
        }
        aFromB(a[l], a[h]);
        while (l < h && a[l].score <= p.score)
        {
            ++l;
        }
        aFromB(a[h], a[l]);
    }
    strcpy(a[l].name, p.name);
    a[l].score = p.score;
    return l;
}

void QuickSort(Stu a[], int l, int h)
{
    if (l < h)
    {
        int p = Partition(a, l, h);
        QuickSort(a, l, p - 1);
        QuickSort(a, p + 1, h);
    }
}

//---------------QuickSort----------------------
int Partition2(Stu a[], int l, int h)
{

    //随机取枢值
    int ri = l + rand() % (h - l + 1);
    mySwap(a[l], a[ri]);

    Stu p = a[l];
    while (l < h)
    {
        while (l < h && a[h].score <= p.score)
        {
            --h;
        }
        aFromB(a[l], a[h]);
        while (l < h && a[l].score >= p.score)
        {
            ++l;
        }
        aFromB(a[h], a[l]);
    }
    strcpy(a[l].name, p.name);
    a[l].score = p.score;
    return l;
}

void QuickSort2(Stu a[], int l, int h)
{
    if (l < h)
    {
        int p = Partition2(a, l, h);
        QuickSort2(a, l, p - 1);
        QuickSort2(a, p + 1, h);
    }
}

int main()
{
    int n, type;
    while (scanf("%d", &n) != EOF)
    {
        Stu *stus = (Stu *)malloc(sizeof(Stu) * (n + 1));
        scanf("%d", &type);
        for (int i = 1; i <= n; i++)
        {
            scanf("%s %d", &stus[i].name, &stus[i].score);
        }
        if (type == 0)
        {
            QuickSort2(stus, 1, n);
        }
        else if (type == 1)
        {
            QuickSort(stus, 1, n);
        }

        for (int j = 1; j <= n; ++j)
        {
            printf("%s %d \n", stus[j].name, stus[j].score);
        }
    }
    system("pause");
    return 0;
}