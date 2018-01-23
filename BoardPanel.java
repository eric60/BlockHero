import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel implements Runnable, KeyListener,Constants {
	
	private Block block;
	private Player player = new Player();
	private final String roadImage = "src/assets/road.jpg";
	private final String titleImage = "src/assets/titleImage.png";
	private int score = 0;
	private String highScore = "";
	private String name = "";
	private boolean beatScore = false;
	private boolean inGame = true;
	private boolean replay = false;
	private JTextField replayText;
	private Timer timer1= new Timer(1000, new TimerListener());
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private Random rand = new Random();
	
	private ImageIcon roadpic = new ImageIcon(roadImage);
	private Image road = roadpic.getImage();
	private ImageIcon titlePic = new ImageIcon(titleImage);
	private Image title = titlePic.getImage();
	
	public BoardPanel() {
		initGame();
	}
	
	public void initGame() {
	    addKeyListener(this);
        setFocusable(true);
        setDoubleBuffered(true);
        timer1.start();
	}
	
	//listens for the timer action event every variable delay milliseconds 
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			score += 1;//update score by 1 point every second
			int x = rand.nextInt(BOARD_WIDTH - 35) + 61;
			blocks.add(new Block(x,125));//random location along board top
			for(Block block : blocks) {
				block.moveDown();
			}
		}
	}
	
	public void drawRoad(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 500, 600);
		g.drawImage(road, 70, 130, this);
		g.drawImage(title, 55, 0, this);
	}
	
	public void drawBlocks(Graphics g) {
		int num = rand.nextInt(3);	
		for(Block block : blocks) {
	 		g.drawImage(block.getImage(), block.getX(), block.getY(), null); 
		}

	}
	
	public void drawPlayer(Graphics g) {
		g.drawImage(player.getImage(), player.getX(), player.getY(), null);	
	}
	
	public void drawScore(Graphics g) {
		g.setFont(new Font("Helvetica", Font.BOLD, 13));
		g.setColor(Color.red);
		g.drawString("Score: " + score, 420, GAME_HEIGHT - 55);
		g.drawString("HighScore " + highScore, 380, GAME_HEIGHT - 36);
	}
	
	public void gameOver(Graphics g) {
		 //g.setColor(Color.black);
	    // g.fillRect(BOARD_WIDTH / 2, 10, 90, 30);
	     g.setFont(new Font("Helvetica", Font.BOLD, 13));
	     g.setColor(Color.green.darker());
	     g.drawString("GAME OVER", (BOARD_WIDTH / 2), 150);
	     if(!beatScore) {
	    	 	g.drawString("You didn't beat the highscore set by " + highScore, (GAME_WIDTH / 2) - 130 , 165);
	     }
	     g.drawString("You scored " + score, (GAME_WIDTH / 2) - 50, 180);        
	    	 replayText = new JTextField("Press Space to replay game");
	    	 replayText.setBounds((GAME_WIDTH / 2) - 100, 180, 182, 30);
	    	 this.add(replayText);    	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  
		if(inGame || replay) {
			drawRoad(g);
			drawPlayer(g);
			drawBlocks(g);
			drawScore(g);
		}			
		if(!inGame) {
			gameOver(g);
		}
		if(highScore.equals("")) {
			highScore = this.getHighScore();
		}	
		repaint();
	}	
	
	public void checkCollison() {
		int playerX = player.getX();
		int playerY = player.getY();
		Rectangle playerRect = new Rectangle(playerX, playerY, PLAYER_WIDTH, PLAYER_HEIGHT);
		
		for(Block block : blocks) 
		{
			int blockX = block.getX();
			int blockY = block.getY();	
			Rectangle blockRect = new Rectangle(blockX, blockY, BLOCK_WIDTH, BLOCK_HEIGHT);
			
			//collision between block and player
			if(playerRect.intersects(blockRect)) {
				timer1.stop();
				checkScore();
				inGame = false;
			}
			
			if(blockY > 530) {
				repaint();
			}
		}
	}
	
	public String getHighScore() {
		FileReader fr = null;
		BufferedReader reader = null;
		try {
			fr = new FileReader("highscore.dat");//data file unable to edited by user
			reader = new BufferedReader(fr);
			return reader.readLine();//returns first line containing name:score
		}
		catch(Exception e) {
			return "default:0";//default user for initial high score of 0
		}
		finally {
			try {
				if(reader != null)
				reader.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkScore() {
		//format is name:highscore
		if(highScore.equals("")){
			return;//exits checkscore method if highscore is nothing
		}
		if(score > Integer.parseInt(getHighScore().split(":")[1])) {
			name = JOptionPane.showInputDialog("New High Score!\nPlease Enter Your Name: ");
			highScore = name + ":" + score;
			beatScore = true;
		}
		
		File scoreFile = new File("highscore.dat");
		if(!scoreFile.exists()) {
			try {
				scoreFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(scoreFile);//file writer writing out to high score dat file
			bw = new BufferedWriter(fw);//buffered writer turning filewriter file into writable file
			bw.write(this.highScore);
		}
		catch(Exception e) {
			
		}
		finally {
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		int playerX = player.getX();
		int playerY = player.getY();
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT && (playerX > 50 + PLAYER_WIDTH)) {
			player.moveLeft();
			checkCollison();
		}
		if(key == KeyEvent.VK_RIGHT && (playerX < 438 - PLAYER_WIDTH)) {
			player.moveRight();
			checkCollison();
		}
		if(key == KeyEvent.VK_UP && (playerY > 95 + PLAYER_HEIGHT)) {
			player.moveUp();
			checkCollison();
		}
		if(key == KeyEvent.VK_DOWN && (playerY < 521 - PLAYER_HEIGHT)) {
			player.moveDown();
			checkCollison();
		}
		if(key == KeyEvent.VK_SPACE) {
			score = 0;
			replay = true;
			initGame();
		}
	}	
	
	public void keyReleased(KeyEvent e) {

    }


    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void run() {
        while (inGame) {          	  
        		repaint();
        }

    }	

}
