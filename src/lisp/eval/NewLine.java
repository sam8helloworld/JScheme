package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 組み込み手続きnewline
 * 改行を行う
 * @author sam0830
 * @version 1.0
 */
public class NewLine implements Subroutine {
	private static final NewLine newLine = new NewLine();
	
	/**
	 * newLineのインスタンスを返す
	 * @return 組み込み手続きNewLine
	 */
	public static NewLine getInstance() {
		return newLine;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		/**
		 * 第一引数は出力ポート(省略の場合は標準出力)
		 * 今回は出力ポートの指定は無し
		 */
		if(sexp instanceof EmptyList) {
			System.out.println("");
			return Undef.getInstance();
		}
		// エラー
		throw new ArgumentException("output port required, but got "+sexp);
	}
	
	@Override
	public String toString() {
		return "#<subr newline>";
	}
}
