import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class is responsible for generating the cartesian product combinations of the given input String
 *The results will be stored in a HashMap where the keys represent the word length and the value is an
 * ArrayList of Words what are of that length.
 * */

public class LetterGenerator {

    private final HashMap<Integer, List<String>> letters = new HashMap<>() {{
        put(0, Arrays.asList(" "));
        put(1, Arrays.asList("/", ".", "@"));
        put(2, Arrays.asList("a", "b", "c"));
        put(3, Arrays.asList("d", "e", "f"));
        put(4, Arrays.asList("g", "h", "i"));
        put(5, Arrays.asList("j", "k", "l"));
        put(6, Arrays.asList("m", "n", "o"));
        put(7, Arrays.asList("p", "q", "r", "s"));
        put(8, Arrays.asList("t", "u", "v"));
        put(9, Arrays.asList("w", "x", "y", "z"));
    }};

    private final HashMap<Integer, List<Word>> combinations= new HashMap<>(){{
        put(3, new ArrayList<>());
        put(4, new ArrayList<>());
        put(7, new ArrayList<>());
    }};

    public HashMap<Integer, List<Word>> getCombinations() {
        return combinations;
    }

    public LetterGenerator(Phone number) {
        combinationGenerator(number);
    }

    private void combinationGenerator(Phone number) {
        //suffix Combinations
        for (String s : letters.get(number.preFix / 100)) {
            for (String str : letters.get((number.preFix % 100) / 10)) {
                for (String sr : letters.get(number.preFix % 10)) {
                    this.combinations.get(3).add(new Word(s + str + sr));
                }
            }
        }

        //prefix Combinations
        for (String s : letters.get(number.suffix / 1000)) {
            for (String str : letters.get((number.suffix % 1000) / 100)) {
                for (String sr : letters.get((number.suffix % 100) / 10)) {
                    for (String st : letters.get(number.suffix % 10)) {
                        this.combinations.get(4).add(new Word(s + str + sr + st));
                    }
                }
            }
        }
        for (Word w : combinations.get(3)) {
            for (Word wo : combinations.get(4)) {
                this.combinations.get(7).add(new Word(w.word + wo.word));
            }
        }

    }
}
//if the number has a 0 or 1 then we know it won't generate a word
//So we are gonna check for that first


