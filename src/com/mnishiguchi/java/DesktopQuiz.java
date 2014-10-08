package com.mnishiguchi.java;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DesktopQuiz extends JFrame
	implements ActionListener, ChangeListener
{
	/* CONSTANTS */
	public static final int WIDTH = 480;
	public static final int HEIGHT = 200;
	public static final String DELIMITER = "\t";
	public static final String FILEPATH = "C:quizzes.txt";

	/* INSTANCE VARIABLES */
	JToggleButton mAnswerToggle;
	JButton mSuffleButton;
	JTextArea mTextArea;
	Random mRandom = new Random();
	int mIndex;

	public static void main(String[] args)
	{
		// set fonts
		UIManager.put("ToggleButton.font", new Font("Arial",Font.PLAIN,13) );
		UIManager.put("Button.font", new Font("Arial",Font.PLAIN,13) );
		UIManager.put("TextArea.font", new Font("Calibri",Font.PLAIN,15));
		
		new DesktopQuiz();    // start the application
	}

	/** CONSTRUCTOR */
	public DesktopQuiz()
	{
		// configuration of the frame
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("DesktopQuiz");
		this.setLocationRelativeTo(null);  // put it at the center of the screen

		JPanel mainPanel = new JPanel(new BorderLayout() );

		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();
		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		JMenuItem menuItem1 = new JMenuItem("How to provide quizzes?");
		menuItem1.addActionListener(this);
		menuItem1.setActionCommand("How to provide quizzes?");
		menuHelp.add(menuItem1);
		this.setJMenuBar(menuBar);
		
		mTextArea = new JTextArea(5, 20);
		mTextArea.setEditable(false);
		mTextArea.setLineWrap(true);
		mTextArea.setBorder(BorderFactory.createEmptyBorder(9,9,0,9) );
		JScrollPane scrollPane = new JScrollPane(mTextArea ); 
		
		mAnswerToggle = new JToggleButton("Show Answer", false);
		mAnswerToggle.addChangeListener(this);
		mAnswerToggle.setPreferredSize(new Dimension(130,25) );
		
		mSuffleButton = new JButton("Next Quiz >>>");
		mSuffleButton.addActionListener(this);
		mSuffleButton.setActionCommand("shuffle");
		mSuffleButton.setPreferredSize(new Dimension(125,25) );
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(Box.createHorizontalGlue() );
		buttonBox.add(mAnswerToggle);
		buttonBox.add(Box.createHorizontalStrut(30) );
		buttonBox.add(mSuffleButton);
		buttonBox.setBorder(BorderFactory.createEmptyBorder(9,9,9,9) );

		mainPanel.add(scrollPane, BorderLayout.NORTH);
		mainPanel.add(buttonBox, BorderLayout.SOUTH);

		this.add(mainPanel);
		this.setVisible(true);  // show this frame
		shuffleQuiz();  // Show a quiz.
	}
	
	/**
	 * Random-pick a quiz and show it on the TextArea.
	 */
	public void shuffleQuiz()
	{
		if (Quiz.size == 0)  // Ensure that quizzes exist.
		{
			mTextArea.setText("No quiz was found. \n");
			String msg = "Please provide question-answer pairs (TAB-delimited) to "
					+ "each line of the file " + FILEPATH + ".\n"
					+ "If you want to insert a new line, use the <br/> tag.";
			JOptionPane.showMessageDialog(DesktopQuiz.this, msg, "Message",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Random-pick a quiz.
		int index;
		while (true)
		{
			index = mRandom.nextInt(Quiz.size);
			// Ensure that the new index is not the same as the previous one.
			if (index != mIndex)
			{
				mIndex = index;
				break;
			}
		}
		mTextArea.setText("---- QUESTION #" + (mIndex + 1) + " ----\n");
		mTextArea.append(Quiz.getQuizzes().get(mIndex).getQuestion() );
		mAnswerToggle.setSelected(false);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ("How to provide quizzes?".equals(e.getActionCommand() ) )
		{
			String msg = "Please provide question-answer pairs (TAB-delimited) to "
					+ "each line of the file " + FILEPATH + ".\n"
					+ "If you want to insert a new line, use the <br/> tag.";
			JOptionPane.showMessageDialog(DesktopQuiz.this, msg, "Message",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else if ("shuffle".equals(e.getActionCommand() ) )
		{
			if (Quiz.size == 0) return; // Do nothing if there is no quiz.
			shuffleQuiz();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		if (Quiz.size == 0) return; // Do nothing if there is no quiz.
		
		AbstractButton abstractButton = (AbstractButton) e.getSource();
		ButtonModel buttonModel = abstractButton.getModel();
		//boolean armed = buttonModel.isArmed();
		//boolean pressed = buttonModel.isPressed();
		boolean selected = buttonModel.isSelected();
		
		if (selected)
		{
			mTextArea.setText("---- ANSWER #" + (mIndex + 1) + " ----\n");
			mTextArea.append(Quiz.getQuizzes().get(mIndex).getAnswer() );
			mAnswerToggle.setText("Review Question");
		}
		else if (!selected)
		{
			mTextArea.setText("---- QUESTION #" + (mIndex + 1) + " ----\n");
			mTextArea.append(Quiz.getQuizzes().get(mIndex).getQuestion() );
			mAnswerToggle.setText("Show Answer");
		}
	}

}
