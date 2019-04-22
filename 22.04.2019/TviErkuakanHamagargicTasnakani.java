package sample;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        System.out.println ("nermuceq tivy erkuakan hamakargov");
        long caunt = 0;
        String strTiv = scanner.nextLine ();
        strTiv = strTiv.replaceAll ("\\D+", "a");

        if (strTiv.contains ("a") || strTiv.length () > (64 - 1)) {
            throw new IllegalArgumentException ("Error");
        } else {
//                    System.out.println (Integer.parseInt (strTiv,2));
//                    porceci derqov havaqel
            for (int i = (strTiv.length () - 1), j = 0; i >= 0; i--, j++) {
                if (Integer.valueOf (strTiv.substring (j, j + 1)) > 1 || Integer.valueOf (strTiv.substring (j, j + 1)) < 0) {
                    throw new IllegalArgumentException ("Tivy Erkuakan hamakargov chi");
                }
                if (strTiv.substring (j, j + 1).equals ("1")) {
                    caunt = (long) (caunt + Math.pow (2, i));
                }
            }
            System.out.println (caunt);
        }
    }
}
