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
	
	private int MAX_MATCHES = 20;
	private int NUM_OF_BUTTONS_CHOISE = 10;
	
	private Game game;
	
	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private JButton but1, but2, butNew;
	private JButton [] butChoise;
	private JPanel buttonsPanel;
	private JPanel buttonsPanelNew;
	private JPanel buttonsPanelStart;
	private JPanel buttonsPanelChose;
	
	private ButtonListener buttonListener;
	
	private Image emptyField;
	private Image matchField;
	private Image gameIcon;
	private int imageSizeX;
	private int imageSizeY;

	private Font font = new Font("Arial", Font.ITALIC, 38);
	
	public static void main(String [] args) {
		
		new Matches();
		
	}
		
	Matches() {
		game = new Game(MAX_MATCHES);
		initImages();
		initPanel();
		initLabel();
		initButtons();
		initFrame();		
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
							
							for(int i = 0; i < MAX_MATCHES; i++){
								if(i < game.getNumOfMatches()){ 
									g.drawImage(matchField, imageSizeX * i, 0, null);
								} else {
									g.drawImage(emptyField, imageSizeX * i, 0, null);
								}
							}
					
						}
					};
		panel.setPreferredSize(new Dimension(MAX_MATCHES * imageSizeX, imageSizeY));
	}
	
	private void initLabel() {
		label = new JLabel("������ ����� ����?");
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);	
	}
	
	private void initButtons() {
		createButtonsPanels();
		createButtons();
		addButtonsToPanels();	
	}
	
	private void createButtonsPanels() {
		buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(MAX_MATCHES * imageSizeX, 100));
		buttonsPanel.setLayout(new GridLayout(1, 1));
		
		buttonsPanelNew = new JPanel();
		buttonsPanelNew.setLayout(new GridLayout(1, 1));
		
		buttonsPanelStart = new JPanel();
		buttonsPanelStart.setLayout(new GridLayout(1, 1));
		
		buttonsPanelChose = new JPanel();
		buttonsPanelChose.setLayout(new GridLayout(1, 1));
	}
	
	private void createButtons() {
		buttonListener = new ButtonListener();
		
		but1 = new JButton("1");
		initButton(but1);
		but2 = new JButton("2");
		initButton(but2);
		butNew = new JButton("����� ����");
		initButton(butNew);
		
		butChoise = new JButton [NUM_OF_BUTTONS_CHOISE];
		for(int i = 0; i < NUM_OF_BUTTONS_CHOISE; i++){
			butChoise[i] = new JButton("" + (i + 11));
			butChoise[i].setFont(font);
			butChoise[i].addActionListener(buttonListener);
			buttonsPanelStart.add(butChoise[i]);
		}
	}
	
	private void initButton(JButton btn) {
		btn.setFont(font);
		btn.addActionListener(buttonListener);
	}
	
	private void addButtonsToPanels() {
		buttonsPanelNew.add(butNew);
		buttonsPanelChose.add(but1);
		buttonsPanelChose.add(but2);
		buttonsPanel.add(buttonsPanelNew);
	}
	
	private void initFrame() {
		frame = new JFrame("������");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(BorderLayout.NORTH, panel);
		frame.add(BorderLayout.CENTER, label);
		frame.add(BorderLayout.SOUTH, buttonsPanel);
		frame.setResizable(false);           //�����p ������ ���������� ��������
		frame.pack();                        //��������� ������ ������, ����� � ���� ����������� ��� ����������
		frame.setLocationRelativeTo(null);   //�������� ����� � ����� ������ ����������
		frame.setIconImage(gameIcon);
		frame.setVisible(true);
	}
	
	class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent ae) {
			
			String actionComand = ae.getActionCommand();
			
			if(actionComand.equals("1") || actionComand.equals("2")){
				int matchesDel = Integer.parseInt(actionComand);
				String resultOfGame = game.getResult(matchesDel);
				label.setText("��������� ������ " + game.getCompDel() + " ������(-�). ������� ������� ��?");
				
				if(resultOfGame.equals("humanWin")) {
					label.setText("�� ��������!!! ������ ����� ����?");
					changePanel(buttonsPanelNew);
				} else if(resultOfGame.equals("compWin")) {
					label.setText("��������� ������ " + game.getCompDel() + " ������(-�) � �������((( ������ ����� ����?");
					changePanel(buttonsPanelNew);
				}
				
				panel.repaint();		
			} else if(actionComand.equals("����� ����")){
				label.setText("�� ������� ������ ������?");
				changePanel(buttonsPanelStart);	
			} else {
				game.setNumOfMatches(Integer.parseInt(actionComand));
				changePanel(buttonsPanelChose);
				label.setText("������� ���� ��� ��� ������?");	
			}	
		}
		
		void changePanel(JPanel newPanel) {
			buttonsPanel.removeAll();
			buttonsPanel.add(newPanel);
			frame.add(BorderLayout.SOUTH, buttonsPanel);
			frame.repaint();
		}	
	}
}


