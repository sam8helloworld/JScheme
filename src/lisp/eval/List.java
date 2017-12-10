package lisp.eval;

/**
 * List
 * @author sam0830
 *
 */
public class List implements Subroutine {
	private static final List list = new List();
	public SExpression apply(SExpression sexp, Environment environment) {
		// list
		return sexp;
		//throw new RuntimeException("*** ERROR: pair required, but got "+sexp);
	}
	
	
	public static List getInstance() {
		return list;
	}
}
