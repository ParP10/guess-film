
/**
 * 
 * Store data about question
 *
 */
public class Question {

	private int id;
	private String name;

	public Question(int idQuestion) {
		id = idQuestion;
		
		DataBase dataBase = new DataBase();
		name = dataBase.selectQuestionName(id);
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
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	/**
	 * @return String representation of question
	 */
	public String getQestionName() {
		return name;
	}
	/**
	 * Append new Question into database
	 * @return
	 */
	public void appendNewQuestion(int idQuestion) {
		// TODO append new question into database
	}

}
