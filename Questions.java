import java.util.Map;
import java.util.HashMap;

/**
 * 
 * Store data about questions
 *
 */
public class Questions {
	private int amountQuestions;
	private Map<Integer, Question> listQuestions = new HashMap<Integer, Question>();

	public Questions() {
		initializeListQuestions();
		amountQuestions = listQuestions.size();
	}
	
	/**
	 * Initialize list of questions (listQuestions).
	 * Select data from database
	 */
	private void initializeListQuestions() {
		// TODO Select all pairs <id, Question> from database and save in listQuestions
	}
	
	/**
	 * @return Amount questions in database
	 */
	public int getAmountQuestions() {
		return amountQuestions;
	}
	
	/**
	 * 
	 * @param idQuestion Question's ID in database
	 * @return Question with ID = idQuestion
	 */
	public Question getQuestion(int idQuestion) {
		return listQuestions.get(idQuestion);
	}
	
	/**
	 *  Append new questions in database
	 */
	public void appendNewQuestions() {
		// TODO append new Questions in database (call Question.appendNewQuestion)
	}

}