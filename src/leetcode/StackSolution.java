package leetcode;

import java.util.Stack;

/**
 * @Author qinwenlong
 * @Date 2022/10/14
 **/
public class StackSolution {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stackReverse(stack);
        System.out.println(stack);
    }

    public static void stackReverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int tail = getAndRemoveElements(stack);
        stackReverse(stack);
        stack.push(tail);
    }

    public static int getAndRemoveElements(Stack<Integer> stack) {
        int top = stack.peek();
        stack.pop();
        if (stack.isEmpty()) {
            return top;
        }
        int ans = getAndRemoveElements(stack);
        stack.push(top);
        return ans;
    }
}
