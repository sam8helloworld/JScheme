package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * ClosureのS式を表す
 * lamda式の評価結果として生成される
 * @author sam0830
 * @version 1.0
 */
public class Closure implements SExpression {	
	private SExpression body;
	private SExpression params;
	private Environment environment;
	
	/**
	 * Closureのインスタンスを返す
	 * @return S式Closure
	 */
	public static Closure getInstance(SExpression body, SExpression params, Environment env) {
		return new Closure(body, params, env);
	} 
	
	/**
	 * Closureのコンストラクタ
	 * @param body 手続き本体
	 * @param params 仮引数
	 * @param env 評価時の環境
	 */
	private Closure(SExpression body, SExpression params, Environment env) {
		this.body = body;
		this.params = params;
		this.environment = env;
	}
	
	/**
	 * 
	 * @param sexp 引数
	 * @param environment 実行時の環境
	 * @return Closureの評価値
	 * @throws LispException 引数が予期しない型の時
	 */
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {	
		// 環境をもうひとつ作成
		Environment env = new Environment(this.environment);
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
					throw new ArgumentException("symbol required, but got "+symbol);
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
		
		for(int i=0;i<((ConsCell)this.body).size();i++) {
			SExpression s = ((ConsCell)this.body).get(i);
			if(i == ((ConsCell)this.body).size()-1) {
				return Evaluator.eval(s, env);
			}
			Evaluator.eval(s, env);
		}
		return Evaluator.eval(this.body, env);
	}
	
	@Override
	public String toString() {
		return "#<closure>";
	}
}
