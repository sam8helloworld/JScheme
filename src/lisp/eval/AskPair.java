package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 引数が空リスト以外のリストかどうかを判別する
 * コマンド名はpair?
 * @author sam0830
 * @version 1.0
 */
public class AskPair implements Subroutine {
	private static final AskPair askPair = new AskPair();
	
	/**
	 * AskPairのインスタンスを返す
	 * @return 組み込み手続きAskPair
	 */
	public static AskPair getInstance() {
		return askPair;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 引数が空リストかどうか
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got 0)");
		}
		// 引数の個数は1個
		int size = ((ConsCell)sexp).size();
		if(size != 1) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got "+size+")");
		}
		return (((ConsCell)sexp).get(0) instanceof ConsCell)?Bool.valueOf(true):Bool.valueOf(false);
	}
	
	@Override
	public String toString() {
		return "#<subr pair?>";
	}
}