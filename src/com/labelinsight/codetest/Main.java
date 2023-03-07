package com.labelinsight.codetest;

import com.labelinsight.codetest.solutions.MissingLetters;
import com.labelinsight.codetest.solutions.ParticleAnimation;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static final String errorMsg = "Please include params for method to run (getMissingLetters or animate) and the input string to provide the method, surrounded by quotes\n" +
            "To run getMissingLetters use: getMissingLetters \"input\"\n" +
            "To run animate use: animate param2: speed \"init\"\n";

    private boolean runAsBenchmark = false;
    private static int numRuns = 500;

    public static void main(String... args) {

        try {
            if (args.length < 2) {
                System.out.println(errorMsg);
                throw new Exception();
            }

            String methodToRun = args[0];
            int speed;
            String input = "";

            if (methodToRun.equals("getMissingLetters")) {
                input = args[1];

                getMissingLetters(input);
            }
            else if (methodToRun.equals("animate")) {
                speed = Integer.parseInt(args[1]);
                input = args[2];

                ParticleAnimation pa = new ParticleAnimation();
                String output = pa.animate(speed, input);

                System.out.println(output);
            }
            else {
                System.out.println(errorMsg);
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
        catch (Exception e) {
            System.out.println(errorMsg);
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
