package lisp.eval;

/**
 * 整数値
 * @author sam0830
 *
 */
public class Int implements SExpression{
	private Integer value;
	
	public Integer getValue() {
		return value;
	}

	public static Int valueOf(int value) {
		return new Int(value);
	}
	
	private Int(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
	
	@Override
	public int hashCode() {
		return this.value.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof Int) == false) {
			return false;
		}
		return this.value.equals(((Int)obj).getValue());
	}
}
