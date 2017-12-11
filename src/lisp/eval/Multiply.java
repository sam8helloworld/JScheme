package lisp.eval;

/**
 * Multiply (乗法)
 * @author sam0830
 *
 */
public class Multiply implements Subroutine {
	private static final Multiply multiply = new Multiply();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		Integer num = 1;
		SExpression s = sexp;
		while(true) {
			if(((ConsCell)s).getCdr() instanceof EmptyList) {
				num *= ((Int)((ConsCell)s).getCar()).getValue();
				break;
			}
			num *= ((Int)((ConsCell)s).getCar()).getValue();
			s = ((ConsCell)s).getCdr(); 
		}
		return Int.valueOf(num);
	}
	
	public static Multiply getInstance() {
		return multiply;
	}
}
