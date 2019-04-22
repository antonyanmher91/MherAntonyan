package sample;


import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner (System.in);
        System.out.println ("nermuceq tivy");
        int count = scanner.nextInt ();
        for (int i = 1; i <= count; i++) {
            for (int j = 1; j <= count; j++) {

                if (i >= j) {
                    System.out.print (j + " ");
                } else {
                    System.out.print (" *");
                }

            }
            System.out.println ();
        }

    }
}
