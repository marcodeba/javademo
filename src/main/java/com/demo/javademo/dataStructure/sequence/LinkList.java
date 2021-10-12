package com.demo.javademo.dataStructure.sequence;

public class LinkList<T> {
    private Node header;
    private Node tail;
    private int size;

    public LinkList() {
        header = null;
        tail = null;
    }

    public LinkList(T element) {
        header = new Node(element, null);
        tail = header;
        size++;
    }

    public static void main(String[] args) {
        LinkList<String> linkList = new LinkList<>();
        linkList.insert("aaaa", 0);
        linkList.addAtTail("bbbb");
        linkList.addAtTail("cccc");
        linkList.insert("dddd", 1);
        System.out.println(linkList);

        linkList.delete(2);
        System.out.println(linkList);

        System.out.println(linkList.get(2));
        System.out.println(linkList.locate("dddd"));
    }

    public int length() {
        return size;
    }

    public T get(int index) {
        return getNodeByIndex(index).data;
    }

    public Node getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表getNodeByIndex索引越界");
        }
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (i == index) {
                return current;
            }
        }
        return null;
    }

    public int locate(T element) {
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (current.data.equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表insert索引越界");
        }
        // 空链表
        if (header == null) {
            addAtTail(element);
        } else {
            if (index == 0) {
                addAtHeader(element);
            } else {
                Node prev = getNodeByIndex(index - 1);
                Node node = new Node(element, null);
                node.next = prev.next;
                prev.next = node;
                size++;
            }
        }
    }

    /**
     * 头插法为链表添加新节点
     */
    private void addAtHeader(T element) {
        header = new Node(element, header);
        if (tail == null) {
            tail = header;
        }
        size++;
    }

    /**
     * 尾插法为链表添加新节点
     */
    public void addAtTail(T element) {
        // 空链表
        if (header == null) {
            header = new Node(element, null);
            tail = header;
        } else {
            Node node = new Node(element, null);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T delete(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表delete索引越界");
        }
        Node delNode;
        // 删除头结点
        if (index == 0) {
            delNode = header;
            header = header.next;
        } else {
            Node prev = getNodeByIndex(index - 1);
            delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
        }
        size--;
        return delNode.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        header = tail = null;
        size = 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer("[");
            for (Node current = header; current != null; current = current.next) {
                sb.append(current.data.toString()).append(", ");
            }
            int length = sb.length();
            return sb.delete(length - 2, length).append("]").toString();
        }
    }

    private class Node {
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
