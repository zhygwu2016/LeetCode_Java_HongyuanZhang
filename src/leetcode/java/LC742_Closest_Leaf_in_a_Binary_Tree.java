package leetcode.java;

// LC742. Closest Leaf in a Binary Tree
// https://leetcode.com/problems/closest-leaf-in-a-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 算法加强
public class LC742_Closest_Leaf_in_a_Binary_Tree {

    private static class Result {
        public int val; // 到最近叶子节点的距离
        public boolean containsTarget;
        public TreeNode leaf; // 最近的叶子节点

        public Result(int val, boolean containsTarget, TreeNode leaf) {
            this.val = val;
            this.containsTarget = containsTarget;
            this.leaf = leaf;
        }
    }

    private int min = Integer.MAX_VALUE;
    private TreeNode res = null;

    public int findClosestLeaf(TreeNode root, int k) {
        dfs(root, k);
        if (res == null) {
            throw new IllegalArgumentException();
        }

        return res.val;
    }

    private Result dfs(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        Result left = dfs(root.left, target);
        Result right = dfs(root.right, target);

        if (left == null && right == null) {
            if (root.val == target) {
                tryUpdate(0, root);
            }

            return new Result(1, root.val == target, root);
        } else if (right == null) {
            return handleOnlyOneChild(root, left, target);
        } else if (left == null) {
            return handleOnlyOneChild(root, right, target);
        } else {
            if (root.val == target) {
                tryUpdate(
                    left.val < right.val ? left.val : right.val,
                    left.val < right.val ? left.leaf : right.leaf);

                return new Result(0, true, null);
            } else if (left.containsTarget) {
                tryUpdate(left.val + 1 + right.val, right.leaf);
                return new Result(left.val + 1, true, null);
            } else if (right.containsTarget) {
                tryUpdate(left.val + 1 + right.val, left.leaf);
                return new Result(right.val + 1, true, null);
            } else {
                return new Result(
                        Math.min(left.val, right.val) + 1,
                        false,
                        left.val < right.val ? left.leaf : right.leaf);
            }
        }
    }

    private Result handleOnlyOneChild(TreeNode root, Result child, int target) {
        int d = 0;
        TreeNode node = child.leaf;
        if (root.val == target) {
            tryUpdate(child.val + 1, node);
        } else {
            d = child.val + 1;
        }

        return new Result(d, root.val == target || child.containsTarget, node);
    }

    private void tryUpdate(int d, TreeNode node) {
        if (d < min) {
            min = d;
            res = node;
        }
    }
}

// https://leetcode.com/problems/closest-leaf-in-a-binary-tree/discuss/113893/Java-DFS-(25ms)-solution-with-detailed-explanation
// ResultType:
// TreeNode leaf: Closest leaf node
// int distToLeaf: Distance to the leaf
// boolean exist: If subtree of this node contains target
// int distToTarget: Distance to target;
class ResultType {
    TreeNode leaf;
    int distToLeaf;
    boolean exist;
    int distToTarget;
    public ResultType(TreeNode leaf, int distToLeaf, boolean exist, int distToTarget) {
        this.leaf = leaf;
        this.distToLeaf = distToLeaf;
        this.exist = exist;
        this.distToTarget = distToTarget;
    }
}

class Solution_LC742 {
    private int shortest = Integer.MAX_VALUE;
    private TreeNode shortestLeaf = null;
    private int k;
    public int findClosestLeaf(TreeNode root, int k) {
        this.k = k;
        helper(root);
        return shortestLeaf.val;
    }

    private ResultType helper(TreeNode root) {
        ResultType crt = new ResultType(null, Integer.MAX_VALUE, false, Integer.MAX_VALUE);

        if (root == null) {
            return crt;
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        if (left.leaf == null && right.leaf == null) {
            // current node is leaf
            crt.leaf = root;
            crt.distToLeaf = 0;
        } else {
            // record the shortest path to leaf in one of children route
            crt.leaf = left.distToLeaf <= right.distToLeaf ? left.leaf : right.leaf;
            crt.distToLeaf = left.distToLeaf <= right.distToLeaf ? left.distToLeaf + 1 : right.distToLeaf + 1;
        }

        if (root.val == k) {
            // if target found, record the shortest path to leaf in one of its children route
            shortestLeaf = crt.leaf;
            shortest = crt.distToLeaf;
            // start to mark target found, and start count the distance to target
            // (increase level by level for its parents)
            crt.exist = true;
            crt.distToTarget = 0;
        } else if (left.exist || right.exist) {
            // if left child or right child contains target, meaning we have moved above the target
            crt.distToTarget = left.exist ? left.distToTarget + 1 : right.distToTarget + 1;
            crt.exist = true;
            // Since we have moved above the target node, we have to consider the 3rd path (which goes across the root node)
            if (crt.distToTarget + crt.distToLeaf < shortest) {
                shortest = crt.distToTarget + crt.distToLeaf;
                shortestLeaf = crt.leaf;
            }
        }

        return crt;
    }
}
