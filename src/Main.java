import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main {

	public static void main(String[] args) {
		JSpace space = new JSpace();
		space.getSpace().addBody(new Body(10000, 0, 0, 0, 0, "Sun", Color.YELLOW, true));
		//space.getSpace().addBody(new Body(5000, 30, 0, Math.PI / 2, 7, "Sun 1", Color.YELLOW));
		//space.getSpace().addBody(new Body(5000, -30, 0, -Math.PI / 2, 7, "Sun 2", Color.YELLOW));
		space.getSpace().addBody(new Body(10, 200, 0, Math.PI / 2, 7, "Mercury", Color.GRAY));
		space.getSpace().addBody(new Body(10, 400, 0, Math.PI / 2, 5, "Venus", Color.GRAY));
		space.getSpace().addBody(new Body(10, 600, 0, Math.PI / 2, 4.1, "Earth", Color.GREEN));
		space.getSpace().addBody(new Body(0.1, 630, 0, Math.PI / 2, 3.4, "Moon", Color.LIGHT_GRAY));
		space.getSpace().addBody(new Body(10, 800, 0, Math.PI / 2, 3.5, "Mars", Color.ORANGE));
		space.getSpace().addBody(new Body(100, 1200, 0, Math.PI / 2, 3, "Jupiter", Color.ORANGE));
		space.getSpace().addBody(new Body(0.01, 3000, 0, Math.PI / 2, 0.5, "Comet", Color.WHITE));
		//space.setObjectTracked("Earth");
		
		//space.getSpace().addBody(new Body(1000000, 0, 0, 0, 0, "BLACK HOLE", Color.DARK_GRAY, true));

		JFrame frame = new JFrame("Space");
		frame.setSize(500, 500);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(space);

		frame.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_EQUALS:
					space.zoom(1.1);
					break;
				case KeyEvent.VK_MINUS:
					space.zoom(0.9);
					break;
				case KeyEvent.VK_G:
					double velo = space.getSpace().getBody("Jupiter").getVelocity(); 
					space.getSpace().getBody("Jupiter").setVelocity(velo - 0.01);
					break;
				case KeyEvent.VK_H:
					double velo2 = space.getSpace().getBody("Jupiter").getVelocity(); 
					space.getSpace().getBody("Jupiter").setVelocity(velo2 + 0.01);
					break;
				}
			}

		});

		Timer t = new Timer(15, (e) -> {
			space.getSpace().tick(0.1);
			frame.repaint();
		});
		t.start();
	}

}
