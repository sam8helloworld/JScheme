package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 特殊形式let
 * 内部的には評価時にlambdaの処理に変換される
 * @author sam0830
 *
 */
public class Let implements SpecialForm {
	private static final int BINDS_SIZE = 2;
	private static final int DAMMY_ARGUMENT_NUMBER = 0;
	private static final int ACTUAL_ARGUMENT_NUMBER = 1;
	private static final Let let = new Let();
	
	/**
	 * Letのインスタンスを返す
	 * @return 組み込み手続きLet
	 */
	public static Let getInstance() {
		return let;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		ConsCell.ListBuilder errorListBuilder = ConsCell.builder();
		errorListBuilder.tail(Symbol.getInstance("let"));
		if(!(sexp instanceof ConsCell)) {
			throw new SyntaxErrorException("malformed let: "+errorListBuilder.build().toString());
		}
		SExpression binds = ((ConsCell)sexp).getCar();
		SExpression body = EmptyList.getInstance();
		if(((ConsCell)sexp).getCdr() instanceof ConsCell) {
			body = ((ConsCell)sexp).getCdr();
		}
		if(!(binds instanceof ConsCell)) {
			errorListBuilder.last(sexp);
			throw new SyntaxErrorException("malformed let: "+errorListBuilder.build().toString());
		}
		int listSize = ((ConsCell)binds).size();
		ConsCell.ListBuilder dammyArg = ConsCell.builder();
		ConsCell.ListBuilder actualArg = ConsCell.builder();
		for(int i=0;i<listSize;i++) {
			SExpression list = ((ConsCell)binds).get(i);
			if(!(list instanceof ConsCell)) {
				errorListBuilder.last(sexp);
				throw new SyntaxErrorException("malformed let: "+errorListBuilder.build().toString());
			}
			if(((ConsCell)list).size() != BINDS_SIZE) {
				errorListBuilder.last(sexp);
				throw new SyntaxErrorException("malformed let: "+errorListBuilder.build().toString());
			}
			dammyArg.tail(((ConsCell)list).get(DAMMY_ARGUMENT_NUMBER));
			actualArg.tail(((ConsCell)list).get(ACTUAL_ARGUMENT_NUMBER));
		}
		ConsCell.ListBuilder listBuilder = ConsCell.builder();
		ConsCell.ListBuilder lambdaListBuilder = ConsCell.builder();
		lambdaListBuilder.tail(Symbol.getInstance("lambda"));
		lambdaListBuilder.tail(dammyArg.build());
		lambdaListBuilder.last(body);
		listBuilder.tail(lambdaListBuilder.build());
		listBuilder.last(actualArg.build());
		return Evaluator.eval(listBuilder.build(), environment);
	}
	
	@Override
	public String toString() {
		return "#<syntax let>";
	}
}
