//
// Created by xcp on 18-11-2.
//
#include <iostream>

struct TNode{
    int e;
    TNode *next;
};

int main(){
    auto *a=new TNode;
    a->next=NULL;
    auto *b=new TNode;
    b->next=NULL;
    auto p=a,q=b;
    int x;
    std::cin>>x;
    while (x!=-1){
        auto node=new TNode;
        node->e=x;
        node->next=NULL;
        p->next=node;
        p=p->next;
        std::cin>>x;
    }

    std::cin>>x;
    while (x!=-1){
        auto node=new TNode;
        node->e=x;
        node->next=NULL;
        q->next=node;
        q=q->next;
        std::cin>>x;
    }

    TNode *c;
    if(p!=NULL&&q!=NULL){
        c= new TNode;
        c->next=NULL;
        auto r=c;

        p=a->next;
        q=b->next;

        while (p!=NULL&&q!=NULL){
            if(p->e>q->e){
                r->next=q;
                q=q->next;
            } else{
                r->next=p;
                p=p->next;
            }
            r=r->next;
        }

        if(p!=NULL){
            r->next=p;
        }

        if(q!=NULL){
            r->next=q;
        }
    } else if(a->next!=NULL){
        c=a;
    } else{
        c=b;
    }

    if(c->next!=NULL){
        p=c->next;
        std::cout<<p->e;
        p=p->next;
        while(p!=NULL){
            std::cout<<" "<<p->e;
            p=p->next;
        }
    } else{
        std::cout<<"NULL";
    }


    return 0;
}