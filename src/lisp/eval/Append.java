package lisp.eval;

/**
 * Append
 * @author sam0830
 *
 */
public class Append implements Subroutine {
	private static final Append append = new Append();
	
	private SExpression append(SExpression list1, SExpression list2) {
		if(list1 instanceof EmptyList) {
			return list2;
		}
		if(!(list1 instanceof ConsCell)) {
			// エラー
			throw new RuntimeException("エラー");
		}
		return ConsCell.getInstance(((ConsCell)list1).getCar(),
				                    append(((ConsCell)list1).getCdr(), list2)
				                    );
	}
	
	public SExpression apply(SExpression sexp, Environment environment) {
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
	
	public static Append getInstance() {
		return append;
	}
}
