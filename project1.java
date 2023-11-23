package projects;

import java.util.*;
import javax.swing.*; // Needed for Swing classes
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;//needed for button click action
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class project1 extends JFrame { // extend to make it easier and not use window

	private JFrame p = new JFrame(); // jframe for the getting name and randnum

	private JFrame panel = new JFrame(); // jframe for the tic tac toe

	private JFrame newP = new JFrame();

	private JPanel main; // To reference a the whole panel

	private JPanel players; // gets the players names from P panel

	private JPanel mainScorePanel; // keeps track of score and names

	private JLabel wScore1; // the displayed score on panel for player 1 wins

	private JLabel lScore1; // the displayed score on panel for player 1 loses

	private JLabel wScore2; // the displayed score on panel for player 2 wins

	private JLabel lScore2; // the displayed score on panel for player 2 loses

	private JPanel menuPanel; // To reference the 3 button on the bottom of main panel

	private JButton[] button; // the 9 tic tac toe buttons

	private JButton newGame; // new game button which restarts game

	private JButton reset; // reset scores and tic tac board

	private JButton exit; // exits game

	private JPanel ticPanel; // panel for the tic tac toe

	private JTextField textField1; // the non editiable text field in the game p1

	private JTextField textField2; // the non editiable text field in the game p2

	private JLabel playerLb1; // player1 label when getting names

	private JLabel playerLb2; // player2 label when getting names

	private JTextField fNameTextField; // name entered into the system for p1

	private JTextField lNameTextField; // name entered into the system for p2

	private JButton done; // buttton used to notify that names are set and to begin

	private String player1, player2; // the 2 player names

	private String starter; // says who starts first

	private int turn = 0; // keeps track of turn to turn so it switches

	private int wCount = 0; // total count of turns taken by both players

	private String winner = " "; // prints out who wins the game

	private int w1 = 0; // p1 win score

	private int w2 = 0; // p2 win score

	private int l1 = 0; // p1 lose score

	private int l2 = 0; // p2 lose score

	private int WINDOW_WIDTH = 400; // Window width

	private int WINDOW_HEIGHT = 400; // Window height

	public project1() {// Constructor

// Set the title.

		p.setTitle("players names");

// Set the size of the window.

		p.setSize(300, 200);

// Specify what happens when the close button is clicked.

		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		playerRand(); // gets rand number and see who starts first

		setPlayers(); // gets players names and inputs them into main panel

		p.add(players); // p panel is set up

		p.setVisible(true); // Display the window.

//sets the name

		panel.setTitle("tic-tac-toe");

// Set the size of the window.

		panel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

// Specify what happens when the close button is clicked.

		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void setPlayers() {

		players = new JPanel(); // new panel names players

		playerLb1 = new JLabel("Enter player1 name:");// the labels and text field to type in names

		fNameTextField = new JTextField(20);

		playerLb2 = new JLabel("Enter player2 name:");

		lNameTextField = new JTextField(20);

		done = new JButton("Start Game");// when click it will start game

		done.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (fNameTextField.getText() != "" && lNameTextField.getText() != "") { // if text is not empty then
																						// start game

					player1 = fNameTextField.getText(); // get p1 name
					player2 = lNameTextField.getText(); // get p2 name
					JOptionPane.showMessageDialog(null, playerRand()); // show who starts
					buildScorePanel(); // builds the panel
					panel.add(main); // adds the created panel
					p.dispose();// dispose of getting names panel
					panel.setVisible(true);// makes tic tac panel visiable

				}

			}

		});

		players.add(playerLb1); // adds the button, label, and textfields to Player panel

		players.add(fNameTextField);

		players.add(playerLb2);

		players.add(lNameTextField);

		players.add(done);

	}

	public String playerRand() {

		Random randNum = new Random(); // gets random number

		Set<Integer> set = new LinkedHashSet<Integer>();

		while (set.size() < 2) {

			set.add(randNum.nextInt(2));

		}

		if (set.iterator().next() == 1) { // if number 1 one them player 2 starts

			turn = 1;

			starter = "player2 starts first!!!";

		} else if (set.iterator().next() == 0) {// if number 0 one them player 1 starts

			turn = 0;

			starter = "player1 starts first!!";

		}

		return starter; // return who starts

	}

	private class Listener implements ActionListener { // used to change the button from "" to x's and o's

		public void actionPerformed(ActionEvent e) {

			JButton buttonClicked = (JButton) e.getSource(); // when button is cli

			if (buttonClicked.getText() != "X" && buttonClicked.getText() != "O") { // make sures that once clicked cant
																					// be changed

				if (turn == 0) {

					buttonClicked.setText("X"); // put x after button is clicked

					turn = 1;

					wCount++;

				} else if (turn == 1) {

					buttonClicked.setText("O"); // put 0 after button is clicked

					turn = 0;

					wCount++;

				}

				checkWin(); // check if anyone wins

			}

		}

	}

	private void buildScorePanel() {

		main = new JPanel(); // new panel names main

		main.setLayout(new BorderLayout()); // borderlayout for main function

		mainScorePanel = new JPanel(); // main score panel consists of both p1 and p2 names and scores

		JPanel scorePl = new JPanel(); // p1 score panel

		JPanel scoreP2 = new JPanel(); // p2 score panel

		scorePl.setLayout(new GridLayout(3, 2));

		scoreP2.setLayout(new GridLayout(3, 2));

		mainScorePanel.setLayout(new GridLayout(1, 2)); // made so it add the 2 players individual panels

		ticPanel = new JPanel(); // setup of buttons

		ticPanel.setLayout(new GridLayout(3, 3)); // sets a grid of 9

		menuPanel = new JPanel(); // the three button used on the menu on the bottom

		menuPanel.setLayout(new GridLayout(1, 3));

		JLabel name2 = new JLabel(); // To reference a last name

		textField2 = new JTextField(); // To reference a last name text field

		textField2.setEditable(false);// non editiable

		textField2.setText(player2); // sets the name

		name2.setText("Player 2"); // sets the scores and texts

		JLabel win2 = new JLabel("win");

		wScore2 = new JLabel("0");

		JLabel lose2 = new JLabel("lose");

		lScore2 = new JLabel("0");

		scoreP2.add(name2);// adds everthing to the player 2 panel

		scoreP2.add(textField2);

		scoreP2.add(win2);

		scoreP2.add(wScore2);

		scoreP2.add(lose2);

		scoreP2.add(lScore2);

		JLabel name1 = new JLabel(); // To reference a first name

		name1.setText("Player 1");

		textField1 = new JTextField(); // To reference a first name text field

		textField1.setEditable(false);// non editable

		textField1.setText(player1); // set the name and the texts

		JLabel win1 = new JLabel("win");

		wScore1 = new JLabel("0");

		JLabel lose1 = new JLabel("lose");

		lScore1 = new JLabel("0");

		scorePl.add(name1); // adds everything into the player 1 panel

		scorePl.add(textField1);

		scorePl.add(win1);

		scorePl.add(wScore1);

		scorePl.add(lose1);

		scorePl.add(lScore1);

		mainScorePanel.add(scorePl);// combines the 2 panels into one main score panel

		mainScorePanel.add(scoreP2);
// Create 9 buttons.

		button = new JButton[9]; // creates 9 buttons

		for (int i = 0; i < 9; i++) {

			button[i] = new JButton("");// every button is left empty until clicked

			button[i].addActionListener(new Listener());

			ticPanel.add(button[i]);// adds every button to panel

		}

		JButton exit = new JButton("exit"); // bottom button exit to exit the game

		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}

		});

		JButton newGame = new JButton("newGame"); // new game reset where it gets new names and reset everthing

		newGame.addActionListener(new newGameReset());

		JButton reset = new JButton("reset"); // reset scores and tic tac panel

		reset.addActionListener(new restarter());

		menuPanel.add(newGame);// adds eveything to menu panel

		menuPanel.add(reset);

		menuPanel.add(exit);

		main.add(mainScorePanel, BorderLayout.NORTH); // organize them as seen on project 1 instructions

		main.add(ticPanel, BorderLayout.CENTER);

		main.add(menuPanel, BorderLayout.SOUTH);

	}

	private class newGameReset implements ActionListener { // used to reset names, scores, and tic tac panels

		public void actionPerformed(ActionEvent e) {

			w1 = 0;

			w2 = 0;

			l1 = 0;

			l2 = 0;

			wScore2.setText(Integer.toString(w2));// reset scores

			lScore2.setText(Integer.toString(l2));

			wScore1.setText(Integer.toString(w1));

			lScore1.setText(Integer.toString(l1));

			for (int i = 0; i < 9; i++) {

				button[i].setText("");// every button is cleared empty until clicked

			}

			JPanel newPlayers = new JPanel(); // new panel names newPlayers

			playerLb1 = new JLabel("Enter player1 name:");

			fNameTextField = new JTextField(20);

			playerLb2 = new JLabel("Enter player2 name:");

			lNameTextField = new JTextField(20);

			done = new JButton("Start Game");

			done.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if (fNameTextField.getText() != "" && lNameTextField.getText() != "") { // gets new names when new
																							// game button is clicked
																							// and updates them

						player1 = fNameTextField.getText();

						player2 = lNameTextField.getText();

						newP.dispose();

						textField2.setText(player2);

						textField1.setText(player1);

					}

				}
			});

			newPlayers.add(playerLb1); // adds everything to new game panel

			newPlayers.add(fNameTextField);

			newPlayers.add(playerLb2);

			newPlayers.add(lNameTextField);

			newPlayers.add(done);

			newP.setTitle("players names"); // set the new Jframe to get the new names

// Set the size of the window.

			newP.setSize(300, 200);

// Specify what happens when the close button is clicked.

			newP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			newP.add(newPlayers); // adds the set up to the new jframe

			newP.setVisible(true);

		}

	}

	private class restarter implements ActionListener { // reset the scores and tic tac panel

		public void actionPerformed(ActionEvent e) {

			w1 = 0;

			w2 = 0;

			l1 = 0;

			l2 = 0;

			wScore2.setText(Integer.toString(w2));

			lScore2.setText(Integer.toString(l2));

			wScore1.setText(Integer.toString(w1));

			lScore1.setText(Integer.toString(l1));

			for (int i = 0; i < 9; i++) {

				button[i].setText("");// every button is cleared empty until clicked

			}

		}

	}

	public void checkWin() {

		if (wCount < 10) {

//checks every row column and dia ways to win and updates scores

			if (button[0].getText() == "X" && button[1].getText() == "X" && button[2].getText() == "X") {

				winner = "the winner is " + player1;

				wCount = 9;

				w1 += 1;

				l2 += 1;

			} else if (button[0].getText() == "O" && button[1].getText() == "O" && button[2].getText() == "O") {

				winner = "the winner is " + player2;

				wCount = 9;

				w2 += 1;

				l1 += 1;

			} else {

				winner = "";

			}

			if (button[3].getText() == "X" && button[4].getText() == "X" && button[5].getText() == "X") {

				winner = "the winner is " + player1;

				wCount = 9;

				w1 += 1;

				l2 += 1;

			} else if (button[3].getText() == "O" && button[4].getText() == "O" && button[5].getText() == "O") {

				winner = "the winner is " + player2;

				wCount = 9;

				w2 += 1;

				l1 += 1;

			} else {

				winner = "";

			}

			if (button[6].getText() == "X" && button[7].getText() == "X" && button[8].getText() == "X") {

				winner = "the winner is " + player1;

				wCount = 9;

				w1 += 1;

				l2 += 1;

			} else if (button[6].getText() == "O" && button[7].getText() == "O" && button[8].getText() == "O") {

				winner = "the winner is " + player2;

				wCount = 9;

				w2 += 1;

				l1 += 1;

			} else {

				winner = "";

			}

			if (button[0].getText() == "X" && button[3].getText() == "X" && button[6].getText() == "X") {

				winner = "the winner is " + player1;

				wCount = 9;

				w1 += 1;

				l2 += 1;

			} else if (button[0].getText() == "O" && button[3].getText() == "O" && button[6].getText() == "O") {

				winner = "the winner is " + player2;

				wCount = 9;

				w2 += 1;

				l1 += 1;

			} else {

				winner = "";

			}

			if (button[1].getText() == "X" && button[4].getText() == "X" && button[7].getText() == "X") {

				winner = "the winner is " + player1;

				wCount = 9;

				w1 += 1;

				l2 += 1;

			} else if (button[1].getText() == "O" && button[4].getText() == "O" && button[7].getText() == "O") {

				winner = "the winner is " + player2;

				wCount = 9;

				w2 += 1;

				l1 += 1;

			} else {

				winner = "";

			}

			if (button[2].getText() == "X" && button[5].getText() == "X" && button[8].getText() == "X") {

				winner = "the winner is " + player1;

				wCount = 9;

				w1 += 1;

				l2 += 1;

			} else if (button[2].getText() == "O" && button[5].getText() == "O" && button[8].getText() == "O") {

				winner = "the winner is " + player2;

				wCount = 9;

				w2 += 1;

				l1 += 1;

			} else {

				winner = "";

			}

			if (button[0].getText() == "X" && button[4].getText() == "X" && button[8].getText() == "X") {

				winner = "the winner is " + player1;

				wCount = 9;

				w1 += 1;

				l2 += 1;

			} else if (button[0].getText() == "O" && button[4].getText() == "O" && button[8].getText() == "O") {

				winner = "the winner is " + player2;

				wCount = 9;

				w2 += 1;

				l1 += 1;

			} else {

				winner = "";

			}

			if (button[2].getText() == "X" && button[4].getText() == "X" && button[6].getText() == "X") {

				winner = "the winner is " + player1;

				wCount = 9;

				w1 += 1;

				l2 += 1;

			} else if (button[2].getText() == "O" && button[4].getText() == "O" && button[6].getText() == "O") {

				winner = "the winner is " + player2;

				wCount = 9;

				w2 += 1;

				l1 += 1;

			} else {

				winner = "";

			}

		}

//iff the count seen 9 click and tic panel is filled or someone won it would update screen score and clear everything 

		if (wCount == 9) {

			wScore2.setText(Integer.toString(w2));

			lScore2.setText(Integer.toString(l2));

			wScore1.setText(Integer.toString(w1));

			lScore1.setText(Integer.toString(l1));

			for (int i = 0; i < 9; i++) {

				button[i].setText("");// every button is left empty until clicked

			}

			wCount = 0;

			System.out.print(winner); // prints the winner

		}

	}

	public static void main(String[] args) {

		new project1(); // call the constructer

	}
}