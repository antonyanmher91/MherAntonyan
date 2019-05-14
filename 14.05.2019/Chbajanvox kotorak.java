package sample;


import java.util.Scanner;

public class Main {

    public static int yndhanurhaytarar(int tiv1, int tiv2) {
        while (tiv1 != tiv2) {
            if (tiv1 > tiv2) {
                tiv1 -= tiv2;
            } else {
                tiv2 -= tiv1;
            }
        }
        return tiv1;

    }

    public static void chbajanvoxKotorak(double tiv) {
        int caunt = 0;
        long tiviAmboxjMas = (long) tiv;
        double tviTasnordakanMas = tiv - tiviAmboxjMas;
        while (tviTasnordakanMas != 0) {
            tiv *= 10;
            caunt++;
            tiviAmboxjMas = (long) tiv;
            tviTasnordakanMas = tiv - tiviAmboxjMas;
        }

        int numerator = (int) tiv;
        int yndhanurhaytarar = yndhanurhaytarar(numerator, (int) Math.pow(10, caunt));
        if (yndhanurhaytarar == 1) {
            System.out.println(numerator + "/" + (int) Math.pow(10, caunt));

        } else {
            System.out.println(numerator / yndhanurhaytarar + "/" + (int) Math.pow(10, caunt) / yndhanurhaytarar);

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            double tasnordakantiv = scanner.nextDouble();
            if (tasnordakantiv < 0&&Math.abs(tasnordakantiv)%1!=0) {
                System.out.print("-");
                chbajanvoxKotorak(Math.abs(tasnordakantiv));}else
            if (tasnordakantiv % 1 != 0) {
                chbajanvoxKotorak(tasnordakantiv);
            }
            else {
                System.out.println("tiv@ tasnordakan  hamakargov chi");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("trasnortakan tiv nermuceq");
        }

    }
}
