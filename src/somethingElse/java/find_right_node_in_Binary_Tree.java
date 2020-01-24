package somethingElse.java;

// Binary Tree, 找到某个node右边的node, 返回其值。
// chenchuxiong电面

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class find_right_node_in_Binary_Tree {
    public int targetLevel = -1;

    public int findRightNodeValue(TreeNode root, int target){
        if(root == null) throw new IllegalArgumentException();
        return dfs(root, target, 0);
    }

    private Integer dfs(TreeNode root, int target, int level){
        if (root == null) return null;
        if (level == targetLevel) return root.val;
        if (root.val == target) targetLevel = level;
        Integer value = dfs(root.left, target, level + 1);
        if (value != null) return value;
        return dfs(root.right, target, level + 1);
    }
}
