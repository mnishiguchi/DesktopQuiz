package com.mnishiguchi.java;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class DesktopQuiz extends JFrame implements ActionListener
{
	/* CONSTANTS */
	public static final String DELIMITER = "\t";
	public static final String FILENAME = "..\tmp.txt";
	public static final int WIDTH = 480;
	public static final int HEIGHT = 300;

	/* INSTANCE VARIABLES */
	JButton mButton1, mButton2;
	JTextField mTextField;

	public static void main(String[] args)
	{
		// set fonts
		UIManager.put("Button.font", new Font("Calibri",Font.PLAIN,16) );
		UIManager.put("TextField.font", new Font("Arial",Font.PLAIN,14));
		UIManager.put("Table.font", new Font("Arial",Font.PLAIN,14));
		UIManager.put("Label.font", new Font("Arial",Font.PLAIN,14));
	
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
		mTextField = new JTextField(200);
		mButton1 = new JButton("Show Answer");
		mButton1.addActionListener(this);
		mButton2 = new JButton("Next Quiz >>>");
		mButton2.addActionListener(this);
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(mButton1);
		buttonBox.add(Box.createGlue() );
		buttonBox.add(mButton2);

		mainPanel.add(mTextField, BorderLayout.NORTH);
		mainPanel.add(buttonBox, BorderLayout.SOUTH);

		this.add(mainPanel);
		this.setVisible(true);    // show this frame
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == mButton1)
		{
			mTextField.setText("mButton1 is clicked");
		}
		else if (e.getSource() == mButton2)
		{
			mTextField.setText("mButton2 is clicked");
		}
	}

}
