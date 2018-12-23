package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Deze klasse leest de woorden uit van het {@link galgje_woorden_lijst}
 * resources.
 * 
 * Aan de hand van de keuze die de speler maakt word de juiste woordenlijst
 * geladen.
 * 
 * The following 2 classes are used to read the file.
 * {@link https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html}
 * {@link https://docs.oracle.com/javase/8/docs/api/java/io/FileReader.html}
 *
 */
public class WoordenlijstUitlezen {

	private Map<Integer, String> galgjeWoorden;

	public WoordenlijstUitlezen() {
		super();
		galgjeWoorden = new HashMap<Integer, String>();

	}

	/**
	 * Leest een bestand uit met galg woorden.
	 * 
	 * @throws IOException
	 */
	public Map<Integer, String> readsSelectedCSVFile(String wordlist) throws IOException {
		//this refers to this class.
		InputStream inputStream = this.getClass().getResourceAsStream("/galgje_woorden_lijst/" +wordlist);
		InputStreamReader inputReader = new InputStreamReader(inputStream);
		
		
		try {
			BufferedReader reader = new BufferedReader(inputReader);
			String line;
			String seperator = ",";

			reader.readLine(); // This reads the first line.

			// This checks if the reader has a next line.
			while ((line = reader.readLine()) != null) {

				String[] field = line.split(seperator);
				int id = Integer.parseInt(field[0]);
				String word = field[1];

				galgjeWoorden.put(id, word);
			}

			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return galgjeWoorden;
	}
}
