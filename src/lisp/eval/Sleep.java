package lisp.eval;

import java.util.concurrent.TimeUnit;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 組み込み手続きsleep
 * プログラムの実行を遅らせる
 * 単位はミリ秒
 * @author sam0830
 * @version 1.0
 */
public class Sleep implements Subroutine {
	private static final Sleep sleep = new Sleep();
	
	/**
	 * Sleepのインスタンスを返す
	 * @return 組み込み手続きSleep
	 */
	public static Sleep getInstance() {
		return sleep;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		/**
		 * 引数は1つ
		 * 引数はNumber型
		 */
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got 0)");
		}
		int size = ((ConsCell)sexp).size();
		if(size != 1) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 1, got "+size+")");
		}
		SExpression arg = ((ConsCell)sexp).getCar();
		if(!(arg instanceof Number)) {
			throw new ArgumentException("number required, but got "+arg);
		}
		try {
			long duration = (arg instanceof Int)?((Int)arg).getValue().longValue():((lisp.eval.Double)arg).getValue().longValue();
			TimeUnit.MILLISECONDS.sleep(duration);
		} catch (InterruptedException e) {
            e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "#<subr sleep>";
	}
}