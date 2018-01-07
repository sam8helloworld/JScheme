package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 特殊形式set!
 * 束縛済みのシンボルに新たなS式を束縛する
 * @author sam0830
 * @version 1.0
 */
public class Set implements SpecialForm {
	private static final Set set = new Set();
	
	/**
	 * Setのインスタンスを返す
	 * @return 組み込み手続きSet
	 */
	public static Set getInstance() {
		return set;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 第一引数はSymbol
		// 第一引数のSymbolが環境にあるとき第二引数を評価して代入
		ConsCell.ListBuilder errorListBuilder = ConsCell.builder();
		errorListBuilder.tail(Symbol.getInstance("set!"));
		if(!(sexp instanceof ConsCell)) {
			throw new SyntaxErrorException("malformed set!: "+errorListBuilder.build().toString());
		}
		if(((ConsCell)sexp).size() != 2) {
			errorListBuilder.last(sexp);
			throw new SyntaxErrorException("malformed set!: "+errorListBuilder.build().toString());
		}
		SExpression symbol = ((ConsCell)sexp).getCar();
		if(!(symbol instanceof Symbol)) {
			errorListBuilder.last(sexp);
			throw new SyntaxErrorException("malformed set!: "+errorListBuilder.build().toString());
		}
		SExpression exp = ((ConsCell)sexp).get(1);
		environment.set((Symbol)symbol, Evaluator.eval(exp, environment));
		return exp;
	}
	
	@Override
	public String toString() {
		return "#<syntax set!>";
	}
}
