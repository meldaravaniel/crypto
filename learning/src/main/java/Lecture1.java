import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * This is the solution class for http://crypto-textbook.com/ Lecture 1: https://wiki.crypto.rub.de/Buch/en/download/problems_only/problems_chaptr_1.pdf
 */
public class Lecture1 {

    static List<Character> englishFrequency = asList('e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'c', 'u', 'm', 'w', 'f', 'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z');
    static List<Character> englishFrequencyInDictionary = asList('e', 's', 'i', 'a', 'r', 'n', 't', 'o', 'l', 'c', 'd', 'u', 'g', 'p', 'm', 'h', 'b', 'y', 'f', 'v', 'k', 'w', 'z', 'x', 'j', 'q');

    public static void main(String[] args) {
        String testString = "lrvmnir bpr sumvbwvr jx bpr lmiwv yjeryrkbi jx qmbm wi bpr xjvni mkd ymibrut jx irhx wi bpr riirkvr jx ymbinlmtmipw utn qmumbr dj w ipmhh but bj rhnvwdmbr bpr yjeryrkbi jx bpr qmbm mvvjudwko bj yt wkbrusurbmbwjk lmird jk xjubt trmui jx ibndt wb wi kjb mk rmit bmiq bj rashmwk rmvp yjeryrkb mkd wbi iwokwxwvmkvr mkd ijyr ynib urymwk nkrashmwkrd bj ower m vjyshrbr rashmkmbwjk jkr cjnhd pmer bj lr fnmhwxwrd mkd wkiswurd bj invp mk rabrkb bpmb pr vjnhd urmvp bpr ibmbr jx rkhwopbrkrd ywkd vmsmlhr jx urvjokwgwko ijnkdhrii ijnkd mkd ipmsrhrii ipmsr w dj kjb drry ytirhx bpr xwkmh mnbpjuwbt lnb yt rasruwrkvr cwbp qmbm pmi hrxb kj djnlb bpmb bpr xjhhjcwko wi bpr sujsru msshwvmbwjk mkd wkbrusurbmbwjk w jxxru yt bprjuwri wk bpr pjsr bpmb bpr riirkvr jx jqwkmcmk qmumbr cwhh urymwk wkbmvb";
        Map<Integer, List<Character>> lettersCount = getLettersCount(
                testString);
        lettersCount.forEach((key, value) -> System.out.println(String.format("%s occur %d times", value.toString(), key)));

        Object[] keys = lettersCount.keySet().toArray();
        System.out.println(Arrays.toString(keys));
        int count = 0;
        Map<String, String> cipher = new HashMap<>();
        // too lazy to account for it later.
        cipher.put(" ", " ");
        // these are the letters the letter frequency didn't quite work for, but it got me far enough.
        cipher.put("s", "p");
        cipher.put("w", "i");
        cipher.put("x", "f");
        cipher.put("l", "b");
        cipher.put("n", "u");
        cipher.put("j", "o");
        cipher.put("k", "n");
        cipher.put("d", "d");
        cipher.put("t", "y");
        cipher.put("h", "l");
        cipher.put("q", "k");
        cipher.put("o", "g");
        cipher.put("a", "x");
        cipher.put("c", "w");
        cipher.put("g", "z");
        for (int i = keys.length - 1; i >= 0; i--) {
            List<Character> characters = lettersCount.get(keys[i]);
//            if (characters.size() == 1) {
                for (int j = characters.size() - 1; j >= 0; j--) {
                    Character character = characters.get(j);
                    Character replacementCharacter = englishFrequency.get(count);
                    // yeah it's hacky. shut up. I'm tired and I want this to work.
                    cipher.computeIfAbsent("" + character, k -> "" + replacementCharacter);
//            }
                    count++;
                }
        }
        System.out.println(cipher.toString());
        StringBuilder decodedString = new StringBuilder();
        for (int i = 0; i < testString.length(); i++) {
            String replacementCharacter = cipher.get("" + testString.charAt(i));
            if (replacementCharacter != null) {
                decodedString.append(replacementCharacter);
            }
        }
        System.out.println(decodedString.toString());
    }

    public static Map<Integer, List<Character>> getLettersCount(String testString) {
        Map<Character, Integer> countByLetter = new HashMap<>();
        for (int i = 0; i < testString.length(); i++) {
            char key = testString.charAt(i);
            if (key == ' ') {
                continue;
            }
            countByLetter.merge(key, 1, Integer::sum);
        }
        Map<Integer, List<Character>> frequency = countByLetter.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getValue,
                entry -> {
                    List<Character> chars = new ArrayList<>();
                    chars.add(entry.getKey());
                    return chars;
                },
                (existing, replacement) -> {
                    existing.addAll(replacement);
                    return existing;
                }, TreeMap::new));
        return frequency;
    }
}
