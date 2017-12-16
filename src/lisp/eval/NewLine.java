package lisp.eval;

/**
 * NewLine(/改行)
 * @author sam0830
 *
 */
public class NewLine implements Subroutine {
	private static final NewLine newLine = new NewLine();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		/**
		 * 第一引数は出力ポート(省略の場合は標準出力)
		 * 今回は出力ポートの指定は無し
		 */
		if(sexp instanceof EmptyList) {
			System.out.println("");
			return Undef.getInstance();
		}
		// エラー
		throw new RuntimeException("");
	}
	
	public static NewLine getInstance() {
		return newLine;
	}
}
