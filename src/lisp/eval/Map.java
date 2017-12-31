package lisp.eval;

/**
 * Map
 * @author sam0830
 *
 */
public class Map implements Subroutine {	
	private static final Map map = new Map();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		// 引数は必ず2個以上
		if(!(sexp instanceof ConsCell)) {
			// エラー
		}
		SExpression proc = ((ConsCell)sexp).getCar();
		SExpression lists = ((ConsCell)sexp).getCdr();
		// すべてのリストがリスト
		// リストの最少要素数を取得
		int min = -1;
		for(int i=0;i<((ConsCell)lists).size();i++) {
			SExpression tmpList = ((ConsCell)lists).get(i);
			if(!(tmpList instanceof ConsCell)) {
				throw new RuntimeException("リスト以外が引数にある");
			}
			if(!((ConsCell)tmpList).isList()) {
				throw new RuntimeException("リスト以外が引数にある");
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
		for(int elNum=0;elNum<min;elNum++) {
			ConsCell.ListBuilder listBuilderElement = ConsCell.builder();
			listBuilderElement.tail(proc);
			for(int listNum=0;listNum<((ConsCell)lists).size();listNum++) {
				// listNum個目のリストを取得
				SExpression list = ((ConsCell)lists).get(listNum);
				if(!(list instanceof ConsCell)) {
					throw new RuntimeException("リスト以外が引数にある");
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
	
	public static Map getInstance() {
		return map;
	}
}
