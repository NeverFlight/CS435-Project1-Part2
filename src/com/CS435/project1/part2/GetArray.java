package com.CS435.project1.part2;

import java.util.*;

public class GetArray {
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
    public static void connstructIterRandomAVLTree(int n){
        AVLTree tree = new AVLTree(0);
        int[] rand = GetArray.getRandomArray(n);
        long startTime = System.nanoTime();
        for(int i : rand){
            tree.insert(i);
        }
        long endTime = System.nanoTime();
        float current = (endTime - startTime) / 1_000_000;
        System.out.println("The total running time of AVL tree is " + current  + " milliseconds, with " + n + " elements.");
    }

    public static void connstructRecBinarySearchTree(int n){
        BinarySearchTree bst = new BinarySearchTree(0);
        int[] rand = GetArray.getRandomArray(n);
        long startTime = System.nanoTime();
        for(int i : rand){
            bst.insertRec(i);
        }
        long endTime = System.nanoTime();
        float current = (endTime - startTime) / 1_000_000;
        System.out.println("The total running time of constructing Binary Search tree recursively is " + current + " millisecondsm, with " + n + " elements ");
    }

    public static void connstructIterBinarySearchTree(int n){
        BinarySearchTree bst = new BinarySearchTree(0);
        int[] rand = GetArray.getRandomArray(n);
        long startTime = System.nanoTime();
        for(int i : rand){
            bst.insertRec(i);
        }
        long endTime = System.nanoTime();
        float current = (endTime - startTime) / 1_000_000;
        System.out.println("The total running time of constructing Binary Search tree iteratively is " + current + " milliseconds, with " + n + " elements.");
    }

}