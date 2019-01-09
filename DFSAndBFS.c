#include <stdio.h>
#include <stdlib.h>
void DFS(int a[10][10], int v, int n, int flag[])
{
    if (!flag[v])
    {
        printf("%d ", v);
        flag[v] = 1;
        for (int i = 0; i < n; i++)
        {
            if (a[v][i]==1)
            {
                DFS(a, i, n, flag);
            }
        }
    }
}

void BFS(int a[10][10],int v,int n,int flag[]){
    int queue[11]={0},h=0,t=0;
    queue[0]=v;
    flag[v]=1;
    t=1;
    while(h!=t){
        int p=queue[h];
        h=(h+1)%11;
        printf("%d ",p);
        for(int i = 0; i < n; i++)
        {
            if(a[p][i]==1&&(flag[i]!=1)){
                queue[t]=i;
                flag[i]=1;
                t=(t+1)%11;
            }
        }
    }
}

int main(int argc, char const *argv[])
{

    int a[10][10];
    int n, e;
    scanf("%d %d", &n, &e);

    for (int i = 0; i < e; i++)
    {
        int x, y;
        scanf("%d %d", &x, &y);
        a[x][y] = 1;
        a[y][x] = 1;
    }
    int flag[10] = {0};
    for (int i = 0; i < n; i++)
    {

        if (!flag[i])
        {
            printf("{ ");
            DFS(a, i, n, flag);
            printf("}\n");
        }
    }

    int flag2[10]={0};
    for (int i = 0; i < n; i++)
    {

        if (!flag2[i])
        {
            printf("{ ");
            BFS(a, i, n, flag2);
            printf("}\n");
        }
    }

    return 0;
}
