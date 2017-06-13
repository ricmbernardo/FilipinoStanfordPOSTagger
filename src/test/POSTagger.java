package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import models.TaggedSentence;
import utils.FileManager;
import utils.TaggedSentenceService;

public class POSTagger {
	private MaxentTagger tagger;

	public POSTagger(String taggerModelFilename) {
		tagger = new MaxentTagger(taggerModelFilename);
	}

	public List<TaggedSentence> tagFile(String untaggedFilename) throws FileNotFoundException, IOException {
		List<String> untagged = FileManager.readFile(new File(untaggedFilename));
		List<TaggedSentence> tagged = new ArrayList<>();
		for (int i = 0; i < untagged.size(); i++) {
			String t = tagger.tagTokenizedString(untagged.get(i));
			System.out.println(t);
			tagged.add(TaggedSentenceService.getTaggedSentence(i, t));
		}
		return tagged;
	}

	public static void saveTagsIntoFile(List<TaggedSentence> taggedSentences, String taggedFilename)
			throws IOException {
		FileManager tagsFile = null;
		tagsFile = new FileManager(taggedFilename);
		tagsFile.createFile();
		for (int x = 0; x < taggedSentences.size(); x++) {
			if (tagsFile != null)
				tagsFile.writeToFile(TaggedSentenceService.getString(taggedSentences.get(x)));
		}
		if (tagsFile != null)
			tagsFile.close();
	}

	public TaggedSentence tagSentence(String sentence) {
		return TaggedSentenceService.getTaggedSentence(tagger.tagTokenizedString(sentence));
	}
}
