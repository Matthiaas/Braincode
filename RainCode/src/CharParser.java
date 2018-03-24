import java.util.LinkedList;

public class CharParser  implements  Parser{


    public static void parseStrig(String code){

        int currX = 0 , currY = 0;

        LinkedList<Position>[] lines = new LinkedList[265];

        for( int i = 0; i < code.length(); i++){

            char c = code.charAt(i);

            switch(c){
                case '\n':
                    currX = 0;
                    currY++;
                    break;
                case '\t':
                    currX += 4;
                    break;
                default:
                    Position pos = new Position(currX , currY , 0);
                    

            }



        }

    }
}
