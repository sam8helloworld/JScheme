package lisp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lisp.eval.Add;
import lisp.eval.Append;
import lisp.eval.AskList;
import lisp.eval.AskNull;
import lisp.eval.AskPair;
import lisp.eval.Car;
import lisp.eval.Cdr;
import lisp.eval.Cons;
import lisp.eval.Cos;
import lisp.eval.Define;
import lisp.eval.Display;
import lisp.eval.Divide;
import lisp.eval.DrawLine;
import lisp.eval.DrawOval;
import lisp.eval.Environment;
import lisp.eval.EqualAddress;
import lisp.eval.EqualNumber;
import lisp.eval.Evaluator;
import lisp.eval.Exit;
import lisp.eval.GreaterThan;
import lisp.eval.GreaterThanOrEqual;
import lisp.eval.If;
import lisp.eval.Lambda;
import lisp.eval.LessThan;
import lisp.eval.LessThanOrEqual;
import lisp.eval.Let;
import lisp.eval.List;
import lisp.eval.MakeCanvas;
import lisp.eval.Map;
import lisp.eval.Modulo;
import lisp.eval.Multiply;
import lisp.eval.NewLine;
import lisp.eval.Not;
import lisp.eval.Quote;
import lisp.eval.SExpression;
import lisp.eval.Set;
import lisp.eval.Sin;
import lisp.eval.Sleep;
import lisp.eval.Sqrt;
import lisp.eval.Sub;
import lisp.eval.Symbol;
import lisp.eval.Write;
import lisp.exception.EndOfFileException;
import lisp.exception.LispException;
import lisp.reader.Reader;

/**
 * mainメソッドを含むクラス
 * 一番はじめに呼ばれるクラス
 * 
 * @author sam0830
 * @version 1.0
 * 
 */
public class Main {
	private static final Environment ENVIRONMENT = new Environment(null);
	
	// 組み込み手続きの初期化
	static {
		ENVIRONMENT.define(Symbol.getInstance("car"), Car.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("cdr"), Cdr.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("list"), List.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("append"), Append.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("cons"), Cons.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("+"), Add.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("-"), Sub.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("*"), Multiply.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("/"), Divide.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("modulo"), Modulo.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("map"), Map.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("="), EqualNumber.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("eq?"), EqualAddress.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("write"), Write.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("display"), Display.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("newline"), NewLine.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("exit"), Exit.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("sin"), Sin.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("cos"), Cos.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("make-canvas"), MakeCanvas.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("draw-line"), DrawLine.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("draw-oval"), DrawOval.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("<"), LessThan.getInstance());
		ENVIRONMENT.define(Symbol.getInstance(">"), GreaterThan.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("<="), LessThanOrEqual.getInstance());
		ENVIRONMENT.define(Symbol.getInstance(">="), GreaterThanOrEqual.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("null?"), AskNull.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("list?"), AskList.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("pair?"), AskPair.getInstance());	
		ENVIRONMENT.define(Symbol.getInstance("sleep"), Sleep.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("sqrt"), Sqrt.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("not"), Not.getInstance());
	}
	
	// 特殊形式の初期化
	static {
		ENVIRONMENT.define(Symbol.getInstance("quote"), Quote.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("define"), Define.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("lambda"), Lambda.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("let"), Let.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("if"), If.getInstance());
		ENVIRONMENT.define(Symbol.getInstance("set!"), Set.getInstance());
	}
	
	/**
	 * 対話が始まった際の挨拶
	 * 著作者情報などを表示
	 */
	private static void printGreetingMessage() {
		System.out.println("  _      _           ");
		System.out.println(" | |    (_)          ");
		System.out.println(" | |     _ ___ _ __  ");
		System.out.println(" | |    | / __| '_ \\ ");
		System.out.println(" | |____| \\__ \\ |_) |");
		System.out.println(" |______|_|___/ .__/ ");
		System.out.println("              | |    ");
		System.out.println("              |_|    ");
		
		System.out.println("Welcome to Lisp by sam0830 [version1.0.0] (2017/12/31) <https://github.com/sam0830/JScheme>");
		System.out.println("");
		System.out.println("CopyRight (c) [sam0830] 2017");
		System.out.println("");
		System.out.println("Type :h and hit Enter for context help.");
	}
	
	/**
	 * バッチ処理を行う
	 * @param fileName コマンドライン引数で入力されたファイル名
	 */
	private static void processBatch(String fileName) {
		try {
			File file = new File(fileName);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			Reader reader = new Reader(bufferedReader);
			while(true) {
				try {
					SExpression exp = reader.read();
					SExpression value = Evaluator.eval(exp, ENVIRONMENT);
					System.out.println(value);
				} catch (EndOfFileException e) {
					break;
				} catch (LispException e) {
					System.err.println(e.getMessage());
				}
			}
			bufferedReader.close();
			return;
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * mainメソッド
	 * @param args コマンドライン引数
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		printGreetingMessage();
		
		// コマンドライン引数あり
		if(args.length != 0) {
			processBatch(args[0]);
			return;
		}
		
		// コマンドライン引数なし
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		Reader reader = new Reader(bufferedReader);
		try {
			while(true) {
				try {
					System.out.print("lisp> ");
					SExpression exp = reader.read();
					SExpression value = Evaluator.eval(exp, ENVIRONMENT);
					if(value == null) {	continue;}
					System.out.println(value);
				} catch (EndOfFileException e) {
					//System.err.println(e.getMessage());
					System.out.println("");
					System.out.println(e.getMessage());
					break;
				} catch (LispException e) {
					//System.err.println(e.getMessage());
					System.out.println("");
					System.out.println(e.getMessage());
				}
				
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		bufferedReader.close();
		return;
	}
}
