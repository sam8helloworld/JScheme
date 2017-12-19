package lisp.eval;

/**
 * Multiply (乗法)
 * @author sam0830
 *
 */
public class Multiply implements Subroutine {
	private static final Multiply multiply = new Multiply();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		Number number = Int.valueOf(1);
		SExpression tmp = sexp;
		while(tmp instanceof ConsCell) {
			SExpression car = ((ConsCell)tmp).getCar();
			if(!(car instanceof Number)) {
				// エラー
			}
			// 引数が数値
			number = number.multiply((Number)car);
			tmp = ((ConsCell)tmp).getCdr(); 
		}
		return number;
	}
	
	public static Multiply getInstance() {
		return multiply;
	}
}
