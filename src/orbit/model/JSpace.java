package orbit.model;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import orbit.orbit.Orbit;

public class JSpace extends JComponent {

	private static final long serialVersionUID = 4791185425636679054L;

	private Space space = new Space();
	private double dx = 0;
	private double dy = 0;
	private String trackedBody;
	private double zoom = 1;

	public Space getSpace() {
		return space;
	}

	public void setObjectTracked(String name) {
		trackedBody = name;
	}

	public void zoom(double factor) {
		zoom *= factor;
	}

	@Override
	public void paintComponent(Graphics gfx) {
		Graphics2D g = (Graphics2D) gfx;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		//g.drawString("Dist. from Sun: " + space.getBody(trackedBody).distance(space.getBody("Sun")), 10, 20);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.translate(getWidth() / 2, getHeight() / 2);
		for (Body body : space) {
			double cx = body.getX() * zoom - dx * zoom;
			double cy = body.getY() * zoom - dy * zoom;
			double r = Math.log(body.getMass()) * 4 * zoom + 10;
			
			g.drawString(body.getName(), (int) cx - (g.getFontMetrics().stringWidth(body.getName()) / 2),
					(int) (cy - r - 5));
		}
		g.scale(zoom, zoom);

		Body tracked = space.getBody(trackedBody);
		if (tracked != null) {
			dx = tracked.getX();
			dy = tracked.getY();
		} else {
			dx = dy = 0;
		}
		g.translate(-dx, -dy);
		Body refOrbit = space.getBody("Sun");
		for (Body body : space) {
			
			double cx = body.getX();
			double cy = body.getY();
			double r = Math.log(body.getMass()) * 4 + 10;
			
			g.setColor(Color.MAGENTA);
			double lastx = 0;
			double lasty = 0;
			
			Orbit orbit = body.computeOrbit(refOrbit);
			for (double theta = 0; theta < 2 * Math.PI; theta += Math.PI / 128) {
				double[] pos = orbit.getPositionRelativeToParent(theta);
				
				double x1 = pos[0];
				double y1 = pos[1];
				if (theta != 0) {
					g.drawLine((int) lastx, (int) lasty, (int) x1, (int) y1);
				}
				lastx = x1;
				lasty = y1;
			}
			g.setColor(body.getColor());
			g.fillOval((int) (cx - r / 2), (int) (cy - r / 2), (int) r, (int) r);
		}
	}

}
