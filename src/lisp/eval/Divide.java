package lisp.eval;

/**
 * Multiply (乗法)
 * @author sam0830
 *
 */
public class Divide implements Subroutine {
 private static final Divide divide = new Divide();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		java.lang.Double num = ((lisp.eval.Double)((ConsCell)sexp).getCar()).getValue();
		SExpression s = sexp;
		while(true) {
			if(((ConsCell)s).getCdr() instanceof EmptyList) {
				num /= ((lisp.eval.Double)((ConsCell)s).getCar()).getValue();
				break;
			}
			num /= ((lisp.eval.Double)((ConsCell)s).getCar()).getValue();
			s = ((ConsCell)s).getCdr(); 
		}
		return lisp.eval.Double.valueOf(num);
	}
	
	public static Divide getInstance() {
		return divide;
	}
}
