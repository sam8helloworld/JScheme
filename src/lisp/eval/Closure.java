package lisp.eval;

/**
 * Closure
 * @author sam0830
 *
 */
public class Closure implements SExpression {	
	private SExpression body;
	private SExpression params;
	
	public SExpression apply(SExpression sexp, Environment environment) {
		// 環境をもうひとつ作成
		Environment env = new Environment(environment);
		// 実引数と仮引数の個数が対応しているか確認
		// 仮引数が空リストの時
		// 仮引数がリストの時
		// 仮引数がシンボルの時
		if(params instanceof EmptyList) {
			if(!(sexp instanceof EmptyList)) {
				throw new RuntimeException("引数の個数が違う");
			}
		}
		if(params instanceof ConsCell) {
			if(((ConsCell)params).size() != ((ConsCell)sexp).size()) {
				throw new RuntimeException("引数の個数が違う");
			}
			for(int i=0;i<((ConsCell)params).size();i++) {
				SExpression symbol = ((ConsCell)params).get(i);
				if(!(symbol instanceof Symbol)) {
					throw new RuntimeException("引数の型がSymbolでない");
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
	
	private Closure(SExpression body, SExpression params) {
		this.body = body;
		this.params = params;
	}
	
	public static Closure getInstance(SExpression body, SExpression params) {
		return new Closure(body, params);
	} 
}
