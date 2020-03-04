package com.CS435.project1.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AVLTree {
    List<TreeNode> ancestor;
    TreeNode root;
    public AVLTree(int key){
        root = new TreeNode(key);
    }

    public void insert(int val){
        System.out.println("Insert " + val + " into the tree: ");
        insert(root, val);
    }

    public void delete(int val){
        System.out.println("Delete " + val + " from the tree: ");
        delete(root, val);
    }

    public void printTree(){
        TreePrinter.print(root);
    }
//        C
//       /
//      B
//     / \
//    A   Br
//
//  After right Rotation:
//       B
//      / \
//     A   C
//        /
//       Br
// So we know we don't need to care about A.
// We need to take care of right kid of B.

    private TreeNode rightRotation(TreeNode C){

        TreeNode B = C.left;
        TreeNode Br = B.right;

        B.right = C;
        C.left = Br;

        updateHeight(B);
        updateHeight(C);
        return B;
    }


//    C
//     \
//      B
//     / \
//    Bl   A
//
//  After right Rotation:
//       B
//      /
//     C
//    / \
//   A   Bl
// So we know we don't need to care about A.
// We need to take care of right kid of B.

    private TreeNode leftRotation(TreeNode C){

        TreeNode B = C.right;
        TreeNode Bl = B.left;

        B.left = C;
        C.right = Bl;

        updateHeight(B);
        updateHeight(C);
        return B;
    }

    private TreeNode rightLeftRotation(TreeNode curr){
        curr.right = rightRotation(curr.right);
        return leftRotation(curr);
    }

    private TreeNode leftRightRotation(TreeNode curr){
        curr.left = leftRotation(curr.left);
        return rightRotation(curr);
    }

    private void rotation(TreeNode parent, TreeNode curr, int BF, int val){
        TreeNode newKid;
        if(BF > 1 && val < curr.left.val){
             newKid = rightRotation(curr);
        }
        else if(BF < -1 && val > curr.right.val){
            newKid = leftRotation(curr);
        }else if (BF > 1 && val > curr.left.val){
            newKid = leftRightRotation(curr);
        }else {
            newKid = rightLeftRotation(curr);
        }
        if(newKid.val < parent.val){
            parent.left = newKid;
        }else{
            parent.right = newKid;
        }
    }

    private void rotateRoot(int BF, int val){
        if(BF > 1 && val < root.left.val){
            root = rightRotation(root);
        }
        else if(BF < -1 && val > root.right.val){
            root = leftRotation(root);
        }else if (BF > 1 && val > root.left.val){
            root = leftRightRotation(root);
        }else {
            root = rightLeftRotation(root);
        }
    }


    private void updateHeight(TreeNode curr){
        int left, right;
        if(curr.left == null) left = -1;
        else left = curr.left.height;
        if(curr.right == null) right = -1;
        else right = curr.right.height;
        curr.height = Math.max(left, right) + 1;
//        System.out.println("Node " + curr.val + " has height: " + curr.height);
    }

    private void updateAncestor(){
        for(int i = ancestor.size() - 1; i >= 0; i--){
            updateHeight(ancestor.get(i));
        }
    }

    private TreeNode insert(TreeNode root, int val){
        ancestor = new ArrayList<>();
        TreeNode curr = root;
        while(curr != null){
            ancestor.add(curr);
            if(val < curr.val){
                if(curr.left == null){
                    curr.left = new TreeNode(val);
                    break;
                }
                curr = curr.left;
            }else if(val > curr.val){
                if(curr.right == null){
                    curr.right = new TreeNode(val);
                    break;
                }
                curr = curr.right;
            }
        }
        // 1. Update the weight of all ancestor
        updateAncestor();
        // 2. check the unbalanced node and if yes, rotate them.
        for(int i = ancestor.size() - 1; i > 0; i--){
            int BF = getBalanced(ancestor.get(i));
            if(BF >= 2 || BF <= -2){
                rotation(ancestor.get(i-1), ancestor.get(i),BF, val);
                updateAncestor();
            }
        }
        // 3. Check if the root is balanced or not.
        int BFRoot = getBalanced(root);
        if(Math.abs(BFRoot) >= 2){
            rotateRoot(BFRoot, val);
            updateAncestor();
        }
        return root;
    }

    private TreeNode delete(TreeNode root, int val){
        TreeNode curr = root, parent = null;
        ancestor = new ArrayList<>();
        while(curr != null && curr.val != val){
            ancestor.add(curr);
            parent = curr;
            if(val > curr.val){
                curr = curr.right;
            }else if(val < curr.val){
                curr = curr.left;
            }
        }
        if(parent == null){
            return deleteCurrNode(curr);
        }
        if(parent.left == curr){
            parent.left = deleteCurrNode(curr);
        }else{
            parent.right = deleteCurrNode(curr);
        }


        // 1. Update the weight of all ancestor
        updateAncestor();
        // 2. check the unbalanced node and if yes, rotate them.
        for(int i = ancestor.size() - 1; i > 0; i--){
            int BF = getBalanced(ancestor.get(i));
            if(BF >= 2 || BF <= -2){
                rotation(ancestor.get(i-1), ancestor.get(i),BF, val);
                updateAncestor();
            }
        }
        // 3. Check if the root is balanced or not.
        int BFRoot = getBalanced(root);
        if(Math.abs(BFRoot) >= 2){
            rotateRoot(BFRoot, val);
            updateAncestor();
        }

        return root;
    }

    private TreeNode deleteCurrNode(TreeNode root){
        if(root == null) return null;
        if (root.left == null && root.right == null) {
            return null;
        } else if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }else{
            TreeNode parent = null;
            TreeNode curr = root.right;
            while(curr.left != null){
                parent = curr;
                curr = curr.left;
            }
            curr.left = root.left;
            if(root.right != curr){
                parent.left = curr.right;
                curr.right = parent.right;
            }
            return curr;
        }
    }



    private int getBalanced(TreeNode curr){
        int left, right;
        if(curr.left == null) left = -1;
        else left = curr.left.height;
        if(curr.right == null) right = -1;
        else right = curr.right.height;
        return left - right;
    }

    public TreeNode findPrevIter(int val){
        return findPrevIter(this.root, val, null);
    }

    private TreeNode findPrevIter(TreeNode root, int val, TreeNode prev){
        if(root == null) return null;
        TreeNode curr = root;
        while(curr != null){
            if((prev == null || prev.val < curr.val) && val > curr.val){
                prev = curr;
                curr = curr.right;
            }else if(val <= curr.val){
                curr = curr.left;
            }
        }
        return prev;
    }

    public TreeNode findNextIter(int val){
        return findNextIter(this.root, val, null);
    }

    private TreeNode findNextIter(TreeNode root, int val, TreeNode next){
        if(root == null) return null;
        TreeNode curr = root;
        while(curr != null){
            if((next == null || next.val > curr.val) && val < curr.val){
                next = curr;
                curr = curr.left;
            }else if(val >= curr.val){
                curr = curr.right;
            }
        }
        return next;
    }


    public TreeNode findMaxIter(TreeNode root){
        while(root.right != null){
            root = root.right;
        }
        return root;
    }


    public TreeNode findMinIter(TreeNode root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }
}
