package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 数値の大小比較の組み込み手続き
 * 大なりイコール(>=)
 * @author sam0830
 * @version 1.0
 */
public class GreaterThanOrEqual implements Subroutine {
	private static final GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual();
	
	/**
	 * GreaterThanOrEqualのインスタンスを返す
	 * @return 組み込み手続きGreaterThanOrEqual
	 */
	public static GreaterThanOrEqual getInstance() {
		return greaterThanOrEqual;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 引数が2個以上ないとエラー
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 2, got 0)");
		}
		int size = ((ConsCell)sexp).size();
		if(size < 2) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 2, got "+size+")");
		}
		for(int i=0;i<size-1;i++) {
			SExpression arg = ((ConsCell)sexp).get(i);
			SExpression argNext = ((ConsCell)sexp).get(i+1);
			if(!(arg instanceof Number) || !(argNext instanceof Number)) {
				String str = (!(arg instanceof Number))?arg.toString():argNext.toString();
				throw new ArgumentException("real number required: "+str);
			}
			if(!((Number)arg).greaterThanOrEqual((Number)argNext)) {
				return Bool.valueOf(false);
			}
		}
		return Bool.valueOf(true);
	}
	
	@Override
	public String toString() {
		return "#<subr >=>";
	}
}
