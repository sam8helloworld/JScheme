package lisp.eval;

/**
 * >
 * @author sam0830
 *
 */
public class GreaterThan implements Subroutine {
	private static final GreaterThan greaterThan = new GreaterThan();
	
	public static GreaterThan getInstance() {
		return greaterThan;
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
			if(!((Number)arg).greaterThan((Number)argNext)) {
				return Bool.valueOf(false);
			}
		}
		return Bool.valueOf(true);
	}

}
