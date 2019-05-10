package sample;


import java.util.Scanner;

public class Main {

    public static double armat(double tiv) {

        if (tiv ==0 || tiv==1){
            return tiv;
        }
        double temp;

        double count = tiv / 2;

        do {
            temp = count;
            count = (temp + (tiv / temp)) / 2;
        } while ((temp - count) != 0);

        return count;
    }

    public static void main(String[] args)
    {
        System.out.print("mutqagreq tiv@");
        Scanner scanner = new Scanner(System.in);
        double num = scanner.nextDouble();
        if (num<0){
            System.out.println("bacasakan tiv");
        }else {

        System.out.println(num+ " i armaty: "+armat(num));}
    }
}
