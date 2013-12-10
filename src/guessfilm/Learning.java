package guessfilm;

import guessfilm.model.Film;
import guessfilm.model.Question;

/**
 * 
 * Class for WEKA Library
 * 
 */

public class Learning {
	// TODO classifier

	public enum ClassifierType {
		NAIVE_BAYES;
		// TODO: add classifier
	}

	public Learning() {

	}

	public void setClassifier(ClassifierType classifierType) {
		// TODO Set Classifier

	}

	public void loadModel() {
		// TODO Load model

	}

	/**
	 * Add feature in the vector of features
	 * 
	 * @param currentQuestion
	 * @param answerOnQuestion
	 */
	public void addFeature(Question currentQuestion,
			GuessFilm.AnswerOnQuestion answerOnQuestion) {
		// TODO Auto-generated method stub

	}

	public Film classify() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Add film's name in the vector of features (for training mode)
	 * 
	 * @param trueFilm
	 */
	public void addAnswer(Film trueFilm) {
		// TODO Auto-generated method stub

	}

	public void train() {
		// TODO Auto-generated method stub

	}

	public void saveModel() {
		// TODO Auto-generated method stub

	}

}
