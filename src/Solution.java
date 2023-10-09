import java.io.*;

import java.util.*;

import java.text.*;

import java.math.*;


import java.util.regex.*;


class LionDescription {


    public String name;


    public int height;


}


class LionSchedule {


    public String name;


    public int enterTime;


    public int exitTime;


}


class LionCompetition {


    private List<LionDescription> lions;

    private List<LionSchedule> schedule;

    private List<Integer> heights;

    private final Map<String, Integer> lionHeightMap;


    public LionCompetition(List<LionDescription> lions, List<LionSchedule> schedule) {

        this.lions = lions;

        this.schedule = schedule;

        this.lionHeightMap = new HashMap<>();

        this.heights = new ArrayList<>();

    }


    public void lionEntered(int currentTime, int height) {

        heights.add(height);

        for (LionSchedule lionSchedule : schedule) {

            if (lionSchedule.enterTime == currentTime ) {

                lionHeightMap.put(lionSchedule.name, height);

                break;

            }

        }

    }


    public void lionLeft(int currentTime, int height) {

        int index = 0;

        for (int i = 0; i < heights.size(); i++) {

            if (heights.get(i) == height) {

                index = i;

                break;

            }

        }

        heights.remove(index);

        for (LionSchedule lionSchedule : schedule) {

            if (lionSchedule.exitTime == currentTime) {

                lionHeightMap.remove(lionSchedule.name);

                break;

            }

        }

    }


    public List<String> getBiggestLions() {

        Integer obj = Collections.max(heights);

        int maxHeight = obj.intValue();


        List<String> biggestLions = new ArrayList<>();

        for (LionDescription lion : lions) {

            if (lionHeightMap.containsKey(lion.name) && lion.height >= maxHeight) {

                biggestLions.add(lion.name);

            }

        }

        Collections.sort(biggestLions);

        return biggestLions;

    }


}


public class Solution {


    public static void main(String args[]) throws Exception {


        Scanner scanner = new Scanner(System.in);

        String operation;

        List<LionDescription> descriptions = new ArrayList<LionDescription>();

        List<LionSchedule> schedule = new ArrayList<LionSchedule>();


        do {


            operation = scanner.next();


            if (operation.equals("definition")) {


                LionDescription description = new LionDescription();


                description.name = scanner.next();


                description.height = scanner.nextInt();


                descriptions.add(description);


            }


            if (operation.equals("schedule")) {


                LionSchedule scheduleEntry = new LionSchedule();


                scheduleEntry.name = scanner.next();


                scheduleEntry.enterTime = scanner.nextInt();


                scheduleEntry.exitTime = scanner.nextInt();


                schedule.add(scheduleEntry);


            }


        } while (!operation.equals("start"));


        LionCompetition lionCompetition = new LionCompetition(descriptions, schedule);


        do {


            int currentTime = scanner.nextInt();


            operation = scanner.next();


            if (operation.equals("enter")) {

                int size = scanner.nextInt();

                lionCompetition.lionEntered(currentTime, size);
            }


            if (operation.equals("exit")) {

                int size = scanner.nextInt();

                lionCompetition.lionLeft(currentTime, size);
            }


            if (operation.equals("inspect")) {


                List<String> lions = lionCompetition.getBiggestLions();


                System.out.print(lions.size());


                for (String name : lions) {


                    System.out.print(" " + name);


                }


                System.out.println();


            }


        } while (!operation.equals("end"));


    }


}