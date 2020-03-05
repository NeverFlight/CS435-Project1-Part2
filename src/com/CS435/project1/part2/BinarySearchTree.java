package com.CS435.project1.part2;

public class BinarySearchTree {

    private TreeNode root;
    private int size, levelTraverse;

    public int getSize() {
        return size;
    }

    public int getLevelTraverse() {
        return levelTraverse;
    }

    public TreeNode getRoot() {
        return root;
    }

    public BinarySearchTree(int val){
        root = new TreeNode(val);
        size = 0;
        levelTraverse = 0;
    }

    // Easier public API offered here.

    public void insertRec(int val){
        root = insertRec(this.root, val);
        size++;
    }

    public void deleteRec(int val){
        root = deleteRec(this.root, val);
        size--;
    }

    public void insertIter(int val){
        root = insertIter(this.root, val);
        size++;
    }

    public void deleteIter(int val){
        root = deleteIter(this.root, val);
        size--;
    }

    public int findMax(){
        return findMaxRec(this.root).val;
    }

    public int findMin(){
        return findMinRec(this.root).val;
    }

    // Recursive Version:

    private TreeNode insertRec(TreeNode root, int val){
        if(root == null){
            return new TreeNode(val);
        }
        if(val > root.val){
            levelTraverse++;
            root.right = insertRec(root.right,val);
        }else if(val < root.val){
            levelTraverse++;
            root.left = insertRec(root.left,val);
        }
        return root;
    }

    private TreeNode deleteRec(TreeNode root, int val){
        if(root == null) return null;
        if(root.val == val) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }else{
                TreeNode inSuccessor = findInSuccessor(root);
                deleteRec(root, inSuccessor.val);
                inSuccessor.left = root.left;
                inSuccessor.right = root.right;
                return inSuccessor;
            }
        }else if(val < root.val){
            root.left = deleteRec(root.left,val);
        }else{
            root.right = deleteRec(root.right, val);
        }
        return root;
    }


    // If the previous cannot be found, this method will return the null node.
    public TreeNode findPrevRec(int val){
        return findPrevRec(this.root, val, null);
    }

    private TreeNode findPrevRec(TreeNode root, int val, TreeNode prev){
        if(root == null) return prev;
        if(root.val >= val){
            return findPrevRec(root.left, val, prev);
        }
        if(prev == null || root.val > prev.val){
            prev = root;
//            if(root.right == null) return prev;
        }
        return findPrevRec(root.right, val, prev);
    }

    // If the next cannot be found, this method will return the null node.
    public TreeNode findNextRec(int val){
        return findNextRec(this.root, val, null);
    }

    private TreeNode findNextRec(TreeNode root, int val, TreeNode next){
        if(root == null) {
            return next;
        }
        if(root.val <= val){
            return findNextRec(root.right, val, next);
        }
        if(next == null || root.val < next.val){
            next = root;
            // if(root.left == null) return next;
        }
        return findNextRec(root.left, val, next);
    }


    private TreeNode findInSuccessor(TreeNode root){
        if(root.left != null){
            return findMaxRec(root.left);
        }else if(root.right != null){
            return findMinRec(root.right);
        }
        return null;
    }

    public TreeNode findMaxRec(TreeNode root){
        if(root.right == null){
            return root;
        }
        return findMaxRec(root.right);
    }

    public TreeNode findMinRec(TreeNode root){
        if(root.left == null){
            return root;
        }
        return findMinRec(root.left);
    }

    // Iterative Version:

    private TreeNode insertIter(TreeNode root, int val){
        TreeNode curr = root;
        while(curr != null){
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
            levelTraverse++;
        }
        return root;
    }

    public TreeNode deleteIter(TreeNode root, int val){
        TreeNode curr = root, parent = null;
        while(curr != null && curr.val != val){
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

    public void printTree(){
        TreePrinter.print(this.root);
    }

}

