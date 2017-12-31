package lisp.eval;

/**
 * 数値比較(=)
 * @author sam0830
 *
 */
public class EqualNumber implements Subroutine {
	private static final EqualNumber equalNumber = new EqualNumber();
	
	public SExpression apply(SExpression sexp, Environment environment) { 
		// 引数が2つ以上でないときエラー
		if(!(sexp instanceof ConsCell)) {
			throw new RuntimeException("引数が足りない");
		}
		int size = ((ConsCell)sexp).size();
		if(size < 2) {
			throw new RuntimeException("引数が足りない");
		}
		SExpression arg = ((ConsCell)sexp).getCar();
		if(!(arg instanceof Number)) {
			throw new RuntimeException("引数が数字でない");
		}
		
		double comparedNumber = (arg instanceof Int)?((Int)arg).getValue():((lisp.eval.Double)arg).getValue();
		for(int i=1;i<size;i++) {
			SExpression tmp = ((ConsCell)sexp).get(i);
			if(!(tmp instanceof Number)) {
				throw new RuntimeException("引数が数字でない");
			}
			Number num = (Number)tmp;
			double number = (num instanceof Int)?((Int)num).getValue():((lisp.eval.Double)num).getValue();
			if(comparedNumber != number) {
				return Bool.valueOf(false);
			}
		}
		return Bool.valueOf(true);
	}
	
	public static EqualNumber getInstance() {
		return equalNumber;
	}
}
