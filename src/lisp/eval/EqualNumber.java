package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 数値比較(=)
 * @author sam0830
 * @version 1.0
 */
public class EqualNumber implements Subroutine {
	private static final EqualNumber equalNumber = new EqualNumber();
	
	/**
	 * EqualNumberのインスタンスを返す
	 * @return 組み込み手続きEqualNumber
	 */
	public static EqualNumber getInstance() {
		return equalNumber;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException { 
		// 引数が2つ以上でないときエラー
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 2, got 0)");
		}
		int size = ((ConsCell)sexp).size();
		if(size < 2) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got "+size+")");
		}
		SExpression arg = ((ConsCell)sexp).getCar();
		if(!(arg instanceof Number)) {
			throw new ArgumentException("real number required: "+arg);
		}
		
		double comparedNumber = (arg instanceof Int)?((Int)arg).getValue():((lisp.eval.Double)arg).getValue();
		for(int i=1;i<size;i++) {
			SExpression tmp = ((ConsCell)sexp).get(i);
			if(!(tmp instanceof Number)) {
				throw new ArgumentException("real number required: "+tmp);
			}
			Number num = (Number)tmp;
			double number = (num instanceof Int)?((Int)num).getValue():((lisp.eval.Double)num).getValue();
			if(comparedNumber != number) {
				return Bool.valueOf(false);
			}
		}
		return Bool.valueOf(true);
	}
	
	@Override
	public String toString() {
		return "#<subr =>";
	}
}
