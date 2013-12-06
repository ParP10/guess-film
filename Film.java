

/**
 * 
 * Store data about film
 *
 */
public class Film {
	
	private int id;
	private String name;

	public Film(int idFilm) {
		id = idFilm;
		
		DataBase dataBase = new DataBase();
		name = dataBase.selectFilmName(id);
	}
	
	public Film() {
	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	/**
	 * @return String representation of film
	 */
	public String getFilmName() {
		return name;
	}
	
	/**
	 * Add new Film into database
	 * @param idFilm
	 */
	public void appendNewFilm(int idFilm) {
		// TODO add new film in database
	}

}
