package dev.shroysha.widgets.hiddenfiletoggle.model;

import java.util.Random;


public class Nums {


    public static void main(String[] args) {
        Random gen = new Random();

        int total = 0;

        for (int i = 0; i < 5; i++) {
            total += gen.nextInt(30);
        }

        total = total / 5;

        System.out.println(total);
    }


}
