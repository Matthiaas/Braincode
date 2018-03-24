import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CharParser  implements  Parser{

    @Override
    public List<List<Point>> parseString(String code){

        int currX = 0 , currY = 0;

        List<List<Point>> lines = new ArrayList<>(256);

        for(int i = 0; i < lines.size(); i++){
            lines.set(i, new LinkedList<>());
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
                    Point pos = new Point(currX, currY, 0);
                    lines.get(c).add(pos);
            }
        }


        return lines;
    }
}
