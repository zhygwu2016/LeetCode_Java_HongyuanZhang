package leetcode.java;

// 222. Count Complete Tree Nodes
// https://leetcode.com/problems/count-complete-tree-nodes/


// https://leetcode.com/problems/count-complete-tree-nodes/discuss/61958/Concise-Java-solutions-O(log(n)2)

public class LC222_Count_Complete_Tree_Nodes {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

        final int leftHeight = getLeftMostHeight(root.left);
        final int rightHeight = getLeftMostHeight(root.right);

        if(leftHeight > rightHeight){
            return countNodes(root.left) + (int)Math.pow(2, rightHeight);
        }else if(leftHeight == rightHeight){
            return (int)Math.pow(2, leftHeight) + countNodes(root.right);
        }else{
            return 0;
        }
    }

    private int getLeftMostHeight(TreeNode root){
        int result = 0;

        while(root != null){
            root = root.left;
            result++;
        }
        return result;
    }
}

// Time: O(logN * logN)

// 算法加强
class LC222 {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

        int lHeight = getHeight(root.left);
        int rHeight = getHeight(root.right);

        int count = 1;
        if (lHeight > rHeight) { // right subtree is a full tree
            count += (1 << rHeight) - 1;
            return count + countNodes(root.left);
        } else if (lHeight == rHeight) { // left subtree is a full tree
            count += (1 << lHeight) - 1;
            return count + countNodes(root.right);
        } else {
            return -1;
        }
    }

    private int getHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        return height;
    }
}
