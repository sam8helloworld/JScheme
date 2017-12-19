package lisp.eval;

/**
 * Add (加法)
 * @author sam0830
 *
 */
public class Add implements Subroutine {
	private static final Add add = new Add();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		Number number = Int.valueOf(0);
		SExpression tmp = sexp;
		while(tmp instanceof ConsCell) {
			SExpression car = ((ConsCell)tmp).getCar();
			if(!(car instanceof Number)) {
				// 引数が数字でない時エラー
			}
			// 引数が数値
			number = number.add((Number)car);
			tmp = ((ConsCell)tmp).getCdr();
		}
		
		return number;
	}
	
	public static Add getInstance() {
		return add;
	}
}
