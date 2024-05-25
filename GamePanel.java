import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent; 
import java.awt.*;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;




public class GamePanel extends JPanel implements ActionListener{
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	JButton resetButton;
	GamePanel(){
		
		
		
		
		
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
		public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
		
		
			
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		if(running) {
			//creates the grid
			/*
		for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
			g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE );
			
		}
		*/
			
				g.setColor(Color.red);
				g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
				
				for(int i = 0; i< bodyParts;i++) {
					if (i == 0) {
						g.setColor(Color.green);
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
					
					
						
					
					else {
					
							g.setColor(new Color(45,180,0));
							//creates the rainbow snake
							g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
							g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
						
						
					
			}
		
			
		}
		//score counter
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospaced", Font.BOLD, 40));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Apples ate: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Apples ate"+applesEaten))/2, g.getFont().getSize());
		
		//secret hint
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospaced", Font.BOLD, 10));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("press comma for super speed "+applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("press comma for super speed "+applesEaten))/2, g.getFont().getSize());

	}
		else gameOver(g);
}
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	public void move() {
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
		
	}
	//adds body parts after apple is eaten
	public void checkApple() {
		if((x[0] == appleX)&& (y[0] == appleY)) {
			bodyParts = bodyParts + 1;
			applesEaten++;
			newApple();
		}
		
	}
	public void checkCollisions() {
		//checks if head collides with body
		for(int i = bodyParts;i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				running = false;
			}
		}
		//check if head touches left border
		if(x[0]<0) {
			running = false;
		}
		//check to see if head touches right border
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		//check to see if head touches top border
		if(y[0]<0) {
			running = false;
		}
		//check to see if head touches bottom border
		if(y[0] > SCREEN_WIDTH) {
			running = false;
		}
		
		if(!running) {
			timer.stop();
		}
		
	}
	public void gameOver(Graphics g) {
		//game over text
		g.setColor(Color.blue);
		g.setFont(new Font("Serif", Font.BOLD, 25));
		FontMetrics metrics3 = getFontMetrics(g.getFont());
		
		
		
		
		
		
		
		if (applesEaten < 10) {
			g.setColor(Color.red);
			g.setFont(new Font("Monospaced", Font.BOLD, 75));
			FontMetrics metrics1 = getFontMetrics(g.getFont());
			g.drawString("your meh", (SCREEN_WIDTH - metrics1.stringWidth("your meh"))/2, SCREEN_HEIGHT/2);
			//displays score after game over 
			g.setColor(Color.red);
			g.setFont(new Font("Monospaced", Font.BOLD, 40));
			FontMetrics metrics2 = getFontMetrics(g.getFont());
			g.drawString("apples ate: "+applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("apples ate:"+applesEaten))/2, g.getFont().getSize());
		}
		else if (applesEaten < 20){
			g.setColor(Color.blue);
			g.setFont(new Font("Monospaced", Font.BOLD, 75));
			FontMetrics metrics1 = getFontMetrics(g.getFont());
			g.drawString("your amazing", (SCREEN_WIDTH - metrics1.stringWidth("Your amazing"))/2, SCREEN_HEIGHT/2);
			//displays score after game over 
			g.setColor(Color.red);
			g.setFont(new Font("Monospaced", Font.BOLD, 40));
			FontMetrics metrics2 = getFontMetrics(g.getFont());
			g.drawString("your amazing "+applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("apples ate:"+applesEaten))/2, g.getFont().getSize());
		}
		else {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Monospaced", Font.BOLD, 75));
			FontMetrics metrics1 = getFontMetrics(g.getFont());
			g.drawString("your amazing", (SCREEN_WIDTH - metrics1.stringWidth("your amazing"))/2, SCREEN_HEIGHT/2);
			//displays score after game over 
			g.setColor(Color.red);
			g.setFont(new Font("Monospaced", Font.BOLD, 40));
			FontMetrics metrics2 = getFontMetrics(g.getFont());
			g.drawString("apples ate: "+applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("apples ate: "+applesEaten))/2, g.getFont().getSize());
		}
		
		}
	public void superSpeed() {
		running = false;
		startGame();
	}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
		
	}
	public class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_COMMA) {
				superSpeed();
				
			
			}
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				running = false;
				/*
				g.setColor(Color.red);
				g.setFont(new Font("Monospaced", Font.BOLD, 40));
				FontMetrics metrics2 = getFontMetrics(g.getFont());
				g.drawString("restarting game"+applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("restarting game"+applesEaten))/2, g.getFont().getSize());
				*/
			}
			
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
		
		}
			
	}
}
    
	

}