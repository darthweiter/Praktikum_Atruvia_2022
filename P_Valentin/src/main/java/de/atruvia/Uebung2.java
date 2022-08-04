package de.atruvia;

public class Uebung2 {
    public static void main(String[] args) {
        int arr[]={7,13,187,420,1337};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);

        }
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            sum=sum+arr[i];
        }
        System.out.println(sum);
    }
}

