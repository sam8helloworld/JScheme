package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 数値の大小比較の組み込み手続き
 * 小なり(<)
 * @author sam0830
 * @version 1.0
 */
public class LessThan implements Subroutine {
	private static final LessThan lessThan = new LessThan();
	
	/**
	 * LessThanのインスタンスを返す
	 * @return 組み込み手続きLessThan
	 */
	public static LessThan getInstance() {
		return lessThan;
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
			if(!((Number)arg).lessThan((Number)argNext)) {
				return Bool.valueOf(false);
			}
		}
		return Bool.valueOf(true);
	}
	
	@Override
	public String toString() {
		return "#<subr <>";
	}
}
