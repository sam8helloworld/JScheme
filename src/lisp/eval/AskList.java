package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * list?
 * @author sam0830
 *
 */
public class AskList implements Subroutine {
	private static final AskList askList = new AskList();
	
	public static AskList getInstance() {
		return askList;
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
		// 引数がリストかどうか
		SExpression arg = ((ConsCell)sexp).get(0);
		if(!(arg instanceof ConsCell)) {
			return Bool.valueOf(false);
		}
		return ((ConsCell)arg).isList()?Bool.valueOf(true):Bool.valueOf(false);
	}
	@Override
	public String toString() {
		return "#<subr list?>";
	}
}
