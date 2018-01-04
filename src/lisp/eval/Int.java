package lisp.eval;

/**
 * 整数値
 * @author sam0830
 *
 */
public class Int implements SExpression, Number {
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
	
	public Number add(Number number) {
		if(number instanceof Int) {
			return valueOf(value + ((Int)number).getValue());
		}
		return lisp.eval.Double.valueOf(value + ((lisp.eval.Double)number).getValue());
	}
	
	public Number sub(Number number) {
		if(number instanceof Int) {
			return valueOf(value - ((Int)number).getValue());
		}
		return lisp.eval.Double.valueOf(value - ((lisp.eval.Double)number).getValue());
	}
	
	public Number multiply(Number number) {
		if(number instanceof Int) {
			return valueOf(value * ((Int)number).getValue());
		}
		return lisp.eval.Double.valueOf(value * ((lisp.eval.Double)number).getValue());
	}
	
	public Number divide(Number number) {
		if(number instanceof Int) {
			if(((Int)number).getValue() == 0) {
				// エラー
			}
			return lisp.eval.Double.valueOf(value / (double)((Int)number).getValue());
		}
		if(((lisp.eval.Double)number).getValue() == 0) {
			// エラー
		}
		return lisp.eval.Double.valueOf(value / ((lisp.eval.Double)number).getValue());
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
		if ((obj instanceof Int) == false) {
			return false;
		}
		return this.value.equals(((Int)obj).getValue());
	}
}
