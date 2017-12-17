package lisp.eval;

/**
 * Add (加法)
 * @author sam0830
 *
 */
public class Sub implements Subroutine{
	private static final Sub sub = new Sub();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		// 引数なし
		if(sexp instanceof EmptyList) {
			// エラー
		}
		// 引数1つ
		if(((ConsCell)sexp).getSize() == 1) {
			SExpression car = ((ConsCell)sexp).getCar();
			// 引数が数値でない
			if(!(car instanceof Number)) {
				// エラー
			}
			// 引数が数値
			return (car instanceof Int)? Int.valueOf(((Int)car).getValue()): lisp.eval.Double.valueOf(((lisp.eval.Double)car).getValue());
		}
		// 引数が2つ以上
		SExpression car = ((ConsCell)sexp).getCar();
		if(!(car instanceof Number)) {
			// エラー
		}
		double num = 0;
		boolean isDouble = false;
		if(car instanceof Int) {
			num = ((Int)car).getValue();
		} else {
			num = ((lisp.eval.Double)car).getValue();
			isDouble = true;
		}
		
		SExpression s = ((ConsCell)sexp).getCdr();
		SExpression tmp = s;
		while(tmp instanceof ConsCell) {
			SExpression arg = ((ConsCell)tmp).getCar();
			if(!(arg instanceof Number)) {
				// エラー
			}
			// 引数が数値
			if(arg instanceof Int) {
				num -= ((Int)arg).getValue();
			}
			if(arg instanceof lisp.eval.Double) {
				num -= ((lisp.eval.Double)arg).getValue();
				isDouble = true;
			}
			tmp = ((ConsCell)tmp).getCdr();
		}
		return isDouble?lisp.eval.Double.valueOf(num):Int.valueOf((int)num);
	}
	
	public static Sub getInstance() {
		return sub;
	}
}
