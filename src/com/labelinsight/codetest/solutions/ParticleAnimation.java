package com.labelinsight.codetest.solutions;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ParticleAnimation {

    private List<String> frames = new ArrayList<String>();
    private List<ParticleFrame> particleFrames = new ArrayList<>();

    /**
     * This animate method takes the input init and casts it as a character array to be used by the animate method which contains the business logic.
     * @param speed
     *        the speed each particles moves per iteration
     * @param init
     *        the initial list of particles
     * @return
     *        the formatted output for all frames
     * @throws Exception
     */
    public String animate(Integer speed, String init) throws Exception {
        // Add init to our list of all frames
        frames.add(init);
        anim(speed, init.toUpperCase().toCharArray());
        String out = "";
        for (String f : frames) {
            f = f.replace('L', 'X');
            f = f.replace('R', 'X');
            out += f + "\n";
        }
        return out;

    }

    /**
     * A utility method for an Object Oriented approach
     * @param speed
     * @param input
     * @return
     * @throws Exception
     */
    public ParticleFrame createParticleFrame(int speed, String input) throws Exception {
        ParticleFrame frame = new ParticleFrame();
        for (char s : input.toCharArray()) {
            Particle p; // = new Particle();
            switch (s) {
                case('.'):
                    p = new Particle(speed, Direction.O);
                    break;
                case('L'):
                    p = new Particle(speed, Direction.L);
                    break;
                case('R'):
                    p = new Particle(speed, Direction.R);
                    break;
                default:
                    throw new Exception("Charater: " + s + " not a particle or space, not allowed. Please only use ., R, or L for input String.");
            }
            frame.addParticle(p);
        }

        return frame;
    }

    /**
     * This was meant to be a more functional, and hopefully faster and less resource intensive, approach for the particle animation.
     * However, I ran out of time before finishing the implementation after finding a bug when more than one R is next to eachother and they encounter
     * an F at the same space they are moving.
     * @param init
     * @return
     * @throws Exception
     */
    private String animateFrame(int speed, String init) throws Exception {

        if (init.matches("\\.*"))
            return init;

        init = init.toUpperCase();
        char[] particles = init.toCharArray();

        char[] nextFrame = particles.clone();
        String nextframeStr = "";

        //int indexForL = -1;
        //int indexForR = -1;
        //int indexForPeriod = -1;

        List<Integer> indexForPeriodList = new ArrayList<>();
        List<Integer> indexForL = new ArrayList<>();
        List<Integer> indexForR = new ArrayList<>();

        //TODO: Fix particle overwrite issue and moving too fast issue
        frameLoop: for (int i = 0; i < particles.length; i++) {

            switch (nextFrame[i]) {
                case '.':
                    nextFrame[i] = '.';
                    break;
                case 'L':   // Logic for L moving particle
                    if (i == 0 || (i - speed) < 0)
                        nextFrame[i] = '.';
                    else {
                            nextFrame[i] = '.';
                        if ((i - (speed)) > 0 && nextFrame[i - speed] == 'X')
                            nextFrame[i - speed] = 'X';
                        else
                            nextFrame[i - speed] = 'L';
                    }
                    break;
                case 'R':   // Logic for R moving particle
                    if (indexForL.contains(i)) {
                        if (i == 0 || (i - speed) < 0)
                            nextFrame[i] = '.';
                        else {
                            //if (!init.substring(i+1).contains("R"))
                            nextFrame[i] = '.';
                            if ((i - (speed)) > 0 && nextFrame[i - speed] == 'X')
                                nextFrame[i - speed] = 'X'; // don't change if already X
                            else
                                nextFrame[i - speed] = 'L';

                            nextFrame[i] = 'R';
                        }

                        break;
                    }


                    if (indexForPeriodList.contains(i)) {
                        break;
                    }


                    if (i >= particles.length || (i + speed) >= particles.length)
                        nextFrame[i] = '.';
                    else {
                        //if (!init.substring(i+1, i+1 +1).contains("L") && !init.substring(i=1, i+1 +1).contains("X"))


                        if ((i + (speed * 2)) < nextFrame.length && (nextFrame[i + (speed * 2)] == 'L' || nextFrame[i + (speed * 2)] == 'X')) {

                            indexForL.add(i + (speed * 2));
                            if (nextFrame[i + speed] == 'R')
                                indexForR.add(i + speed);

                            if (nextFrame[i + speed] == '.')
                                indexForPeriodList.add(i + speed);

                            nextFrame[i] = '.';
                            nextFrame[i + speed] = 'X';

                        } else {
                            nextFrame[i] = '.';

                            if (nextFrame[i + speed] == 'R')
                                indexForR.add(i + speed);

                            if (nextFrame[i + speed] == '.')
                                indexForPeriodList.add(i + speed);

                            nextFrame[i + speed] = 'R';

                            if (indexForR.contains(i)) {
                                nextFrame[i] = 'R';
                            }

                        }
                    }

                    break;


                case 'X':   // Logic for L and R moving particles on same space
                    if (indexForPeriodList.contains(i)) {
                        break;
                    }

                    if ((i - speed) < 0)
                        nextFrame[i] = '.';
                    else {
                        if (indexForPeriodList.contains(i)) {
                            break;
                        }
                        nextFrame[i] = '.';
                        if ((i - (speed)) > 0 && nextFrame[i - speed] == 'X')
                            nextFrame[i - speed] = 'X';
                        else
                            nextFrame[i - speed] = 'L';
                    }

                    /*if (indexForL.contains(i)) {
                        if (i == 0 || (i - speed) < 0)
                            nextFrame[i] = '.';
                        else {
                            //nextFrame[i] = '.';
                            if ((i - (speed)) > 0 && nextFrame[i - speed] == 'X')
                                nextFrame[i - speed] = 'X'; // don't change if already X
                            else
                                nextFrame[i - speed] = 'L';

                        }
                        //indexForL = -1;

                        break;
                    }*/


                    if (i >= particles.length || (i + speed) >= particles.length)
                        nextFrame[i] = '.';
                    else {
                        //if (!init.substring(i+1, i+1 +1).contains("L") && !init.substring(i=1, i+1 +1).contains("X"))


                        if ((i + (speed * 2)) < nextFrame.length && (nextFrame[i + (speed * 2)] == 'L' || nextFrame[i + (speed * 2)] == 'X')) {
                            nextFrame[i + speed] = 'X';
                            indexForL.add(i + (speed * 2));
                        } else {


                            if (nextFrame[i + speed] == 'R')
                                indexForR.add(i + speed);

                            if (nextFrame[i + speed] == '.')
                                indexForPeriodList.add(i + speed);

                            nextFrame[i] = '.';

                            nextFrame[i + speed] = 'R';

                            if (indexForR.contains(i)) {
                                nextFrame[i] = 'R';
                            }

                        }
                    }
                    //i++;
                    break;
                default:
                    throw new Exception("Charater: " + particles[i] + " not a particle or space, not allowed. Please only use ., R, or L for input String.");
            }

        }

        nextframeStr = String.copyValueOf(nextFrame);
        frames.add(nextframeStr);

        return animateFrame(speed, nextframeStr);
    }


    private ParticleFrame animateFrame3(ParticleFrame frame) throws Exception {

        if (frame.toString().matches("\\.*"))
            return frame;

        // Create ParticleFrame for current input string

        int speed = frame.getParticles().get(0).getSpeed();

        // During check for each particle in currentFrame, if particle at currentFrame is L or R, then
        // check to see if particle at new frame is L or R, and if so change to Both.
        ParticleFrame nextFrame = new ParticleFrame();

        /*for (int i = 0; i < frame.getParticles().size(); i++) {
            Particle p = frame.getParticles().get(i);
            switch (p.getDir()) {
                case Direction.O:
                    nextFrame.addParticle(i, new Particle(speed, Direction.O));
                    break;
                case 'L':
                    if (i == 0)
                        nextFrame[i] = '.';
                    else
                        nextFrame[i] = '.';
                    nextFrame[i - 1] = 'L';
                    break;
                case 'R':
                    if (i >= particles.length - 1)
                        nextFrame[i] = '.';
                    else
                        nextFrame[i] = '.';
                    nextFrame[i + 1] = 'R';
                    break;
                default:
                    throw new Exception("Charater: " + particles[i] + " not a particle or space, not allowed. Please only use ., R, or L for input String.");
            }


        }

        nextframeStr = String.copyValueOf(nextFrame);
        frames.add(nextframeStr);

        return animateFrame3(nextframeStr);*/
        return new ParticleFrame();
    }


    /**
     * This method contains the bulk of the animate logic.
     * It takes the speed and the currentFrame and first transforms the currentFrame input into a series of Boolean
     * arrays, called Directional frame. Each directional frame indicates whether the particle is moving left, right, or both
     * by assigning the value true. In the each frame, false indicates no particle present at the location of the index, and
     * true indicates a particle(s) is present at the index.
     * @param speed
     *        the speed each particles moves per iteration
     * @param currentFrame
     *        the value of the current list of particles
     * @return
     */
    private char[] anim(int speed, char[] currentFrame) {
        // If all characters are '.' then no particles exist in the frame and we return
        if (String.copyValueOf(currentFrame).matches("\\.*"))
            return currentFrame;

        int frameLength = currentFrame.length;

        // Directional Frames
        Boolean[] lFrame = new Boolean[frameLength];
        Boolean[] rFrame = new Boolean[frameLength];
        Boolean[] xFrame = new Boolean[frameLength];


        Boolean[] nextLFrame = new Boolean[currentFrame.length];
        Boolean[] nextRFrame = new Boolean[currentFrame.length];
        Boolean[] nextXFrame = new Boolean[currentFrame.length];

        // Initialize our arrays
        for (int i = 0; i < frameLength; i++) {
            lFrame[i] = false;
            rFrame[i] = false;
            xFrame[i] = false;
            nextLFrame[i] = false;
            nextRFrame[i] = false;
            nextXFrame[i] = false;
        }
        
        // Donstruct directional frames
        for (int i = 0; i < frameLength; i++) {
            char p = currentFrame[i];
            if (p == 'L') lFrame[i] = true;
            if (p == 'R') rFrame[i] = true;
            if (p == 'X') xFrame[i] = true;
        }

        // Move particles in each directional frame
        //Boolean[] nextLFrame = new Boolean[currentFrame.length];
        for (int i = 0; i < frameLength; i++) {
            if (lFrame[i]) {
                if (i - speed >= 0) {
                    nextLFrame[i - speed] = true;
                }
            }
        }

        //Boolean[] nextRFrame = new Boolean[currentFrame.length];
        for (int i = 0; i < frameLength; i++) {
            if (rFrame[i]) {
                if (i + speed < frameLength) {
                    nextRFrame[i + speed] = true;
                }
            }
        }

        //Boolean[] nextRFrame = new Boolean[currentFrame.length];
        for (int i = 0; i < frameLength; i++) {
            if (xFrame[i]) {
                if (i + speed < frameLength) {
                    if (i - speed >= 0) {
                        nextLFrame[i - speed] = true;
                    }
                    if (i + speed < frameLength) {
                        nextRFrame[i + speed] = true;
                    }
                }
            }
        }

        // Combine back to single frame
        char[] nextFrame = new char[frameLength];
        for (int i = 0; i < frameLength; i++) {
            nextFrame[i] = '.';
        }

        for (int i = 0; i < frameLength; i++) {

            // If if both directional frames at i are true, we indicate the next frame in the series contains
            // particles of both directions at that space
            if (nextLFrame[i] && nextRFrame[i]) {
                nextFrame[i] = 'X';
            } else {

                if (nextLFrame[i]) {
                    //if (i - speed <= 0) {
                        nextFrame[i] = 'L';
                    //}
                }

                if (nextRFrame[i]) {
                    //if (i + speed < frameLength) {
                        nextFrame[i] = 'R';
                    //}
                }
            }
        }

        // add the frame to a list of all frames to show as output
        String nextframeStr = String.copyValueOf(nextFrame);
        frames.add(nextframeStr);

        return anim(speed, nextFrame);
    }
    
}
