package sample;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("nermuceq texty");
        String str = scanner.nextLine();
        if (!str.substring(str.length()-1).equals(":")) {
            System.out.println("verjum verjaket chka");
        }
        String[] strmasiv = str.split(":");
        for (int i = 0; i < strmasiv.length; i++) {
            if (strmasiv[i].equals("")) {
                System.out.println(i + 1 + " naxadastutyun@ datark e");
            } else {
                if (!(strmasiv[i].substring(0, 1).equals(strmasiv[i].substring(0, 1).toUpperCase()))) {
                    System.out.println(i + 1 + "  naxadasutyun@ sksum e poqratarov");
                }
            }
        }

        System.out.println(strmasiv.length + " naxadasutyun ka");
    }
}

