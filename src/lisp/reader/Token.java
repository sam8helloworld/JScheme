package lisp.reader;

/**
 * 軸の種類
 * @author sam0830
 *
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
	
	Token(Kind kind) {
		this.kind = kind;
	}
	Token(int value) {
		this.kind = Kind.INTEGER;
		this.intValue = value;
	}
	Token(double value) {
		this.kind = Kind.REALNUMBER;
		this.doubleValue = value;
	}
	Token(String value, int string) {
		this.kind = Kind.STRING;
		this.stringValue = value;
	}
	Token(boolean value) {
		this.kind = Kind.BOOLEAN;
		this.booleanValue = value;
	}
	Token(String value) {
		this.kind = Kind.SYMBOL;
		this.symbol = value;
	}
	
	Kind getKind() {
		return this.kind;
	}
	
	int getIntValue() {
		return this.intValue;
	}
	
	double getDoubleValue() {
		return this.doubleValue;
	}
	
	String getStringValue() {
		return this.stringValue;
	}
	
	boolean getBooleanValue() {
		return this.booleanValue;
	}
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
