package lisp.eval;

/**
 * pair?
 * @author sam0830
 *
 */
public class AskPair implements Subroutine {
	private static final AskPair askPair = new AskPair();
	
	public static AskPair getInstance() {
		return askPair;
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
		return (((ConsCell)sexp).get(0) instanceof ConsCell)?Bool.valueOf(true):Bool.valueOf(false);
	}
	
}