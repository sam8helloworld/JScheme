package lisp.eval;

/**
 * Sub routine (組み込み手続き)
 * @author sam0830
 *
 */
interface Subroutine extends SExpression {
	public SExpression apply(SExpression sexp, Environment environment);
}
