package lisp.eval;

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
