package lisp.eval;

/**
 * LispString(/文字列)
 * @author sam0830
 *
 */
public class LispString implements SExpression {
	private String string;
	
	public String getString() {
		return string;
	}
	
	public static LispString getInstance(String string){
		return new LispString(string);
	}

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
