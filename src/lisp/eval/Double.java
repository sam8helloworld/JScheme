package lisp.eval;

/**
 * 整数値
 * @author sam0830
 *
 */
public class Double implements SExpression, Number {
	private java.lang.Double value;
	
	public java.lang.Double getValue() {
		return value;
	}

	public static lisp.eval.Double valueOf(double value) {
		return new lisp.eval.Double(value);
	}
	
	private Double(java.lang.Double value) {
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
		if ((obj instanceof lisp.eval.Double) == false) {
			return false;
		}
		return this.value.equals(((lisp.eval.Double)obj).getValue());
	}
}
