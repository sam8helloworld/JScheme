package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 組み込み手続きnot
 * @author sam0830
 * @version 1.0
 */
public class Not implements Subroutine {
	private static final Not not = new Not();
	
	/**
	 * Notのインスタンスを返す
	 * @return 組み込み手続きNot
	 */
	public static Not getInstance() {
		return not;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("pair required, but got "+sexp);
		}
		int size = ((ConsCell)sexp).size();
		if(size != 1) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got "+size+")");
		}
		SExpression car = ((ConsCell)sexp).getCar();
		if(car instanceof Bool) {
			if(!((Bool)car).isValid()) {
				return Bool.valueOf(true);
			}
		}
		return Bool.valueOf(false);
	}
	
	@Override
	public String toString() {
		return "#<subr not>";
	}
	
}
