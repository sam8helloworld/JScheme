package lisp.eval;

import java.util.ArrayList;

/**
 * Map
 * @author sam0830
 *
 */
public class Map implements Subroutine {	
	private static final Map map = new Map();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		SExpression proc = ((ConsCell)sexp).getCar();
		SExpression lists = ((ConsCell)sexp).getCdr();
		ConsCell.ListBuilder listBuilder = ConsCell.builder();
		
		ArrayList<SExpression> arrayList = new ArrayList<SExpression>();
		ArrayList<SExpression> argumentsList = new ArrayList<SExpression>();
		while(!(lists instanceof EmptyList)) {
			arrayList.add(((ConsCell)lists).getCar());
			lists = ((ConsCell)lists).getCdr();
		}
		
		
		int listsNum = arrayList.size();
		int firstListSize = ((ConsCell)arrayList.get(0)).size();
		for(int i=0;i<firstListSize;i++) {
			ConsCell.ListBuilder tmp = ConsCell.builder();
			boolean isEmpty = false;
			for(int j=0;j<listsNum;j++) {
				SExpression element = ((ConsCell)arrayList.get(j)).get(i);
				if(element instanceof EmptyList) {
					isEmpty = true;
				}
				tmp.tail(element);
			}
			if(!isEmpty) {
				argumentsList.add(tmp.build());
			}
		}
		for(SExpression arg: argumentsList) {
			if(proc instanceof SpecialForm) {
				listBuilder.tail(((SpecialForm)proc).apply(arg, environment));
			}
			if(proc instanceof Subroutine) {
				listBuilder.tail(((Subroutine)proc).apply(arg, environment));
			}
			if(proc instanceof Closure) {
				listBuilder.tail(((Closure)proc).apply(arg, environment));
			}
			
		}
		return listBuilder.build();
	}
	
	public static Map getInstance() {
		return map;
	}
}
