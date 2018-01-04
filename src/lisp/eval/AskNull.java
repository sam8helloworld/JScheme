package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * null?
 * @author sam0830
 *
 */
public class AskNull implements Subroutine {
	private static final AskNull askNull = new AskNull();
	
	public static AskNull getInstance() {
		return askNull;
	}
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 引数が空リストかどうか
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got 0)");
		}
		// 引数の個数は1個
		int size = ((ConsCell)sexp).size();
		if(size != 1) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got "+size+")");
		}
		return (((ConsCell)sexp).get(0) instanceof EmptyList)?Bool.valueOf(true):Bool.valueOf(false);
	}
	
}
