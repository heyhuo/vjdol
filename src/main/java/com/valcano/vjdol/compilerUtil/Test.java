package com.valcano.vjdol.compilerUtil;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //提交的代码，循环进行输入
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            System.out.println(a + b);
        }
    }
}