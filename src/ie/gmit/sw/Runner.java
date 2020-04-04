package ie.gmit.sw;

import java.io.IOException;

/**
 * Calls the Menu.
 * @author Arnas Steponavicius - G00361891 - Object Oriented Programming GMIT
 */

public class Runner {
	public static void main(String[] args) throws IOException, Throwable {		
		Menu menu = new Menu();
		menu.show();	
	}
}
