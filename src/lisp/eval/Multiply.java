package lisp.eval;

import lisp.exception.ArgumentException;

/**
 * 組み込み手続きmultiply
 * 数値の乗算を行う
 * @author sam0830
 * @version 1.0
 */
public class Multiply implements Subroutine {
	private static final Multiply multiply = new Multiply();
	
	/**
	 * Multiplyのインスタンスを返す
	 * @return 組み込み手続きMultiply
	 */
	public static Multiply getInstance() {
		return multiply;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws ArgumentException {
		Number number = Int.valueOf(1);
		SExpression tmp = sexp;
		while(tmp instanceof ConsCell) {
			SExpression car = ((ConsCell)tmp).getCar();
			if(!(car instanceof Number)) {
				throw new ArgumentException("operation * is not defined on object "+car);
			}
			// 引数が数値
			number = number.multiply((Number)car);
			tmp = ((ConsCell)tmp).getCdr(); 
		}
		return number;
	}
	
	@Override
	public String toString() {
		return "#<subr *>";
	}
}
