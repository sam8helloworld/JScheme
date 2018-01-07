package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 組み込み手続きdisplay
 * 人に読みやすい形式で出力
 * @author sam0830
 * @version 1.0
 */
public class Display implements Subroutine {
	private static final Display display = new Display();
	
	/**
	 * Displayのインスタンスを返す
	 * @return 組み込み手続きDisplay
	 */
	public static Display getInstance() {
		return display;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		/**
		 * 第一引数はS式
		 * 第二引数は出力ポート(省略の場合は標準出力)
		 * 今回は出力ポートの指定は無し
		 */
		if(sexp instanceof EmptyList) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got 0)");
		}
		// 引数の個数が1個でない時
		int size = ((ConsCell)sexp).size();
		if(size != 1) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got "+size+")");
		}
		// 引数の個数が1個の時
		SExpression s = ((ConsCell)sexp).getCar();
		if(s instanceof LispString) {
			System.out.print(((LispString)s).getString());
		} else {
			System.out.print(s);
		}
		return Undef.getInstance();
	}
	
	@Override
	public String toString() {
		return "#<subr display>";
	}
}
