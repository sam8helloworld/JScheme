package lisp.eval;

import lisp.exception.LispException;

/**
 * 特殊形式を表すインタフェース
 * @author sam0830
 * @version 1.0
 */
interface SpecialForm extends SExpression {
	public SExpression apply(SExpression sexp, Environment environment) throws LispException;
}
