package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.EndOfFileException;
import lisp.exception.LispException;

/**
 * Exit (終了)
 * エラー処理として終了する
 * @author sam0830
 * @version 1.0
 */
public class Exit implements Subroutine {
	private static final Exit exit = new Exit();
	
	/**
	 * Exitのインスタンスを返す
	 * @return 組み込み手続きExit
	 */
	public static Exit getInstance() {
		return exit;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 引数なしの時
		if(!(sexp instanceof ConsCell)) {
			throw new EndOfFileException();
		}
		// 引数1個以上(0か正の整数)
		int size = ((ConsCell)sexp).size();
		SExpression code;
		if(size == 1) {
			code = ((ConsCell)sexp).get(0);
			if(code instanceof Int) {
				throw new EndOfFileException();
			}
			throw new ArgumentException("small integer required, but got "+code.toString());
		}
		// 引数2個以上
		SExpression fmtstr = ((ConsCell)sexp).get(1);
		code = ((ConsCell)sexp).get(0);
		if(code instanceof Int) {
			if(fmtstr instanceof LispString) {
				throw new EndOfFileException(((LispString)fmtstr).getString());
			}
			throw new EndOfFileException();
		}
		throw new ArgumentException("small integer required, but got "+code.toString());
	}
	
	@Override
	public String toString() {
		return "#<subr exit>";
	}
}
