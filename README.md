# Label Insight - Backend Code Test

## Prerequisits
- Java 1.8+
- Make sure your Java Home is 1.8, or your IDE uses 1.8 to compile and run this project, or that you use Java 8 to build this as a jar and run with using javac, etc.

## How to Run
This can run from either an IDE or built and then ran from the command line. 

If running from an IDE (IntelliJ/Eclipse):
- Open Project in IDE
- Setup the project to use Java 1.8
- Use the IDE to run Main, located at src/com/lableinsight/codetest
- You will be prometed to enter the method (getMissingLetters or animate)
  - Please do not use quotes
- Another prompt will show for input or speed and then input
  - Please make sure speed is an integer and do not use quotes for either parameter

If Building and Running from cmd or a terminal:
- Use desired IDE or java to compile and build the project with Java 1.8+ and build a jar artifact
- Make sure you use Java 1.8 to run in cmd/terminal or that Java Home is set to your jdk1.8+ location
- To run use `java -jar <location of jar file> <methodName> <param1> <param2>` 
  - `<methodName>` is getMissingLetters or animate, no quotes, `<param2>` is not used if you are running the `getMissingLetters` method.
  - If running getMissingLetters, `<param1>` will be the input String surrounded with quotes. Example: `java -jar LabelInsightBECodeTest.jar getMissingLetters "I am a kitty cat"`
  - If running animate, `<param1>` will be the speed as an integer, no quotes, while `<param2>` will be the init String surrounded by quotes. Example: `java -jar LabelInsightBECodeTest.jar animate 2 "..LRRL..RLRL.."`

## Notes
<p>I also created a benchmark class which uses Java reflection to invoke the given method from given class and run it while tracking the time in nanoseconds that it took to execute. <br>
It will also run this a given number of times and average the nanoseconds that the method took per each run and will output the result.</p>

<p>I used this class to run benchmark tests on my methods and tweak them to gain faster execution speed. However, I did not have enough time to enable a user to use this as an option when running, but left it in the source and it can be played with if desired.</p>

<p>I also have this project on GitHub and was working on more implementations, using different techniques (some working, some not quite yet) and left those in a branch called feature/other-impl-wip. These different implementations were ment to serve as different approaches to the same problem. Then I woulod be able to benchmark them with small/large inputs and running/many/few times to se which performed better under certain circumstances. 
<br>
<br>
These were taken out of the project to clean things up, but note that the Particle class and the ParticleFrame class both are part of these and remain in the source code.<br><br>
The github page can be found at https://github.com/mupetmower/LabelInsightBECodeTest if interested (or to view the readme with formatting).
</p>

# Thank You!
