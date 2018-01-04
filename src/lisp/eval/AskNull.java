package lisp.eval;

/**
 * null?
 * @author sam0830
 *
 */
public class AskNull implements Subroutine {
	private static final AskNull askNull = new AskNull();
	
	public static AskNull getInstance() {
		return askNull;
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
		return (((ConsCell)sexp).get(0) instanceof EmptyList)?Bool.valueOf(true):Bool.valueOf(false);
	}
	
}
