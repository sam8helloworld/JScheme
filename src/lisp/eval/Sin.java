package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 組み込み手続きsin
 * 三角関数sin
 * 弧度法を使用
 * @author sam0830
 * @version 1.0
 */
public class Sin implements Subroutine {
	private static final Sin sin = new Sin();
	
	/**
	 * Sinのインスタンスを返す
	 * @return 組み込み手続きSin
	 */
	public static Sin getInstance() {
		return sin;
	}
	
	@Override
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
		return lisp.eval.Double.valueOf(Math.sin(number));
		
	}
	
	@Override
	public String toString() {
		return "#<subr sin>";
	}
}
