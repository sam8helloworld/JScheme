package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * リストとリストを結合したリストを作る
 * @author sam0830
 * @version 1.0
 */
public class Append implements Subroutine {
	private static final Append append = new Append();
	
	/**
	 * Appendのインスタンスを返す
	 * @return 組み込み手続きAppend 
	 */
	public static Append getInstance() {
		return append;
	}
	
	/**
	 * 
	 * @param list1 結合されるリスト
	 * @param list2 結合するリスト
	 * @return 結合後のリスト
	 * @throws LispException list1がリストでない時
	 */
	private SExpression append(SExpression list1, SExpression list2) throws LispException {
		if(list1 instanceof EmptyList) {
			return list2;
		}
		if(!(list1 instanceof ConsCell)) {
			throw new ArgumentException("list required, but got "+list1);
		}
		return ConsCell.getInstance(((ConsCell)list1).getCar(),
				                    append(((ConsCell)list1).getCdr(), list2)
				                    );
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// appendの引数が空リスト 
		if(!(sexp instanceof ConsCell)) {
			return sexp;
		}
		if(((ConsCell)sexp).size() == 1) {
			return sexp;
		}
		SExpression appendedList = ((ConsCell)sexp).get(0);
		for(int i=0;i<((ConsCell)sexp).size()-1;i++) {
			appendedList = append(appendedList, ((ConsCell)sexp).get(i+1));
		}
		return appendedList;
	}
	
	@Override
	public String toString() {
		return "#<subr append>";
	}
}
