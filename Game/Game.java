package Game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements KeyListener, Runnable {
    private Ship ship;
	//private Alien alien;
	//private Aliens aliens;
	private Bullets bullets;
	
  	private boolean[] keys;
	private BufferedImage back;
	private int shootTimer = 0;
	private int rotation = 270;
	
	private Thread thread;
  
	Game() {
		setBackground(Color.BLACK);
    
		keys = new boolean[7];
    
        ship = new Ship(480, 500, 0, 0, 40 , 40);
        //alien = new Alien();
        //aliens = new Aliens();
        bullets = new Bullets();

		this.addKeyListener(this);
		thread = new Thread(this);
		thread.start();

		setVisible(true);
	}
  
    public void update(Graphics window) {
        paint(window);
        if (shootTimer > 0) {
            shootTimer--;
        }
    }
  
	public void paint( Graphics window ) {
		Graphics2D twoDGraph = (Graphics2D)window;
        twoDGraph.setBackground(Color.BLACK);
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		Graphics graphToBack = back.createGraphics();
    
    
		if(keys[0]) {
			ship.move(-1, 0, -4, 0);
		}
        else if (keys[1]) {
            ship.move(1, 0, 4, 0);
        }
        if (keys[2]) {
            ship.move(0, -1, 0, -4);
        }
        else if (keys[3]) {
            ship.move(0, 1, 0, 4);
        }
        if (keys[5]) {
            rotation++;
            rotation %= 360;
        }
        else if (keys[6]) {
            rotation--;
            rotation %= 360;
        }
        if (keys[4] && shootTimer == 0) {
            shootTimer = 200;
            float c = (float)Math.cos(Math.toRadians(rotation));
            float d = (float)Math.sin(Math.toRadians(rotation));
            Bullet bullet = new Bullet(ship.getPosX() + 15, ship.getPosY(), c, d, 10, 10);
            bullets.add(bullet);
        }
        bullets.move();
        bullets.cleanUp();


		//add in collision detection
		
		
		
        bullets.draw(graphToBack);
        ship.draw(graphToBack, rotation);
    
		twoDGraph.drawImage(back, null, 0, 0);
	}

    void stop() {
        thread = null;
    }

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = true;
		}
		if (e.getKeyChar() == 'a') {
		  keys[5] = true;
		}
		if (e.getKeyChar() == 's') {
		  keys[6] = true;
		}
		System.out.println(bullets.getList());
		repaint();
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = false;
		}
		if (e.getKeyChar() == 'a') {
		  keys[5] = false;
		}
		if (e.getKeyChar() == 's') {
		  keys[6] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e) {}
	
    public void run() {
   	    try {
   	        Thread thisThread = Thread.currentThread();
            while(thread == thisThread) {
                Thread.sleep(5);
                repaint();
            }
        }
        catch(Exception e) { }
    }
}

