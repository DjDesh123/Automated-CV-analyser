import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import org.apache.commons.text.similarity.LevenshteinDistance;
import java.util.*;




// this class is for processing text using the stanford NLP
public class NLPProcessor {
    //pipeline is the instance of StanfordCoreNLP which is used for anaylsing text
    private StanfordCoreNLP pipeline;

    public NLPProcessor() {
        // Initialize the Stanford NLP pipeline
        Properties props = new Properties();

        //you define which NLP annotators  (the processesing steps) to use:
        /*
            "tokenize" - Splits text into words (tokens).

            "ssplit" - Splits text into sentences.

            "pos" - Tags each word with its Part of Speech (POS) (e.g., noun, verb).

            "lemma" - Converts words to their base form (e.g., "running" → "run").

            "ner" - Recognizes named entities (e.g., locations, dates, names).
         */
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        this.pipeline = new StanfordCoreNLP(props);
    }
    public List<String> AnalyzeAndMatch(String CvText, List<String> JobRequirements) {
        // Create a CoreDocument with the CV text so Stanford NLP can process it
        CoreDocument doc = new CoreDocument(CvText);
        // Analyze the text
        pipeline.annotate(doc);

        // Set to store extracted words and noun phrases
        // used sets to prevent duplicates
        Set<String> ExtractedWords = new HashSet<>();
        Set<String> ExtractedPhrases = new HashSet<>();

        // doc.token gives all words in the cv
        //they are also converted to lowercase to prevent case mismatch
        for (CoreLabel token : doc.tokens()) {
            ExtractedWords.add(token.word().toLowerCase());
        }

        // Extract noun phrases from the CV using dependency parsing
        //loops through every sentence in the cv and because each word has a POS tag then we write an if statements to say if there's a noun then store it in the Extracted phrases
        for (CoreSentence sentence : doc.sentences()) {
            List<String> phrase = new ArrayList<>();
            for (CoreLabel token : sentence.tokens()) {
                // If it's a noun
                if (token.tag().startsWith("NN") || token.tag().startsWith("NNP")) {
                    phrase.add(token.word().toLowerCase());
                } else {
                    if (!phrase.isEmpty()) {
                        ExtractedPhrases.add(String.join(" ", phrase));
                        phrase.clear();
                    }
                }
            }
            if (!phrase.isEmpty()) {
                ExtractedPhrases.add(String.join(" ", phrase));
            }
        }

        // helps getting the noun phrases
        for (CoreSentence sentence : doc.sentences()) {
            for (CoreLabel token : sentence.tokens()) {
                if (token.tag().startsWith("NN") || token.tag().startsWith("NNP")) {
                    ExtractedPhrases.add(token.word().toLowerCase());
                }
            }
        }

        // Debugging output
        System.out.println("Extracted Noun Phrases: " + ExtractedPhrases);
        System.out.println("Extracted Words: " + ExtractedWords);

        // stores the matched requirements
        // we used the LevenshteinDistance for the fuzzy words
        List<String> MatchedRequirements = new ArrayList<>();
        LevenshteinDistance ld = new LevenshteinDistance();

        // Compare noun phrases first
        // if the word that is extracted fits with one of the words in the requirements then it adds the matched requirements
        // Find sentences that contain the extracted phrase
        Map<String, String> PhraseToSentence = new HashMap<>();
        for (CoreSentence sentence : doc.sentences()) {
            for (String phrase : ExtractedPhrases) {
                if (sentence.text().toLowerCase().contains(phrase)) {
                    PhraseToSentence.put(phrase, sentence.text().toLowerCase());
                }
            }
        }

        // basically it converts the extracted phrases and compares them to the actual requirements and by getting the actual sentence with using the distance to see if they remotely match
        for (String Requirement : JobRequirements) {
            String LowerReq = Requirement.toLowerCase();

            for (String Phrase : ExtractedPhrases) {
                // Get the full sentence
                String CvSentence = PhraseToSentence.get(Phrase);

                if (CvSentence != null) {
                    int Distance = ld.apply(LowerReq, CvSentence);

                    // Small difference allowed
                    if (Distance <= 15) {
                        MatchedRequirements.add(Requirement);
                        break;
                    }
                }
            }
        }

        // If no matches are found in the phrases, compare individual words
        // uses levenshtein distance to compare similar words  by coutning the number of changes needed to make them identical
        if (MatchedRequirements.isEmpty()) {
            for (String Requirement : JobRequirements) {
                String LowerReq = Requirement.toLowerCase();
                for (String Word : ExtractedWords) {
                    int Distance = ld.apply(Word, LowerReq);
                    if (Distance <= 2) {
                        MatchedRequirements.add(Requirement);
                        // Stop checking if a match is found
                        break;
                    }
                }
            }
        }

        return MatchedRequirements;
    }
}
