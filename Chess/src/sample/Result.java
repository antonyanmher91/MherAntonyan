package sample;

import java.util.Scanner;

 class Result {
      void result() {
        Chess chess;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mutqagreq xaxaqary Pawns-P, Bishops-B, Rooks-R,Knights-KH,, Queen-Q, King-K");
        String qar = scanner.nextLine();
        System.out.println("Mutqagreq skzbnakan Dirq@ orinak 5-3");
        String skizb = scanner.nextLine();
        System.out.println("Mutqagreq verjnakan dirq@ Dirq@ orinak 5-4");
        String verj = scanner.nextLine();
        try {
            int firstPositionX = Integer.valueOf(skizb.substring(0, 1));
            int firstPositionY = Integer.valueOf(skizb.substring(2));
            int lastPositionX = Integer.valueOf(verj.substring(0, 1));
            int lastPositionY = Integer.valueOf(verj.substring(2));
            if (stugum(firstPositionX, lastPositionX, firstPositionY, lastPositionY)) {

                switch (qar) {
                    case "P":
                        chess = new Pawns(firstPositionX, firstPositionY);
                        System.out.println(chess.xod(lastPositionX, lastPositionY));
                        break;
                    case "B":
                        chess = new Bishops(firstPositionX, firstPositionY);
                        System.out.println(chess.xod(lastPositionX, lastPositionY));
                        break;
                    case "R":
                        chess = new Rooks(firstPositionX, firstPositionY);
                        System.out.println(chess.xod(lastPositionX, lastPositionY));
                        break;
                    case "KH":
                        chess = new Knights(firstPositionX, firstPositionY);
                        System.out.println(chess.xod(lastPositionX, lastPositionY));
                        break;
                    case "Q":
                        chess = new Queen(firstPositionX, firstPositionY);
                        System.out.println(chess.xod(lastPositionX, lastPositionY));
                        break;
                    case "K":
                        chess = new King(firstPositionX, firstPositionY);
                        System.out.println(chess.xod(lastPositionX, lastPositionY));
                        break;
                }
            } else {
                System.out.println("Doskic dursa");
            }
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Mutqagreq tvyanler@ cisht");
            result();
        }

    }

     private boolean stugum(int x, int X, int y, int Y) {
         return x > 0 && x < 9 && X > 0 && X < 9 && y > 0 && y < 9 && Y > 0 && Y < 9;
     }
}
