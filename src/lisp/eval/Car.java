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
		// ConsCellのサイズが1でなければエラー
		// 引数がConsCellでなければエラー
		// ConsCellが渡されてていればcarを返す
		if(!(sexp instanceof ConsCell)) {
			throw new RuntimeException("*** ERROR: pair required, but got "+sexp);
		}
		if(((ConsCell)sexp).size() != 1) {
			throw new RuntimeException("*** ERROR: pair required, but got "+sexp);
		}
		SExpression arg = ((ConsCell)sexp).get(0); // 第一引数
		if(!(arg instanceof ConsCell)) {
			throw new RuntimeException("*** ERROR: pair required, but got "+sexp);
		}
		return ((ConsCell)arg).getCar();
		
	}
	
	public static Car getInstance() {
		return car;
	}
}
