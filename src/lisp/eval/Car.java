package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * Car
 * @author sam0830
 *
 */
public class Car implements Subroutine {
	private static final Car car = new Car();
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// car
		// ConsCellが渡されなければエラー
		// ConsCellのサイズが1でなければエラー
		// 引数がConsCellでなければエラー
		// ConsCellが渡されてていればcarを返す
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("pair required, but got "+sexp);
		}
		int size = ((ConsCell)sexp).size();
		if(size != 1) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got "+size+")");
		}
		SExpression arg = ((ConsCell)sexp).get(0); // 第一引数
		if(!(arg instanceof ConsCell)) {
			throw new ArgumentException("pair required, but got "+arg);
		}
		return ((ConsCell)arg).getCar();
		
	}
	@Override
	public String toString() {
		return "<subr car>";
	}
	public static Car getInstance() {
		return car;
	}
}
