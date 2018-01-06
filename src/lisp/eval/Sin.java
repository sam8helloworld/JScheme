package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * Sin
 * @author sam0830
 *
 */
public class Sin implements Subroutine {
	private static final Sin sin = new Sin();
	
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got 0)");
		}
		int size = ((ConsCell)sexp).size();
		if(size != 1) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got "+size+")");
		}
		SExpression car = ((ConsCell)sexp).getCar();
		if(!(car instanceof Number)) {
			throw new ArgumentException("number required, but got "+car);
		}
		double number = (car instanceof Int)?((Int)car).getValue():((lisp.eval.Double)car).getValue();
		return lisp.eval.Double.valueOf(Math.sin(Math.toRadians(number)));
		
	}
	
	public static Sin getInstance() {
		return sin;
	}
	
	@Override
	public String toString() {
		return "#<subr sin>";
	}
}
