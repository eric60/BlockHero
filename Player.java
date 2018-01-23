import javax.swing.ImageIcon;

public class Player extends Sprite {
	
	private final String playerImage = "src/assets/car.png";
	
	public Player() {
		x = 100;//initial x position
		y = 300;//initial y position 
		width = 28;
		height = 53;
		dx = 10;
		dy = 10;  
		initPlayer();
	}
	
	private void initPlayer() {
		ImageIcon player = new ImageIcon(playerImage);
		setImage(player.getImage());
	}
	
	public void moveLeft() {
		x -= dx;
	}
	
	public void moveRight() {
		x += dx;
	}
	
	public void moveUp() {	
		y -= dy;
	}
	
	public void moveDown() {
		y += dy;
	}
}
