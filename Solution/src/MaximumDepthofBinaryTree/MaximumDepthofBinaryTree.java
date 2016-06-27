package MaximumDepthofBinaryTree;

import java.util.Stack;

/**
 * Created by Zhang Hongchuan on 2016/6/27.
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaximumDepthofBinaryTree {

    public int maxDepth(TreeNode root) {
        // code 104
        if (root == null) {
            return 0;
        }
        int maxDepth = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        root.val = 1;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            int tmpDeep = top.val;
            if (top.right != null) {
                top.right.val = tmpDeep + 1;
                stack.push(top.right);
            }
            if (top.left != null) {
                top.left.val = tmpDeep + 1;
                stack.push(top.left);
            }
            if (tmpDeep > maxDepth) {
                maxDepth = tmpDeep;
            }
        }

        return maxDepth;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
