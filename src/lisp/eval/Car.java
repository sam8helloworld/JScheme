package lisp.eval;

/**
 * Car
 * @author sam0830
 *
 */
public class Car implements Subroutine {
	private static final Car car = new Car();
	public SExpression apply(SExpression sexp, Environment environment) {
		// car
		// ConsCellが渡されなければエラー
		// ConsCellが渡されてていればcarを返す
		if(sexp instanceof ConsCell) {
			return ((ConsCell) sexp).getCar();
		}
		throw new RuntimeException("*** ERROR: pair required, but got "+sexp);
	}
	
	
	public static Car getInstance() {
		return car;
	}
}
