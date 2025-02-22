// Approach: Traverse the tree in a BFS manner using a queue. Enqueue the root and loop while the queue is not empty. The queue size in
// each iteration determines the number of nodes at the current level. Process these nodes by dequeuing them one by one and enqueuing their
// left and right children, if they exist. Although the children are added to the queue, they are not processed until the next level,
// ensuring that only the nodes from the current level are handled in each iteration.
// Time Complexity: O(n)
// Space Complexity: O(n)

// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// https://leetcode.com/problems/binary-tree-level-order-traversal/description/

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class BTLevelOrder {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode (int v) {
            val = v;
        }
    }

    List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> Q = new ArrayDeque<>();
        Q.add(root);
        while (!Q.isEmpty()) {
            int levelSize = Q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = Q.poll();
                level.add(curr.val);
                if (curr.left != null) {
                    Q.add(curr.left);
                }
                if (curr.right != null) {
                    Q.add(curr.right);
                }
            }
            ans.add(level);
        }
        return ans;
    }

    public static void main(String[] args) {
        BTLevelOrder btlo = new BTLevelOrder();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> levels = btlo.levelOrderBFS(root);

        for (List<Integer> level: levels) {
            System.out.println(level.toString());
        }
    }
}