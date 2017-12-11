package lisp.eval;

/**
 * 評価器
 * @author sam0830
 *
 */
public class Evaluator {
	/**
	 * 引数の環境の下で引数のS式を評価する。
	 * @param sexp S式
	 * @param env 環境
	 * @return 評価値(S式)
	 */
	public static SExpression eval(SExpression sexp, Environment env) {
		//sexpが整数(/Int)の時
		if(sexp instanceof Int) {
			return sexp;
		}
		
		//sexpが記号の時
		if(sexp instanceof Symbol) {
			/*
			 * 現在の環境のフレームにあるかどうか確認
			 * フレームになければエラー
			 * フレームにあれば対応するS式を返す
			 * 手続きが返された場合はListでないのでエラー
			 */
			SExpression symbolExpression = env.get((Symbol)sexp);
			//env.define((Symbol)sexp, sexp);//テスト用
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
			SExpression car = ((ConsCell) sexp).getCar();
			SExpression cdr = ((ConsCell) sexp).getCdr();
			
			// 特殊形式(引数を評価しない)
			// defineは第一引数を評価せず、第二引数を評価して第一引数にセットする
			// 参考URL: http://www.geocities.jp/m_hiroi/func/abcscm02.html
			if(eval(car, env) instanceof SpecialForm) {
				return ((SpecialForm)eval(car, env)).apply(((ConsCell) sexp).getCdr(), env);
			}
			
			if(!(cdr instanceof EmptyList)) {
				cdr = eval(cdr, env);
			}
			
			car = eval(car, env);
			
			
			// carがクロージャ(/Closure)の時
			if(car instanceof Closure) {
				return ((Closure)car).apply(((ConsCell) sexp).getCdr(), env);
			}
			
			// 組み込み形式
			if(car instanceof Subroutine) { 
				return ((Subroutine)car).apply(((ConsCell) sexp).getCdr(), env);
			}
			
			
			((ConsCell) sexp).setCar(car);
			((ConsCell) sexp).setCdr(cdr);
		}
		return sexp;
	}
}