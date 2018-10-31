#include <iostream>
#define MaxSize 100001
typedef int ElemType;

int Partition(int a[],int l,int h){
    int p=a[l];
    while (l<h){
        while (l<h&&a[h]>=p){
            --h;
        }
        a[l]=a[h];
        while(l<h&&a[l]<=p){
            ++l;
        }
        a[h]=a[l];
    }
    a[l]=p;
    return l;
}

void QuickSort(ElemType a[],int l,int h){
    if(l<h){
        int p=Partition(a,l,h);
        QuickSort(a,l,p-1);
        QuickSort(a,p+1,h);
    }
}

//a[0] not use
void InsterSort(ElemType a[],int n){
    int j;
    for (int i = 1; i <= n; ++i) {
        if(a[i]<a[i-1]){
            a[0]=a[i];
            for (j = i-1;a[j]>a[0]; --j) {
                a[j+1]=a[j];
            }
            a[j+1]=a[0];
        }
    }
}

//a[0] not use
void InsterSortHalf(ElemType a[],int n){
    int low,hight,mid;
    for (int i = 2; i <=n; ++i) {
        if(a[i]<a[i-1]){
            a[0]=a[i];
            low=1;hight=i-1;
            while(low<hight){
                mid=(low+hight)/2;
                if(a[mid]>a[0]){
                    hight=mid-1;
                }else
                    low=mid+1;
            }
            low=i-1;
            while(low>hight){
                a[low+1]=a[low];
                --low;
            }
            a[hight+1]=a[0];

        }
    }

}
//a[0] not use
void ShellInsertSort(ElemType a[],int n,int span){
    for (int i = n/span; i >=1; i=i/span) {
        for (int j = 1+i; j <= n; ++j) {
            if(a[j]<a[j-i]){
                a[0]=a[j];
                int k;
                for (k = j-i; k >0&&a[k]>a[0] ; k-=i) {
                    a[k+i]=a[k];
                }
                a[k+i]=a[0];
            }
        }
    }
}

int main() {
    int a[MaxSize];
    int  n;
    std::cin>>n;
    for (int i = 1; i <= n; ++i) {
        std::cin>>a[i];
    }
    //InsterSort(a,n);
    ShellInsertSort(a,n,2);
    //InsterSortHalf(a,n);
    //QuickSort(a,1,n);
    std::cout<<a[1];
    for (int j = 2; j <=n; ++j) {
        std::cout<<" "<<a[j];
    }

    return 0;
}