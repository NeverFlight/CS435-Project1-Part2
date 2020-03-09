package com.CS435.project1.part2;

import java.util.*;

public class GetArray {
    static int n = 10_000;
    static int[] rand;
    static int[] sort;
    static BinarySearchTree bst;
    public static void setArrayLength(int n) {
        GetArray.n = n;
        rand = GetArray.getRandomArray(n);
        sort = GetArray.getSortedArray(n);
    }

    public static int[] getRandomArray(int n){
        Set<Integer> unique = new HashSet<>();
        int[] arr = new int[n];
        Random random = new Random();
        for(int i = 0; i < n;i++){
            int randomNum = random.nextInt();
            while(unique.contains(randomNum)){
                randomNum = random.nextInt();
            }
            arr[i] = randomNum;
            unique.add(randomNum);
        }
        return arr;
    }

    public static int[] getSortedArray(int n){
        int[] arr = new int[n];
        for(int i = n; i > 0; i--){
            arr[n - i] = i;
        }
        return arr;
    }

    // Construct the AVLTree with size n.
    public static void constructIterRandomAVLTree(){
        AVLTree tree = new AVLTree(0);
        long startTime = System.nanoTime();
        for(int i : rand){
            tree.insert(i);
        }
        long endTime = System.nanoTime();
        float current = (endTime - startTime) / 1_000_000;
        System.out.println("For iteratively AVL tree constructing from random array, the total level order is " + tree.getLevelTraverse());
        System.out.println("The total running time of AVL tree is " + current  + " milliseconds, with " + n + " elements.");
    }



    public static void constructRecRandomBinarySearchTree(){
        bst = new BinarySearchTree(0);
        long startTime = System.nanoTime();
        for(int i : rand){
            bst.insertRec(i);
        }
        long endTime = System.nanoTime();
        float current = (endTime - startTime) / 1_000_000;
        System.out.println("For recursively bst constructing from random array, the total level order is " + bst.getLevelTraverse());
        System.out.println("The total running time of constructing Binary Search tree recursively is " + current + " millisecondsm, with " + n + " elements ");
    }


    public static void deleteRecRandomBinarySearchTree(){
        long startTime = System.nanoTime();
        for(int i : rand){
            bst.deleteRec(i);
        }
        long endTime = System.nanoTime();
        float current = (endTime - startTime) / 1_000_000;
        System.out.println("The total running time of constructing Binary Search tree iteratively is " + current + " milliseconds, with " + n + " elements.");
    }


    public static void constructIterRandomBinarySearchTree(){
        BinarySearchTree bst = new BinarySearchTree(0);
        long startTime = System.nanoTime();
        for(int i : rand){
            bst.insertRec(i);
        }
        long endTime = System.nanoTime();
        float current = (endTime - startTime) / 1_000_000;
        System.out.println("For iterative bst constructing from random array, the total level order is " + bst.getLevelTraverse());
        System.out.println("The total running time of constructing Binary Search tree iteratively is " + current + " milliseconds, with " + n + " elements.");
    }

    public static void constructIterSortBinarySearchTree(){
        BinarySearchTree bst = new BinarySearchTree(0);
        long startTime = System.nanoTime();
        for(int i : sort){
            bst.insertRec(i);
        }
        long endTime = System.nanoTime();
        float current = (endTime - startTime) / 1_000_000;
        System.out.println("For iterative bst constructing from sorted array, the total level order is " + bst.getLevelTraverse());
        System.out.println("The total running time of constructing Binary Search tree iteratively is " + current + " milliseconds, with " + n + " elements.");
    }

    // Construct the AVLTree with size n.
    public static void constructIterSortAVLTree(){
        AVLTree tree = new AVLTree(0);
        long startTime = System.nanoTime();
        for(int i : sort){
            tree.insert(i);
        }
        long endTime = System.nanoTime();
        float current = (endTime - startTime) / 1_000_000;
        System.out.println("For iteratively AVL tree constructing from sorted array, the total level order is " + tree.getLevelTraverse());
        System.out.println("The total running time of AVL tree is " + current  + " milliseconds, with " + n + " elements.");
    }
}