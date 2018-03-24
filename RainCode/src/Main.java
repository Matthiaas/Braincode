import Splines.Casteljau;
import Splines.Interpolator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Main extends JPanel {

    private BufferedImage bufferedImage;

    public Main(BufferedImage bufferedImage){
        this.bufferedImage = bufferedImage;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("RainCode");
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);

        List<Line> lines = null;

        BufferedImage bufferedImage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(Line l: lines){
            Interpolator interpolator = new Casteljau(l);
        }

        JPanel panel = new Main(bufferedImage);
        frame.add(panel);

        frame.setVisible(true);
        panel.setVisible(true);

        panel.repaint();
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
    }


}
