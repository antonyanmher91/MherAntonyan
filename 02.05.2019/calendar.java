package sample;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nermuceq amsativy (1-31)");
        int amsativ = scanner.nextInt();
        System.out.println("nermuceq amisy  (1-12)");
        int amis = scanner.nextInt();
        System.out.println("nermuceq taretivy  (YYYY)");
        int tarin = scanner.nextInt();
        if ((amsativ > 31 || amsativ < 0 || amis < 0 || amis > 12)) {
            System.out.println("Error");
        } else if (((amis == 2) && (amsativ > 28) && ((tarin % 4) != 0)) || ((amis == 2) && (amsativ >29) && (tarin % 4) == 0)||
                ((((amis == 4) || amis == 6 || (amis == 9) || (amis == 11)) && (amsativ > 30)))) {
            System.out.println("sxal eq nermucel");
        }else{
            Calendar calendar = Calendar.getInstance();
            calendar.set(tarin, amis - 1, amsativ);
            int ory = calendar.get(Calendar.DAY_OF_WEEK);
            switch (ory) {
                case 1:
                    System.out.println("kiraki");
                    break;
                case 2:
                    System.out.println("erkushabti");
                    break;
                case 3:
                    System.out.println("ereqshabti");
                    break;
                case 4:
                    System.out.println("choreqshabti");
                    break;
                case 5:
                    System.out.println("hingshabti");
                    break;
                case 6:
                    System.out.println("urbat");
                    break;
                case 7:
                    System.out.println("shabat");
                    break;
            }
        }
    }
}
