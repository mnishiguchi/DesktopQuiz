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
	public static final String DELIMITER = "\t";
	public static final String FILENAME = "C:tmp.txt";
	public static final int WIDTH = 300;
	public static final int HEIGHT = 170;

	/* INSTANCE VARIABLES */
	JToggleButton mAnswerToggle;
	JButton mSuffleButton;
	JTextArea mTextArea;
	Random mRandom = new Random();
	int mIndex;

	public static void main(String[] args)
	{
		// set fonts
		UIManager.put("ToggleButton.font", new Font("Calibri",Font.PLAIN,14) );
		UIManager.put("Button.font", new Font("Calibri",Font.PLAIN,14) );
		UIManager.put("TextArea.font", new Font("Arial",Font.PLAIN,14));
		
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

		mTextArea = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(mTextArea); 
		mTextArea.setEditable(false);
		
		mAnswerToggle = new JToggleButton("Show Answer", false);
		mAnswerToggle.addChangeListener(this);
		mAnswerToggle.setPreferredSize(new Dimension(130,25) );
		
		mSuffleButton = new JButton("Next Quiz >>>");
		mSuffleButton.addActionListener(this);
		mSuffleButton.setActionCommand("shuffle");
		mSuffleButton.setPreferredSize(new Dimension(125,25) );
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(mAnswerToggle);
		buttonBox.add(Box.createHorizontalGlue() );
		buttonBox.add(mSuffleButton);
		buttonBox.setBorder(BorderFactory.createEmptyBorder(9,9,14,9) );

		mainPanel.add(scrollPane, BorderLayout.NORTH);
		mainPanel.add(buttonBox, BorderLayout.SOUTH);

		this.add(mainPanel);
		this.pack();
		this.setVisible(true);    // show this frame
		shuffleQuiz();
	}
	
	public void shuffleQuiz()
	{
		// Random-pick a quiz.
		mIndex = mRandom.nextInt(Quiz.size);
		mTextArea.setText(Quiz.getQuizzes().get(mIndex).getQuestion() );
		mAnswerToggle.setSelected(false);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ("shuffle".equals(e.getActionCommand() ) )
		{
			shuffleQuiz();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		AbstractButton abstractButton = (AbstractButton) e.getSource();
		ButtonModel buttonModel = abstractButton.getModel();
		//boolean armed = buttonModel.isArmed();
		//boolean pressed = buttonModel.isPressed();
		boolean selected = buttonModel.isSelected();
		
		if (selected)
		{
			mTextArea.setText(Quiz.getQuizzes().get(mIndex).getAnswer() );
			mAnswerToggle.setText("Review Question");
		}
		if (!selected)
		{
			mTextArea.setText(Quiz.getQuizzes().get(mIndex).getQuestion() );
			mAnswerToggle.setText("Show Answer");
		}
	}

}
