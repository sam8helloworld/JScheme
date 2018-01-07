package lisp.eval;
/**
 * 空リストを表すS式
 * @author sam0830
 * @version 1.0
 */
public class EmptyList implements SExpression {
	private static final EmptyList emptyList = new EmptyList();
	
	/**
	 * EmptyListのインスタンスを返す
	 * @return 組み込み手続きEmptyList
	 */
	public static EmptyList getInstance() {
		return emptyList;
	}

	@Override
	public String toString() {
		return "()";
	}
}
