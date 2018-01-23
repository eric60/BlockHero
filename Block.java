import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;

public class Block extends Sprite {
	
	private final String blockImage1 = "src/assets/block1.png";
	private final String blockImage2 = "src/assets/block2.png";
	private final String blockImage3 = "src/assets/block3.png";
	private Image[] blockImages = new Image[3];
	Random rand = new Random();
	
	public Block(int initialX, int initialY) {
		x = initialX;
		y = initialY;
		width = 30;
		height = 30;
		dy = 25;
		initBlock();
	}
	
	private void initBlock() {
		ImageIcon block1 = new ImageIcon(blockImage1);
		ImageIcon block2 = new ImageIcon(blockImage2);
		ImageIcon block3 = new ImageIcon(blockImage3);	
		blockImages[0] = block1.getImage();
		blockImages[1] = block2.getImage();
		blockImages[2] = block3.getImage();
		int num = rand.nextInt(3);
		setImage(blockImages[num]);	
	}
		
	public void moveDown() {
		//bottom of board panel
		if(y <= 490)
			y += dy;
		else
			y += 100;//block disappears from screen by moving it down out of sight
	}
	
}
