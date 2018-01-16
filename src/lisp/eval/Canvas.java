package lisp.eval;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JFrame;

/**
 * 画面描画用のクラス
 * SwingをUIとして採用している
 * Windowのリサイズ不可
 * @author sam0830
 * @version 1.0
 */
public class Canvas extends JFrame implements SExpression {
	private static final int ADJUST_POSITION = 250;
	private Rectangle rectangle;
	private Image imageBuffer;
	
	/**
	 * Canvasのコンストラクタ
	 * 描画用画面の左上の座標と
	 * 幅と高さを設定する
	 * @param x 描画用画面左上の座標のx座標
	 * @param y 描画用画面左上の座標のx座標
	 * @param width 描画用画面の幅
	 * @param height 描画用画面の高さ
	 */
	private Canvas(Number x, Number y, Number width, Number height) {
		int xPos = (x instanceof Int)?((Int)x).getValue():(((lisp.eval.Double)x).getValue()).intValue();
		int yPos = (y instanceof Int)?((Int)y).getValue():(((lisp.eval.Double)y).getValue()).intValue();
		int windowWidth = (width instanceof Int)?((Int)width).getValue():(((lisp.eval.Double)width).getValue()).intValue();
		int windowHeight = (height instanceof Int)?((Int)height).getValue():(((lisp.eval.Double)height).getValue()).intValue();
		rectangle = new Rectangle();
		this.rectangle.setBounds(xPos, yPos, windowWidth, windowHeight);
		setBounds(this.rectangle);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);
		// setAlwaysOnTop(true);
	    setVisible(true);
	}
	
	/**
	 * 描画用画面の座標と幅・高さを
	 * Rectangleで取得
	 * @return 描画用画面の情報
	 */
	public Rectangle getValue() {
		return this.rectangle;
	}
	
	/**
	 * 指定された座標・幅・高さのCanvasを返す
	 * @param x 描画用画面左上の座標のx座標
	 * @param y 描画用画面左上の座標のx座標
	 * @param width 描画用画面の幅
	 * @param height 描画用画面の高さ
	 * @return Canvasのインスタンス
	 */
	public static Canvas getInstance(Number x, Number y, Number width, Number height) {
		return new Canvas(x, y, width, height);
	}
	
	/**
	 * 指定された座標(x1, y1)から(x2, y2)に線を引く
	 * @param x1 始点のx座標
	 * @param y1 始点のy座標
	 * @param x2 終点のx座標
	 * @param y2 終点のy座標
	 */
	public void drawLine(Number x1, Number y1, Number x2, Number y2) {
		int x1Pos = (x1 instanceof Int)?((Int)x1).getValue():(((lisp.eval.Double)x1).getValue()).intValue();
		int y1Pos = (y1 instanceof Int)?((Int)y1).getValue():(((lisp.eval.Double)y1).getValue()).intValue();
		int x2Pos = (x2 instanceof Int)?((Int)x2).getValue():(((lisp.eval.Double)x2).getValue()).intValue();
		int y2Pos = (y2 instanceof Int)?((Int)y2).getValue():(((lisp.eval.Double)y2).getValue()).intValue();
		// 描画位置調整
		x1Pos += ADJUST_POSITION;
		y1Pos += ADJUST_POSITION;
		x2Pos += ADJUST_POSITION;
		y2Pos += ADJUST_POSITION;
		Graphics g = imageBuffer.getGraphics();
		g.drawLine(x1Pos, y1Pos, x2Pos, y2Pos);
		repaint(); 
	}
	
	/**
	 * 引数の x、y、width、height で指定される矩形内に収まる円または楕円が描かれる
	 * @param x1 始点のx座標
	 * @param y1 始点のy座標
	 * @param x2 終点のx座標
	 * @param y2 終点のy座標
	 */
	public void drawOval(Number x1, Number y1, Number x2, Number y2) {
		int x = (x1 instanceof Int)?((Int)x1).getValue():(((lisp.eval.Double)x1).getValue()).intValue();
		int y = (y1 instanceof Int)?((Int)y1).getValue():(((lisp.eval.Double)y1).getValue()).intValue();
		int width = (x2 instanceof Int)?((Int)x2).getValue():(((lisp.eval.Double)x2).getValue()).intValue();
		int height = (y2 instanceof Int)?((Int)y2).getValue():(((lisp.eval.Double)y2).getValue()).intValue();
		// 描画位置調整
		x += ADJUST_POSITION;
		y += ADJUST_POSITION;
		Graphics g = this.getGraphics();
		g.drawOval(x, y, width, height);
		repaint(); 
	}
	
	@Override
	public void paint(Graphics g) {
		if(imageBuffer == null) {
			imageBuffer = this.createImage(this.getWidth(),this.getHeight());
		}
		g.drawImage(imageBuffer,0,0,this); 
		//super.paint(g);
	}
	
	@Override
	public String toString() {
		return "canvas" + "(" + this.rectangle.x + "," + this.rectangle.y + "," + this.rectangle.width + "," + this.rectangle.height + ")";
	}
	
	@Override
	public int hashCode() {
		return this.rectangle.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof Canvas) == false) {
			return false;
		}
		return this.rectangle.equals(((Canvas)obj).getValue());
	}
}
