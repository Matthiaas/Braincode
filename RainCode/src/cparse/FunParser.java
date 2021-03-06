package cparse;

import Splines.Line;
import Splines.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * WARNING!!!!!!!!!!!!!!!!!!!!!!!!!
 * <p>
 * this class is toxic,
 * do not enter without proper supervision!
 * you WILL break something and I will
 * track you down, I will find you and swear to god
 * I WILL MURDER YOU IN YOUR sleep
 * <p>
 * kind regards,
 * fooris
 */

public class FunParser {


    public static int PRIMEX = 3840, PRIMEY = 2160, PRIMEZ = 1;
    public final static double DROPOUT = .005;

    private int index = 0;
    private GaussDistr gaussDistr = new GaussDistr(1337);

    private Map<String, Point> methodeNameToPoint = new HashMap<>();


    public FunParser(int width, int height) {
        PRIMEX = width;
        PRIMEY = height;
    }

    /**
     * super magic main method of class
     *
     * @param code
     * @return
     */
    public List<Line> parseString(String code) {
        code = prePro(code);
        List<Line> ret = new ArrayList<Line>();
        code += "\n";
        int length = code.length();

        for (index = 0; index < length; index++) {
            char c = code.charAt(index);

            if (c == '/') {
                c = code.charAt(++index);
                if (c == '/') {
                    do {
                        c = code.charAt(++index);
                    } while (c != '\n');
                } else if (c == '*') {
                    do {
                        c = code.charAt(++index);
                    } while (c != '*' && code.charAt(index + 1) != '/');
                    index++;
                }
            } else if (c == '{') {
                index++;
                Line line = findCalls(code);
                if (line.length() >= 4)
                    ret.add(line);
            }
        }

        return ret;
    }


    private String prePro(String code) {
        int in = 0;
        HashMap<String, Integer> funcs = new HashMap<>();
        int methodGlobalCount = 0;
        String nameBuffer = "";
        // code = code.replaceAll("/\\*(.|[\\r\\n])*?\\*/", " ");
        //  code = code.replaceAll("//.*(?=\\n)" ," ");

        while (in < code.length() - 1) {

            char c;
            c = code.charAt(++in);
            if (c == ' ') {
                while (c == ' ') c = code.charAt(in++);
                if (c == '(') {
                    if (isFunction(nameBuffer)) {
                        if (funcs.containsKey(nameBuffer))
                            funcs.put(nameBuffer, funcs.get(nameBuffer) + 1);
                        else
                            funcs.put(nameBuffer, 1);
                        methodGlobalCount++;
                    }
                }
                nameBuffer = "";
            } else if (goodChar(c)) {
                nameBuffer += c + "";
            } else if (c == '(') {
                if (isFunction(nameBuffer)) {
                    if (funcs.containsKey(nameBuffer))
                        funcs.put(nameBuffer, funcs.get(nameBuffer) + 1);
                    else
                        funcs.put(nameBuffer, 1);
                    methodGlobalCount++;
                }
            } else {
                nameBuffer = "";
            }
        }

        for (Map.Entry<String, Integer> entr : funcs.entrySet()) {

            if (entr.getValue() < methodGlobalCount * DROPOUT) {
                code = code.replaceAll(entr.getKey() + " *\\(.*", " ");
            }
        }
        return code;
    }

    private Line findCalls(String code) {
        int brackets = 1;
        String nameBuffer = "";
        char c;

        List<String> functions = new LinkedList<String>();

        boolean mayReset = false;
        while (brackets > 0 && index < code.length()) {
            c = code.charAt(index);
            while (c == ' ') {
                index++;
                c = code.charAt(index);
                mayReset = true;
            }

            if (goodChar(c)) {
                if (mayReset) {
                    nameBuffer = "";
                    mayReset = false;
                }
                nameBuffer += c;
            } else if (c == '(') {
                if (isFunction(nameBuffer)) {
                    functions.add(nameBuffer);
                }
            } else {
                if (c == '{') brackets++;
                if (c == '}') brackets--;
                nameBuffer = "";
            }
            index++;
        }

        return mapNamesToPoints(functions, PRIMEX, PRIMEY, PRIMEZ);

    }


    private boolean goodChar(char c) {
        return ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) || (c == '_');
    }

    private boolean isFunction(String name) {
        switch (name) {
            case "if":
            case "else":
            case "elseif":
            case "while":
            case "for":
            case "return":
            case "switch":
            case "":
                return false;
            default:
                return true;
        }
    }

    public Point getCentreOfMethod(String name) {
        return methodeNameToPoint.get(name);
    }

    public Set<String> getMethods() {
        return methodeNameToPoint.keySet();
    }

    private Line mapNamesToPoints(List<String> names, int primex, int primey, int primez) {
        Line line = new Line();

        for (String str : names) {
            int hash = Math.abs(str.hashCode());

            long count = names.stream().filter(e -> e.equals(str)).count();
            hash++;
            long x = (hash * 235723l) % primex, y = (hash * 238477l) % primey, z = (hash * 424234l) % primez;
            methodeNameToPoint.put(str, new Point(x, y));
            double[] xy = gaussDistr.distribute(x, y, count);

            line.add(xy[0], xy[1]);
        }
        return line;
    }

    public List<Line> parseFile(String filename) {
        try {
            return parseString(new String(Files.readAllBytes(Paths.get(filename))));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Line> parseFiles(String[] filenames) {
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

}
