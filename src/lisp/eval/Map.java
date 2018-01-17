package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 組み込み手続きmap
 * @author sam0830
 * @version 1.0
 */
public class Map implements Subroutine {	
	private static final Map map = new Map();
	
	/**
	 * Mapのインスタンスを返す
	 * @return 組み込み手続きMap
	 */
	public static Map getInstance() {
		return map;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 引数は必ず2個以上
		if(!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 2, got 0)");
		}
		SExpression proc = ((ConsCell)sexp).getCar();
		SExpression lists = ((ConsCell)sexp).getCdr();
		// すべてのリストがリスト
		// リストの最少要素数を取得
		int min = -1;
		for(int i=0;i<((ConsCell)lists).size();i++) {
			SExpression tmpList = ((ConsCell)lists).get(i);
			if(!(tmpList instanceof ConsCell)) {
				throw new ArgumentException("argument lists contained an improper list");
			}
			if(!((ConsCell)tmpList).isList()) {
				throw new ArgumentException("argument lists contained an improper list");
			}
			int size = ((ConsCell)tmpList).size();
			if(min == -1) {
				min = size;
				continue;
			}
			if(size < min) {
				min = size;
			}
		}
		
		ConsCell.ListBuilder listBuilder = ConsCell.builder();
		
		int listsSize = ((ConsCell)lists).size();		
		for(int elNum=0;elNum<min;elNum++) {
			ConsCell.ListBuilder listBuilderElement = ConsCell.builder();
			listBuilderElement.tail(proc);
			for(int listNum=0;listNum<listsSize;listNum++) {
				// listNum個目のリストを取得
				SExpression list = ((ConsCell)lists).get(listNum);
				if(!(list instanceof ConsCell)) {
					throw new ArgumentException("argument lists contained an improper list");
				}
				// listNum個目のリストのelNum番目の要素を取得
				SExpression element = ((ConsCell)list).get(elNum);
				// 要素をリストに追加
				listBuilderElement.tail(element);
			}
			SExpression evaled = Evaluator.eval(listBuilderElement.build(), environment);
			listBuilder.tail(evaled);
		}
		return listBuilder.build();
	}
	
	@Override
	public String toString() {
		return "#<subr map>";
	}
}
