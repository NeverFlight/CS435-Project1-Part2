package com.CS435.project1.part2;

import javax.swing.tree.TreeCellEditor;

public class TreeNode implements TreePrinter.PrintableNode {
    int val, height;
    TreeNode left, right;
    public TreeNode(int val){
        this.val = val;
        this.height = 0;
    }
    @Override
    public TreePrinter.PrintableNode getLeft() {
        return this.left;
    }

    @Override
    public TreePrinter.PrintableNode getRight() {
        return this.right;
    }

    @Override
    public String getText() {
        return String.valueOf(val);
    }
}
