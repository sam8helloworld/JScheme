package lisp.eval;

import lisp.exception.LispException;

/**
 * 組み込み手続きのインタフェース
 * @author sam0830
 * @version 1.0
 */
interface Subroutine extends SExpression {
	/**
	 * 組み込み手続きを実行する
	 * @param sexp 組み込み手続きの引数
	 * @param environment 実行時の環境
	 * @return 手続きの結果
	 * @throws LispException 引数が手続きの望む型でない時
	 */
	public SExpression apply(SExpression sexp, Environment environment) throws LispException;
}
