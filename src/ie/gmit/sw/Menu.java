package ie.gmit.sw;

import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * Allows user to specify their desired Dataset file, Kmer count (1-4) and Query file.
 * @author Arnas Steponavicius - G00361891 - Object Oriented Programming GMIT
 */

public class Menu {
	private Scanner console = new Scanner(System.in);
	private String dataLoc;
	private String queryFile;
	private int kmer;
	private boolean choice = true;

	/**
	 * Handle the menu and method calls.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void show() throws IOException, InterruptedException {
		display();
		System.out.print("Enter WiLI Data Location: ");
		dataLoc = console.next();
		while(choice) {
			System.out.print("1 - Continue\n2 - Exit\nenter: ");
			String option = console.next();
			handle(dataLoc, option);
		}
	}

	private void display() {
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*             Text Language Detector              *");
		System.out.println("*                                                 *");
		System.out.println("***************************************************");
	}

	private void handle(String dataLoc, String option) throws IOException, InterruptedException {
		
		if(option.equals("2")) {			
			choice = false;	
		}else if(option.equals("1")) {
			
			System.out.print("Enter Kmers (1-4):");
			kmer = console.nextInt();
			
			if(kmer > 4 || kmer <= 0) {
				System.out.println("Invalid value for kmer");
			}else {
				System.out.print("Enter Query file location: ");
				queryFile = console.next();
				
				System.out.println("Building Dataset...");
				Parser p = new Parser(queryFile, dataLoc, kmer);
				Database db = new Database();
				p.setDb(db);
				
				
				Thread t = new Thread(p);
				t.start();
				
//				System.out.println(t.isAlive());
				
				t.join();
				
//				System.out.println(t.isAlive());
				
				db.resize(300);		
			}
		}
	}
}
