package lisp.eval;

/**
 * EqualAddress
 * @author sam0830
 *
 */
public class EqualAddress implements Subroutine {
	private static final EqualAddress equalAddress = new EqualAddress();
	/**
	 * 引数は2つのみ
	 * 引数が2つでないときエラー
	 */
	public SExpression apply(SExpression sexp, Environment environment) { 
		// 引数の個数が0個の時
		if(sexp instanceof EmptyList) {
			// エラー
		}
		if(((ConsCell)sexp).getSize() != 2) {
			// エラー
		}
		// 引数の個数が2個の時
		return ((ConsCell)sexp).get(0).equals(((ConsCell)sexp).get(1))?Bool.valueOf(true) : Bool.valueOf(false);
	}
	
	public static EqualAddress getInstance() {
		return equalAddress;
	}
}
