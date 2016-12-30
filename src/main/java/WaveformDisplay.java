import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.awt.*;
import java.io.*;

public class WaveformDisplay {
    public static void main(String[] args) {
        if (args.length==0) {
            System.err.println("Please supply the name of the wave file to display");
            System.exit(-1);
        }
        WaveformDisplay wds = new WaveformDisplay();
        try { // first look for the file in resources/
          wds.run(wds.getClass().getClassLoader().getResource(args[0]).getFile());
        } catch (Exception e) { // now look for the file in the current directory
            wds.run(args[0]);
        }
    }

    private void run(String file) {

        try {
            JFrame frame = new JFrame("Waveform Display Simulator");
            frame.setBounds(200,200, 500, 350);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream (new FileInputStream(file)));

            WaveformPanelContainer container = new WaveformPanelContainer();
            container.setAudioToDisplay(audioInputStream);

            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(container, BorderLayout.CENTER);

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            frame.setVisible(true);
            frame.validate();
            frame.repaint();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
