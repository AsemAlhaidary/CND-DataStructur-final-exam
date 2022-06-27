package com.company.Tree;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Tree {

    Node root = null, current, node, temp;
    private int size;

    public void start() {
        Scanner userInput = new Scanner(System.in);
        int choice, num;

        while (true) {
            System.out.println("==================================================");
            System.out.println("1) To 'INSERT' to the Tree");
            System.out.println("2) To 'PRINT' the Tree");
            System.out.println("3) To 'SEARCH' in the Tree");
            System.out.println("0) To 'EXIT'");
            System.out.print("\nYour choice: ");

            try {
                userInput = new Scanner(System.in);
                choice = userInput.nextInt();
            } catch (InputMismatchException e) {
                choice = -1;
            }

            System.out.println("==================================================");

            switch (choice) {
                case 1:
                    System.out.print("Enter a number to add to the TREE: ");
                    try {
                        num = userInput.nextInt();
                        insert(num, true);
                    } catch (InputMismatchException e) {
                        System.err.println("Incorrect Input!!!");
                        System.out.println("==================================================");
                    }
                    break;
                case 2:
                    System.out.print("{");
                    print(root);
                    System.out.println("}");
                    break;
                case 3:
                    System.out.print("Enter Element to search in the TREE: ");
                    try {
                        num = userInput.nextInt();
                        search(num);
                    } catch (InputMismatchException e) {
                        System.err.println("Incorrect Input!!!");
                        System.out.println("==================================================");
                    }
                    break;
                case 0:
                    System.out.println("Exiting from Tree...");
                    return;
                default:
                    System.out.println("Chose an option from the given!!!");
            }
        }
    }

    public void insert(int data, boolean logging) {
        node = new Node(data);

        if (setNode()) {
            inceaseSize();

            if (logging) System.out.println(data + " is ADDED.");
        }
    }

    public void print(Node root) {
        if (root == null) {
            return;
        } else {
            System.out.print(root.data + ", ");
            print(root.left);
            print(root.right);
        }
    }

    public void search(int elem) {
        node = root;
        while (node != null) {
            if (elem == node.data) {
                System.out.println("Elemet was found ^_^");
                return;
            } else if (elem > node.data) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        System.out.println("Elemet was not found -_-");
    }

    private void inceaseSize() {
        ++this.size;
    }

    private boolean setNode() {
        if (root == null) {
            root = node;
        } else {
            moveToLast();
            addNode();
        }

        return true;
    }

    private void addNode() {
        if (node.data >= current.data) {
            current.right = node;
        } else {
            current.left = node;
        }
    }

    private void moveToLast() {
        initNode();

        while (temp != null) {
            current = temp;
            if (node.data >= current.data) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
    }

    private void initNode() {
        temp = root;
    }
}
