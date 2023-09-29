package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import org.example.API.API;
import org.json.JSONArray;
import org.json.JSONObject;

public class FetchAPIData {

    static String routine="https://cse.uiu.ac.bd/notices/class-routine-fall-2023-dept-of-cse/";
    public static void main(String[] args) throws ParseException {
//        Scanner sc=new Scanner(System.in);
//        System.out.println("ID:");
        String id="011221408";




        ArrayList<String>Sat=new ArrayList<>();
        ArrayList<String>Sun=new ArrayList<>();
        ArrayList<String>Tue=new ArrayList<>();
        ArrayList<String>Wed=new ArrayList<>();

        FreeTimeExtractor fte=new FreeTimeExtractor();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");

        try {
            API api=new API();

            JSONObject json=api.call_API(id,routine);
            JSONArray data = json.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject course = data.getJSONObject(i);

//                System.out.println("Course Title: " + course.getString("Course Title"));
//                System.out.println("Section: " + course.getString("Section"));
//                System.out.println("Credit: " + course.getString("Credit"));
//                System.out.println("Day: " + course.getString("Day"));
//                System.out.println("Time: " + course.getString("Time"));


                if(Objects.equals(course.getString("Day"), "Sat T")){
                    String time=course.getString("Time");
                    Sat.add(time);
                    Tue.add(time);
                }else if(Objects.equals(course.getString("Day"), "Sat")){
                    String time=course.getString("Time");
                    Sat.add(time);
                }else if(Objects.equals(course.getString("Day"), "T")){
//                    String course_code=course.getString("Course Code");
                    String time=course.getString("Time");
                    Tue.add(time);
                }else if(Objects.equals(course.getString("Day"), "S W")){
//                    String course_code=course.getString("Course Code");
                    String time=course.getString("Time");
                    Sun.add(time);
                    Wed.add(time);
                }else if(Objects.equals(course.getString("Day"), "S")){
//                    String course_code=course.getString("Course Code");
                    String time=course.getString("Time");
                    Sun.add(time);
                }else if(Objects.equals(course.getString("Day"), "W")){
//                    String course_code=course.getString("Course Code");
                    String time=course.getString("Time");
                    Wed.add(time);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek().plus(2);
        System.out.println(dayOfWeek);
        System.out.println("Today's Classes: ");

        if(dayOfWeek.toString().equals("SATURDAY")){


            if(Sat.isEmpty()){
                System.out.println("No classes today");
                return;
            }
//
//
//
//            for (Map.Entry<String, String> entry : Sat.entrySet()) {
//                System.out.println(entry.getKey() + " : " + entry.getValue());
//            }


            List<TimeInterval> freeTimes=fte.getFreeTimes(Sat);
            for (TimeInterval freeTime : freeTimes) {
                long timeDifference = freeTime.getEndTime().getTime() - freeTime.getStartTime().getTime();
                // Check if the time difference is greater than one minute
                if (timeDifference > 60000) { // 60000 milliseconds = 1 minute
                    System.out.println("Free time: " + dateFormat.format(freeTime.getStartTime()) + " - " + dateFormat.format(freeTime.getEndTime()));
                }
            }
        }

        else if(dayOfWeek.toString().equals("SUNDAY")){

//            if(Sun.isEmpty()){
//                System.out.println("No classes today");
//                return;
//            }
//
//
//            for(String x: Sun){
//                System.out.println(x);
//            }
//            for (Map.Entry<String, String> entry : Sun.entrySet()) {
//                System.out.println(entry.getKey() + " : " + entry.getValue());
//            }



            List<TimeInterval> freeTimes=fte.getFreeTimes(Sun);
            for (TimeInterval freeTime : freeTimes) {
                long timeDifference = freeTime.getEndTime().getTime() - freeTime.getStartTime().getTime();

                // Check if the time difference is greater than one minute
                if (timeDifference > 60000) { // 60000 milliseconds = 1 minute
                    System.out.println("Free time: " + dateFormat.format(freeTime.getStartTime()) + " - " + dateFormat.format(freeTime.getEndTime()));
                }
            }
        }


        else if(dayOfWeek.toString().equals("TUESDAY")){

            if(Tue.isEmpty()){
                System.out.println("No classes today");
                return;
            }
//            for (Map.Entry<String, String> entry : Tue.entrySet()) {
//                System.out.println(entry.getKey() + " : " + entry.getValue());
//            }


            List<TimeInterval> freeTimes=fte.getFreeTimes(Tue);
            for (TimeInterval freeTime : freeTimes) {
                long timeDifference = freeTime.getEndTime().getTime() - freeTime.getStartTime().getTime();

                // Check if the time difference is greater than one minute
                if (timeDifference > 60000) { // 60000 milliseconds = 1 minute
                    System.out.println("Free time: " + dateFormat.format(freeTime.getStartTime()) + " - " + dateFormat.format(freeTime.getEndTime()));
                }
            }
        }


        else if(dayOfWeek.toString().equals("WEDNESDAY")){
            if(Wed.isEmpty()){
                System.out.println("No classes today");
                return;
            }
//            for (Map.Entry<String, String> entry : Wed.entrySet()) {
//                System.out.println(entry.getKey() + " : " + entry.getValue());
//            }


            List<TimeInterval> freeTimes=fte.getFreeTimes(Wed);
            for (TimeInterval freeTime : freeTimes) {
                long timeDifference = freeTime.getEndTime().getTime() - freeTime.getStartTime().getTime();

                // Check if the time difference is greater than one minute
                if (timeDifference > 60000) { // 60000 milliseconds = 1 minute
                    System.out.println("Free time: " + dateFormat.format(freeTime.getStartTime()) + " - " + dateFormat.format(freeTime.getEndTime()));
                }
            }
        }
        else{
            System.out.println("No classes today");
        }


    }
}
