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

  private HashMap<Integer, List<String>> letters = new HashMap<>(){{
        put(2, Arrays.asList("a", "b", "c"));
        put(3, Arrays.asList("d", "e", "f"));
        put(4, Arrays.asList("g", "h", "i"));
        put(5, Arrays.asList("j", "k", "l"));
        put(6, Arrays.asList("m", "n", "o"));
        put(7, Arrays.asList("p", "q", "r", "s"));
        put(8, Arrays.asList("t", "u", "v"));
        put(9, Arrays.asList("w", "x", "y", "z"));
    }};

//if the number has a 0 or 1 then we know it won't generate a word
//So we are gonna check for that first

}
