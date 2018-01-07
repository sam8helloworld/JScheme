package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 除算の組み込み手続き
 * @author sam0830
 * @version 1.0
 */
public class Divide implements Subroutine {
	private static final Divide divide = new Divide();
	
	/**
	 * Divideのインスタンスを返す
	 * @return 組み込み手続きDivide
	 */
 	public static Divide getInstance() {
		return divide;
	}
	
 	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 引数なし
		if(sexp instanceof EmptyList) {
			throw new ArgumentException("procedure requires at least one argument: (/)");
		}
		
		SExpression car = ((ConsCell)sexp).getCar();
		
		// 引数1個
		if(((ConsCell)sexp).size() == 1) {
			// 引数が数値でない
			if(!(car instanceof Number)) {
				throw new ArgumentException("operation / is not defined on object "+car);
			}
			// 引数が数値
			return Int.valueOf(1).divide((Number)car);
		}
		
		// 引数が2個以上
		if(!(car instanceof Number)) {
			throw new ArgumentException("operation / is not defined between "+car+" and "+((ConsCell)sexp).get(1));
		}
		Number number = (Number)car;
		SExpression tmp = ((ConsCell)sexp).getCdr();
		while(tmp instanceof ConsCell) {
			SExpression arg = ((ConsCell)tmp).getCar();
			if(!(arg instanceof Number)) {
				throw new ArgumentException("operation / is not defined between "+number+" and "+arg);
			}
			// 引数が数値
			number = number.divide((Number)arg);
			tmp = ((ConsCell)tmp).getCdr();
		}
		return number;
	}
	
	@Override
	public String toString() {
		return "#<subr />";
	}
}
