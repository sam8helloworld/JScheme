package lisp.eval;

/**
 * Undef(/未定義)を表すS式
 * displayやwriteでしか用いられない
 * @author sam0830
 * @version 1.0
 */
public class Undef implements SExpression {
	private static final Undef undef = new Undef();
	
	/**
	 * Undefのインスタンスを返す
	 * @return 組み込み手続きUndef
	 */
	public static Undef getInstance() {
		return undef;
	}
	
	@Override
	public String toString() {
		return "#<undef>";
	}
}
