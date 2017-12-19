package lisp.eval;

/**
 * Multiply (乗法)
 * @author sam0830
 *
 */
public class Divide implements Subroutine {
 private static final Divide divide = new Divide();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		// 引数なし
		if(sexp instanceof EmptyList) {
			// エラー
		}
		
		SExpression car = ((ConsCell)sexp).getCar();
		
		// 引数1個
		if(((ConsCell)sexp).getSize() == 1) {
			// 引数が数値でない
			if(!(car instanceof Number)) {
				// エラー
			}
			// 引数が数値
			return Int.valueOf(1).divide((Number)car);
		}
		
		// 引数が2個以上
		if(!(car instanceof Number)) {
			// エラー
		}
		Number number = (Number)car;
		SExpression tmp = ((ConsCell)sexp).getCdr();
		while(tmp instanceof ConsCell) {
			SExpression arg = ((ConsCell)tmp).getCar();
			if(!(arg instanceof Number)) {
				// エラー
			}
			// 引数が数値
			number = number.divide((Number)arg);
			tmp = ((ConsCell)tmp).getCdr();
		}
		return number;
	}
	
	public static Divide getInstance() {
		return divide;
	}
}
