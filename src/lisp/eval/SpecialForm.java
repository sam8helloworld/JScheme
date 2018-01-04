package lisp.eval;

import lisp.exception.LispException;

/**
 * Special form (特殊形式)
 * @author sam0830
 *
 */
interface SpecialForm extends SExpression {
	public SExpression apply(SExpression sexp, Environment environment) throws LispException;
}
