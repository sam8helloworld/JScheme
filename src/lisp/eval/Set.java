package lisp.eval;

/**
 * Set!
 * @author sam0830
 *
 */
public class Set implements SpecialForm {
	private static final Set set = new Set();
	public static Set getInstance() {
		return set;
	}
	@Override
	public SExpression apply(SExpression sexp, Environment environment) {
		// 第一引数はSymbol
		// 第一引数のSymbolが環境にあるとき第二引数を評価して代入
		if(!(sexp instanceof ConsCell)) {
			// エラー
		}
		if(((ConsCell)sexp).size() != 2) {
			// エラー
		}
		SExpression symbol = ((ConsCell)sexp).getCar();
		if(!(symbol instanceof Symbol)) {
			// エラー
		}
		SExpression exp = ((ConsCell)sexp).get(1);
		environment.set((Symbol)symbol, Evaluator.eval(exp, environment));
		return exp;
	}
	
}
