package lisp.eval;

/**
 * Exit (終了)
 * @author sam0830
 *
 */
public class Exit implements Subroutine {
	private static final Exit exit = new Exit();
	@Override
	public SExpression apply(SExpression sexp, Environment environment) {
		System.exit(0);
		return null;
	}
	
	public static Exit getInstance() {
		return exit;
	}
}
