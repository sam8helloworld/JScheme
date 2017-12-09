package lisp.eval;

/**
 * List
 * @author sam0830
 *
 */
public class List implements SExpression {
	private SExpression car;
	private SExpression cdr;

	public SExpression getCar() {
		return this.car;
	}
	public SExpression getCdr() {
		return this.cdr;
	}
	
	public static List getInstance(SExpression car, SExpression cdr) {
		return new List(car, cdr);
	}
	
	private List(SExpression car, SExpression cdr) {
		this.car = car;
		this.cdr = cdr;
	}
	
	@Override
	public String toString() {
		return "(" + this.car + " " + this.cdr + ")";
	}
}
