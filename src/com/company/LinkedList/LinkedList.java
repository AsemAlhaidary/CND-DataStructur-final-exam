package com.company.LinkedList;

import org.jetbrains.annotations.NotNull;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LinkedList<T> {

    Node<T> node = null, head = null, tail = null;
    private int size;

    public void start() {
        Scanner userInput = new Scanner(System.in);
        int choice;
        T elem;

        while (true) {
            try {
                System.out.println("==================================================");
                System.out.println("1) to INSERT element");
                System.out.println("2) to PRINT elements");
                System.out.println("3) to DELETE element");
                System.out.println("4) to SEARCH for element");
                System.out.println("0) to EXIT");

                try {
                    userInput = new Scanner(System.in);
                    choice = userInput.nextInt();
                } catch (InputMismatchException e) {
                    choice = -1;
                }

                System.out.println("==================================================");

                switch (choice) {
                    case 1:
                        System.out.print("Enter Element to add to the LIST: ");
                        elem = (T) userInput.next();
                        add(elem, true);
                        break;
                    case 2:
                        print();
                        break;
                    case 3:
                        System.out.print("Enter Element to remove from the LIST: ");
                        elem = (T) userInput.next();
                        remove();
                        break;
                    case 4:
                        System.out.print("Enter Element to search in the LIST: ");
                        elem = (T) userInput.next();
                        search(elem);
                        break;
                    case 0:
                        System.out.println("Exiting from Linked List...");
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

    public boolean add(T data, boolean logging) {
        node = new Node<T>(data);

        if (linkNode(node)) {
            inceaseSize();

            if (logging) System.out.println(data + " is ADDED.");
        }

        return true;
    }

    public boolean remove() {
        return remove(tail.data);
    }

    public boolean remove(T elem) {
        int found = search(elem);

        switch (found) {
            case 1:
                removeHead();
                break;
            case 2:
                removeMiddle();
                break;
            case 3:
                removeTail();
                break;
            default:
                System.out.println("This element is not exists.");
                return false;
        }

        decreaseSize();
        return true;
    }

    public boolean isFound(T elem) {
        if (search(elem) > 0) {
            System.out.println(elem + " is found.");
            return true;
        }

        System.out.println(elem + " is not found.");
        return false;
    }

    public boolean isValid() {
        return (head != null);
    }

    public Node<T> getIndex(int index) {
        if (index + 1 <= size) {
            for (int i = 0; i <= index + 1; i++) {
                move();
            }

            return node;
        }

        return null;
    }

    public int getSize() {
        return this.size;
    }

    public T getLastValue() {
        if (tail != null) return getValue(tail);
        return null;
    }

    public T getFirstValue() {
        if (head != null) return getValue(head);
        return null;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void initNode() {
        node = head;
    }

    private void print() {
        if (head == null) {
            System.out.println("List is Empty");
        } else {
            initNode();
            System.out.print("{");
            while (isValid()) {
                System.out.print(node.data + ", ");
                node = node.next;
            }
            System.out.println("}");
        }
    }

    private void removeTail() {
        node = tail;
        tail = tail.prev;
        tail.next = null;
        node.prev = null;
        node = null;
    }

    private void removeMiddle() {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = null;
        node.prev = null;
        node = null;
    }

    private void removeHead() {
        initNode();
        if (head.next != null) {
            head = head.next;
            node.next = null;
            head.prev = null;
            node = null;
        } else {
            head = tail = node = null;
        }
    }

    private T getValue(@NotNull Node<T> node) {
        return node.data;
    }

    /**
     * Links the new node that were added to the list
     */
    private boolean linkNode(Node<T> node) {
        if (head == null) {
            node.next = node.prev = null;
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            node.next = null;
            tail = node;
        }

        return true;
    }

    /**
     * Checks if the element is exists or not
     *
     * @param elem - Element to search for
     * @return 3 - If the element is the last element
     */
    private int search(T elem) {
        if (head.data.equals(elem)) {
            return 1;
        } else if (tail.data.equals(elem)) {
            return 3;
        } else {
            moveTo(elem);
            if (node != null) return 2;
        }
        return 0;
    }

    /**
     * Will move the current node to the passed param
     *
     * @param elem
     */
    private void moveTo(T elem) {
        initNode();

        while (node != null) {
            if (node.data.equals(elem)) {
                return;
            } else {
                move();
            }
        }
    }

    private void move() {
        node = node.next;
    }

    private void inceaseSize() {
        ++this.size;
    }

    private void decreaseSize() {
        --this.size;
    }
}
