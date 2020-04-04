package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Parse Subject file and Query file.
 * @author Arnas Steponavicius - G00361891 - Object Oriented Programming GMIT
 */

public class Parser implements Runnable{
	private Map<Integer, LanguageEntry> query = new ConcurrentHashMap<>();
	private int kmer = 0;
	private int frequency = 1;
	private Database db = null;
	private String file;
	private String queryFile;
	private int k;
	
	public Parser(String queryFile, String file, int k) {
		this.file = file;
		this.queryFile = queryFile;
		this.k = k;
	}

	public void setDb(Database db) {
		this.db = db;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader datasetReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			BufferedReader queryReader = new BufferedReader(new InputStreamReader(new FileInputStream(queryFile)));
			String queryFile;		
			String line = null;
			
			//Read in dataset/subject file
			while((line = datasetReader.readLine()) != null) {
				String[] record = line.trim().split("@");
				if(record.length != 2) continue;
				parse(record[0], record[1]);
			}
			
			//Read in query file
			while((queryFile = queryReader.readLine()) != null) {			
				System.out.println("Reading in file...");
				
				//loop over the query file and convert it into hash code.
				for(int i = 0; i <= queryFile.length() - k; i++) {
					CharSequence lang = queryFile.substring(i, i+k);
					kmer = lang.hashCode();
							
					if (query.containsKey(kmer)) {
						frequency += query.get(kmer).getFrequency();
					}
					LanguageEntry l = new LanguageEntry(kmer, frequency);
					query.put(kmer, l);
				}
				analyseQuery(query);
			}
			
			datasetReader.close();
			queryReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void parse(String text, String lang, int... ks) {
		Language language = Language.valueOf(lang);
		
		for(int i = 0; i <= text.length() - k; i++) {
			CharSequence kmer = text.substring(i, i+k);
			db.add(kmer, language);
		}
	}
	
	private void analyseQuery(Map<Integer, LanguageEntry> query) throws IOException {
		Language language;	

		language = db.getLanguage(query);
		System.out.println("Language is: "+ language + "\n");
	}
}
