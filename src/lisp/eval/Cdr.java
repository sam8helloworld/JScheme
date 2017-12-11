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
		// ConsCellが渡されてていればcarを返す
		if(sexp instanceof ConsCell) {
			return ((ConsCell)((ConsCell) sexp).getCar()).getCdr();
		}
		throw new RuntimeException("*** ERROR: pair required, but got "+sexp);
	}
	
	
	public static Cdr getInstance() {
		return cdr;
	}
}
