import Splines.Casteljau;
import Splines.Interpolator;
import Splines.Line;
import Splines.Point;
import cparse.FunParser;
import cparse.GaussDistr;
import cparse.Parser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JPanel {

    private BufferedImage bufferedImage;
    private static final Color[] colors = new Color[]{new Color(250, 15, 12), new Color(111, 187, 37),
            new Color(253, 95, 0), new Color(31, 66, 151),
            new Color(46, 172, 192), new Color(250, 245, 35), new Color(237, 36, 141)};


    public Main(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public static void main(String[] args) {
        boolean server = args.length == 2;


        int width = 3840 * 2;
        int height = 2160 * 2;


        Parser parser = new FunParser(width, height);

        List<Line> lines;
        if (server) {
            String[] files = {args[0]};
            lines = parser.parseFiles(files);
        } else {
            String[] files = {"res/test.c"};
            lines = parser.parseFiles(files);
        }

        Line.scale(lines, width, height);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(new Color(26, 25, 25));
        g.clearRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

        for (int i = 0; i < colors.length; i++) {
            g.setColor(colors[i]);
            g.fillRect(i * 100, 0, 100, 100);
        }

        System.out.println(lines.size() + " lines");

        GaussDistr gauss = new GaussDistr(42);

        /*
        g.setColor(Color.red);
        for(Line l: lines) {
            for (int i = 0; i < l.length(); i++) {
                g.fillRect((int) l.getX()[i], (int) l.getY()[i], 5, 5);
            }
        }
        */


        int color = (int) (Math.random() * 7);
        /*
        List<Point> constructs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            constructs.add(new Point(Math.random() * width, Math.random() * height, 0, ""));
        }
        */

        for (int i = 0; i < lines.size(); i++) {
            Line longLine = lines.get(i);

           // if(Math.random() > 1.0/2) continue;;

            List<Line> splitLines = Line.betterHack(longLine, width, height, 2, 4, lines.size(), gauss);
            //List<Line> splitLines = Line.evenBetterHack(longLine, constructs, 5, gauss, longLine.length(), i);

            for (int j = 0; j < splitLines.size(); j++) {
                Interpolator interpolator = new Casteljau(splitLines.get(j));
                interpolator.paint(bufferedImage, 0.01, colors[color % colors.length]);
            }
            color++;
        }

        String fileName = server ? "out/" + args[1] : "res/pics/" + System.currentTimeMillis() + ".png";
        File f = new File(fileName);
        try {
            ImageIO.write(bufferedImage, "PNG", f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("RainCode");
        frame.setSize(1600, 800);
        frame.setLocationRelativeTo(null);

        JPanel panel = new Main(bufferedImage);
        frame.add(panel);

        frame.setVisible(true);
        panel.setVisible(true);

        panel.repaint();
        */

        int html_width = 690;
        int html_hight = 538;
        String html = "" +
                "<img src=\"" + args[2] + "\" width=\"" + html_width + "\" height=\"" + html_hight + "\" alt=\"Karte\" usemap=\"#Landkarte\"> "
                + "<map name=\"Methods\">";

        List included
        for (Line l : lines) {

    <area shape =\"rect\" coords=\"11,10,80,46\"
            href =\"http://www.koblenz.de/\" alt=\"Koblenz\" title=\"Koblenz\">
        }

               html = html + "</map>";




    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(bufferedImage, 0, 0, null);
    }

}
