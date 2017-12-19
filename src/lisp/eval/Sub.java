package lisp.eval;

/**
 * Add (加法)
 * @author sam0830
 *
 */
public class Sub implements Subroutine{
	private static final Sub sub = new Sub();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		// 引数なし
		if(sexp instanceof EmptyList) {
			// エラー
		}
		
		SExpression car = ((ConsCell)sexp).getCar();
		
		// 引数1つ
		if(((ConsCell)sexp).getSize() == 1) {
			// 引数が数値でない
			if(!(car instanceof Number)) {
				// エラー
			}
			// 引数が数値
			return ((Number)car).multiply(Int.valueOf(-1));
		}
		
		// 引数が2つ以上
		if(!(car instanceof Number)) {
			// エラー
		}
		Number number = (Number)car;
		SExpression tmp = ((ConsCell)sexp).getCdr();
		while(tmp instanceof ConsCell) {
			SExpression arg = ((ConsCell)tmp).getCar();
			if(!(arg instanceof Number)) {
				// エラー
			}
			// 引数が数値
			number = number.sub((Number)arg);
			tmp = ((ConsCell)tmp).getCdr();
		}
		return number;
	}
	
	public static Sub getInstance() {
		return sub;
	}
}
