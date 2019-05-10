package sample;


import java.util.HashSet;
import java.util.Set;

public class Main {
    static int factoiral(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public static void main(String[] args) {
        Set set = new HashSet();
        String str = "abcdef";
        char[] str1 = str.toCharArray();
        char t;
        int caunt = 0;
       durs: while(true){
        for (int i = 0; i < str1.length; i++) {

            for (int j = 0; j < str1.length; j++) {
                t = str1[j];
                str1[j] = str1[i];
                str1[i] = t;
                ++caunt;
                str = String.valueOf(str1);
                set.add(str);
                t = str1[0];
                str1[0] = str1[str1.length-1];
                str1[str1.length-1] = t;
                str = String.valueOf(str1);
                set.add(str);


            }

            }
           if (caunt>factoiral(str1.length)){
               break durs;
        }

    }
        System.out.println(set);
        System.out.println("popoxutyunneri qanak@" +" "+factoiral(str1.length));}


}
