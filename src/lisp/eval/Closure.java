package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * Closure
 * @author sam0830
 *
 */
public class Closure implements SExpression {	
	private SExpression body;
	private SExpression params;
	
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 環境をもうひとつ作成
		Environment env = new Environment(environment);
		// 実引数と仮引数の個数が対応しているか確認
		// 仮引数が空リストの時
		// 仮引数がリストの時
		// 仮引数がシンボルの時
		if(params instanceof EmptyList) {
			if(!(sexp instanceof EmptyList)) {
				throw new ArgumentException("wrong number of arguments for "+this+" (required 0, got 1)");
			}
		}
		if(params instanceof ConsCell) {
			int requiredSize = ((ConsCell)params).size();
			int actualSize = ((ConsCell)sexp).size();
			if(requiredSize != actualSize) {
				throw new ArgumentException("wrong number of arguments for "+this+" (required "+requiredSize+", got "+actualSize+")");
			}
			for(int i=0;i<requiredSize;i++) {
				SExpression symbol = ((ConsCell)params).get(i);
				if(!(symbol instanceof Symbol)) {
					throw new ArgumentException("symbol required: "+symbol);
				}
				env.define((Symbol)symbol, ((ConsCell)sexp).get(i));
			}
		}
		if(params instanceof Symbol) {
			// 実引数をまとめてリストにする
			if(sexp instanceof ConsCell) {
				ConsCell.ListBuilder listBuilder = ConsCell.builder();
				for(int i=0;i<((ConsCell)sexp).size();i++) {
					listBuilder.tail(((ConsCell)sexp).get(i));
				}
				sexp = listBuilder.build();
			}
			env.define((Symbol)params, sexp);
		}
		return Evaluator.eval(this.body, env);
	}
	@Override
	public String toString() {
		return "#<closure>";
	}
	private Closure(SExpression body, SExpression params) {
		this.body = body;
		this.params = params;
	}
	
	public static Closure getInstance(SExpression body, SExpression params) {
		return new Closure(body, params);
	} 
}
