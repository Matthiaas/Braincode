import Splines.Casteljau;
import Splines.Interpolator;
import Splines.Line;
import Splines.Regression;
import cparse.FunParser;
import cparse.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MatzesRegressionMain extends JPanel {

    private BufferedImage bufferedImage;
    private static final Color[] colors = new Color[]{new Color(250, 15, 12), new Color(111, 187, 37),
            new Color(234, 232, 43), new Color(31, 66, 151), new Color(230, 35, 137),
            new Color(46, 172, 192), new Color(250, 245, 35), new Color(237, 36, 141)};


    public MatzesRegressionMain(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("RainCode");
        frame.setSize(1600, 800);
        frame.setLocationRelativeTo(null);

        Parser parser = new FunParser();
        List<Line> lines = parser.parseFile("res/test3.c");
        Line.scale(lines, parser.getMaxX(), parser.getMaxY(), frame.getWidth(), frame.getHeight());

        BufferedImage bufferedImage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().clearRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

        System.out.println(lines.size() + " lines");
        for (int i = 0; i < lines.size(); i++) {
            Line longLine = lines.get(i);

            Interpolator interpolator = new Regression(longLine);
            interpolator.paint(bufferedImage, 0.001, colors[i % colors.length]);

        }

        JPanel panel = new MatzesRegressionMain(bufferedImage);
        frame.add(panel);

        frame.setVisible(true);
        panel.setVisible(true);

        panel.repaint();
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(bufferedImage, 0, 0, null);
    }

}
