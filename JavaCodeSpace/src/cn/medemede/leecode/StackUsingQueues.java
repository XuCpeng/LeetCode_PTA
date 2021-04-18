package cn.medemede.leecode;

import java.util.LinkedList;

public class StackUsingQueues {
    int top;
    LinkedList<Integer> stack;
    /**
     * Initialize your data structure here.
     */
    public StackUsingQueues() {
        stack=new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        top=x;
        stack.addLast(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        int res=top;
        int size=stack.size();
        while(size>1){
            stack.addLast(stack.pollFirst());
            size--;
        }
        stack.removeFirst();
        if (!stack.isEmpty())
            top=stack.peekLast();
        return res;
    }

    /**
     * Get the top element.
     */
    public int top() {
        return top;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        StackUsingQueues s=new StackUsingQueues();
        s.push(1);
        s.push(2);
        s.push(3);
        int a=s.pop();
        int b=s.pop();
        int c=s.pop();
        System.out.println();
    }
}
