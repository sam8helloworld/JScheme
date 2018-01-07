package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 組み込み手続きmake-canvas
 * 描画用のWindowを作成する
 * @author sam0830
 * @version 1.0
 */
public class MakeCanvas implements Subroutine {
	private static final Int STANDARD_POSITION = Int.valueOf(200);
	private static final Int STANDARD_SIZE = Int.valueOf(400);
	private static final MakeCanvas makeCanvas = new MakeCanvas();
	
	/**
	 * MakeCanvasのインスタンスを返す
	 * @return 組み込み手続きMakeCanvas
	 */
	public static MakeCanvas getInstance() {
		return makeCanvas;
	}
	
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 引数無しの時は自動でサイズ決定
		if(sexp instanceof EmptyList) {
			return Canvas.getInstance(STANDARD_POSITION, STANDARD_POSITION, STANDARD_SIZE, STANDARD_SIZE);
		}
		// 引数ありの時はNumber型で4つが必須
		int size = ((ConsCell)sexp).size();
		if(size != 4) {
			throw new ArgumentException("wrong number of arguments for "+this+" (required 4, got "+size+")");
		}
		Number[] value = new Number[4];
		for(int i=0;i<size;i++) {
			SExpression tmp = ((ConsCell)sexp).get(i);
			if(!(tmp instanceof Number)) {
				throw new ArgumentException("operation make-canvas is not defined on object "+tmp);
			}
			value[i] = (Number)tmp;
		}
		return Canvas.getInstance(value[0], value[1], value[2], value[3]);
	}
	
	@Override
	public String toString() {
		return "#<subr make-canvas>";
	}
}
