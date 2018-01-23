import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements Constants{

	public GameFrame() {
		add(new BoardPanel());
		setTitle("Block Hero");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			GameFrame game = new GameFrame();
	        game.setVisible(true);
	    });
	}
}
