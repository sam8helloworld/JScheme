package lisp.eval;

import lisp.exception.LispException;

/**
 * If
 * @author sam0830
 *
 */
public class If implements SpecialForm {
	private static final If lispIf = new If();
	public static If getInstance() {
		return lispIf;
	}
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		if(!(sexp instanceof ConsCell)) {
			// TODO エラー
		}
		// 引数が3個ある時(predicate then else)
		// 第一引数の評価結果が#f以外ならthen
		// 第一引数の評価結果が#fならelse
		if(((ConsCell)sexp).size() == 3) {
			SExpression predicate = ((ConsCell)sexp).getCar();
			SExpression evaled = Evaluator.eval(predicate, environment);
			if(evaled instanceof Bool) {
				if(!((Bool)evaled).isValid()) {
					// else
					SExpression elseExpression = ((ConsCell)((ConsCell)((ConsCell)((ConsCell)sexp).getCdr())).getCdr()).getCar();
					return Evaluator.eval(elseExpression, environment);
				}
			}
			// then
			SExpression thenExpression = ((ConsCell)((ConsCell)sexp).getCdr()).getCar();
			return Evaluator.eval(thenExpression, environment);
		}
		
		// 引数が2個ある時(predicate then )
		// 第一引数の評価結果が#f以外ならthen
		// 第一引数の評価結果が#fならUndef
		if(((ConsCell)sexp).size() == 2) {
			SExpression predicate = ((ConsCell)sexp).getCar();
			SExpression evaled = Evaluator.eval(predicate, environment);
			if(evaled instanceof Bool) {
				if(!((Bool)evaled).isValid()) {
					// else
					return Undef.getInstance();
				}
			}
			// then
			SExpression thenExpression = ((ConsCell)((ConsCell)sexp).getCdr()).getCar();
			return Evaluator.eval(thenExpression, environment);
		}
		// エラー
		return null;
	}
}
