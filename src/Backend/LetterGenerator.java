package Backend;

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

    private final HashMap<Integer, List<String>> numberLetterMappings = new HashMap<>() {{
        put(0, List.of(" "));
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

    private  HashMap<Integer, List<Word>> letterCombinations;

    public LetterGenerator(PhoneNumber number) {
        combinationGenerator(number);
    }

    public HashMap<Integer, List<Word>> getLetterCombinations() {
        return letterCombinations;
    }

    private void combinationGenerator(PhoneNumber number) {

        //will reset list if it is not empty
        letterCombinations = new HashMap<>(){{
            put(3, new ArrayList<>());
            put(4, new ArrayList<>());
            put(7, new ArrayList<>());
        }};

        //suffix Combinations
        int numberPrefix = number.getPreFix();
        int numberSuffix = number.getSuffix();

        for (String s : numberLetterMappings.get(numberPrefix / 100)) {
            for (String str : numberLetterMappings.get((numberPrefix % 100) / 10)) {
                for (String sr : numberLetterMappings.get(numberPrefix % 10)) {
                    this.letterCombinations.get(3).add(new Word(s + str + sr));
                }
            }
        }

        //prefix Combinations
        for (String s : numberLetterMappings.get(numberSuffix / 1000)) {
            for (String str : numberLetterMappings.get((numberSuffix % 1000) / 100)) {
                for (String sr : numberLetterMappings.get((numberSuffix % 100) / 10)) {
                    for (String st : numberLetterMappings.get(numberSuffix % 10)) {
                        this.letterCombinations.get(4).add(new Word(s + str + sr + st));
                    }
                }
            }
        }
        for (Word threeLetterWord : letterCombinations.get(3)) {
            for (Word fourLetterWord : letterCombinations.get(4)) {
                this.letterCombinations.get(7).add(new Word(threeLetterWord.getWord() + fourLetterWord.getWord()));
            }
        }

    }

}


//if the number has a 0 or 1 then we know it won't generate a word
//So we are gonna check for that first


