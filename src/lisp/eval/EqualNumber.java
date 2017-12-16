package lisp.eval;

import java.util.ArrayList;

/**
 * 数値比較(=)
 * @author sam0830
 *
 */
public class EqualNumber implements Subroutine {
	private static final EqualNumber equalNumber = new EqualNumber();
	
	public SExpression apply(SExpression sexp, Environment environment) { 
		ArrayList<Number> arrayList = new ArrayList<Number>();
		SExpression tmp = sexp;
		// 各引数をリストに格納
		while(!(tmp instanceof EmptyList)) {
			// 引数はすべてNumberである必要がある
			SExpression car = ((ConsCell)tmp).getCar();
			if(!(car instanceof Number)) {
				// TODO:エラー
			}
			arrayList.add((Number)car);
			tmp = ((ConsCell)tmp).getCdr();
		}
		// リストの要素がすべて同じかどうか確認
		Number element = arrayList.get(0);
		double elementFirst = (element instanceof Int)?((Int)element).getValue():((lisp.eval.Double)element).getValue();
		
		for(Number number : arrayList) {
			double arg = (number instanceof Int)?((Int)number).getValue():((lisp.eval.Double)number).getValue();
			if(elementFirst != arg) {
				return Bool.valueOf(false);
			}
		}
		return Bool.valueOf(true);
	}
	
	public static EqualNumber getInstance() {
		return equalNumber;
	}
}
