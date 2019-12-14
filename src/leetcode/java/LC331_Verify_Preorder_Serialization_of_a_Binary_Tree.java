package leetcode.java;

// LC331. Verify Preorder Serialization of a Binary Tree
// https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/

public class LC331_Verify_Preorder_Serialization_of_a_Binary_Tree {
    public boolean isValidSerialization(String preorder) {
        // #(node) + 1 = #(#)

        String tokens[] = preorder.split(",");

        int count = 1;

        for (String s : tokens) {
            if (s.equals("#")) {
                count--;
            } else {
                if (count <= 0) {
                    return false;
                }
                count++;
            }
        }

        return count == 0;
    }
}
