package lisp.eval;

/**
 * Lambda
 * @author sam0830
 *
 */
public class Lambda implements SpecialForm {
	private static final Lambda lambda = new Lambda();

	public SExpression apply(SExpression sexp, Environment environment) {
		SExpression s1 = ((ConsCell)sexp).getCar(); // 引数
		SExpression s2 = ((ConsCell)((ConsCell)sexp).getCdr()).getCar(); // 手続き本体
		
		return Closure.getInstance(s2, s1, environment);
	}

	public static Lambda getInstance() {
		return lambda;
	}
}
