package cparse;

import Splines.Line;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CharParser  implements  Parser{

    @Override
    public List<Line> parseString(String code){

        int currX = 0 , currY = 0;

        List<Line> lines = new ArrayList<>(256);

        for(int i = 0; i < 256 ; i++){
            lines.add(i, new Line());
        }

        for( int i = 0; i < code.length(); i++){

            char c = code.charAt(i);

            switch(c) {
                case '\n':
                    currX = 0;
                    currY++;
                    break;
                case '\t':
                    currX += 4;
                    break;
                default:
                    System.out.println();
                    lines.get((int)c).add(currX *100, currY*100 , 0);
            }
        }

        return lines;
    }

}
