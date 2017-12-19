package lisp.eval;

/**
 * Cons
 * @author sam0830
 *
 */
public class Cons implements Subroutine {
	private static final Cons cons = new Cons();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		// 引数2個以外ではエラー
		if(!(sexp instanceof ConsCell)) {
			// エラー
		}
		if(!(((ConsCell)sexp).getSize() == 2)) {
			// エラー
		}
		SExpression car = ((ConsCell)sexp).getCar();
		SExpression cdr = ((ConsCell)((ConsCell)sexp).getCdr()).getCar();
		return ConsCell.getInstance(car, cdr);
	}
	
	public static Cons getInstance() {
		return cons;
	}
}
