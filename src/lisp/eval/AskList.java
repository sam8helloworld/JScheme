package lisp.eval;

/**
 * list?
 * @author sam0830
 *
 */
public class AskList implements Subroutine {
	private static final AskList askList = new AskList();
	
	public static AskList getInstance() {
		return askList;
	}
	@Override
	public SExpression apply(SExpression sexp, Environment environment) {
		// 引数が空リストかどうか
		if(!(sexp instanceof ConsCell)) {
			throw new RuntimeException("引数がない");
		}
		// 引数の個数は1個
		if(((ConsCell)sexp).size() != 1) {
			throw new RuntimeException("引数の個数が1個以上ある");
		}
		// 引数がリストかどうか
		SExpression arg = ((ConsCell)sexp).get(0);
		if(!(arg instanceof ConsCell)) {
			return Bool.valueOf(false);
		}
		return ((ConsCell)arg).isList()?Bool.valueOf(true):Bool.valueOf(false);
	}

}
