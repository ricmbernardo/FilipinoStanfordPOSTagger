package test;

import java.io.IOException;
import java.util.List;

import models.TaggedSentence;
import utils.Constants;

public class Main {

	public static void main(String[] args) throws IOException {
		POSTagger posTagger = new POSTagger(Constants.LEFT5WORDS_OWLQN2_DISTSIM_PREF6_INF2);
		List<TaggedSentence> taggedSentences = posTagger
				.tagFile("data/abnk.doc.txt.noBlank.noHex.convHTML.tok.cleaned");
		posTagger.saveTagsIntoFile(taggedSentences, "data/output.tags");
	}

}
