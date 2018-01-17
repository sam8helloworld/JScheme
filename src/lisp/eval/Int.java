package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 整数を表すS式
 * @author sam0830
 * @version 1.0
 */
public class Int implements SExpression, Number {
	private Integer value;
	
	/**
	 * int型の値を返す
	 * @return int型の変数
	 */
	public Integer getValue() {
		return value;
	}
	
	/**
	 * 整数型のS式のインスタンスを返す
	 * @param value 整数のS式の中身のdouble
	 * @return 整数型のS式のインスタンス
	 */
	public static Int valueOf(int value) {
		return new Int(value);
	}
	
	/**
	 * コンストラクタ
	 * @param value int型の整数
	 */
	private Int(Integer value) {
		this.value = value;
	}
	
	/**
	 * 余りを計算する
	 * @param number valueを割る数 
	 * @return valueをnumberで割った余り
	 */
	public Number modulo(Number number) {
		return valueOf(value%((Int)number).getValue());
	}
	
	@Override
	public Number add(Number number) {
		if(number instanceof Int) {
			return valueOf(value + ((Int)number).getValue());
		}
		return lisp.eval.Double.valueOf(value + ((lisp.eval.Double)number).getValue());
	}
	
	@Override
	public Number sub(Number number) {
		if(number instanceof Int) {
			return valueOf(value - ((Int)number).getValue());
		}
		return lisp.eval.Double.valueOf(value - ((lisp.eval.Double)number).getValue());
	}
	
	@Override
	public Number multiply(Number number) {
		if(number instanceof Int) {
			return valueOf(value * ((Int)number).getValue());
		}
		return lisp.eval.Double.valueOf(value * ((lisp.eval.Double)number).getValue());
	}
	
	@Override
	public Number divide(Number number) throws LispException {
		if(number instanceof Int) {
			if(((Int)number).getValue() == 0) {
				throw new ArgumentException("attempt to calculate a division by zero");
			}
			return lisp.eval.Double.valueOf(value / (double)((Int)number).getValue());
		}
		if(((lisp.eval.Double)number).getValue() == 0) {
			throw new ArgumentException("attempt to calculate a division by zero");
		}
		return lisp.eval.Double.valueOf(value / ((lisp.eval.Double)number).getValue());
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
		if ((obj instanceof Int) == false) {
			return false;
		}
		return this.value.equals(((Int)obj).getValue());
	}
}
