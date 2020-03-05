package com.CS435.project1.part2;

import java.util.ArrayList;
import java.util.List;

public class BSTSort {

    static BinarySearchTree bst ;
    static TreeNode root;

    public static List<Integer> Sort(List<Integer> list) {
        List<Integer> ret = new ArrayList<>();
        bst = new BinarySearchTree(list.get(0));
        for(int i = 1; i < list.size();i++){
            bst.insertRec(list.get(i));
        }
        root = bst.getRoot();
        inOrder(ret, root);
        return ret;
    }

    private static void inOrder(List<Integer> sortedList, TreeNode curr){
        if(curr == null){
            return;
        }
        inOrder(sortedList, curr.left);
        sortedList.add(curr.val);
        inOrder(sortedList, curr.right);
    }
}
