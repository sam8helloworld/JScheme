package lisp.eval;

import lisp.exception.LispException;

/**
 * 評価器
 * @author sam0830
 * @version 1.0
 */
public class Evaluator {
	/**
	 * 組み込み手続きなどで引数のみを予め評価する
	 * @param sexp 手続きの引数
	 * @param env 評価時の環境
	 * @return 評価が終了した引数
	 * @throws LispException 評価の際、各手続きのapply
	 */
	private static SExpression evalArguments(SExpression sexp, Environment env) throws LispException {
		if(sexp instanceof EmptyList) {
			return EmptyList.getInstance();
		}
		ConsCell.ListBuilder listBuilder = ConsCell.builder();
		for(int i=0;i<((ConsCell)sexp).size();i++) {
			listBuilder.tail(eval(((ConsCell)sexp).get(i), env));
		}
		return listBuilder.build();
//		((ConsCell)sexp).setCar(eval(((ConsCell)sexp).getCar(), env));
//		((ConsCell)sexp).setCdr(evalArguments(((ConsCell)sexp).getCdr(), env));
//		
//		return sexp;
	}
	
	/**
	 * 引数の環境の下で引数のS式を評価する。
	 * @param sexp S式
	 * @param env 環境
	 * @return 評価値(S式)
	 * @throws LispException 評価の際、各手続きのapply
	 */
	public static SExpression eval(SExpression sexp, Environment env) throws LispException { 
		// sexpが整数(/Int)の時
		if(sexp instanceof Int) {
			return sexp;
		}
		
		// sexpが実数(/lisp.eval.Double)の時
		if(sexp instanceof lisp.eval.Double) {
			return sexp;
		}
		
		// sexpが文字列の時
		if(sexp instanceof LispString) {
			return sexp;
		}
		
		// sexpが記号の時
		if(sexp instanceof Symbol) {
			/*
			 * 現在の環境のフレームにあるかどうか確認
			 * フレームになければエラー
			 * フレームにあれば対応するS式を返す
			 * 手続きが返された場合はListでないのでエラー
			 */
			return env.get((Symbol)sexp);
		}
		
		// sexpが空リストの時
		if(sexp instanceof EmptyList) {
			return sexp;
		}
				
		//sexpがConsCellの時
		if(sexp instanceof ConsCell) { 
			/*
			 * ConsCellの場合
			 * (A . B)
			 * (+ 1 2)
			 * などの括弧を使ったほぼすべての式の可能性がある
			 * carは命令かアトム
			 */
			SExpression car = eval(((ConsCell) sexp).getCar(), env);
			SExpression cdr = ((ConsCell) sexp).getCdr();
			
			// 特殊形式(引数を評価しない)
			// defineは第一引数を評価せず、第二引数を評価して第一引数にセットする
			// 参考URL: http://www.geocities.jp/m_hiroi/func/abcscm02.html
			if(car instanceof SpecialForm) {
				return ((SpecialForm)eval(car, env)).apply(((ConsCell) sexp).getCdr(), env);
			}
			
			// carがクロージャ(/Closure)の時
			if(car instanceof Closure) {
				return ((Closure)car).apply(evalArguments(cdr, env), env);
			}
			
			// 組み込み形式
			if(car instanceof Subroutine) {
				return ((Subroutine)car).apply(evalArguments(cdr, env), env);
			}
			
			// TODO:carが手続き型でないので何かしらのエラー
		}
		
		// どのデータ型とも一致しない
		return sexp;
	}
}