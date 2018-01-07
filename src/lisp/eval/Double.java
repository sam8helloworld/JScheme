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
	
	/**
	 * 引数に与えられた数値との加算を行う
	 * Int同士の場合はIntを返す
	 * doubleが計算に含まれている場合はDoubleを返す
	 * @param 加算する数値
	 * @return 加算後の数値
	 */
	public Number add(Number number) {
		if(number instanceof Int) {
			return valueOf(value + ((Int)number).getValue());
		}
		return valueOf(value + ((lisp.eval.Double)number).getValue());
	}
	
	/**
	 * 引数に与えられた数値との減算を行う
	 * Int同士の場合はIntを返す
	 * doubleが計算に含まれている場合はDoubleを返す
	 * @param 減算する数値
	 * @return 減算後の数値
	 */
	public Number sub(Number number) {
		if(number instanceof Int) {
			return valueOf(value - ((Int)number).getValue());
		}
		return valueOf(value - ((lisp.eval.Double)number).getValue());
	}
	
	/**
	 * 引数に与えられた数値との乗算を行う
	 * Int同士の場合はIntを返す
	 * doubleが計算に含まれている場合はDoubleを返す
	 * @param 乗算する数値
	 * @return 乗算後の数値
	 */
	public Number multiply(Number number) {
		if(number instanceof Int) {
			return valueOf(value * ((Int)number).getValue());
		}
		return valueOf(value * ((lisp.eval.Double)number).getValue());
	}
	
	/**
	 * 引数に与えられた数値との除算を行う
	 * Int同士の場合はIntを返す
	 * doubleが計算に含まれている場合はDoubleを返す
	 * @param 除算する数値
	 * @return 除算後の数値
	 */
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
	
	/**
	 * 引数に与えられた数値との大小比較を行う
	 * 引数で与えられた数値のほうが小さい時true
	 * 引数で与えられた数値のほうが小さくない時false
	 * @param 比較対象の数値
	 * @return 比較結果の真理値
	 */
	public boolean lessThan(Number number) {
		double comparedNumber = (number instanceof Int)?((Int)number).getValue():((lisp.eval.Double)number).getValue();
		return (value < comparedNumber)?true:false;
	}
	
	/**
	 * 引数に与えられた数値との大小比較を行う
	 * 引数で与えられた数値のほうが大きい時true
	 * 引数で与えられた数値のほうが大きくない時false
	 * @param 比較対象の数値
	 * @return 比較結果の真理値
	 */
	public boolean greaterThan(Number number) {
		double comparedNumber = (number instanceof Int)?((Int)number).getValue():((lisp.eval.Double)number).getValue();
		return (value > comparedNumber)?true:false;
	}
	
	/**
	 * 引数に与えられた数値との大小比較を行う
	 * 引数で与えられた数値のほうが数値以下時true
	 * 引数で与えられた数値のほうが数値以下でない時false
	 * @param 比較対象の数値
	 * @return 比較結果の真理値
	 */
	public boolean lessThanOrEqual(Number number) {
		double comparedNumber = (number instanceof Int)?((Int)number).getValue():((lisp.eval.Double)number).getValue();
		return (value <= comparedNumber)?true:false;
	}
	
	/**
	 * 引数に与えられた数値との大小比較を行う
	 * 引数で与えられた数値のほうが数値以上時true
	 * 引数で与えられた数値のほうが数値以上でない時false
	 * @param 比較対象の数値
	 * @return 比較結果の真理値
	 */
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
