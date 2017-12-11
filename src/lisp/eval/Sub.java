package lisp.eval;

/**
 * Add (加法)
 * @author sam0830
 *
 */
public class Sub implements Subroutine{
	private static final Sub sub = new Sub();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		Integer num = ((Int)((ConsCell)sexp).getCar()).getValue();
		SExpression s = ((ConsCell)sexp).getCdr();
		while(true) {
			if(((ConsCell)s).getCdr() instanceof EmptyList) {
				num -= ((Int)((ConsCell)s).getCar()).getValue();
				break;
			}
			num -= ((Int)((ConsCell)s).getCar()).getValue();
			s = ((ConsCell)s).getCdr(); 
		}
		return Int.valueOf(num);
	}
	
	public static Sub getInstance() {
		return sub;
	}
}
