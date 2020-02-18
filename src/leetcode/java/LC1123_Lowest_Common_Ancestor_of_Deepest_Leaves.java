package leetcode.java;

// LC1123. Lowest Common Ancestor of Deepest Leaves
// https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/

// https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/discuss/334577/JavaC%2B%2BPython-Two-Recursive-Solution

public class LC1123_Lowest_Common_Ancestor_of_Deepest_Leaves {
    class Pair {
        TreeNode node;
        int d;
        Pair(TreeNode node, int d) {
            this.node = node;
            this.d = d;
        }
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Pair p = getLca(root, 0);
        return p.node;
    }
    private Pair getLca(TreeNode root, int d) {
        if (root == null) return new Pair(null, d);
        Pair l = getLca(root.left, d + 1);
        Pair r = getLca(root.right, d + 1);
        if (l.d == r.d) {
            return new Pair(root, l.d);
        } else {
            return  l.d > r.d ? l : r;
        }
    }
}
