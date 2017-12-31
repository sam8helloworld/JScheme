package lisp.eval;

/**
 * DrawLine
 * @author sam0830
 *
 */
public class DrawLine implements Subroutine {
	private static final DrawLine drawLine = new DrawLine();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		// 引数の型は(Number, Number, Number, Number, Canvas)
		if(!(sexp instanceof ConsCell)) {
			// エラー
		}
		if(((ConsCell)sexp).size() != 5) {
			// エラー
		}
		Number[] elements = new Number[4];
		for(int i =0;i<4;i++) {
			SExpression element = ((ConsCell)sexp).get(i);
			if(!(element instanceof Number)) {
				// エラー
			}
			elements[i] = (Number)element;
		}
		SExpression canvas = ((ConsCell)sexp).get(4);
		if(!(canvas instanceof Canvas)) {
			// エラー
		}
		((Canvas)canvas).drawLine(elements[0], elements[1], elements[2], elements[3]);
		return Bool.valueOf(true);
	}
	
	public static DrawLine getInstance() {
		return drawLine;
	}
}
