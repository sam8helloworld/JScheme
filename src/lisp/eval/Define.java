package lisp.eval;

/**
 * List
 * @author sam0830
 *
 */
public class Define implements SpecialForm {
	private static final Define define = new Define();
	public SExpression apply(SExpression sexp, Environment environment) {
		SExpression s1 = ((ConsCell)sexp).getCar();
		SExpression s2 = ((ConsCell)((ConsCell)sexp).getCdr()).getCar();
		environment.define(Symbol.getInstance(((Symbol)s1).getName()), s2);
		return Symbol.getInstance((((ConsCell)sexp).getCar()).toString());
	}
	
	
	public static Define getInstance() {
		return define;
	}
}
