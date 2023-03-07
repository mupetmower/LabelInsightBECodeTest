package com.labelinsight.codetest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Benchmark<T, K> {

    private static final String lineBreak = "------------------------------------------------------";

    /**
     * WarmUp JVM with some looping logic so benchmarks are more consistent.
     */
    public void jvmWarmUp() {
        System.out.printf("%s%nJVM WarmUp Start... %nPlease Wait.", lineBreak);

        float n = 1;
        double m = 10;
        String s = "";
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 100000; i++) {
                n++;
                for (int j = 0; j < 1000; j++) {
                    n = n * (float) m;
                    ++n;
                    m = (((int) (n + (i * 0.333))) * (n / m));
                }
                n--;
                s = new String();
                s += m;
            }
            System.out.print(".");
        }
        System.out.printf("%nJVM WarmUp Finished.%n%s%n%n", lineBreak);
    }


    /**
     * Run method from clazz as a benchmark test and print avg nanosecs for the runtime. At higher numRuns (i.e. 100, 500, 1000+),
     * the avg runtime seems to grow as well.
     * <p>Ideally this should not happen, but for now, keeping numRuns around 5 seems safe.</p>
     * <p>This could point to a needed optimization for many small runs of the method being invoked. Another possibility is that
     * I have some issue with they way I am benchmarking these runs when performing many at a time. Using more threads and a ThreaGroup
     * could possibly help. As could not using reflection, and instead using a more straightforward approach..</p>
     * @param numRuns
     *        amount of runs for method to find average nanosecs.
     * @param clazz
     *        the class from which to call the method.
     * @param method
     *        the method to run.
     * @param input
     *        the input for the method.
     */
    public void runAsBenchmark(int numRuns, Class<? extends T> clazz, Method method, K input) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        jvmWarmUp();
        System.out.printf("%s%n%s %s Benchmarck (%d runs) Start:%n%n", lineBreak, clazz.getName(), method.getName(), numRuns);

        long sumNano = 0;

        for (int i = 0; i < numRuns; i++) {
            long startNano = System.nanoTime();

            String output = (String) method.invoke(clazz.newInstance(), input);

            long endNano = System.nanoTime();

            long totalNano = endNano - startNano;
            sumNano += totalNano;

            //System.out.printf("Start: %d%nEnd: %d%nTime(Nanosec): %d%n%n", startNano, endNano, totalNano);

            //System.out.printf("Output: %s%n", output);

            // Hint for jvm to clear unused objects from heap (no guarantee)
            System.gc();
            // Small wait between each execution to reduce inconsistency
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long avgNano = sumNano / numRuns;
        System.out.printf("%nAvg Nanosec for %d Runs: %d%n%s%n%n", numRuns, avgNano, lineBreak);
    }
}
