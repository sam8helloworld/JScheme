package lisp.eval;

/**
 * Write
 * @author sam0830
 *
 */
public class Write implements Subroutine {
	private static final Write write = new Write();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		/**
		 * 第一引数はS式
		 * 第二引数は出力ポート(省略の場合は標準出力)
		 * 今回は出力ポートの指定は無し
		 */
		if(sexp instanceof EmptyList) {
			// エラー
		}
		// 引数の個数が1個でない時
		if(((ConsCell)sexp).size() != 1) {
			// エラー
		}
		// 引数の個数が1個の時
		System.out.print(((ConsCell)sexp).getCar());
		return Undef.getInstance();
	}
	
	public static Write getInstance() {
		return write;
	}
}
