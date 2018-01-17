package lisp.eval;

import lisp.exception.LispException;

/**
 * 数値を表すS式のインタフェース
 * @author sam0830
 * @version 1.0
 */
public interface Number extends SExpression{
	/**
	 * 引数に与えられた数値との加算を行う
	 * Int同士の場合はIntを返す
	 * doubleが計算に含まれている場合はDoubleを返す
	 * @param 加算する数値
	 * @return 加算後の数値
	 */
	public Number add(Number number);
	
	/**
	 * 引数に与えられた数値との減算を行う
	 * Int同士の場合はIntを返す
	 * doubleが計算に含まれている場合はDoubleを返す
	 * @param 減算する数値
	 * @return 減算後の数値
	 */
	public Number sub(Number number);
	
	/**
	 * 引数に与えられた数値との乗算を行う
	 * Int同士の場合はIntを返す
	 * doubleが計算に含まれている場合はDoubleを返す
	 * @param 乗算する数値
	 * @return 乗算後の数値
	 */
	public Number multiply(Number number);
	
	/**
	 * 引数に与えられた数値との除算を行う
	 * Int同士の場合はIntを返す
	 * doubleが計算に含まれている場合はDoubleを返す
	 * @param 除算する数値
	 * @return 除算後の数値
	 */
	public Number divide(Number number) throws LispException;
	
	/**
	 * 引数に与えられた数値との大小比較を行う
	 * 引数で与えられた数値のほうが小さい時true
	 * 引数で与えられた数値のほうが小さくない時false
	 * @param 比較対象の数値
	 * @return 比較結果の真理値
	 */
	public boolean lessThan(Number number);
	
	/**
	 * 引数に与えられた数値との大小比較を行う
	 * 引数で与えられた数値のほうが大きい時true
	 * 引数で与えられた数値のほうが大きくない時false
	 * @param 比較対象の数値
	 * @return 比較結果の真理値
	 */
	public boolean greaterThan(Number number);
	
	/**
	 * 引数に与えられた数値との大小比較を行う
	 * 引数で与えられた数値のほうが数値以下時true
	 * 引数で与えられた数値のほうが数値以下でない時false
	 * @param 比較対象の数値
	 * @return 比較結果の真理値
	 */
	public boolean lessThanOrEqual(Number number);
	
	/**
	 * 引数に与えられた数値との大小比較を行う
	 * 引数で与えられた数値のほうが数値以上時true
	 * 引数で与えられた数値のほうが数値以上でない時false
	 * @param 比較対象の数値
	 * @return 比較結果の真理値
	 */
	public boolean greaterThanOrEqual(Number number);
}
