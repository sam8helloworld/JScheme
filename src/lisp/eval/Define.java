package lisp.eval;

/**
 * Define
 * @author sam0830
 *
 */
public class Define implements SpecialForm {
	private static final Define define = new Define();
	public SExpression apply(SExpression sexp, Environment environment) {
		SExpression s1 = ((ConsCell)sexp).getCar();
		SExpression s2 = ((ConsCell)((ConsCell)sexp).getCdr()).getCar();
		SExpression s2Result = Evaluator.eval(s2, environment);
		environment.define(Symbol.getInstance(s1.toString()), s2Result);
		return Symbol.getInstance((((ConsCell)sexp).getCar()).toString());
	}
	
	public static Define getInstance() {
		return define;
	}
}
