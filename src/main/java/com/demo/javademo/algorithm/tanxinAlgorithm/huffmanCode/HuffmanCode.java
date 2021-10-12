package com.demo.javademo.algorithm.tanxinAlgorithm.huffmanCode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

// 哈夫曼编码
public class HuffmanCode {
    public static void main(String[] args) {
        System.out.print("请结点总数：");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        HuffmanNode[] huffmanNodes = initHuffmanTree(n);
        huffmanNodes = buildHuffmanTree(huffmanNodes, n);
//        for (int i = 0; i < huffmanNodes.length; i++) {
//            System.out.println(huffmanNodes[i]);
//        }
        printHuffmanTree(huffmanNodes, n);
    }

    public static HuffmanNode[] initHuffmanTree(int nodeCount) {
        HuffmanNode[] huffmanNodes = new HuffmanNode[2 * nodeCount - 1];
//        huffmanNodes[0] = new HuffmanNode("a", 5);
//        huffmanNodes[1] = new HuffmanNode("b", 32);
//        huffmanNodes[2] = new HuffmanNode("c", 18);
//        huffmanNodes[3] = new HuffmanNode("d", 7);
//        huffmanNodes[4] = new HuffmanNode("e", 25);
//        huffmanNodes[5] = new HuffmanNode("f", 13);
//        huffmanNodes[6] = new HuffmanNode("", 0);
//        huffmanNodes[7] = new HuffmanNode("", 0);
//        huffmanNodes[8] = new HuffmanNode("", 0);
//        huffmanNodes[9] = new HuffmanNode("", 0);
//        huffmanNodes[10] = new HuffmanNode("", 0);

        Scanner input = new Scanner(System.in);
        for (int i = 0; i < huffmanNodes.length; i++) {
            if (i < nodeCount) {
                System.out.println("请输入第" + (i + 1) + "个结点值");
                String value = input.next();
                System.out.println("请输入第" + (i + 1) + "个结点权重");
                int weight = input.nextInt();
                huffmanNodes[i] = new HuffmanNode(value, weight);
            } else {
                huffmanNodes[i] = new HuffmanNode();
            }
        }

        return huffmanNodes;
    }

    public static HuffmanNode[] buildHuffmanTree(HuffmanNode[] huffmanNodes, int n) {
        int lchildWeight, lchildIndex, rchildWeight, rchildIndex;
        for (int i = 0; i < n - 1; i++) {
            lchildWeight = rchildWeight = Integer.MAX_VALUE;
            lchildIndex = rchildIndex = -1;
            for (int j = 0; j < n + i; j++) {
                if (huffmanNodes[j].getWeight() < lchildWeight && huffmanNodes[j].getParent() == -1) {
                    rchildWeight = lchildWeight;
                    rchildIndex = lchildIndex;
                    lchildWeight = huffmanNodes[j].getWeight();
                    lchildIndex = j;
                } else if (huffmanNodes[j].getWeight() < rchildWeight && huffmanNodes[j].getParent() == -1) {
                    rchildWeight = huffmanNodes[j].getWeight();
                    rchildIndex = j;
                }
            }
            huffmanNodes[n + i].setWeight(lchildWeight + rchildWeight);
            huffmanNodes[n + i].setLchild(lchildIndex);
            huffmanNodes[n + i].setRchild(rchildIndex);
            huffmanNodes[lchildIndex].setParent(n + i);
            huffmanNodes[rchildIndex].setParent(n + i);
        }

        return huffmanNodes;
    }

    public static void printHuffmanTree(HuffmanNode[] huffmanNodes, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(huffmanNodes[i].getValue() + ":");
            int parent = huffmanNodes[i].getParent();
            int index = i;
            LinkedList<Integer> linkedList = new LinkedList<>();
            while (parent != -1) {
                if (huffmanNodes[parent].getLchild() == index) {
                    linkedList.add(0);
                } else if (huffmanNodes[parent].getRchild() == index) {
                    linkedList.add(1);
                }
                index = parent;
                parent = huffmanNodes[parent].getParent();
            }
            Collections.reverse(linkedList);
            System.out.println(linkedList);
        }
    }
}

class HuffmanNode {
    private int weight = 0;
    private int parent = -1;
    private int lchild = -1;
    private int rchild = -1;
    private String value = "";

    public HuffmanNode() {
    }

    public HuffmanNode(String value, int weight) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getLchild() {
        return lchild;
    }

    public void setLchild(int lchild) {
        this.lchild = lchild;
    }

    public int getRchild() {
        return rchild;
    }

    public void setRchild(int rchild) {
        this.rchild = rchild;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "weight=" + weight +
                ", parent=" + parent +
                ", lchild=" + lchild +
                ", rchild=" + rchild +
                ", value='" + value + '\'' +
                '}';
    }
}
