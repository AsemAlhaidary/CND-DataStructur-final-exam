package com.company;

import com.company.LinkedList.LinkedList;
import com.company.Queue.Queue;
import com.company.Stack.Stack;
import com.company.Tree.Tree;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice;

        while (true) {
            System.out.println("==================================================");
            System.out.println("1) To go to 'STACK'");
            System.out.println("2) To go to 'QUEUE'");
            System.out.println("3) To go to 'LINKED LIST'");
            System.out.println("4) To go to 'TREE'");
            System.out.println("0) To EXIT the program.");
            System.out.print("\nYour choice: ");

            try {
                Scanner userInput = new Scanner(System.in);
                choice = userInput.nextInt();
            } catch (InputMismatchException e) {
                choice = -1;
            }

            System.out.println("==================================================");

            switch (choice) {
                case 1:
                    stack();
                    break;
                case 2:
                    queue();
                    break;
                case 3:
                    linkedList();
                    break;
                case 4:
                    tree();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Chose an option from the given!!!");
            }
        }
    }

    private static void stack() {
        Stack<Integer> stack = new Stack<Integer>();
        System.out.println("Entering to Stack...");
        stack.start();
    }

    private static void queue() {
        Queue<Integer> queue = new Queue<Integer>();
        System.out.println("Entering to Queue...");
        queue.start();
    }

    private static void linkedList() {
        LinkedList<String> linkedList = new LinkedList<String>();
        System.out.println("Entering to Linked List...");
        linkedList.start();
    }

    private static void tree() {
        Tree tree = new Tree();
        System.out.println("Entering to Tree...");
        tree.start();
    }
}
