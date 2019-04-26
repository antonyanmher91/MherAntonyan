package sample;


public class Main {
    public static void main(String[] args) {
        double[] kordinat1={0,-0.5};
        double[] kordinat2={0.5,0};
        double[] kordinat3={0,0.5};
        double koxmA = koxiErkarutyun (kordinat1, kordinat2);
        double koxmB = koxiErkarutyun (kordinat2, kordinat3);
        double koxmC = koxiErkarutyun (kordinat3, kordinat1);
        double p=(koxmA+koxmB+koxmC)/2;
        double shrjanagciSharavix=(koxmA*koxmB*koxmC)/(4*Math.sqrt ((p*(p-koxmA)*(p-koxmB)*(p-koxmC))));
        double srjanagciErkarutyun = 2*Math.PI*shrjanagciSharavix;
        System.out.println (srjanagciErkarutyun);

    }

    private static double koxiErkarutyun(double[] kordinat1, double[] kordinat2) {
        return Math.sqrt (Math.pow (((kordinat2[0]) -  (kordinat1[0])), 2) + Math.pow(kordinat2[1] -  (kordinat1[1]), 2));
    }
}
