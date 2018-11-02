//
// Created by xcp on 18-11-1.
//
#include <iostream>
typedef int ElemType;

//---------------QuickSort----------------------
int Partition(ElemType a[],int l,int h){

    //随机取枢值
    int ri= l+rand()%(h-l+1);
    std::swap(a[l],a[ri]);

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

//-----------------InsertSort----------------
//a[0] not use
void InsterSort(ElemType a[],int n){
    int j;
    for (int i = 2; i <= n; ++i) {
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
            while(low<=hight){
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

//--------------------ShellSort--------------------
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


//----------------BubbeSort-------------------
void BubbleSort(ElemType a[],int n){
    int l=1,h=n;
    bool flag= true;
    while (l<h&&flag){
        flag= false;
        for (int i = l; i <h ; ++i) {
            if(a[i]>a[i+1]){
                std::swap(a[i],a[i+1]);
                flag= true;
            }
        }

        --h;

        for (int j = h; j > l ; --j) {
            if(a[j]<a[j-1]){
                std::swap(a[j],a[j-1]);
                flag= true;
            }
        }

        ++l;
    }

}

//------------------SimpleSelect------------------

void SimpleSelect(ElemType a[],int n){
    int min;
    //a[0] not use
    for (int i = 1; i <n ; ++i) {
        min=i;
        for (int j = i+1; j <=n ; ++j) {
            if(a[j]<a[min]){
                min=j;
            }
        }
        if(i!=min)
            std::swap(a[min],a[i]);
    }
}

//--------------MergeSort-----------------------
int b[100001];
void Merge(ElemType a[],int l,int h,int mid){

    for (int i = l; i <= h; ++i) {
        b[i]=a[i];
    }

    int p,q,k;
    for ( p = l,q=mid+1,k=l;p<=mid&&q<=h; ++k) {
        if(b[p]>b[q]){
            a[k]=b[q++];
        } else{
            a[k]=b[p++];
        }
    }
    while(p<=mid){
        a[k++]=b[p++];
    }
    while (q<=h){
        a[k++]=b[q++];
    }

}

void MergeSort(ElemType a[],int l,int h){
    if(l<h){
        int mid=(l+h)/2;
        MergeSort(a,l,mid);
        MergeSort(a,mid+1,h);
        Merge(a,l,h,mid);
    }

}


int main() {

    int a[100001];
    int  n;
    std::cin>>n;
    for (int i = 1; i <= n; ++i) {
        std::cin >> a[i];
    }

    //a[0] not use
//    int a[]={0,4,981,10,-17,0,-20,29,50,8,43,-5};
//    int  n=11;

    //InsterSort(a,n);
    //ShellInsertSort(a,n,2);
    //InsterSortHalf(a,n);
    //QuickSort(a,1,n);
    //SimpleSelect(a,n);
    //BubbleSort(a,n);
    MergeSort(a,1,n);

    std::cout<<a[1];
    for (int j = 2; j <=n; ++j) {
        std::cout<<" "<<a[j];
    }

    return 0;
}