package lisp.reader;

/**
 * 軸の種類
 * @author sam0830
 *
 */
public class Token {
	public enum Kind {
		NUMBER,
		BOOLEAN,
		SYMBOL,
		LEFTPAR,
		RIGHTPAR,
		DOT
	}
	
	private Kind kind;
	private int intValue;
	private boolean booleanValue;
	private String symbol;
	
	Token(Kind kind) {
		this.kind = kind;
	}
	Token(int value) {
		this.kind = Kind.NUMBER;
		this.intValue = value;
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
	
	boolean getBooleanValue() {
		return this.booleanValue;
	}
	String getSymbolValue() {
		return this.symbol;
	}
	
	@Override
	public String toString() {
		// 数値
		if (this.kind == Kind.NUMBER) {
			return "Token (Number, " + this.intValue + ")";
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
		return "Token (Unknown)";
	}
}
