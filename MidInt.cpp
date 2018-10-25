#include <iostream>
#define MaxSize 100001
using namespace std;

//不使用x暂存，耗时更长（120ms），使用空间更大（最大测试集1132K）
int main(){
    int a[MaxSize],b[MaxSize];
    int n;
    cin>>n;
    for (int i=0;i<n;++i){
        cin>>a[i];
    }
    for (int j = 0; j < n; ++j) {
        cin>>b[j];
    }

    int mid1,mid2,l1,l2,p1,p2;
    l1=l2=0;
    p1=p2=n-1;
    while (l1<p1&&l2<p2){
        mid1=(l1+p1)/2;
        mid2=(l2+p2)/2;
        if(a[mid1]==b[mid2]){
            break;
        } else if(a[mid1]<b[mid2]){
            l1=mid1;
            p2=mid2;
        } else{
            p1=mid1;
            l2=mid2;
        }

        //舍去同样的长度
        if((p1-l1)<(p2-l2))
            ++l2;
        else if((p1-l1)>(p2-l2))
            ++l1;
    }

    mid1=(l1+p1)/2;
    mid2=(l2+p2)/2;

    if(a[mid1]<b[mid2])
        cout<<a[mid1]<<endl;
    else
        cout<<b[mid2]<<endl;


//    下面是使用x暂存的方法 看起来很怪异，但是耗时更短85ms，空间更少（最大测试集占用1024K）
//    int x;
//    while (l1<p1&&l2<p2){
//        mid1=(l1+p1)/2;
//        mid2=(l2+p2)/2;
//        if(a[mid1]==b[mid2]){
//            x=a[mid1];
//            break;
//        } else if(a[mid1]<b[mid2]){
//            l1=mid1;
//            p2=mid2;
//        } else{
//            p1=mid1;
//            l2=mid2;
//        }
//
//        if((p1-l1)<(p2-l2))
//            ++l2;
//        else if((p1-l1)>(p2-l2))
//            ++l1;
//    }
//
//    if(a[mid1]!=x){
//        if(a[l1]<b[l2])
//            x=a[l1];
//        else
//            x=b[l2];
//    }
//
//    cout<<x<<endl;
}