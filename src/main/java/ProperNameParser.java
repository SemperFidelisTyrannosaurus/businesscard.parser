import com.google.common.base.Joiner;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProperNameParser {

    private static Tokenizer tokenizer = null;
    private static NameFinderME nameFinder = null;

    static {
        try {
            //these files contain the raw token/name values used for natural language processing
            tokenizer = new TokenizerME(new TokenizerModel(new FileInputStream("src/main/resources/en-token.bin")));
            nameFinder = new NameFinderME(new TokenNameFinderModel(new FileInputStream("src/main/resources/en-ner-person.bin")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // modified code from https://gist.github.com/johnmiedema/312819de5bd80ca3438a
    private List<Double> nameProbabilities = new ArrayList<Double>();
    private List<String> names = new ArrayList<>();

    public void consider(String line) {
        String tokens[] = tokenizer.tokenize(line);
        Span nameSpans[] = nameFinder.find(tokens);

        if (nameSpans.length == 0)
            return;
        // stats(tokens, nameSpans);

        nameProbabilities.add(nameFinder.probs(nameSpans)[0]);
        names.add(line);
    }

    public String getName() {
        if (names.size() == 1) {
            return names.get(0);
        } else if (names.size() > 1) {
            // return the highest probability name
            double maxNameProbability = Double.MIN_VALUE;
            int maxNameProbabilityPosition = -1;
            for (int i = 0; i < nameProbabilities.size(); i++) {
                if (nameProbabilities.get(i) > maxNameProbability) {
                    maxNameProbability = nameProbabilities.get(i);
                    maxNameProbabilityPosition = i;
                }
            }
            return names.get(maxNameProbabilityPosition);
        }
        return "";
    }

    /**
     * Gives information on how OpenNLP determined the most likely name
     * @param tokens
     * @param nameSpans
     */
    private void stats(String[] tokens, Span[] nameSpans) {
        if (nameSpans.length == 0)
            return;
        Span nameSpan = nameSpans[0];
        System.out.println("Name span: " + nameSpan.toString());
        System.out.println("Text Covered: " + Joiner.on(" ").join(Arrays.asList(tokens).subList(nameSpans[0].getStart(), nameSpans[0].getEnd())));
        System.out.println("Probability: " + nameFinder.probs(nameSpans)[0]);
    }
}
