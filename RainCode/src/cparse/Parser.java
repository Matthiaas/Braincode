package cparse;

import java.util.List;

public interface Parser {

    public default List<Line> parseString(String code){
        throw  new RuntimeException("Not Implemented yet");
    }

    public default List<Line> parseFile(String filename){
        throw  new RuntimeException("Not Implemented yet");
    }

}

