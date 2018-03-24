package cparse;

import Splines.Line;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public interface Parser {

    public default List<Line> parseString(String code){
        throw  new RuntimeException("Not Implemented yet");
    }

    public default List<Line> parseFile(String filename) {
        try {
            return parseString(new String(Files.readAllBytes(Paths.get(filename))));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public int getMaxX();
    public int getMaxY();

}

