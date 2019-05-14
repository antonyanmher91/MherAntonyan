package sample;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String str;

    public static void quickSort(int[] array, int skizb, int verj, String a) {
        if (array.length == 0 || skizb >= verj)
            return;

        int count = skizb + (verj - skizb) / 2;
        int arrCount = array[count];
        int i = skizb, j = verj;
        while (i <= j) {
            if (a.equals("P")) {
                while (array[i] < arrCount) {
                    i++;
                }

                while (array[j] > arrCount) {
                    j--;
                }
            } else if (a.equals("M")) {

                while (array[i] > arrCount) {
                    i++;
                }

                while (array[j] < arrCount) {
                    j--;
                }
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (skizb < j)
            quickSort(array, skizb, j, str);

        if (verj > i)
            quickSort(array, i, verj, str);
    }

    public static void main(String[] args) {
        System.out.println("nermuceq tveri qanak@");
        Scanner scanner = new Scanner(System.in);
        int tveriqanak = 0;
        try {
            tveriqanak = scanner.nextInt();
            int[] arr = new int[tveriqanak];
            for (int i = 0; i < arr.length; i++) {
                System.out.println("nermuceq " + i + " elementy");
                arr[i] = scanner.nextInt();
            }
            System.out.println("nermuceq sortavorman funkcian");
            System.out.println("M-mecic poqr  /P-poqric mec ");
            str = scanner.next();
            System.out.println("Duq nermucel eq"+Arrays.toString(arr));
            if (str.equals("M")|| str.equals("P")){
            quickSort(arr, 0, arr.length - 1, str);
            System.out.println("sortavorvac masiv"+Arrays.toString(arr));}
            else {
                System.out.println("nermuceq M kam P sortavorman hamar");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("nermuceq int tipi tiv");
        }
    }
}