package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * EqualAddress
 * @author sam0830
 *
 */
public class EqualAddress implements Subroutine {
	private static final EqualAddress equalAddress = new EqualAddress();
	/**
	 * 引数は2つのみ
	 * 引数が2つでないときエラー
	 * @throws LispException 
	 */
	public SExpression apply(SExpression sexp, Environment environment) throws LispException { 
		// 引数の個数が0個の時
		if(sexp instanceof EmptyList) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 2, got 0)");
		}
		int size = ((ConsCell)sexp).size();
		if(size != 2) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 2, got "+size+")");
		}
		// 引数の個数が2個の時
		return ((ConsCell)sexp).get(0).equals(((ConsCell)sexp).get(1))?Bool.valueOf(true) : Bool.valueOf(false);
	}
	
	public static EqualAddress getInstance() {
		return equalAddress;
	}
	
	@Override
	public String toString() {
		return "#<subr eq?>";
	}
}
