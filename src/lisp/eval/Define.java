package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * Define
 * @author sam0830
 *
 */
public class Define implements SpecialForm {
	private static final Define define = new Define();
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 引数が2個ではない時エラー
		ConsCell.ListBuilder errorListBuilder = ConsCell.builder();
		errorListBuilder.tail(Symbol.getInstance("define"));
		if(!(sexp instanceof ConsCell)) {
			throw new SyntaxErrorException("malformed define: "+errorListBuilder.build().toString());
		}
		if(((ConsCell)sexp).size() != 2) {
			errorListBuilder.last(sexp);
			throw new SyntaxErrorException("malformed define: "+errorListBuilder.build().toString());
		}
		SExpression s1 = ((ConsCell)sexp).getCar();
		SExpression s2 = ((ConsCell)((ConsCell)sexp).getCdr()).getCar();
		// 第一引数がリスト
		if(s1 instanceof ConsCell) {
			ConsCell.ListBuilder listBuilderDefine = ConsCell.builder();
			ConsCell.ListBuilder listBuilderLambda = ConsCell.builder();
			SExpression name = ((ConsCell)s1).getCar();
			SExpression args = ((ConsCell)s1).getCdr();
			SExpression body = s2;
			listBuilderLambda.tail(Symbol.getInstance("lambda"));
			listBuilderLambda.tail(args);
			listBuilderLambda.tail(body);
			listBuilderDefine.tail(name);
			listBuilderDefine.tail(listBuilderLambda.build());
			SExpression s = listBuilderDefine.build();
			s1 = ((ConsCell)s).getCar();
			s2 = ((ConsCell)((ConsCell)s).getCdr()).getCar();
		}
		// 第一引数がSymbol
		SExpression s2Result = Evaluator.eval(s2, environment);
		environment.define((Symbol)s1, s2Result);
		return Symbol.getInstance(s1.toString());
	}
	
	public static Define getInstance() {
		return define;
	}
}
