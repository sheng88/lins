package com.lin.poc;

import java.lang.Math;

public class MyCalculator {

    public static void main(String args[]){
        try {
            System.out.println(String.valueOf(power(5, 0)));
            System.out.println(String.valueOf(Math.pow(5, 0)));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static int power(int n, int p) throws Exception {
        if( n < 0 || p < 0){
            throw new Exception("n and p should be non-negative");
        }

        if(n == 0) {
            return 0;
        }

        int result = 1;
        for(int i = 1; i <= p; i++){
            result = result*n;
        }

       return result;
    }


}
