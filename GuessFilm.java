//import javax.jws.WebParam.Mode;

/**
 * 
 * Core class
 *
 */
public class GuessFilm {

	private final int TRAINING_MODE = 0;
	private final int GUESS_MODE = 1;
	private final int APPEND_NEW_QUESTIONS = 2;
	private final int APPEND_NEW_FILMS = 3;
	
	private Films films = new Films();
	private Questions questions = new Questions();
	
	/**
	 * Program start here
	 */
	public void main(String[] args) {
		GuessFilm guessFilm = new GuessFilm();
		
		/*
		 *  Choose mode
		 */
		if (guessFilm.modeSelection() == TRAINING_MODE) {
			guessFilm.train();
		} else if (guessFilm.modeSelection() == GUESS_MODE) {
			guessFilm.guess();
		} else if (guessFilm.modeSelection() == APPEND_NEW_QUESTIONS){
			questions.appendNewQuestions();
		} else if (guessFilm.modeSelection() == APPEND_NEW_FILMS) {
			films.appendNewFilms();
		}
	}

	private void guess() {
		/*
		 * TODO main mode:
		 * Choose classifier
		 * Load model (if exists)
		 * While user doesn't stop program and there are questions
		 *** Choose new question
		 *** Ask question
		 *** Get instance (answer to question)
		 *** Give instance to classifier
		 *** Display answer
		 * If user give right answer(film),
		 *** save instances and right answer into database and train
		 */
		
	}

	private void train() {
		/*
		 * TODO train mode:
		 * Get instances and answers from user
		 * Choose classifier
		 * Give instances and answers to classifier
		 * Save instances and answers into database
		 * Save model
		 */
		
	}
	
	/**
	 * 
	 * @return Program mode
	 */
	private int modeSelection() {
		// TODO Get mode from User
		return 0;
	}

}
