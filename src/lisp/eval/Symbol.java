package lisp.eval;

/**
 * 記号
 * @author sam0830
 *
 */
public class Symbol implements SExpression {
	private String name;

	public String getName() {
		return name;
	}
	
	public static Symbol getInstance(String name){
		return new Symbol(name);
	}

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
