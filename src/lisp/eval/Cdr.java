package lisp.eval;

/**
 * Cdr
 * @author sam0830
 *
 */
public class Cdr implements Subroutine {
	private static final Cdr cdr = new Cdr();
	public SExpression apply(SExpression sexp, Environment environment) {
		// cdr
		// ConsCellが渡されなければエラー
		// ConsCellのサイズが1でなければエラー
		// 引数がConsCellでなければエラー
		// ConsCellが渡されてていればcdrを返す
		if(!(sexp instanceof ConsCell)) {
			throw new RuntimeException("*** ERROR: pair required, but got "+sexp);
		}
		if(((ConsCell)sexp).size() != 1) {
			throw new RuntimeException("*** ERROR: pair required, but got "+sexp);
		}
		SExpression arg = ((ConsCell)sexp).get(0); // 第一引数
		if(!(arg instanceof ConsCell)) {
			throw new RuntimeException("*** ERROR: pair required, but got "+sexp);
		}
		return ((ConsCell)arg).getCdr();
	}
	
	
	public static Cdr getInstance() {
		return cdr;
	}
}
