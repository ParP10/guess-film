package guessfilm.model;

import java.util.Map;
import java.util.HashMap;

/**
 * 
 * Store data about questions
 * 
 */
// XXX Why not to use just List<Film>? It would be more efficient in terms of
// both memory and cpu.
public class Questions {
	private int amountQuestions;
	private int amountAskedQuestions;
	private Map<Integer, Question> listQuestions = new HashMap<Integer, Question>();

	public Questions() {
		initializeListQuestions();
		amountQuestions = listQuestions.size();
		amountAskedQuestions = 0;
	}

	/**
	 * Initialize list of questions (listQuestions). Select data from database
	 */
	private void initializeListQuestions() {
		// TODO Select all pairs <id, Question> from database and save in
		// listQuestions
	}

	/**
	 * @return Amount questions in database
	 */
	public int getAmountQuestions() {
		return amountQuestions;
	}

	/**
	 * 
	 * @param idQuestion
	 *            Question's ID in database
	 * @return Question with ID = idQuestion
	 */
	public Question getQuestion(int idQuestion) {
		return listQuestions.get(idQuestion);
	}

	/**
	 * Append new questions in database
	 */
	public void appendNewQuestions() {
		// TODO append new Questions in database (call
		// Question.appendNewQuestion)
	}

	/**
	 * @return true, if there is a question, which haven't been asked yet.
	 *         false, otherwise
	 */
	public boolean existsQuestion() {
		if (amountQuestions > amountAskedQuestions) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * next question
	 */
	public Question getNextQuestion() {
		Question curQuestion = new Question();

		// TODO realize algorithm

		amountAskedQuestions++;
		return curQuestion;
	}

}