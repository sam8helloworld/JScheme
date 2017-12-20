package lisp.eval;

/**
 * MakeCanvas
 * @author sam0830
 *
 */
public class MakeCanvas implements Subroutine {
	private static final MakeCanvas makeCanvas = new MakeCanvas();
	
	public SExpression apply(SExpression sexp, Environment environment) {
		if(!(sexp instanceof EmptyList)) {
			// 引数無し以外はエラー
		}
		return Canvas.getInstance(Int.valueOf(200), Int.valueOf(200), Int.valueOf(200), Int.valueOf(200));
	}
	
	public static MakeCanvas getInstance() {
		return makeCanvas;
	}
}
