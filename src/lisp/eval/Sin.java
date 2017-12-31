package lisp.eval;

/**
 * Sin
 * @author sam0830
 *
 */
public class Sin implements Subroutine {
	private static final Sin sin = new Sin();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		if(!(sexp instanceof ConsCell)) {
			// エラー
		}
		if(((ConsCell)sexp).size() != 1) {
			// エラー
		}
		SExpression car = ((ConsCell)sexp).getCar();
		if(!(car instanceof Number)) {
			// エラー
		}
		double number = (car instanceof Int)?((Int)car).getValue():((lisp.eval.Double)car).getValue();
		return lisp.eval.Double.valueOf(Math.sin(Math.toRadians(number)));
		
	}
	
	public static Sin getInstance() {
		return sin;
	}
}
