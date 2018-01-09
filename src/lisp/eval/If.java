package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 組み込み手続きif
 * @author sam0830
 * @version 1.0
 */
public class If implements SpecialForm {
	private static final If lispIf = new If();
	
	/**
	 * Ifのインスタンスを返す
	 * @return 組み込み手続きIf
	 */
	public static If getInstance() {
		return lispIf;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		ConsCell.ListBuilder errorListBuilder = ConsCell.builder();
		errorListBuilder.tail(Symbol.getInstance("if"));
		if(!(sexp instanceof ConsCell)) {
			throw new SyntaxErrorException("malformed if: "+errorListBuilder.build().toString());
		}
		// 引数が3個ある時(predicate then else)
		// 第一引数の評価結果が#f以外ならthen
		// 第一引数の評価結果が#fならelse
		if(((ConsCell)sexp).size() == 3) {
			SExpression predicate = ((ConsCell)sexp).getCar();
			SExpression evaled = Evaluator.eval(predicate, environment);
			if(!(evaled instanceof Bool)) {
				
			}
			if(((Bool)evaled).isValid()) {
				// then
				SExpression thenExpression = ((ConsCell)((ConsCell)sexp).getCdr()).getCar();
				return Evaluator.eval(thenExpression, environment);
			}
			// else
			SExpression elseExpression = ((ConsCell)((ConsCell)((ConsCell)((ConsCell)sexp).getCdr())).getCdr()).getCar();
			return Evaluator.eval(elseExpression, environment);
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
		errorListBuilder.last(sexp);
		throw new SyntaxErrorException("malformed if: "+errorListBuilder.build().toString());
	}
	
	@Override 
	public String toString() {
		return "#<syntax if>";
	}
}
