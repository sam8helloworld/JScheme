package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * DrawLine
 * @author sam0830
 *
 */
public class DrawLine implements Subroutine {
	private static final DrawLine drawLine = new DrawLine();
	
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 引数の型は(Number, Number, Number, Number, Canvas)
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 5, got 0)");
		}
		int size = ((ConsCell)sexp).size();
		if(size != 5) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got "+size+")");
		}
		Number[] elements = new Number[4];
		for(int i =0;i<4;i++) {
			SExpression element = ((ConsCell)sexp).get(i);
			if(!(element instanceof Number)) {
				throw new ArgumentException("operation draw-line is not defined on object "+element);
			}
			elements[i] = (Number)element;
		}
		SExpression canvas = ((ConsCell)sexp).get(4);
		if(!(canvas instanceof Canvas)) {
			throw new ArgumentException("operation draw-line is not defined on object "+canvas);
		}
		((Canvas)canvas).drawLine(elements[0], elements[1], elements[2], elements[3]);
		return Bool.valueOf(true);
	}
	
	public static DrawLine getInstance() {
		return drawLine;
	}
	
	@Override
	public String toString() {
		return "#<subr draw-line>";
	}
}
