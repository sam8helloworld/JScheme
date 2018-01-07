package lisp.eval;

/**
 * 記号(/シンボル)を表すS式
 * @author sam0830
 * @version 1.0
 */
public class Symbol implements SExpression {
	private String name;
	
	/**
	 * getter
	 * シンボルの文字列を返す
	 * @return シンボルの文字列
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Symbolのインスタンスを返す
	 * @param name シンボルの文字列
	 * @return S式シンボル
	 */
	public static Symbol getInstance(String name){
		return new Symbol(name);
	}
	
	/**
	 * シンボルのコンストラクタ
	 * @param symbol シンボルの文字列
	 */
	private Symbol(String symbol) {
		this.name = symbol;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Symbol) {
			return this.name.equals(((Symbol)obj).getName());
		}
		return false;
	}
}
