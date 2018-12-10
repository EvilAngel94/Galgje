package opstarten.galgje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Deze klasse leest de woorden uit van het {@link galgje_woorden_lijst}
 * resources.
 * 
 * Aan de hand van de keuze die de speler maakt word de juiste woordenlijst
 * geladen.
 *
 */
public class WoordenlijstUitlezen {

	private final String TEMPLATE_FILE = "4_words_galgje.csv";

	private Map<Integer, String> galgjeWoorden;

	public WoordenlijstUitlezen() {
		super();
		galgjeWoorden = new HashMap<Integer, String>();

	}

	/**
	 * Leest een bestand uit met galg woorden.
	 */
	public void leesBestandUit() {
//		InputStream in = this.getClass().getResourceAsStream(TEMPLATE_FILE); Necessary for reading src/main/resources.
		File file = new File(TEMPLATE_FILE);
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				System.out.print(scanner.next() + "|");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
