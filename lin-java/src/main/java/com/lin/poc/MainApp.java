package com.lin.poc;

public class MainApp {

    public static void main(String[] args) {

        System.out.println("Hello");

        String temp [] = {"2", "s"};

        System.out.println(temp.toString());

        System.out.println(String.valueOf(divide(4, 0)));

        System.out.println("Rounded float =" + (roundInt(3.1f)));
    }

    private static int divide(int a, int b){
        int c = -1;

                try{
            c = a/b;
                } catch(Exception e){
            System.err.print("ex");
                }
                finally{
                    System.err.print("some err");
                }

                return c;
    }

    private static int roundInt(float a){
        return (int) Math.ceil(a);
    }
}
