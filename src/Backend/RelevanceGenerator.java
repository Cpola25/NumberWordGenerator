package Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class will define the functionality for the relevance algorithim
 *
 * */
public class RelevanceGenerator {

    public void shedList(HashMap<Integer, List<Word>> words, int currentRelevance){
        HashMap<Integer, List<Word>> w = new HashMap<>(){{
            put(3, new ArrayList<>());
            put(4, new ArrayList<>());
            put(7, new ArrayList<>());
        }};


        Main.wordList = w;
    }

}
