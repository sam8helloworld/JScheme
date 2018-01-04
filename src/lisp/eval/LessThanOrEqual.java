package lisp.eval;

/**
 * <=
 * @author sam0830
 *
 */
public class LessThanOrEqual implements Subroutine {
	private static final LessThanOrEqual lessThanOrEqual = new LessThanOrEqual();
	
	public static LessThanOrEqual getInstance() {
		return lessThanOrEqual;
	}
	@Override
	public SExpression apply(SExpression sexp, Environment environment) {
		// 引数が2個以上ないとエラー
		if(!(sexp instanceof ConsCell)) {
			throw new RuntimeException("引数");
		}
		int size = ((ConsCell)sexp).size();
		if(size < 2) {
			throw new RuntimeException("引数が2個より少ない");
		}
		for(int i=0;i<size-1;i++) {
			SExpression arg = ((ConsCell)sexp).get(i);
			SExpression argNext = ((ConsCell)sexp).get(i+1);
			if(!(arg instanceof Number) || !(argNext instanceof Number)) {
				throw new RuntimeException("比較対象が数値でない");
			}
			if(!((Number)arg).lessThanOrEqual((Number)argNext)) {
				return Bool.valueOf(false);
			}
		}
		return Bool.valueOf(true);
	}
}
