import java.util.Map;
import java.util.HashMap;

/**
 * 
 * Store data about films
 *
 */
public class Films {
	private int amountFilms;
	private Map<Integer, Film> listFilms = new HashMap<Integer, Film>();

	public Films() {
		initializeListFilms();
		amountFilms = listFilms.size();
	}
	
	/**
	 * Initialize list of films (listFilms).
	 * Select data from database
	 */
	private void initializeListFilms() {
		// TODO Select all pairs <id, Film> from database and save in listFilms
	}
	
	/**
	 * @return Amount films in database
	 */
	public int getAmountFilms() {
		return amountFilms;
	}
	
	/**
	 * 
	 * @param idFilm Film's ID in database
	 * @return Film with ID = idFilm
	 */
	public Film getFilm(int idFilm) {
		return listFilms.get(idFilm);
	}
	
	/**
	 *  Append new Films in database
	 */
	public void appendNewFilms() {
		// TODO append new films in database (call Film.appendNewFilm)
	}

}
