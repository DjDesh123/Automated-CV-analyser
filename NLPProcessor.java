import edu.stanford.nlp.pipeline.*;

import java.util.*;

public class NLPProcessor {
    private StanfordCoreNLP pipeline;

    public NLPProcessor() {
        // Load the NLP pipeline with English models
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public void analyzeText(String text) {
        CoreDocument doc = new CoreDocument(text);
        pipeline.annotate(doc);

        // Extract Named Entities (Organizations, Locations, People, etc.)
        for (CoreEntityMention em : doc.entityMentions()) {
            System.out.println("Entity: " + em.text() + " -> " + em.entityType());
        }
    }
}
