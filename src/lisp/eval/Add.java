package lisp.eval;

/**
 * Add (加法)
 * @author sam0830
 *
 */
public class Add implements Subroutine {
	private static final Add add = new Add();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		double num = 0;
		SExpression tmp = sexp;
		boolean isDouble = false;;
		while(tmp instanceof ConsCell) {
			SExpression car = ((ConsCell)tmp).getCar();
			if(!(car instanceof Number)) {
				// 引数が数字でない時エラー
			}
			// 引数が数値
			if(car instanceof Int) {
				num += ((Int)car).getValue();
			}
			if(car instanceof lisp.eval.Double) {
				num += ((lisp.eval.Double)car).getValue();
				isDouble = true;
			}
			tmp = ((ConsCell)tmp).getCdr();
		}
		
		return isDouble?lisp.eval.Double.valueOf(num):Int.valueOf((int)num);
	}
	
	public static Add getInstance() {
		return add;
	}
}
