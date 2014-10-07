package com.mnishiguchi.java;

import java.util.ArrayList;

public class Quiz
{
	// CONSTANTS
	public static final String DELIMITER = "\t";
	public static final String FILEPATH = "C:tmp.txt";

	// CLASS VARIABLES
	public static ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
	static  // Initialize the array.
	{
		// get data from the file.
		ArrayList<String> lines = ReadFile.getDataFromFile(FILEPATH); // to store raw data
		String[] data;  // to store processed data

		if (!lines.isEmpty() )  // ensure that some customers exist
		{
			// create a Quiz object from each line
			for (String temp: lines)
			{
				data = temp.split(DELIMITER); 
				if (data.length != 2)    // ensure that data has 2 items
				{
					System.out.println("data.length should be 2");
					continue;
				}
				Quiz.quizzes.add( new Quiz(data[0], data[1]) ) ;  
			}
		}
	}
	public static ArrayList<String> questions = new ArrayList<String>();
	public static int size = quizzes.size();

	// INSTANCE VARIABLE
	private String mQuestion;
	private String mAnswer;

	/** CONSTRUCTOR */
	public Quiz(String question, String answer)
	{
		this.mQuestion = question;
		this.mAnswer = answer;
	}

	public String getQuestion()
	{
	return mQuestion;
	}

	public String getAnswer()
	{
		return mAnswer;
	}
	
	/**
	* @return An ArrayList of Quiz objects.
	*/
	public static ArrayList<Quiz> getQuizzes()
	{
		return Quiz.quizzes;
	}
	
}
