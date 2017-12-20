package lisp.eval;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;

/**
 * Canvas
 * @author sam0830
 *
 */
public class Canvas extends JFrame implements SExpression {
	private Rectangle rectangle;
	private Canvas(Number x, Number y, Number width, Number height) {
		int xPos = (x instanceof Int)?((Int)x).getValue():(((lisp.eval.Double)x).getValue()).intValue();
		int yPos = (y instanceof Int)?((Int)y).getValue():(((lisp.eval.Double)y).getValue()).intValue();
		int windowWidth = (width instanceof Int)?((Int)width).getValue():(((lisp.eval.Double)width).getValue()).intValue();
		int windowHeight = (height instanceof Int)?((Int)height).getValue():(((lisp.eval.Double)height).getValue()).intValue();
		rectangle = new Rectangle();
		this.rectangle.setBounds(xPos, yPos, windowWidth, windowHeight);
		setBounds(this.rectangle);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	}
	
	public Rectangle getValue() {
		return this.rectangle;
	}
	
	public static Canvas getInstance(Number x, Number y, Number width, Number height) {
		return new Canvas(x, y, width, height);
	}
	
	public void drawLine(Number x1, Number y1, Number x2, Number y2) {
		int x1Pos = (x1 instanceof Int)?((Int)x1).getValue():(((lisp.eval.Double)x1).getValue()).intValue();
		int y1Pos = (y1 instanceof Int)?((Int)y1).getValue():(((lisp.eval.Double)y1).getValue()).intValue();
		int x2Pos = (x2 instanceof Int)?((Int)x2).getValue():(((lisp.eval.Double)x2).getValue()).intValue();
		int y2Pos = (y2 instanceof Int)?((Int)y2).getValue():(((lisp.eval.Double)y2).getValue()).intValue();
		Graphics g = this.getGraphics();
		g.drawLine(x1Pos, y1Pos, x2Pos, y2Pos);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	@Override
	public String toString() {
		return "canvas" + "(" + this.rectangle.x + "," + this.rectangle.y + "," + this.rectangle.width + "," + this.rectangle.height + ")";
	}
	
	@Override
	public int hashCode() {
		return this.rectangle.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof Canvas) == false) {
			return false;
		}
		return this.rectangle.equals(((Canvas)obj).getValue());
	}
}
