package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * Add (加法)
 * @author sam0830
 *
 */
public class Add implements Subroutine {
	private static final Add add = new Add();
	
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		Number number = Int.valueOf(0);
		// 引数なし
		if(sexp instanceof EmptyList) {
			return number;
		}
		// 引数が1個
		if(!(sexp instanceof ConsCell)) {
			// 
		}
		int size = ((ConsCell)sexp).size();
		if(size == 1) {
			return ((ConsCell)sexp).get(0);
		}
		// 引数が2個以上
		for(int i=0;i<size;i++) {
			SExpression arg = ((ConsCell)sexp).get(i);
			if(!(arg instanceof Number)) {
				if(i==0) {
					// 引数が数字でない時エラー
					throw new ArgumentException("operation " + this+ " is not defined between "+arg+" and "+((ConsCell)sexp).get(1));
				}
				// 引数が数字でない時エラー
				throw new ArgumentException("operation " + this+ " is not defined between "+number+" and "+arg);
			}
			// 引数が数値
			number = number.add((Number)arg);
		}
		return number;
	}
	
	public static Add getInstance() {
		return add;
	}
	
	@Override
	public String toString() {
		return "+";
	}
}
