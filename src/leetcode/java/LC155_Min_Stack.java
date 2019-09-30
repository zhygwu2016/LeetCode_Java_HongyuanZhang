package leetcode.java;

import java.util.Stack;

public class LC155_Min_Stack {
    class MinStack {

        Stack<Integer> stack;
        Stack<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);

            if(minStack.isEmpty()){
                minStack.push(x);
            } else {
                minStack.push(Math.min(minStack.peek(), x));
            }
        }

        public void pop() {
            if(stack.isEmpty()) return;
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            if(minStack.isEmpty()) return Integer.MAX_VALUE;
            return minStack.peek();
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
}
