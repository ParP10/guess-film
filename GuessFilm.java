/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guessfilm;

import java.util.ArrayList;
import java.io.Serializable.*;
import java.io.*;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream.*;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Attribute;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;

/**
 *
 * @author Pavel Parkhomenko
 */
public class GuessFilm { // очновной класс

    /**
     * @param args the command line arguments
     */
    
    static boolean flag = true; //флаг: нужно ли продолжать задавать вопросы
    static boolean train_mode = true; // режим тренировки
    
    public static void main(String[] args) {
        
        if (train_mode) {
            /*
                Тренировочный режим. Необходим на первое время. После того, как будет реализована
                достаточная функциональность, его можно будет убрать, так как обучать можно
                будет в основном режиме.
            */
            Attribute attribute[] = new Attribute[Question.amount_question];
            ArrayList <Attribute> all_attributes = new ArrayList<Attribute>(Question.amount_question + 1);
            /*
                Заполнение атрибутов.
                Думаю, это все можно сделать лучше. Пока слабо разобрался в библиотеке.
            */
            for (int i = 0; i < Question.amount_question; i++) {
                attribute[i] = new Attribute("q" + i);
                all_attributes.add(i, attribute[i]); 
            }
            ArrayList<String> class_val = new ArrayList<String>(Film.amount_film);
            for (int i = 0; i < Film.amount_film; i++){
                class_val.add(i, Integer.toString(i));
            }
            Attribute class_attribute = new Attribute("theClass", class_val);
            all_attributes.add(Question.amount_question, class_attribute);
            
            /*
                Создаем тренировочный корпус
            */
            Instances training_set = new Instances("Rel", all_attributes, Film.amount_film);
            training_set.setClassIndex(Film.amount_film);
            for (int i = 0; i < Film.amount_film; i++) {
                Instance example = new DenseInstance(Question.amount_question + 1);
                for (int j = 0; j < Question.amount_question; j++) {
                    int x;
                    // Задаем вопрос, получаем ответ на него (пусть, пока x == 0 - "не знаю"
                    // x == 1 - "да", x == 2 - "нет"
                    // В классификатор не будем кидать ответы "не знаю"
                    if (x == 1 || x == 2) {
                        example.setValue((Attribute) all_attributes.get(j), x);
                    }
                }
                training_set.add(example);
            }
            /*
                Сохраненине модели. Не уверен, что хорошо написано.
            */
            Classifier cModel = (Classifier) (new NaiveBayes());
            cModel.buildClassifier(training_set);
            weka.core.SerializationHelper.write("cls.model", cModel);
            return;
        } 
        
        /*
            Основной режим работы.
        */
        /*
            Загружаем модель, создаем атрибуты.
        */
        Classifier cls = (Classifier) weka.core.SerializationHelper.read("cls.model");
        Instance inst = new DenseInstance(Question.amount_question + 1);
        FastVector all_attributes = new FastVector(Question.amount_question + 1);
        int counter = 0;
        while (Question.amount_given_question < Question.amount_question && flag) {
            /*
                Пока остались вопросы и пользователь не прекратил работу:
                    Задаем вопрос
                    Получаем ответ(0 - "не знаю", 1 - "да", 2 - "нет")
                    Перестраиваем вектор признаков
                    Кидаем вектор признаков в классификатор, получаем ответ
                    Выводим его на экран
            */
            Question next_question = Question.getNextQuestion();
            int ans = 0;
            // TODO: вывести текст вопроса на экран, считать ответ
            Attribute att = new Attribute("q" + next_question.question_number);
            all_attributes.addElement(att);
            if (ans != 0) {
                inst.setValue((Attribute) all_attributes.elementAt(counter), ans);
            }
            double [] res = cls.distributionForInstance(inst);
            ++counter;
        }
    }
    
}

class Question { // Вопросы
    static int amount_question = 0; // общее количество вопросов в базе данных
    static int amount_given_question = 0; // количество уже заданных вопросов
    static ArrayList <String> array_of_question_name; // текстовое представление вопросов
    int question_number; // номер данного вопроса
    String question_name; // текстовое представение данного вопроса
    Question (int next_question_number) {
        question_number = next_question_number;
        question_name = array_of_question_name.get(question_number);
    }
    void initializeArrayOfQuestionName() { //инициализация массива "static String array_of_question_name[]"
        // TODO: загрузить строчное представление вопросов (заполнить массив)
    }
    static public Question getNextQuestion() { // получение следующего вопроса
        int number_of_next_question = 0;
        
        // TODO: реализовать алгоритм нахождения номера следующего подходящего вопроса
        Question next_question = new Question(number_of_next_question);
        return next_question;
    }
    static public void addNewQestion() { // функция, пополняющая базу вопросов
        while () { // пока пользователь не прекращает ввод вопросов - сохранять в памяти
            // Возможно, стоит проверить, встречался ли этот вопрос ранее.
            // Но, думаю, если ее реализовывать в лоб, пользы немного будет
            String tmp_string = new String();
            // TODO: считать строку
            array_of_question_name.add(tmp_string);
        }
        amount_question = array_of_question_name.size();
    }
}

class Film { // Фильмы
    static int amount_film = 0; // общее количество фильмов
    static ArrayList <String> array_of_film_name; // массив названий фильмов
    int film_number; // номер фильма
    String film_name; // название фильма
    Film (int number) {
        film_number = number;
        film_name = array_of_film_name.get(number);
    }
    void initializeArrayOfFilmName() { //инициализация массива "static String array_of_film_name[]"
        // TODO: загрузить строчное представление фильмов (заполнить массив)
    }
    static public void addNewFilm() { // функция, добавляющая новый фильм в базу
        while () { // пока пользователь не прекращает ввод вопросов - сохранять в памяти
            // TODO: проверить, встречался ли этот фильм ранее. Если да, то не обрабатывать
            String tmp_string = new String();
            // TODO: считать строку
            array_of_film_name.add(tmp_string);
        }
        amount_film = array_of_film_name.size();
    }
    
}
