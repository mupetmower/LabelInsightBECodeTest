package com.labelinsight.codetest;

import com.labelinsight.codetest.solutions.MissingLetters;
import com.labelinsight.codetest.solutions.ParticleAnimation;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static final String errorMsgTerminal = "Please include params for method to run (getMissingLetters or animate) and the input string to provide the method, surrounded by quotes\n" +
            "To run getMissingLetters use: getMissingLetters \"input\"\n" +
            "To run animate use: animate param2: speed \"init\"\n";

    public static final String errorMsgIDE = "Please use prompts to enter the method to run and parameters for the method.";

    private boolean runAsBenchmark = false;
    private static int numRuns = 500;

    public static void main(String... args) {
        // NOTE - this was first written as if it were going to be ran from terminal, but then realized it would possibly be ran
        // from an IDE. So I added logic so both can be done.
        try {
            //Check to see if runnning from a built jar file
            URL res = Main.class.getResource("Main.class");
            // If not, assume it is running from IDE and use Scanner.in to get input
            if (!res.toString().contains("jar")) {
                Scanner in = new Scanner(System.in);
                System.out.print("Please enter method to run(getMissingLetters or animate): ");
                String methodToRun = in.nextLine();
                int speed;
                String input = "";

                if (methodToRun.equals("getMissingLetters")) {
                    System.out.print("Please enter the input for getMissingLetters (do not use quotes): ");
                    input = in.nextLine();

                    getMissingLetters(input);
                } else if (methodToRun.equals("animate")) {
                    System.out.print("Please enter the speed value for animate as an integer: ");

                    String spdStr = in.nextLine();
                    speed = Integer.parseInt(spdStr);

                    System.out.print("Please enter the init value for animate (do not use quotes, only use R, L, or . in init with no spaces): ");
                    input = in.nextLine();

                    ParticleAnimation pa = new ParticleAnimation();
                    String output = pa.animate(speed, input);

                    System.out.println(output);
                } else {
                    System.out.println(errorMsgIDE);
                }
            }
            // If so, run with input from args[] from terminal
            else {
                if (args.length < 2) {
                    System.out.println(errorMsgTerminal);
                    throw new Exception();
                }

                String methodToRun = args[0];
                int speed;
                String input = "";

                if (methodToRun.equals("getMissingLetters")) {
                    input = args[1];

                    getMissingLetters(input);
                } else if (methodToRun.equals("animate")) {
                    speed = Integer.parseInt(args[1]);
                    input = args[2];

                    ParticleAnimation pa = new ParticleAnimation();
                    String output = pa.animate(speed, input);

                    System.out.println(output);
                } else {
                    System.out.println(errorMsgTerminal);
                }

                // Benchmarking - not enough time to implement into running with console

                /*List<String> argsList = Arrays.asList(args);
                if (argsList.contains("-runAsBenchmark")) {
                    runBenchmark = true;
                }

                if (runBenchmark) {
                    try {
                        getMissingLettersBenchmark(input);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                } else {
                    MissingLetters missingLetters = new MissingLetters();
                    missingLetters.getMissingLetters(input);
                }*/
            }
        } catch(Exception e){
            System.out.println(errorMsgTerminal);
            e.printStackTrace();
        }

    }

    public static void getMissingLetters(String input) {
        MissingLetters missingLetters = new MissingLetters();
        String output = missingLetters.getMissingLetters(input);
        System.out.println(output);
    }

    public static void getMissingLettersBenchmark(String testInput) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        String input = (testInput != null) ? testInput : "dswgE#Yfih w;o qvbvpqiouGGBRwqpoiv hqpw 9w h49893t93784t6309ff &(dsg$(#(31tfGGEW^ 46393%%-*+//`~~ d247560Y^T^937ty93g3 g78 g97g79g0g57795";
        String t2 = " ";

        MissingLetters missingLetters; //= new MissingLetters();


        missingLetters = new MissingLetters();
        Benchmark<MissingLetters, String> speedTest = new Benchmark<MissingLetters, String>();
        speedTest.runAsBenchmark(numRuns, missingLetters.getClass(), missingLetters.getClass().getMethod("getMissingLetters", String.class), testInput);

    }

    public static void animateTest(String testInput) throws Exception {

        String input = (testInput != null) ? testInput :  "LRRL.LR.LRR.R.LRRL.";
        Integer speed = 1;

        ParticleAnimation pa = new ParticleAnimation();
        String output = pa.animate(speed, input);

        System.out.println(output);
    }

}
