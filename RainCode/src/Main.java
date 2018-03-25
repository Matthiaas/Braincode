import Splines.Casteljau;
import Splines.Interpolator;
import Splines.Line;
import Splines.Point;
import cparse.FunParser;
import cparse.GaussDistr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int WIDTH = 3840 * 2;
    private static final int HEIGHT = 2160 * 2;
    private static final String[] LOCAL_FILES = new String[]{"res/test4.c"};
    private static final GaussDistr GAUSS = new GaussDistr(42);
    private static final Random RANDOM = new Random(1);

    private static final int HTML_WIDTH = 960;
    private static final int HTML_HEIGHT = 540;

    private static final Color[] colors = new Color[]{new Color(250, 15, 12), new Color(111, 187, 37),
            new Color(253, 95, 0), new Color(31, 66, 151),
            new Color(46, 172, 192), new Color(250, 245, 35), new Color(237, 36, 141)};

    public static void main(String[] args) {

        boolean server = args.length == 2;


        FunParser parser = new FunParser(WIDTH, HEIGHT);

        List<Line> lines = createLines(parser, server ? new String[]{args[0]} : LOCAL_FILES);
        List<List<Line>> splitLines = splitLines(lines);

        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(new Color(26, 25, 25));
        g.clearRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

        int color = (int) (RANDOM.nextInt(7));
        for (List<Line> splitLine : splitLines) {
            for (int j = 0; j < splitLine.size(); j++) {
                Interpolator interpolator = new Casteljau(splitLine.get(j));
                interpolator.paint(bufferedImage, 0.01, colors[color % colors.length]);
                if (splitLine.get(j).length() > 20) j++;
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

        if (server) {
            double widthFaktor = WIDTH * 1.0 / HTML_WIDTH;
            double heightFaktor = HEIGHT * 1.0 / HTML_HEIGHT;

            String html = "" +
                    "<img src=\"out\\" + args[1] + "\" WIDTH=\"" + HTML_WIDTH + "\" HEIGHT=\"" + HTML_HEIGHT + "\" alt=\"Brainbow\" usemap=\"#Methods\"> "
                    + "<map name=\"Methods\">";

            for (String method : parser.getMethods()) {
                Point location = parser.getCentreOfMethod(method);
                location.scale(WIDTH, HEIGHT);
                int xScaled = (int) (location.getX() / widthFaktor);
                int yScaled = (int) (location.getY() / heightFaktor);

                html = html +
                        "<area shape =\"circle\" coords=\"" + (xScaled) + "," + (yScaled) + "," + 20 + "\"" +
                        "alt=\"" + method + "\" title=\"" + method + "\">";

            }

            html = html + "</map>";
            System.out.println(html);
        }
    }

    private static List<Line> createLines(FunParser parser, String[] filenames) {
        List<Line> lines = parser.parseFiles(filenames);
        Line.scale(lines, WIDTH, HEIGHT);
        return lines;
    }

    private static List<List<Line>> splitLines(List<Line> longLines) {
        List<List<Line>> r = new ArrayList<>(longLines.size());

        //List<Point> constructs = new ArrayList<>();
        //for (int i = 0; i < 5; i++) {
        //    constructs.add(new Point(Math.RANDOM() * WIDTH, Math.RANDOM() * HEIGHT, 0, ""));
        //}

        for (int i = 0; i < longLines.size(); i++) {
            Line longLine = longLines.get(i);

            List<Line> splitLines = Line.lineSplitV1(longLine, WIDTH, HEIGHT, 3, 4, longLines.size(), GAUSS);
            //List<Line> splitLines = Line.lineSplitV2(longLine, constructs, 5, GAUSS, longLine.length(), i);

            r.add(splitLines);
        }
        return r;
    }

}
