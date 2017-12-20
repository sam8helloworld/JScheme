package lisp.eval;

/**
 * Cos
 * @author sam0830
 *
 */
public class Cos implements Subroutine {
	private static final Cos cos = new Cos();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		if(!(sexp instanceof ConsCell)) {
			// エラー
		}
		if(((ConsCell)sexp).getSize() != 1) {
			// エラー
		}
		SExpression car = ((ConsCell)sexp).getCar();
		if(!(car instanceof Number)) {
			// エラー
		}
		double number = (car instanceof Int)?((Int)car).getValue():((lisp.eval.Double)car).getValue();
		return lisp.eval.Double.valueOf(Math.cos(Math.toRadians(number)));
		
	}
	
	public static Cos getInstance() {
		return cos;
	}
}
