package lisp.eval;

/**
 * MakeCanvas
 * @author sam0830
 *
 */
public class MakeCanvas implements Subroutine {
	private static final Int STANDARD_POSITION = Int.valueOf(200);
	private static final Int STANDARD_SIZE = Int.valueOf(400);
	private static final MakeCanvas makeCanvas = new MakeCanvas();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		// 引数無しの時は自動でサイズ決定
		if(sexp instanceof EmptyList) {
			return Canvas.getInstance(STANDARD_POSITION, STANDARD_POSITION, STANDARD_SIZE, STANDARD_SIZE);
		}
		// 引数ありの時はNumber型で4つが必須
		if(!(sexp instanceof ConsCell)) {
			// エラー
		}
		int size = ((ConsCell)sexp).size();
		if(size != 4) {
			// エラー
		}
		Number[] value = new Number[4];
		for(int i=0;i<size;i++) {
			SExpression tmp = ((ConsCell)sexp).get(i);
			if(!(tmp instanceof Number)) {
				// エラー
			}
			value[i] = (Number)tmp;
		}
		return Canvas.getInstance(value[0], value[1], value[2], value[3]);
	}
	
	public static MakeCanvas getInstance() {
		return makeCanvas;
	}
}
