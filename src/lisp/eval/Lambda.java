package lisp.eval;

/**
 * Lambda
 * @author sam0830
 *
 */
public class Lambda implements SpecialForm {
	private static final Lambda lambda = new Lambda();

	public SExpression apply(SExpression sexp, Environment environment) {
		if(!(sexp instanceof ConsCell)) {
			throw new RuntimeException("引数の個数が違う");
		}
		if(((ConsCell)sexp).size() != 2) {
			throw new RuntimeException("引数の個数が違う");
		}
		SExpression params = ((ConsCell)sexp).get(0); // 引数
		SExpression body = ((ConsCell)sexp).get(1); // 手続き本体
		
		return Closure.getInstance(body, params);
	}

	public static Lambda getInstance() {
		return lambda;
	}
}
