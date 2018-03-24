import java.util.List;

public interface Parser {

    public default List<List<Position>> parseString( String code){
        throw  new RuntimeException("Not Implemented yet");
    }

    public default List<List<Position>> parseFile(String filname){
        throw  new RuntimeException("Not Implemented yet");
    }

}

