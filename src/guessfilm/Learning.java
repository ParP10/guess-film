package guessFilm;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import guessFilm.model.Film;
import guessFilm.model.Question;

/**
 * 
 * Class for WEKA Library
 * 
 */

public class Learning {
	private Classifier cls;
	private ArrayList<Attribute> attributes;
	private Instances data;
	

	public enum ClassifierType {
		NAIVE_BAYES;
		// TODO: add classifier
	}

	
	public void createModel(ClassifierType classifierType) {
		switch (classifierType) {
		case NAIVE_BAYES:
			cls = new NaiveBayes();
			break;
		default:
			break;
		}
		
	}

	public void loadModel(ClassifierType classifierType) {
		switch (classifierType) {
		case NAIVE_BAYES:
			// Deserializing a classifier
			try {
				cls = (Classifier) SerializationHelper.read("naive_bayes.model");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
	
	public void createAttributes(int numQuestions, int numFilms) {
		attributes = new ArrayList<Attribute>();
		
		ArrayList<String> questionAnswers = new ArrayList<String>();
		questionAnswers.add("NO");
		questionAnswers.add("YES");
		questionAnswers.add("DO NOT KNOW");
		
		for (int i = 0; i < numQuestions; i++) {
			attributes.add(new Attribute(Integer.toString(i), questionAnswers));
		}
		
		ArrayList<String> filmAnswers = new ArrayList<String>();
		for (int i = 0; i <= numFilms; i++) {
			filmAnswers.add(Integer.toString(i));
		}
		
		attributes.add(new Attribute("Result", filmAnswers));
	}
	
	public void loadData() throws Exception {
		data = new Instances("rel", attributes, 0);
		
		Scanner scanner = new Scanner(new File("trainset.txt"), "UTF-8");
		
		int numInstances = scanner.nextInt();
		int oldNumAttributes = scanner.nextInt();
		
		for (int i = 0; i < numInstances; i++) {
			Instance inst = new DenseInstance(data.numAttributes());
			inst.setDataset(data);
			
			for (int j = 0; j < oldNumAttributes; j++) {
				/*
				 * NO = 0
				 * YES = 1
				 * DO NOT KNOW = 2
				 * NULL = 3
				 */
				int value = scanner.nextInt();
				inst.setValue(j, value);
				/*if (j == oldNumAttributes - 1) {
					inst.setValue(j, value);
				} else {
					switch(value) {
					case 0:
						inst.setValue(j, 0);
						break;
					case 1:
						inst.setValue(j, 1);
						break;
					case 2:
						inst.setValue(j, 2);
						break;
					default:
						break;
					}
				}*/
			}
			
			data.add(inst);
		}
		
		data.setClassIndex(data.numAttributes() - 1);
	}
	
	
	public void createFeatureVector() {
		
		// Create instances
		data = new Instances("Dataset", attributes, 0);
		data.setClassIndex(data.numAttributes() - 1);
		
		Instance inst = new DenseInstance(data.numAttributes());
		data.add(inst);
		
	}

	/**
	 * Add feature in the vector of features
	 * 
	 * @param currentQuestion
	 * @param answerOnQuestion
	 */
	public void addFeature(Question currentQuestion,
			GuessFilm.AnswerOnQuestion answerOnQuestion) {
		
		switch(answerOnQuestion) {
		case NO:
			data.get(0).setValue(data.attribute(Integer.toString(currentQuestion.getIndex() - 1)), 0);
			break;
		case YES:
			data.get(0).setValue(data.attribute(Integer.toString(currentQuestion.getIndex() - 1)), 1);
			break;
		case DO_NOT_KNOW:
			data.get(0).setValue(data.attribute(Integer.toString(currentQuestion.getIndex() - 1)), 2);
			break;
		default:
			break;	
		}

	}
	
	/**
	 * 
	 * @return Film ID in String representation
	 * @throws Exception
	 */

	public String classify() throws Exception {
		
		double res = cls.classifyInstance(data.instance(0));
		data.instance(0).setClassValue(res);
		return data.classAttribute().value((int)res);
	}


	public void train() throws Exception {
		cls.buildClassifier(data);
	}

	
	public void saveModel() throws Exception {
		SerializationHelper.write("naive_bayes.model", cls);
	}
	
	
	/**
	 * Add film's name in the vector of features (for training mode)
	 * 
	 * @param trueFilm
	 */
	public void addAnswer(Film trueFilm) {
		// TODO

	}

}
