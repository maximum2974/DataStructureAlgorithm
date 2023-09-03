package com.maximum.recursion;

public class PascalTriangle {
    private static int element1(int[][] triangle, int i, int j){
        if(triangle[i][j] > 0){
            return triangle[i][j];
        }
        if(j == 0 || i == j){
            triangle[i][j] = 1;
            return 1;
        }
        triangle[i][j] = element1(triangle, i - 1, j - 1) + element1(triangle, i - 1, j);
        return triangle[i][j];
    }

    private static void printSpace(int n, int i){
        int num = (n - 1 - i) * 2;
        for (int j = 0; j < num; j++) {
            System.out.print(" ");
        }
    }

    public static void print1(int n){
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i + 1];
            printSpace(n , i);
            for(int j = 0; j <= i; j++){
                System.out.printf("%-4d", element1(triangle,i,j));
            }
            System.out.println();
        }
    }

    private static void createRow(int[] row, int i){
        if(i == 0){
            row[0] = 1;
            return;
        }
        for (int j = i; j > 0; j--) {
            row[j] = row[j] + row[j - 1];
        }
    }

    public static void print2(int n){
        int[] row = new int[n];
        for (int i = 0; i < n; i++) {
            printSpace(n, i);
            createRow(row, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", row[j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print2(10);
    }
}
