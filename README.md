# Waveform Display

Reworked example 75 in Chapter 10 of [Swing Hacks](http://shop.oreilly.com/product/9780596009076.do) 
by Joshua Marinacci and Chris Adamson.

## To Run
This fails because `resource.jar` from the Java Runtime is not included in the classpath. 
Not sure of the best way to do that.
IntelliJ IDEA puts together a huge classpath that includes `resource.jar`:

    $ sbt "runMain WaveformDisplay chord.wav"

You can also build a fat jar and run it with arbitrary message to voice:

    $ sbt assembly
    $ java -jar target/scala-2.12/waveform-display-assembly-0.1.0.jar chord.wav
