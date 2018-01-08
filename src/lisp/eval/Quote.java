package lisp.eval;

/**
 * 特殊形式quote
 * 引数を評価しない
 * @author sam0830
 * @version 1.0
 */
public class Quote implements SpecialForm {
	private static final Quote quote = new Quote();
	
	/**
	 * Quoteのインスタンスを返す
	 * @return 組み込み手続きQuote
	 */
	public static Quote getInstance() {
		return quote;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) {
		// quote 
		// 評価せずにそのまま返す
		return ((ConsCell)sexp).getCar();
	}
	
	@Override
	public String toString() {
		return "#<syntax quote>";
	}
}
