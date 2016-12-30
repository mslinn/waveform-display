import javax.sound.sampled.UnsupportedAudioFileException;
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

        String fileName = args[0];
        WaveformDisplay wds = new WaveformDisplay();
        try { // first look for the file in resources/
          String fileNameRes = wds.getClass().getClassLoader().getResource(fileName).getFile();
          wds.run(fileNameRes);
        } catch (Exception e) { // now look for the file in the current directory
            try {
                System.err.println("Error: " + e.getMessage());
                wds.run(fileName);
            } catch (Exception e2) {
                System.err.println("Error reading " + fileName + ".\n" + e2.getMessage());
                System.exit(1);
            }
        }
    }

    private void run(String filePath) throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame("Waveform Display");
        frame.setBounds(200,200, 500, 350);

        System.out.println("Looking for " + filePath);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(filePath)));

        WaveformPanelContainer container = new WaveformPanelContainer();
        container.setAudioToDisplay(audioInputStream);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(container, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.validate();
        frame.repaint();
    }
}
