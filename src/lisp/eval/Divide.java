package lisp.eval;

/**
 * Multiply (乗法)
 * @author sam0830
 *
 */
public class Divide implements Subroutine {
 private static final Divide divide = new Divide();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		java.lang.Double num = (((Int)((ConsCell)sexp).getCar()).getValue()).doubleValue();
		SExpression s = ((ConsCell)sexp).getCdr();
		while(true) {
			if(((ConsCell)s).getCdr() instanceof EmptyList) {
				num /= (((Int)((ConsCell)s).getCar()).getValue()).doubleValue();
				break;
			}
			num /= (((Int)((ConsCell)s).getCar()).getValue()).doubleValue();
			s = ((ConsCell)s).getCdr(); 
		}
		return lisp.eval.Double.valueOf(num);
	}
	
	public static Divide getInstance() {
		return divide;
	}
}
