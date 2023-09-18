package org.example;

public class split {

    public static void main(String[] args) {
        String s="8:30 AM – 9:50 AM";


        String[] sp=s.split("–");

        System.out.println(sp.length);
    }
}
