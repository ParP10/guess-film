package guessfilm;

import guessfilm.Learning.ClassifierType;
import guessfilm.model.Film;
//import javax.jws.WebParam.Mode;
import guessfilm.model.Films;
import guessfilm.model.Question;
import guessfilm.model.Questions;

/**
 * 
 * Core class
 * 
 */
public class GuessFilm {
	public enum Mode {
		TRAINING_MODE, GUESS_MODE, APPEND_NEW_QUESTIONS, APPEND_NEW_FILMS;
	};

	public enum AnswerOnQuestion {
		YES, NO, DO_NOT_KNOW;
	};

	private Films films = new Films();
	private Questions questions = new Questions();
	private UserInterface user = new UserInterface();

	/**
	 * Program start here
	 */
	// TODO it is probably better to have main method in interface classes.
	public void main(String[] args) {
		GuessFilm guessFilm = new GuessFilm();

		/*
		 * Choose mode
		 */
		// XXX You can use switch with enums
		if (guessFilm.modeSelection() == Mode.TRAINING_MODE) {
			guessFilm.train();
		} else if (guessFilm.modeSelection() == Mode.GUESS_MODE) {
			guessFilm.guess();
		} else if (guessFilm.modeSelection() == Mode.APPEND_NEW_QUESTIONS) {
			questions.appendNewQuestions();
		} else if (guessFilm.modeSelection() == Mode.APPEND_NEW_FILMS) {
			films.appendNewFilms();
		}
	}

	private void guess() {

		/*
		 * Choose classifier
		 */
		ClassifierType classifierType = ClassifierType.NAIVE_BAYES;

		/*
		 * Set classifier and load model
		 */
		Learning classifier = new Learning();
		classifier.setClassifier(classifierType);
		classifier.loadModel();

		/*
		 * While there is question and user does't stop program - ask question
		 */
		while (questions.existsQuestion() && !user.stopProgram()) {
			/*
			 * Ask question
			 */
			Question currentQuestion = new Question();
			currentQuestion = questions.getNextQuestion();
			user.printQuestion(currentQuestion);

			/*
			 * Answer question
			 */
			AnswerOnQuestion answerOnQuestion = user.getAnswerOnQuestion();
			classifier.addFeature(currentQuestion, answerOnQuestion);

			/*
			 * Classification
			 */
			Film currentFilm = new Film();
			currentFilm = classifier.classify();

			/*
			 * Show the result
			 */
			user.printFilm(currentFilm);
		}

		/*
		 * If user says right answer, train classifier
		 */
		if (user.giveTrueAnswer()) {
			Film trueFilm = new Film();
			trueFilm = user.trueFilm();
			classifier.addAnswer(trueFilm);
			classifier.train();
			classifier.saveModel();
		}
	}

	private void train() {
		/*
		 * TODO train mode:
		 */
	}

	/**
	 * 
	 * @return Program mode
	 */
	private Mode modeSelection() {
		return user.getMode();
	}

}
