package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CheckAvailability {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        try {
            Scanner sc=new Scanner(System.in);
            String time=sc.nextLine();
            // Parse the current time
            Date currentTime = sdf.parse(time);

            // Parse the start and end times of the given period
            Date startTime = sdf.parse("01:40 PM");
            Date endTime = sdf.parse("11:40 PM");

            // Check if the current time is within the given period
            if (currentTime.after(startTime) && currentTime.before(endTime)) {
                System.out.println("The current time is within the given period.");
            } else {
                System.out.println("The current time is not within the given period.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
