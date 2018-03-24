import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;

public class FunParser implements Parser {

    int index = 0;

    @Override
    public List<List<Point>> parseFile(String filename) {
        try {
            return parseString(new String(Files.readAllBytes(Paths.get(filename))));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<List<Point>> parseString(String code) {
        List<List<Point>> ret = new LinkedList<>();
        code += "\n";
        int length = code.length();


        for (index = 0; index < length; index++) {
            char c = code.charAt(index);

            if (c == '/') {
                c = code.charAt(++index);
                if (c == '/') {
                   do{
                       c = code.charAt(++index);
                   }while (c != '\n');
                } else if (c == '*') {
                    do{
                        c = code.charAt(++index);
                    }while (c != '*' && code.charAt(index+1) != '/');
                    index++;
                }
            }
            else if(c == '{'){
                ret.add(findCalls(code));
            }
        }
        return null;
    }

    private List<Point> findCalls(String code){
        int brackets = 1;
        String nameBuffer = "";
        char c;

        List<String> functions = new LinkedList<String>();

        while(brackets > 0){
            c = code.charAt(++index);
            if(c == ' '){
                while (c == ' ') c = code.charAt(index++);
                if(c == '('){
                    if(isFunction(nameBuffer)) functions.add(nameBuffer);
                }
                nameBuffer = "";
            }
            else if(goodChar(c)){
                nameBuffer += c +"";
            }
            else if(c == '('){
                if(isFunction(nameBuffer)) functions.add(nameBuffer);
            }
            else{
                if(c == '{') brackets++;
                if(c == '}') brackets--;
                nameBuffer = "";
            }
        }
        functions.stream().forEach(e -> System.out.println(e));
        return mapNamesToPoints(functions);
    }

    private boolean goodChar(char c){
        return (c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') || (c == '_');
    }

    private boolean isFunction(String name){
        switch (name){
            case "if":
            case "while":
            case "for":
                return false;
            default:
                return true;
        }
    }

    private List<Point> mapNamesToPoints(List<String> names){
        return null;
    }

}
