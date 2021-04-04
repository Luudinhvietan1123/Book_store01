package com.example.Book_store.TestPackage;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bai1 bai1 = new Bai1();
        System.out.println("Nhập số phần tử của mảng: ");
        int nbelement = scanner.nextInt();
        int arr[] = new int[nbelement];
        bai1.nhap(arr, nbelement);
        bai1.xuat(arr, nbelement);
    }
}
class Bai1{

    Scanner scanner = new Scanner(System.in);

    public void nhap(int[] arr, int nbelement){
        arr = new int[nbelement];
        for(int i=0;i<nbelement;i++){
            arr[i] = scanner.nextInt();
        }
    }

    public void xuat(int[] arr, int nbelement){
        for(int i=0;i<nbelement;i++){
            System.out.println("a["+(i+1)+"]="+arr[i]);
        }
    }
}