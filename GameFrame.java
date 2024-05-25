import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameFrame extends JFrame implements ActionListener{
	JButton resetButton;
	GamePanel game;
	GameFrame(){
		
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
		
		
		
	
	
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
	}

}
