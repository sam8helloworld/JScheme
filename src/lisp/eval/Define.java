package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 特殊形式define
 * @author sam0830
 * @version 1.0
 */
public class Define implements SpecialForm {
	private static final Define define = new Define();
	
	/**
	 * Defineのインスタンスを返す
	 * @return 組み込み手続きDefine
	 */
	public static Define getInstance() {
		return define;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 引数が2個ではない時エラー
		ConsCell.ListBuilder errorListBuilder = ConsCell.builder();
		errorListBuilder.tail(Symbol.getInstance("define"));
		if(!(sexp instanceof ConsCell)) {
			throw new SyntaxErrorException("malformed define: "+errorListBuilder.build().toString());
		}
		if(((ConsCell)sexp).size() < 2) {
			errorListBuilder.last(sexp);
			throw new SyntaxErrorException("malformed define: "+errorListBuilder.build().toString());
		}
		SExpression s1 = ((ConsCell)sexp).getCar();
		SExpression s2 = ((ConsCell)sexp).getCdr();
		// 第一引数がリスト
		if(s1 instanceof ConsCell) {
			ConsCell.ListBuilder listBuilderDefine = ConsCell.builder();
			ConsCell.ListBuilder listBuilderLambda = ConsCell.builder();
			SExpression name = ((ConsCell)s1).getCar();
			SExpression args = ((ConsCell)s1).getCdr();
			SExpression body = s2;
			listBuilderLambda.tail(Symbol.getInstance("lambda"));
			listBuilderLambda.tail(args);
			listBuilderLambda.last(body);
			listBuilderDefine.tail(name);
			listBuilderDefine.tail(listBuilderLambda.build());
			SExpression s = listBuilderDefine.build();
			SExpression symbol = ((ConsCell)s).getCar();
			SExpression lambda = ((ConsCell)((ConsCell)s).getCdr()).getCar();
			environment.define((Symbol)symbol, Evaluator.eval(lambda, environment));
			return symbol;
		}
		// 第一引数がSymbol
		s2 = ((ConsCell)s2).getCar();
		SExpression s2Result = Evaluator.eval(s2, environment);
		environment.define((Symbol)s1, s2Result);
		return Symbol.getInstance(s1.toString());
	}
	
	@Override
	public String toString() {
		return "#<syntax define>";
	}
}
