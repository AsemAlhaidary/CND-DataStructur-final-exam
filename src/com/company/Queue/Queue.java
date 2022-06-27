package com.company.Queue;

import com.company.LinkedList.LinkedList;
import com.company.LinkedList.Node;
import com.company.Stack.Stack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Queue<T> {
    private final LinkedList<T> queueList = new LinkedList<T>();
    private final LinkedList<T> tempList = new LinkedList<T>();
    private Node<T> head = queueList.getHead(), tail = queueList.getTail();

    public void start() {
        Scanner userInput = new Scanner(System.in);
        int choice;
        T elem;

        while (true) {
            System.out.println("==================================================");
            System.out.println("1) To 'PUSH' to the Queue");
            System.out.println("2) To 'POP' from the Queue");
            System.out.println("3) To 'PRINT' the Queue");
            System.out.println("4) To 'INVERSE' the Queue");
            System.out.println("5) To 'DELETE' from the Queue");
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
                    System.out.print("Enter Element to add to the QUEUE: ");
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
                    System.out.print("Enter Element to delete from the QUEUE: ");
                    elem = (T) userInput.next();
                    delete(elem);
                    break;
                case 0:
                    System.out.println("Exiting from Queue...");
                    return;
                default:
                    System.out.println("Unkown choice -_- !!!");
            }
        }
    }

    public void push(T elem, boolean logging) {
        if (queueList.add(elem, false))
            if (logging) System.out.println(elem + " is ADDED.");
    }

    public T pop(boolean logging) {
        if (queueList.isValid()) {
            T popped = queueList.getFirstValue();

            if (queueList.remove(popped))
                if (logging) System.out.println(popped + " is POPPED.");

            return popped;
        }

        System.out.println("The Queue is empty!!!");
        return null;
    }

    public boolean isValid() {
        return queueList.isValid();
    }

    public void delete(T elem) {
        if (queueList.isValid()) {
            if (queueList.remove(elem)) System.out.println(elem + " is DELETED.");
        } else {
            System.out.println("The Queue is empty!!!");
        }
    }

    private void print() {
        System.out.print("{");
        while (queueList.isValid()) {
            System.out.print(queueList.getFirstValue() + ", ");
            moveDataToTemp();
        }
        System.out.println("}");

        while (tempList.isValid()) {
            getDataFromTemp();
        }
    }

    private void inverse() {
        Stack<T> stack = new Stack<T>();

        while (queueList.isValid()) {
            stack.push(pop(false), false);
        }

        while (stack.isValid()) {
            push(stack.pop(false), false);
        }

        System.out.print("Queue was inverted successfully: ");
        print();
    }

    private void moveDataToTemp() {
        tempList.add(queueList.getFirstValue(), false);
        queueList.remove(queueList.getFirstValue());
    }

    private void getDataFromTemp() {
        queueList.add(tempList.getFirstValue(), false);
        tempList.remove(tempList.getFirstValue());
    }
}
