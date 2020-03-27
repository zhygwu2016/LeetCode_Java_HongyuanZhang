package leetcode.java;

// 272. Closest Binary Search Tree Value II
// https://leetcode.com/problems/closest-binary-search-tree-value-ii/

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 自己写的
// inOrder traverse然后利用最大堆找出k个最接近
// 除了maxHeap 可以使用deque
public class LC272_Closest_Binary_Search_Tree_Value_II {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - target) > Math.abs(o2 - target) ? -1 : 1;
            }
        });

        for (Integer n : nums) {
            maxHeap.add(n);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        while (k-- > 0 ) {
            res.addFirst(maxHeap.poll());
        }

        return res;
    }

    public void inOrder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inOrder(root.left, nums);
        nums.add(root.val);
        inOrder(root.right, nums);
    }
}

// 算法加强
// 理论基础：用stack模拟左边指针的in-order traverse: 沿着左方向打开子树
// 从右边打开同理, 用另一个stack模拟右边指针反方向in order, 沿着右方向打开左子树
//
// 此题：用两个stack分别放入比target小和比target大的起始节点
// Average runtime is O(log(n) + k)
// Building each of the stacks takes O(log(n)) assuming BST is balanced.
// Each call to getNextLeft/Right has an amortized cost of O(1),
// since every node is pushed and popped at most once.
class LC272_Stack {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<Integer>();

        Stack<TreeNode> left = new Stack<TreeNode>();
        Stack<TreeNode> right = new Stack<TreeNode>();

        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < target) {
                left.push(cur);
                cur = cur.right;
            } else {
                right.push(cur);
                cur = cur.left;
            }
        }

        while (k-- > 0) {  // O(k)
            if (!left.isEmpty() && !right.isEmpty()) {
                if (Math.abs((double)left.peek().val - target)
                        < (Math.abs((double)right.peek().val - target))) {
                    res.add(getNextLeft(left));
                } else {
                    res.add(getNextRight(right));
                }
            } else if (!left.isEmpty()) {
                res.add(getNextLeft(left));
            } else if (!right.isEmpty()) {
                res.add(getNextRight(right));
            }
        }

        return res;
    }

    private int getNextLeft(Stack<TreeNode> left) {
        TreeNode top = left.pop();
        int ret = top.val;
        TreeNode cur = top.left;
        while (cur != null) {
            left.push(cur);
            cur = cur.right;
        }
        return ret;
    }

    private int getNextRight(Stack<TreeNode> right) {
        TreeNode top = right.pop();
        int ret = top.val;
        TreeNode cur = top.right;
        while (cur != null) {
            right.push(cur);
            cur = cur.left;
        }
        return ret;
    }
}

// https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/70511/AC-clean-Java-solution-using-two-stacks
class Solution_LC272 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();

        Stack<Integer> s1 = new Stack<>(); // predecessors
        Stack<Integer> s2 = new Stack<>(); // successors

        inorder(root, target, false, s1);
        inorder(root, target, true, s2);

        while (k-- > 0) {
            if (s1.isEmpty())
                res.add(s2.pop());
            else if (s2.isEmpty())
                res.add(s1.pop());
            else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
                res.add(s1.pop());
            else
                res.add(s2.pop());
        }

        return res;
    }

    // inorder traversal
    void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
        if (root == null) return;

        inorder(reverse ? root.right : root.left, target, reverse, stack);
        // early terminate, no need to traverse the whole tree
        if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
        // track the value of current node
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }
}

// Best
// https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/70503/O(logN)-Java-Solution-with-two-stacks-following-hint
// Average runtime is O(log(n) + k)
// Building each of the stacks takes O(log(n)) assuming BST is balanced.
// Each call to getNextPred/Succ has an amortized cost of O(1),
// since every node is pushed and popped at most once.
class Solution_LC272_follow_up {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> ret = new LinkedList<>();
        Stack<TreeNode> succ = new Stack<>();
        Stack<TreeNode> pred = new Stack<>();
        initializePredecessorStack(root, target, pred);
        initializeSuccessorStack(root, target, succ);
        if(!succ.isEmpty() && !pred.isEmpty() && succ.peek().val == pred.peek().val) {
            getNextPredecessor(pred);
        }
        while(k-- > 0) {
            if(succ.isEmpty()) {
                ret.add(getNextPredecessor(pred));
            } else if(pred.isEmpty()) {
                ret.add(getNextSuccessor(succ));
            } else {
                double succ_diff = Math.abs((double)succ.peek().val - target);
                double pred_diff = Math.abs((double)pred.peek().val - target);
                if(succ_diff < pred_diff) {
                    ret.add(getNextSuccessor(succ));
                } else {
                    ret.add(getNextPredecessor(pred));
                }
            }
        }
        return ret;
    }

    private void initializeSuccessorStack(TreeNode root, double target, Stack<TreeNode> succ) {
        while(root != null) {
            if(root.val == target) {
                succ.push(root);
                break;
            } else if(root.val > target) {
                succ.push(root);
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }

    private void initializePredecessorStack(TreeNode root, double target, Stack<TreeNode> pred) {
        while(root != null){
            if(root.val == target){
                pred.push(root);
                break;
            } else if(root.val < target){
                pred.push(root);
                root = root.right;
            } else{
                root = root.left;
            }
        }
    }

    private int getNextSuccessor(Stack<TreeNode> succ) {
        TreeNode curr = succ.pop();
        int ret = curr.val;
        curr = curr.right;
        while(curr != null) {
            succ.push(curr);
            curr = curr.left;
        }
        return ret;
    }

    private int getNextPredecessor(Stack<TreeNode> pred) {
        TreeNode curr = pred.pop();
        int ret = curr.val;
        curr = curr.left;
        while(curr != null) {
            pred.push(curr);
            curr = curr.right;
        }
        return ret;
    }
}