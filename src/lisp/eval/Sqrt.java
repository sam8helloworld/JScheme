package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 組み込み手続きsqrt
 * 平方根を計算する
 * @author sam0830
 * @version 1.0
 */
public class Sqrt implements Subroutine {
	private static final Sqrt sqrt = new Sqrt();
	
	/**
	 * Sqrtのインスタンスを返す
	 * @return 組み込み手続きSqrt
	 */
	public static Sqrt getInstance() {
		return sqrt;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		/**
		 * 引数は1つ
		 * 引数はNumber型
		 */
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got 0)");
		}
		int size = ((ConsCell)sexp).size();
		if(size != 1) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got "+size+")");
		}
		SExpression arg = ((ConsCell)sexp).getCar();
		if(!(arg instanceof Number)) {
			throw new ArgumentException("number required, but got "+arg);
		}
		double number = (arg instanceof Int)?((Int)arg).getValue():((lisp.eval.Double)arg).getValue();
		return lisp.eval.Double.valueOf(Math.sqrt(number));
	}
	
	@Override
	public String toString() {
		return "#<subr sqrt>";
	}
}
