package sample;

public class Main {

    public static void main(String[] args) {
            //nermuceq tiv@
        double nermucvoxTiv = 3.1228;
        //cragir
        String str = String.valueOf(nermucvoxTiv);
        int caunt = str.substring(str.indexOf(".") + 1).length();
        str = str.replace(".", "");
        int tiv = Integer.valueOf(str);
        if (nermucvoxTiv%1==0){
            System.out.println("tiv@ tasnortakan hamakargov chi");
        }
         else if (tiv % 2 != 0 && tiv % 10 != 5) {
            System.out.println(tiv + "/" + Math.pow(10, caunt));
        } else if (nermucvoxTiv % 10 == 5) {
            System.out.println(tiv / 5 + "/" + (Math.pow(10, caunt) / 5));
        } else {
             int temp=(int) (Math.pow(10, caunt));
          while (tiv%2==0){
              tiv=tiv/2;
              temp= temp/2;
              caunt--;
          }
            System.out.println(tiv + "/" + temp);
            }

    }
}

