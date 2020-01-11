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
