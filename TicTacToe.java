package tictactoe;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
	
	JFrame frame = new JFrame();
	
	JPanel textPanel = new JPanel();
	JLabel textLabel = new JLabel();
	
	JPanel gamePanel = new JPanel();
	JButton buttons[][] = new JButton[3][3];
	
	JPanel restartPanel = new JPanel();
	JButton restartButton = new JButton();
	
	String playerX = "X";
	String playerO = "O";
	String currentPlayer = playerX;
	
	
	boolean gameOver = false;
	int counter = 0;  //counts how many moves are made
	
	
	public TicTacToe() {
		
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setSize(600, 650);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tic-Tac-Toe");
		
		textLabel.setBackground(Color.black);
		textLabel.setForeground(Color.white);
		textLabel.setText("Tic-Tac-Toe");
		textLabel.setFont(new Font("Arial", Font.BOLD, 50));
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setOpaque(true);
		textPanel.setLayout(new BorderLayout());
		textPanel.add(textLabel);
		frame.add(textPanel, BorderLayout.NORTH);
		
		restartButton.setBackground(Color.black);
		restartButton.setForeground(Color.white);
		restartButton.setText("RESTART");
		restartButton.setFont(new Font("Arial", Font.BOLD, 50));
		restartButton.setHorizontalAlignment(JButton.CENTER);
		restartButton.setOpaque(true);
		restartPanel.setLayout(new BorderLayout());
		restartPanel.add(restartButton);
		frame.add(restartPanel, BorderLayout.SOUTH);
		
		restartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int r = 0; r < 3; r++) {
					for (int c = 0; c < 3; c++) {
						JButton tile = buttons[r][c];
						
						tile.setBackground(Color.darkGray);
						tile.setForeground(Color.white);
						tile.setFont(new Font("Arial", Font.BOLD, 120));
						tile.setFocusable(false);
						tile.setText("");
					}
				}
				
				gameOver = false;
				counter = 0;
				textLabel.setText("Tic-Tac-Toe");
				
			}
			
		});
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton b = new JButton();
				b.setBackground(Color.darkGray);
				b.setForeground(Color.white);
				b.setFont(new Font("Arial", Font.BOLD, 70));
				b.setText("");
				b.setFocusable(false);
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton b = (JButton) e.getSource();
						if (b.getText().equals("") && !gameOver) {
							String text = currentPlayer == playerX ? playerX : playerO;
							b.setText(text);
							counter++;
							winnerCheck();
							if (!gameOver) {
								currentPlayer = currentPlayer == playerX ? playerO : playerX;
								textLabel.setText(currentPlayer + "'s turn");
							}
						}
						
					}
					
				});
				buttons[i][j] = b;
				gamePanel.add(b);
				
			}
		}
		
		gamePanel.setLayout(new GridLayout(3,3));
		frame.add(gamePanel, BorderLayout.CENTER);	
		
	}
	
	public void winnerCheck() {
		
		//horizontal
				for (int r = 0; r < 3; r++) {
					int counter = 0;
					if (buttons[r][0].getText() == playerX) {
						for (int c = 0; c < 3; c++) {
							if (buttons[r][c].getText() != playerX) break;
							counter++;
						}
					} else if (buttons[r][0].getText() == playerO) {
						for (int c = 0; c < 3; c++) {
							if (buttons[r][c].getText() != playerO) break;
							counter++;
						}
					}
					if (counter == 3) {
						gameOver = true;
						for (int i = 0; i < 3; i++) endGame(buttons[r][i]);
						return;
					}
				}
				
				//vertical
				for (int c = 0; c < 3; c++) {
					int counter = 0;
					if (buttons[0][c].getText() == playerX) {
						for (int r = 0; r < 3; r++) {
							if (buttons[r][c].getText() != playerX) break;
							counter++;
						}
					} else if (buttons[0][c].getText() == playerO) {
						for (int r = 0; r < 3; r++) {
							if (buttons[r][c].getText() != playerO) break;
							counter++;
						}
					}
					if (counter == 3) {
						gameOver = true;
						for (int i = 0; i < 3; i++) endGame(buttons[i][c]);
						return;
					}
				}
				
				//main diagonal
				if (buttons[0][0].getText() == playerX) {
					int counter = 0;
					for (int i = 0; i < 3; i++) {
						if (buttons[i][i].getText() != playerX) break;
						counter++;
					}
					if (counter == 3) {
						gameOver = true;
						for (int j = 0; j < 3; j++) endGame(buttons[j][j]);
						return;
					}
				} else if (buttons[0][0].getText() == playerO) {
					int counter = 0;
					for (int i = 0; i < 3; i++) {
						if (buttons[i][i].getText() != playerO) break;
						counter++;
					}
					if (counter == 3) {
						gameOver = true;
						for (int j = 0; j < 3; j++) endGame(buttons[j][j]);
						return;
					}
				}
				
				//side diagonal
				if (buttons[0][2].getText() == playerX) {
					int counter = 0;
					for (int i = 0; i < 3; i++) {
						if (buttons[i][2 - i].getText() != playerX) break;
						counter++;
					}
					if (counter == 3) {
						gameOver = true;
						for (int j = 0; j < 3; j++) endGame(buttons[j][2-j]);
						return;
					}
				} else if (buttons[0][2].getText() == playerO) {
					int counter = 0;
					for (int i = 0; i < 3; i++) {
						if (buttons[i][2 - i].getText() != playerO) break;
						counter++;
					}
					if (counter == 3) {
						gameOver = true;
						for (int j = 0; j < 3; j++) endGame(buttons[j][2-j]);
						return;
					}
				}
				
				if (counter == 9) {
					gameOver = true;
					for (int r = 0; r < 3; r++) {
						for (int c = 0; c < 3; c++) {
							buttons[r][c].setForeground(Color.yellow);
							buttons[r][c].setBackground(Color.gray);
						}
					}
					textLabel.setText("It's a tie!");
					return;
				}
		
	}
	
	public void endGame(JButton b) {
		gameOver = true;
		b.setForeground(Color.green);
		b.setBackground(Color.gray);
		textLabel.setText(currentPlayer + " is the winner!");
		return;
	}



	public static void main(String[] args) {
		TicTacToe ticTacToe = new TicTacToe();
	}

}
