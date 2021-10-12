package com.demo.javademo.dataStructure.linkedlist;

public class ReverseList {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;

        Node reverse = reverseList(n1);
        System.out.println(reverse.getData());
    }

    public static Node reverseList(Node head) {
        if (head == null) return null;

        Node previous = head;
        Node current = head.next;
        Node tempNode;
        while (current != null) {
            tempNode = current.next;
            current.next = previous;
            previous = current;
            current = tempNode;
        }
        head.setNext(null);

        return previous;
    }

    static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
