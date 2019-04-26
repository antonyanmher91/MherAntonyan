package sample;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
       int tiv = scanner.nextInt ();
        long result = 0;
        if (tiv<0){
            throw new IllegalArgumentException ("bacasakan tiv");
        }
        while (tiv != 0) {
            result = (result + (tiv % 10)) * 10;
            tiv = tiv / 10;
        }
        result/=10;
        System.out.println (result);
    }
}
