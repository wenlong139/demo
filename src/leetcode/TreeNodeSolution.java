package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author qinwenlong
 * @Date 2022/10/14
 **/
public class TreeNodeSolution {
    static class TreeNode {
        public Integer val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Integer val) {
            this.val = val;
        }

        public TreeNode(Integer val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void traverseTree(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                continue;
            }
            System.out.println(cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    public static TreeNode deserializeTree(Integer[] nodes) {
        if (nodes == null) {
            return null;
        }
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len = nodes.length;
        for (int i = 1; i < len; ) {
            // 队列存的都是父节点
            TreeNode parent = queue.poll();
            Deque deque = new LinkedList<>();
            deque.addLast(parent);
            deque.push(parent);
            Integer left = nodes[i++];
            if (left != null) {
                TreeNode leftNode = new TreeNode(left);
                parent.left = leftNode;
                queue.offer(leftNode);
            } else {
                parent.left = null;
            }
            Integer right = nodes[i++];
            if (right != null) {
                TreeNode leftNode = new TreeNode(right);
                parent.right = leftNode;
                queue.offer(leftNode);
            } else {
                parent.right = null;
            }

        }
        return root;
    }
}
