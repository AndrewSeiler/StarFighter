package Game;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 632;
	private static Game game;

	private Main() {
		super("STARFIGHTER");
		setSize(WIDTH, HEIGHT);

		game = new Game();
		game.setFocusable(true);
		getContentPane().add(game);

		setVisible(true);
	}

	private static void stop() {
		if (game != null)
			game.stop();
	}

	public static void main(String[] args) {
		Main main = new Main();

		main.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				stop();
				System.exit(0);
			}
		});
	}
}