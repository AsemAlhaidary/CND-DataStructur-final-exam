package com.company.Stack;

import com.company.LinkedList.LinkedList;
import com.company.Queue.Queue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Stack<T> {
    private final LinkedList<T> stackList = new LinkedList<T>();
    private LinkedList<T> tempList = new LinkedList<T>();

    public void start() {
        Scanner userInput = new Scanner(System.in);
        int choice;
        T elem;

        while (true) {
            try {
                System.out.println("==================================================");
                System.out.println("1) To 'PUSH' to the Stack");
                System.out.println("2) To 'POP' from the Stack");
                System.out.println("3) To 'PRINT' the Stack");
                System.out.println("4) To 'INVERSE' the Stack");
                System.out.println("5) To 'SEARCH' in the Stack");
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
                        System.out.print("Enter Element to add to the STACK: ");
                        elem = (T) userInput.next();
                        push(elem, true);
                        break;
                    case 2:
                        pop(true);
                        break;
                    case 3:
                        print();
                        break;
                    case 4:
                        inverse();
                        break;
                    case 5:
                        System.out.print("Enter Element to search in the STACK: ");
                        elem = (T) userInput.next();
                        search(elem);
                        break;
                    case 0:
                        System.out.println("Exiting from Stack...");
                        return;
                    default:
                        System.out.println("Chose an option from the given!!!");
                }

            } catch (InputMismatchException e) {
                System.out.println(e);
                break;
            }
        }
    }

    public void push(T data, boolean logging) {
        if (stackList.add(data, false))
            if (logging) System.out.println(data + " is ADDED.");
    }

    public T pop(boolean logging) {
        if (stackList.isValid()) {
            T popped = stackList.getLastValue();

            if (stackList.remove(popped))
                if (logging) System.out.println(popped + " is POPPED.");

            return popped;
        }

        System.out.println("The Queue is empty!!!");
        return null;
    }

    public void search(T data) {
        LinkedList<T> tempList = new LinkedList<T>();
        boolean isFound = false;
        stackList.initNode();

        while (stackList.isValid()) {
            if (stackList.getLastValue().equals(data)) {
                isFound = true;
                break;
            } else {
                moveDataToTemp();
            }
        }

        while (tempList.isValid()) {
            getDataFromTemp();
        }

        if (isFound) {
            System.out.println(data + " is Found.");
        } else {
            System.out.println(data + " is Not Found.");
        }
    }

    public boolean isValid() {
        return stackList.isValid();
    }

    public void getMax() {
        LinkedList<T> tempList = new LinkedList<T>();
        T maxElement = stackList.getLastValue();

        while (stackList.isValid()) {
            if ((int) maxElement < (int) stackList.getLastValue()) {
                maxElement = stackList.getLastValue();
            }
            moveDataToTemp();
        }

        System.out.println("The Max value in the stack is " + maxElement);

        while (tempList.isValid()) {
            getDataFromTemp();
        }
    }

    private void inverse() {
        Queue<T> queue = new Queue<T>();

        while (stackList.isValid()) {
            queue.push(pop(false), false);
        }

        while (queue.isValid()) {
            push(queue.pop(false), false);
        }

        System.out.print("Stack was inverted successfully: ");
        print();
    }

    private void print() {
        System.out.print("{");
        while (stackList.isValid()) {
            System.out.print(stackList.getLastValue() + ", ");
            moveDataToTemp();
        }
        System.out.println("}");

        while (tempList.isValid()) {
            getDataFromTemp();
        }
    }

    private void moveDataToTemp() {
        tempList.add(stackList.getLastValue(), false);
        stackList.remove(stackList.getLastValue());
    }

    private void getDataFromTemp() {
        stackList.add(tempList.getLastValue(), false);
        tempList.remove(tempList.getLastValue());
    }
}
