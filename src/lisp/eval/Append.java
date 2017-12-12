package lisp.eval;

import java.util.ArrayList;

/**
 * Append
 * @author sam0830
 *
 */
public class Append implements Subroutine {
	private static final Append append = new Append();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		// appendの引数が空リスト or アトム
		if(!(sexp instanceof ConsCell)) {
			return sexp;
		}
		
		ArrayList<SExpression> lists = new ArrayList<SExpression>();
		SExpression tmp = sexp;
		while(true) {
			lists.add(((ConsCell)tmp).getCar());
			if(((ConsCell)tmp).getCdr() instanceof EmptyList) {
				break;
			}
			tmp = ((ConsCell)tmp).getCdr(); 
		}
		
		ConsCell.ListBuilder listBuilder = ConsCell.builder();
		for(SExpression list : lists) {
			for(;list instanceof ConsCell; list = ((ConsCell)list).getCdr()) {
				listBuilder.tail(((ConsCell)list).getCar());
			}
		}
		return listBuilder.build();
	}
	
	public static Append getInstance() {
		return append;
	}
}
