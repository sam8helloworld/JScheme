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
			if(car instanceof SpecialForm) {
				return ((SpecialForm)car).apply(((ConsCell) sexp).getCdr(), env);
			}
			
			if(car instanceof Subroutine) {
				return ((Subroutine)car).apply(((ConsCell) sexp).getCdr(), env);
			}
		}
		return sexp;
	}
}
