package com.egoo.seckill.bigfish.test;

import com.egoo.seckill.bigfish.utils.CV;

import java.util.Random;

public class SelfTest {

    public static void main(String[] args) {


        double bases = CV.toD(100D);

        double finalTemp = 0D;

        for (int i = 0 ; i<3 ; i++){

            System.out.println("bases = "+bases);


            double temp = CV.toD_down(new Random().nextDouble() * bases);

            System.out.println("temp = "+temp );
            System.out.println("finalTemp = "+CV.toD(finalTemp+=temp));
            if(temp == 0D){break;}

            bases = CV.toD(bases - CV.toD(temp) );

            System.out.println("final :"+bases);


        }

        System.out.println("over:="+bases);
    }
}
