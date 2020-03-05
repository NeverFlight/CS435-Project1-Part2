package com.CS435.project1.part2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree(0);

        GetArray.connstructIterBinarySearchTree(10_000);
        GetArray.connstructIterRandomAVLTree(10_000);
        GetArray.connstructRecBinarySearchTree(10_000);
    }

    public static void testAVLTree(){
        AVLTree tree = new AVLTree(0);
        tree.insert(18);
        tree.printTree();
        tree.insert(20);
        tree.printTree();
        tree.insert(22);
        tree.printTree();
        tree.insert(25);
        tree.printTree();
        tree.insert(21);
        tree.printTree();
        tree.insert(29);
        tree.printTree();
        tree.insert(33);
        tree.printTree();
        tree.insert(37);
        tree.printTree();
        tree.delete(18);
        tree.printTree();
        System.out.println(tree.findNextIter(20).val);
    }
}
