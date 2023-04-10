import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The phone number 409-880-8748 =
 * String: {g, h, i} ⨯ {+} ⨯ {w, x, y, z} – {t, u, v} ⨯ {t, u, v} ⨯ {+} - {t, u, v} ⨯ {p, q, r, s} ⨯ {g, h, i} ⨯ {t, u, v}, where ⨯ means the cartesian product of the previous sets.
 *
 * 409-880-7011 could be any word from the cartesian product
 * {g, h, i} ⨯ {_} ⨯ {w, x, y, z} ⨯ {t, u, v} ⨯ {t, u, v} ⨯ {_} ⨯ {p, q, r, s} ⨯ {_} ⨯ {/, ., @} ⨯ {/, ., @}.
 *
 * the words “call now” correspond to “2255 669”.
 *
 *
 * Number C-Product Breakdown:
 *
 * 0 - {  }
 * 1 - {/, . , @ }
 * 2 - { a, b, c}
 * 3 - { d, e, f }
 * 4 - { g, h, i }
 * 5 - { j, k, l }
 * 6 - { m, n, o }
 * 7 - { p, q, r, s }
 * 8 - { t, u, v }
 * 9 - { w, x, y, z }
 * */

public class LetterGenerator {

  private final HashMap<Integer, List<String>> letters = new HashMap<>(){{
        put(2, Arrays.asList("a", "b", "c"));
        put(3, Arrays.asList("d", "e", "f"));
        put(4, Arrays.asList("g", "h", "i"));
        put(5, Arrays.asList("j", "k", "l"));
        put(6, Arrays.asList("m", "n", "o"));
        put(7, Arrays.asList("p", "q", "r", "s"));
        put(8, Arrays.asList("t", "u", "v"));
        put(9, Arrays.asList("w", "x", "y", "z"));
    }};

  private final List<Word> prefixWords = new ArrayList<>();
  private final List<Word> suffixWords = new ArrayList<>();
  private final List<Word> sevenLetterWords = new ArrayList<>();

    public List<Word> letterGeneration(Phone number){
      List<Word> allWords = new ArrayList<>();

      //suffix Combinations
      for (String s: letters.get(number.preFix/100)) {
          for (String str: letters.get((number.preFix % 100)/10)){
              for (String sr: letters.get(number.preFix % 10)) {
                  this.prefixWords.add(new Word(s + str + sr));
              }
          }
      }
      //prefix Combinations
      for (String s: letters.get(number.suffix/1000)) {
          for (String str: letters.get((number.suffix % 1000)/100)){
              for (String sr: letters.get((number.suffix % 100)/10)) {
                  for (String st: letters.get(number.suffix % 10)) {
                      this.suffixWords.add(new Word(s + str + sr + st));
                  }
              }
          }
      }

      for (Word w: prefixWords) {
          for (Word wo: suffixWords) {
              this.sevenLetterWords.add(new Word(w.word + wo.word));
          }
      }
      allWords.addAll(prefixWords);
      allWords.addAll(suffixWords);
      allWords.addAll(sevenLetterWords);
       return allWords;
      }
  }
//if the number has a 0 or 1 then we know it won't generate a word
//So we are gonna check for that first


