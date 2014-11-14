package ui.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RoundButton extends JButton {
	private static final long serialVersionUID = -1403149857932538287L;

	public RoundButton(String label) {
		super(label);
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}

	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(Color.lightGray);
		} else {
			g.setColor(getBackground());
		}
		g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
	}

	Shape shape;

	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}

	public static void main(String[] args) {
		JButton button = new RoundButton("Jackpot");
		button.setBackground(Color.green);
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(Color.yellow);
		frame.getContentPane().add(button);
		frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(150, 150);
		frame.setVisible(true);
	}
}
