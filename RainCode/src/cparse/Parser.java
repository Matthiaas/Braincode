package cparse;

import Splines.Line;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public default List<Line> parseFiles(String[] filenames) {
        List<Line> lines = new ArrayList<>();
        try {
            for (String file : filenames)
            lines.addAll(parseString(new String(Files.readAllBytes(Paths.get(file)))));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return lines;
    }


    public int getMaxX();
    public int getMaxY();

}

