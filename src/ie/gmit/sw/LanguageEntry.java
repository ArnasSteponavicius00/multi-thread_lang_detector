package ie.gmit.sw;

/**
 * Getter and Setter methods for ranking Languages, by comparing a kmer to another
 * by comparing their frequency. Done in descending order to keep the most occuring kmer first.
 * @author Arnas Steponavicius - G00361891 - Object Oriented Programming GMIT
 */

public class LanguageEntry implements Comparable<LanguageEntry> {
	private int kmer;
	private int frequency;
	private int rank;

	public LanguageEntry(int kmer, int frequency) {
		super();
		this.kmer = kmer;
		this.frequency = frequency;
	}

	public int getKmer() {
		return kmer;
	}

	public void setKmer(int kmer) {
		this.kmer = kmer;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(LanguageEntry next) {
		return - Integer.compare(frequency, next.getFrequency());
	}
	
	@Override
	public String toString() {
		return "[" + kmer + "/" + frequency + "/" + rank + "]";
	}
}