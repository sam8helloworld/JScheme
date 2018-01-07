package lisp.reader;

/**
 * 字句の種類
 * @author sam0830
 * @version 1.0
 */
public class Token {
	public enum Kind {
		INTEGER,
		REALNUMBER,
		STRING,
		BOOLEAN,
		SYMBOL,
		LEFTPAR,
		RIGHTPAR,
		DOT,
		QUOTE
	}
	
	private Kind kind;
	private int intValue;
	private double doubleValue;
	private String stringValue;
	private boolean booleanValue;
	private String symbol;
	
	/**
	 * コンストラクタ
	 * 指定された種類のトークンを生成する
	 * @param kind コンストラクタの種類
	 */
	Token(Kind kind) {
		this.kind = kind;
	}
	
	/**
	 * コンストラクタ
	 * 整数のトークンを生成する
	 * @param value 整数
	 */
	Token(int value) {
		this.kind = Kind.INTEGER;
		this.intValue = value;
	}
	
	/**
	 * コンストラクタ
	 * 実数のトークンを生成する
	 * @param value 実数
	 */
	Token(double value) {
		this.kind = Kind.REALNUMBER;
		this.doubleValue = value;
	}
	
	/**
	 * コンストラクタ
	 * 文字列のトークンを生成する
	 * @param value 文字列
	 * @param string シンボルとの区別用のダミー引数
	 */
	Token(String value, int string) {
		this.kind = Kind.STRING;
		this.stringValue = value;
	}
	
	/**
	 * コンストラクタ
	 * 真理値のトークンを生成する
	 * @param value 真理値
	 */
	Token(boolean value) {
		this.kind = Kind.BOOLEAN;
		this.booleanValue = value;
	}
	
	/**
	 * コンストラクタ
	 * シンボルのトークンを生成する
	 * @param value シンボルの文字列
	 */
	Token(String value) {
		this.kind = Kind.SYMBOL;
		this.symbol = value;
	}
	
	/**
	 * getter
	 * @return トークンの種類
	 */
	Kind getKind() {
		return this.kind;
	}
	
	/**
	 * getter
	 * @return 整数
	 */
	int getIntValue() {
		return this.intValue;
	}
	
	/**
	 * getter
	 * @return 実数
	 */
	double getDoubleValue() {
		return this.doubleValue;
	}
	
	/**
	 * getter
	 * @return 文字列
	 */
	String getStringValue() {
		return this.stringValue;
	}
	
	/**
	 * getter
	 * @return 真理値
	 */
	boolean getBooleanValue() {
		return this.booleanValue;
	}
	
	/**
	 * getter
	 * @return シンボルの文字列
	 */
	String getSymbolValue() {
		return this.symbol;
	}
	
	@Override
	public String toString() {
		// 整数
		if (this.kind == Kind.INTEGER) {
			return "Token (Integer, " + this.intValue + ")";
		}
		// 実数
		if (this.kind == Kind.REALNUMBER) {
			return "Token (RealNumber, " + this.doubleValue + ")";
		}
		// 文字列
		if (this.kind == Kind.STRING) {
			return "Token (String, " + this.stringValue + ")";
		}
		// 真理値
		if (this.kind == Kind.BOOLEAN) {
			return "Token (Boolean, " + this.booleanValue + ")";
		}
		// 記号
		if (this.kind == Kind.SYMBOL) {
			return "Token (Symbol, " + this.symbol + ")";
		}
		// 左括弧
		if (this.kind == Kind.LEFTPAR) {
			return "Token (LeftPar)";
		}
		// 右括弧
		if (this.kind == Kind.RIGHTPAR) {
			return "Token (RightPar)";
		}
		// ドット
		if (this.kind == Kind.DOT) {
			return "Token (Dot)";
		}
		// '(クォート)
		if (this.kind == Kind.QUOTE) {
			return "Token (Quote)";
		}
		return "Token (Unknown)";
	}
}
