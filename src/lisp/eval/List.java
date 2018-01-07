package lisp.eval;

/**
 * 組み込み手続きlist
 * @author sam0830
 * @version 1.0
 */
public class List implements Subroutine {
	private static final List list = new List();
	
	/**
	 * Listのインスタンスを返す
	 * @return 組み込み手続きList
	 */
	public static List getInstance() {
		return list;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) {
		return sexp;
	}
	
	@Override
	public String toString() {
		return "#<subr list>";
	}
}
