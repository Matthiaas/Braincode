package cparse;

import Splines.Line;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CharParser implements Parser {


    int maxX = 0 , maxY = 0;


    @Override
    public List<Line> parseString(String code) {

        int currX = 0, currY = 0;

        List<Line> lines = new ArrayList<>(256);

        for (int i = 0; i < 256; i++) {
            lines.add(i, new Line());
        }

        for (int i = 0; i < code.length(); i++) {

            char c = code.charAt(i);

            switch (c) {
                case '\n':
                    currX = 0;
                    currY++;
                    break;
                case '\t':
                    currX += 4;
                    break;
                default:
                    maxX = Integer.max(maxX , currX);
                    maxY = Integer.max(maxY , currY);
                    lines.get((int) c).add(currX, currY , 0);
            }
        }

        return lines.stream().filter(l -> l.length() > 4).collect(Collectors.toList());
    }

    @Override
    public int getMaxX() {
        return maxX;
    }

    @Override
    public int getMaxY() {
        return maxY;
    }

}
