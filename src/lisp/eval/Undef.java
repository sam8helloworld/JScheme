package lisp.eval;

/**
 * Undef(/未定義)
 * @author sam0830
 *
 */
public class Undef implements SExpression {
	private static final Undef undef = new Undef();
	
	public static Undef getInstance() {
		return undef;
	}
	@Override
	public String toString() {
		return "#<undef>";
	}
}
