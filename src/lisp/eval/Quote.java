package lisp.eval;

/**
 * Quote
 * @author sam0830
 *
 */
public class Quote implements SpecialForm {
	private static final Quote quote = new Quote();
	public SExpression apply(SExpression sexp, Environment environment) {
		//TODO:quote
		if(sexp instanceof ConsCell) {
			((ConsCell) sexp).getCar();
		}
		return sexp;
	}
	
	
	public static Quote getInstance() {
		return quote;
	}
}
