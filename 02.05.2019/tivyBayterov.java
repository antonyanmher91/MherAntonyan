package sample;


import java.util.Arrays;

public class Main  {


    public static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }

    public static void main(String[] args) {
        int value = 1024;
        System.out.println(Arrays.toString(intToByteArray(value)));

    }
}
