package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 特殊形式lambda
 * 評価されるとClosureを返す
 * @author sam0830
 * @version 1.0
 */
public class Lambda implements SpecialForm {
	private static final Lambda lambda = new Lambda();
	
	/**
	 * lambdaのインスタンスを返す
	 * @return 組み込み手続きLambda
	 */
	public static Lambda getInstance() {
		return lambda;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		ConsCell.ListBuilder errorListBuilder = ConsCell.builder();
		errorListBuilder.tail(Symbol.getInstance("lambda"));
		if(!(sexp instanceof ConsCell)) {
			throw new SyntaxErrorException("malformed lambda: "+errorListBuilder.build().toString());
		}
		if(((ConsCell)sexp).size() != 2) {
			errorListBuilder.last(sexp);
			throw new SyntaxErrorException("malformed lambda: "+errorListBuilder.build().toString());
		}
		SExpression params = ((ConsCell)sexp).get(0); // 引数
		SExpression body = ((ConsCell)sexp).get(1); // 手続き本体
		
		return Closure.getInstance(body, params);
	}

	@Override 
	public String toString() {
		return "#<syntax lambda>";
	}
}
