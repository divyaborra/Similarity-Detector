package similarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sets.SetUtilities;

public class SimilarityUtilities
{
	/**
	 * Returns the set of non-empty lines contained in a text, trimmed of leading
	 * and trailing whitespace.
	 * 
	 * @param text
	 * @return the trimmed set of lines
	 */
	public static Set<String> trimmedLines(String text)
	{
		Set<String> result = new HashSet<String>();

		String[] splitLines = text.split("\\n");

		for (int i = 0; i < splitLines.length; i++)
		{
			if (splitLines[i].trim().isEmpty() == false)
			{
				result.add(splitLines[i].trim());
			}
		}

		return result;

	}

	/**
	 * Returns a list of words in the text, in the order they appeared in the text,
	 * converted to lowercase.
	 * 
	 * Words are defined as a contiguous sequence of letters and numbers.
	 *
	 * @param text
	 * @return a list of lowercase words
	 */
	public static List<String> asLowercaseWords(String text)
	{
		List<String> result = new ArrayList<String>();

		String[] lowercase = text.toLowerCase().split("\\W+");

		for (int i = 0; i < lowercase.length; i++)
		{
			if (lowercase[i].trim().isEmpty() == false)
			{
				result.add(lowercase[i].trim());
			}
		}

		return result;

	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines.
	 * 
	 * @param text1 a text
	 * @param text2 another text
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2)
	{
		// Set<String> t1 = asLowercaseWords(text1);
		Set<String> t1 = trimmedLines(text1);

		Set<String> t2 = trimmedLines(text2);

		return (SetUtilities.jaccardIndex(t1, t2));

		// else

	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines, less the set of trimmed lines from the templateText. Removes
	 * the template text from consideration after trimming lines, not before.
	 * 
	 * @param text1        a text
	 * @param text2        another text
	 * @param templateText a template, representing things the two texts have in
	 *                     common
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2, String templateText)
	{

		Set<String> ignore = trimmedLines(templateText);

		Set<String> t1 = trimmedLines(text1);
		Set<String> t2 = trimmedLines(text2);

		Set<String> diff13 = SetUtilities.setDifference(t1, ignore);
		Set<String> diff23 = SetUtilities.setDifference(t2, ignore);
		return (SetUtilities.jaccardIndex(diff13, diff23));

	}

	/**
	 * Returns a set of strings representing the shingling of the given length of a
	 * list of words.
	 * 
	 * A shingling of length k of a list of words is the set of all k-shingles of
	 * that list.
	 * 
	 * A k-shingle is the concatenation of k adjacent words.
	 * 
	 * For example, a 3-shingle of the list: ["a" "very" "fine" "young" "man" "I"
	 * "know"] is the set: {"averyfine" "veryfineyoung" "fineyoungman" "youngmanI"
	 * "manIknow"}.
	 * 
	 * @param words
	 * @param shingleLength
	 * @return
	 */
	public static Set<String> shingle(List<String> words, int shingleLength)
	{

		// Set<String> s

		Set<String> result = new HashSet<String>();

		for (int j = 0; j < words.size() - shingleLength + 1; j++)
		{
			String newWord = "";
			for (int i = j; i < j + shingleLength; i++)
			{
				newWord += words.get(i);
			}
			result.add(newWord);

		}

		return result;

	}

	/**
	 * Returns the shingled word similarity of two texts.
	 * 
	 * The shingled word similarity is the Jaccard index between each text's shingle
	 * set.
	 * 
	 * A text's shingle set is the set of shingles (of the given length) for the
	 * entire text, as defined by shingle and asLowercaseWords, less the shingle set
	 * of the templateText. Removes the templateText from consideration after
	 * shingling, not before.
	 * 
	 * @param text1
	 * @param text2
	 * @param templateText
	 * @param shingleLength
	 * @return
	 */
	public static double shingleSimilarity(String text1, String text2, String templateText, int shingleLength)
	{

		// List<String> t1 = asLowercaseWords(templateText);
		List<String> ignore = asLowercaseWords(templateText);

		List<String> t1 = asLowercaseWords(text1);

		List<String> t2 = asLowercaseWords(text2);

		Set<String> toIgnore = shingle(ignore, shingleLength);

		Set<String> s1 = shingle(t1, shingleLength);
		Set<String> s2 = shingle(t2, shingleLength);

		Set<String> diff1 = SetUtilities.setDifference(s1, toIgnore);
		Set<String> diff2 = SetUtilities.setDifference(s2, toIgnore);

		return (SetUtilities.jaccardIndex(diff1, diff2));

	}
}
