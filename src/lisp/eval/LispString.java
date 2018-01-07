package lisp.eval;

/**
 * 文字列を表すS式
 * @author sam0830
 * @version 1.0
 */
public class LispString implements SExpression {
	private String string;
	
	/**
	 * 文字列をString型で取得
	 * @return 文字列
	 */
	public String getString() {
		return string;
	}
	
	/**
	 * LispStringのインスタンスを返す
	 * @return 組み込み手続きLispString
	 */
	public static LispString getInstance(String string){
		return new LispString(string);
	}
	
	/**
	 * LispStringのコンストラクタ
	 * @param string 文字列
	 */
	private LispString(String string) {
		this.string = string;
	}
	
	@Override
	public String toString() {
		return "\""+this.string+"\"";
	}
	
	@Override
	public int hashCode() {
		return this.string.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LispString) {
			return this.string.equals(((LispString)obj).getString());
		}
		return false;
	}
}
