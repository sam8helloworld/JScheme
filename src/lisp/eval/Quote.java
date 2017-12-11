package lisp.eval;

/**
 * Quote
 * @author sam0830
 *
 */
public class Quote implements SpecialForm {
	private static final Quote quote = new Quote();
	public SExpression apply(SExpression sexp, Environment environment) {
		// quote 
		// 評価せずにそのまま返す
		return ((ConsCell)sexp).getCar();
	}
	
	
	public static Quote getInstance() {
		return quote;
	}
}
