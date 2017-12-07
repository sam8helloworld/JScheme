package lisp.eval;

/**
 * Cons cell (ドット対)
 * @author sam0830
 *
 */
public class ConsCell implements SExpression{
	private SExpression car;
	private SExpression cdr;

	public SExpression getCar() {
		return this.car;
	}
	public SExpression getCdr() {
		return this.cdr;
	}
	
	public static ConsCell getInstance(SExpression car, SExpression cdr) {
		return new ConsCell(car, cdr);
	}
	
	private ConsCell(SExpression car, SExpression cdr) {
		this.car = car;
		this.cdr = cdr;
	}
	
	@Override
	public String toString() {
		return "(" + this.car + " . " + this.cdr + ")";
	}
}
