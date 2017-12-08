package lisp.eval;

/**
 * 評価器
 * @author sam0830
 *
 */
public class Evaluator {
	/**
	 * 引数の環境の下で引数のS式を評価する。
	 * @param sexp S式
	 * @param env 環境
	 * @return 評価値(S式)
	 */
	public static SExpression eval(SExpression sexp, Environment env) {
		//sexpが整数(/Int)の時
		if(sexp instanceof Int) {
			return sexp;
		}
		return sexp;
	}
}
