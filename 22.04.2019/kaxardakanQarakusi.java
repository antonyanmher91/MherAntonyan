package sample;

public class Main {

    public static void main(String[] args) {
        //nermuceq qarakusu tvery
        int[][] arr = {
                {
                        21, 71, 61
                },
                {
                        91, 51, 11
                },
                {
                        41, 31, 81}
        };
        //humnakan cragir
        int ankyunagcitverigumar = 0;
        int caunt = 0;
        int toxiTverigumar = 0;
        for (int i = 0, j = arr.length - 1; i < arr.length; j--, i++) {
            ankyunagcitverigumar += arr[i][i];
            caunt += arr[i][j];
            if ((arr[i][i] == arr[j][j] && i != j) || (arr[i][j] == arr[j][i] && i != j)) {
                break;
            }
        }
        if (ankyunagcitverigumar == caunt) {
            for (int i = 0; i < arr.length; i++) {
                caunt = 0;
                toxiTverigumar = 0;
                for (int j = 0; j < arr.length; j++) {
                    caunt += arr[i][j];
                    toxiTverigumar += arr[j][i];
                }
                if (caunt != toxiTverigumar || ankyunagcitverigumar != caunt) {
                    break;
                }

            }

        }
        if (ankyunagcitverigumar == caunt && ankyunagcitverigumar == toxiTverigumar) {
            System.out.println ("kaxardakana");

        } else {
            System.out.println ("kaxardakan chi");
        }


    }

}

