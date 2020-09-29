import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Matches {
	
	Game game;
	
	JFrame frame;
	JPanel panel;
	JLabel label;
	JButton but1, but2, butNew;
	JButton [] butChoise;
	int numOfButtonsChose = 10;
	JPanel buttonsPanel;
	JPanel buttonsPanelNew;
	JPanel buttonsPanelStart;
	JPanel buttonsPanelChose;
	//int numOfMatches = 0; 
	int maxMatches = 20;
	Image emptyField;
	Image matchField;
	Image gameIcon;
	int imageSizeX;
	int imageSizeY;
	
	Font font = new Font("Arial", Font.ITALIC, 38);
	
	ButtonListener buttonListener = new ButtonListener();
	
	

		
	public static void main(String [] args) {
			
		new Matches();
			
	}
		
	Matches() {
		
		game = new Game(maxMatches);
		initImages();
		initPanel();
		initLabel();
		initButtons();
		initFrame();
		//start();
		
			
	}
	
	private void initImages() {
		
		emptyField = new ImageIcon(getClass().
							getResource("img/drawable.png")).getImage();
		matchField = new ImageIcon(getClass().
				getResource("img/match.png")).getImage();
		gameIcon = new ImageIcon(getClass().
				getResource("img/iconMatches.png")).getImage();
		imageSizeX = emptyField.getWidth(null);
		imageSizeY = emptyField.getHeight(null);
		
	}
	
	private void initPanel() {
		
		panel = new JPanel() {
						public void paintComponent(Graphics g) {
							super.paintComponent(g);
							
							for(int i = 0; i < maxMatches; i++){
								if(i < game.getNumOfMatches()){ 
									g.drawImage(matchField, imageSizeX * i, 0, null);
								} else {
									g.drawImage(emptyField, imageSizeX * i, 0, null);
								}
							}
						
						}
					};
		panel.setPreferredSize(new Dimension(maxMatches * imageSizeX, imageSizeY));
	}
	
	private void initLabel() {
		
		label = new JLabel("Начнем новую игру?");
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
		
	}
	
	private void initButtons() {
		
		buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(maxMatches * imageSizeX, 100));
		buttonsPanel.setLayout(new GridLayout(1, 1));
		buttonsPanelNew = new JPanel();
		//buttonsPanelNew.setPreferredSize(new Dimension(maxMatches * imageSizeX, 100));
		buttonsPanelNew.setLayout(new GridLayout(1, 1));
		buttonsPanelStart = new JPanel();
		//buttonsPanelStart.setPreferredSize(new Dimension(maxMatches * imageSizeX, 100));
		buttonsPanelStart.setLayout(new GridLayout(1, 1));
		buttonsPanelChose = new JPanel();
		//buttonsPanelChose.setPreferredSize(new Dimension(maxMatches * imageSizeX, 100));
		buttonsPanelChose.setLayout(new GridLayout(1, 1));
		
		
		//здесь неплохо было бы воспользоваться перечислением, чтобы не повторять код
		but1 = new JButton("1");
		but1.setFont(font);
		but1.addActionListener(buttonListener);
		but2 = new JButton("2");
		but2.setFont(font);
		but2.addActionListener(buttonListener);
		butNew = new JButton("New Game");
		butNew.setFont(font);
		butNew.addActionListener(buttonListener);
		butChoise = new JButton [numOfButtonsChose];
		
		for(int i = 0; i < numOfButtonsChose; i++){
			butChoise[i] = new JButton("" + (i + 11));
			butChoise[i].setFont(font);
			butChoise[i].addActionListener(buttonListener);
			buttonsPanelStart.add(butChoise[i]);
		}
		
		buttonsPanelNew.add(butNew);
		buttonsPanelChose.add(but1);
		buttonsPanelChose.add(but2);
		buttonsPanel.add(buttonsPanelNew);
	}
	
	private void initFrame() {
		
		frame = new JFrame("Спички");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(BorderLayout.NORTH, panel);
		frame.add(BorderLayout.CENTER, label);
		frame.add(BorderLayout.SOUTH, buttonsPanel);
		frame.setResizable(false);           //Размеp фрейма невозможно изменить
		frame.pack();                        //Подгоняет размер фрейма, чтобы в него поместились все компоненты
		frame.setLocationRelativeTo(null);   //Помещает фрейм в центр экрана компьютера
		frame.setIconImage(gameIcon);
		frame.setVisible(true);
	}
	
	
	
	
	
	class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent ae) {
			
			String actionComand = ae.getActionCommand();
			
			if(actionComand.equals("1") || actionComand.equals("2")){
				
				int matchesDel = Integer.parseInt(actionComand);
				//game.removeMatches(matchesDel);
				String resultOfGame = game.getResult(matchesDel);
				label.setText("Компьютер удалил " + game.getCompDel() + " спички(-у). Сколько удалите Вы?");
				if(resultOfGame.equals("humanWin")) {
					label.setText("Вы выиграли!!! Начнем новую игру?");
					changePanel(buttonsPanelNew);
				} else if(resultOfGame.equals("compWin")) {
					label.setText("Компьютер удалил " + game.getCompDel() + " спички(-у) и выиграл((( Начнем новую игру?");
					changePanel(buttonsPanelNew);
				}
				panel.repaint();
				
			} else if(actionComand.equals("New Game")){
				
				label.setText("Со скольки спичек начнем?");
				changePanel(buttonsPanelStart);
				
			} else {
				
				game.setNumOfMatches(Integer.parseInt(actionComand));
				changePanel(buttonsPanelChose);
				label.setText("Удаляем одну или две спички?");
				
			}	
		}
		
		void changePanel(JPanel newPanel) {
			buttonsPanel.removeAll();
			buttonsPanel.add(newPanel);
			frame.add(BorderLayout.SOUTH, buttonsPanel);
			frame.repaint();
			//frame.setVisible(true);
		}
		
	}
}


