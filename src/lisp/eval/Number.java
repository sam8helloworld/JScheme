package lisp.eval;

/**
 * Number
 * @author sam0830
 *
 */
public interface Number extends SExpression{
	public Number add(Number number);
	public Number sub(Number number);
	public Number multiply(Number number);
	public Number divide(Number number);
}
