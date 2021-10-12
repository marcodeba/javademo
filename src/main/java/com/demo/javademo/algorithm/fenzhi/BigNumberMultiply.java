package com.demo.javademo.algorithm.fenzhi;

import java.util.Arrays;

public class BigNumberMultiply {

    public static void main(String[] args) {
//        int n = 3278;
//        Node number = new Node(n);
//        // 分治法，把数字一拆为二
//        int ma = number.length / 2;
//        System.out.println("大数的高位" + divideNumber(number, ma, number.length - ma));
//        System.out.println("大数的低位" + divideNumber(number, 0, ma));

//        Node a = new Node(900);
//        Node b = new Node(87000);
//        multiply(a, b);

        Node a = new Node(980000);
        System.out.println(a);
        Node b = new Node(67300);
        System.out.println(b);
    }

    /**
     * 把大数拆分成高位和低位
     *
     * @param src    待分解的数
     * @param st     stc中取数的开始位置
     * @param length
     */
    public static Node divideNumber(Node src, int st, int length) {
        // 分解后得到的数
        Node des = new Node(new int[length], length, st + src.power);
        for (int i = st, j = 0; i < st + length; i++, j++) {
            des.s[j] = src.s[i];
        }
        return des;
    }

    public static Node multiply(Node a, Node b) {
        int ma = a.length >> 1, mb = b.length >> 1;
        //Node ah, al, bh, bl;
        Node answer = new Node(new int[a.length + b.length]);
        if (ma == 0 || mb == 0) {
            if (ma == 0) {
                Node temp = a;
                a = b;
                b = temp;
            }
            answer.power = a.power + b.power;
            int w = b.s[0];
            int cc = 0;
            int i;
            for (i = 0; i < a.length; i++) {
                answer.s[i] = (w * a.s[i] + cc) % 10;
                cc = (w * a.s[i] + cc) / 10;
            }
            if (cc != 0) {
                answer.s[i++] = cc;
            }
            answer.length = i;
        }
        return answer;
    }

    public static void add(Node a, Node b) {
        Node answer = new Node(new int[a.length + b.length]);
        // 确保a的幂次大
        if (a.power < b.power) {
            Node temp = a;
            a = b;
            b = temp;
        }
        // 两个数中小的幂次
        answer.power = b.power;
        // 进位标识
        int cc = 0;
        int k = a.power - b.power;
        int palen = a.length + a.power;
        int pblen = b.length + b.power;
        // 总长度的最大值
        int len = palen > pblen ? palen : pblen;
    }

    static class Node {
        // 数组倒序存储的最大数
        int[] s;
        // 代表数的位数
        int length;
        // 代表数的次幂
        int power = 0;

        public Node(int[] s) {
            this.s = s;
        }

        public Node(int[] s, int length, int power) {
            this.s = s;
            this.length = length;
            this.power = power;
        }

        /**
         * 将一个数字初始化为Node对象
         *
         * @param number
         */
        public Node(int number) {
            int power = 0;
            while (number % 10 == 0) {
                number = number / 10;
                power++;
            }
            this.length = String.valueOf(number).length();
            this.power = power;
            this.s = new int[this.length];
            int i = 0;
            while (number > 0 && i < this.length) {
                this.s[i++] = number % 10;
                number = number / 10;
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "length=" + length +
                    ", s=" + Arrays.toString(s) +
                    ", power=" + power +
                    '}';
        }
    }
}