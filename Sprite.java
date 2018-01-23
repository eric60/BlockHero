import java.awt.Image;

public class Sprite {
	
    private boolean visible;
    protected Image image;
    protected boolean dying;
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int width;
    protected int height;
    
    public Sprite() {
    
        visible = true;
    }
    
    public int getHeight() {
    		return height;
    }
    
    public int getWidth() {
    		return width;
    }

    public void die() {
    
        visible = false;
    }

    public boolean isVisible() {
    
        return visible;
    }

    protected void setVisible(boolean visible) {
    
        this.visible = visible;
    }

    public void setImage(Image image) {
    
        this.image = image;
    }

    public Image getImage() {
    
        return image;
    }

    public void setX(int x) {
    
        this.x = x;
    }

    public void setY(int y) {
    
        this.y = y;
    }

    public int getY() {
    
        return y;
    }

    public int getX() {
    
        return x;
    }
}