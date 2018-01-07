package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * ConsCellのcdrを取得する組み込み手続き
 * @author sam0830
 * @version 1.0
 */
public class Cdr implements Subroutine {
	private static final Cdr cdr = new Cdr();
	
	/**
	 * Cdrのインスタンスを返す
	 * @return 組み込み手続きCdr
	 */
	public static Cdr getInstance() {
		return cdr;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// cdr
		// ConsCellが渡されなければエラー
		// ConsCellのサイズが1でなければエラー
		// 引数がConsCellでなければエラー
		// ConsCellが渡されてていればcdrを返す
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("pair required, but got "+sexp);
		}
		int size = ((ConsCell)sexp).size();
		if(size != 1) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got "+size+")");
		}
		SExpression arg = ((ConsCell)sexp).get(0); // 第一引数
		if(!(arg instanceof ConsCell)) {
			throw new ArgumentException("pair required, but got "+arg);
		}
		return ((ConsCell)arg).getCdr();
	}
	
	@Override
	public String toString() {
		return "<subr cdr>";
	}
}
