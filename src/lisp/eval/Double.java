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
	
	// Double型での加減乗除はすべてDoubleで帰る
	public Number add(Number number) {
		if(number instanceof Int) {
			return valueOf(value + ((Int)number).getValue());
		}
		return valueOf(value + ((lisp.eval.Double)number).getValue());
	}
	
	public Number sub(Number number) {
		if(number instanceof Int) {
			return valueOf(value - ((Int)number).getValue());
		}
		return valueOf(value - ((lisp.eval.Double)number).getValue());
	}
	
	public Number multiply(Number number) {
		if(number instanceof Int) {
			return valueOf(value * ((Int)number).getValue());
		}
		return valueOf(value * ((lisp.eval.Double)number).getValue());
	}
	
	public Number divide(Number number) {
		
		if(number instanceof Int) {
			if(((Int)number).getValue() == 0) {
				// エラー
			}
			return valueOf(value / ((Int)number).getValue());
		}
		if(((lisp.eval.Double)number).getValue() == 0) {
			// エラー
		}
		return valueOf(value / ((lisp.eval.Double)number).getValue());
	}
	
	public boolean lessThan(Number number) {
		double comparedNumber = (number instanceof Int)?((Int)number).getValue():((lisp.eval.Double)number).getValue();
		return (value < comparedNumber)?true:false;
	}
	
	public boolean greaterThan(Number number) {
		double comparedNumber = (number instanceof Int)?((Int)number).getValue():((lisp.eval.Double)number).getValue();
		return (value > comparedNumber)?true:false;
	}
	
	public boolean lessThanOrEqual(Number number) {
		double comparedNumber = (number instanceof Int)?((Int)number).getValue():((lisp.eval.Double)number).getValue();
		return (value <= comparedNumber)?true:false;
	}
	
	public boolean greaterThanOrEqual(Number number) {
		double comparedNumber = (number instanceof Int)?((Int)number).getValue():((lisp.eval.Double)number).getValue();
		return (value >= comparedNumber)?true:false;
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
