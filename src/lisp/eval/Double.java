package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 実数を表すS式
 * @author sam0830
 * @version 1.0
 */
public class Double implements SExpression, Number {
	private java.lang.Double value;
	
	/**
	 * double型の値を返す
	 * @return double型の変数
	 */
	public java.lang.Double getValue() {
		return value;
	}
	
	/**
	 * 実数型のS式のインスタンスを返す
	 * @param value 実数のS式の中身のdouble
	 * @return 実数型のS式のインスタンス
	 */
	public static lisp.eval.Double valueOf(double value) {
		return new lisp.eval.Double(value);
	}
	
	/**
	 * コンストラクタ
	 * @param value double型の実数
	 */
	private Double(java.lang.Double value) {
		this.value = value;
	}
	
	@Override
	public Number add(Number number) {
		if(number instanceof Int) {
			return valueOf(value + ((Int)number).getValue());
		}
		return valueOf(value + ((lisp.eval.Double)number).getValue());
	}
	
	@Override
	public Number sub(Number number) {
		if(number instanceof Int) {
			return valueOf(value - ((Int)number).getValue());
		}
		return valueOf(value - ((lisp.eval.Double)number).getValue());
	}
	
	@Override
	public Number multiply(Number number) {
		if(number instanceof Int) {
			return valueOf(value * ((Int)number).getValue());
		}
		return valueOf(value * ((lisp.eval.Double)number).getValue());
	}
	
	@Override
	public Number divide(Number number) throws LispException {
		if(number instanceof Int) {
			if(((Int)number).getValue() == 0) {
				throw new ArgumentException("attempt to calculate a division by zero");
			}
			return valueOf(value / ((Int)number).getValue());
		}
		if(((lisp.eval.Double)number).getValue() == 0) {
			throw new ArgumentException("attempt to calculate a division by zero");
		}
		return valueOf(value / ((lisp.eval.Double)number).getValue());
	}
	
	@Override
	public boolean lessThan(Number number) {
		double comparedNumber = (number instanceof Int)?((Int)number).getValue():((lisp.eval.Double)number).getValue();
		return (value < comparedNumber)?true:false;
	}
	
	@Override
	public boolean greaterThan(Number number) {
		double comparedNumber = (number instanceof Int)?((Int)number).getValue():((lisp.eval.Double)number).getValue();
		return (value > comparedNumber)?true:false;
	}
	
	@Override
	public boolean lessThanOrEqual(Number number) {
		double comparedNumber = (number instanceof Int)?((Int)number).getValue():((lisp.eval.Double)number).getValue();
		return (value <= comparedNumber)?true:false;
	}
	
	@Override
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
