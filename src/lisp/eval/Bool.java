package lisp.eval;

/**
 * 真理値を表すS式
 * @author sam0830
 * @version 1.0
 */
public class Bool implements SExpression {
	private static final Bool TRUE = new Bool(true);
	private static final Bool FALSE = new Bool(false);

	private boolean value;
	
	/**
	 * Boolのメンバ変数valueの真理値を返す
	 * @return Boolのメンバ変数value
	 */
	public boolean isValid() {
		return value;
	}
	
	/**
	 * Boolのインスタンスを返す
	 * @param value 設定する真理値
	 * @return S式Bool
	 */
	public static Bool valueOf(boolean value) {
		if (value == true) {
			return TRUE;
		}
		return FALSE;
	}
	
	/**
	 * 真理値のsetter
	 * @param value 設定する真理値
	 */
	private Bool(boolean value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		if (this.value == true) {
			return "#t";
		}
		return "#f";
	}
}
