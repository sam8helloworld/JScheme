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
import lisp.eval.Car;
import lisp.eval.Cdr;
import lisp.eval.Cons;
import lisp.eval.Cos;
import lisp.eval.Define;
import lisp.eval.Divide;
import lisp.eval.DrawLine;
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
import lisp.eval.LispString;
import lisp.eval.List;
import lisp.eval.MakeCanvas;
import lisp.eval.Map;
import lisp.eval.Multiply;
import lisp.eval.NewLine;
import lisp.eval.Quote;
import lisp.eval.SExpression;
import lisp.eval.Set;
import lisp.eval.Sin;
import lisp.eval.Sub;
import lisp.eval.Symbol;
import lisp.eval.Write;
import lisp.exception.EndOfFileException;
import lisp.exception.LispException;
import lisp.reader.Reader;

/**
 * Mainクラス
 * 
 * @author sam0830
 * @version 1.0
 */
public class Main {
	static void printGreetingMessage() {
		System.out.println("  _      _           ");
		System.out.println(" | |    (_)          ");
		System.out.println(" | |     _ ___ _ __  ");
		System.out.println(" | |    | / __| '_ \\ ");
		System.out.println(" | |____| \\__ \\ |_) |");
		System.out.println(" |______|_|___/ .__/ ");
		System.out.println("              | |    ");
		System.out.println("              |_|    ");
		
		System.out.println("Welcome to Lisp by sam0830 [version1.0.0] (2017/12/31) <https://github.com/sam0830/LispInterpriter>");
		System.out.println("");
		System.out.println("CopyRight (c) [sam0830] 2017");
		System.out.println("");
		System.out.println("Type :h and hit Enter for context help.");
	}

	public static void main(String[] args) throws IOException {
		Environment environment = new Environment(null);
		environment.define(Symbol.getInstance("car"), Car.getInstance());
		environment.define(Symbol.getInstance("cdr"), Cdr.getInstance());
		environment.define(Symbol.getInstance("list"), List.getInstance());
		environment.define(Symbol.getInstance("append"), Append.getInstance());
		environment.define(Symbol.getInstance("cons"), Cons.getInstance());
		environment.define(Symbol.getInstance("+"), Add.getInstance());
		environment.define(Symbol.getInstance("-"), Sub.getInstance());
		environment.define(Symbol.getInstance("*"), Multiply.getInstance());
		environment.define(Symbol.getInstance("/"), Divide.getInstance());
		environment.define(Symbol.getInstance("map"), Map.getInstance());
		environment.define(Symbol.getInstance("="), EqualNumber.getInstance());
		environment.define(Symbol.getInstance("eq?"), EqualAddress.getInstance());
		environment.define(Symbol.getInstance("write"), Write.getInstance());
		environment.define(Symbol.getInstance("newline"), NewLine.getInstance());
		environment.define(Symbol.getInstance("exit"), Exit.getInstance());
		environment.define(Symbol.getInstance("sin"), Sin.getInstance());
		environment.define(Symbol.getInstance("cos"), Cos.getInstance());
		environment.define(Symbol.getInstance("make-canvas"), MakeCanvas.getInstance());
		environment.define(Symbol.getInstance("draw-line"), DrawLine.getInstance());
		environment.define(Symbol.getInstance("<"), LessThan.getInstance());
		environment.define(Symbol.getInstance(">"), GreaterThan.getInstance());
		environment.define(Symbol.getInstance("<="), LessThanOrEqual.getInstance());
		environment.define(Symbol.getInstance(">="), GreaterThanOrEqual.getInstance());
		environment.define(Symbol.getInstance("null?"), AskNull.getInstance());
		environment.define(Symbol.getInstance("list?"), AskList.getInstance());
		environment.define(Symbol.getInstance("quote"), Quote.getInstance());
		environment.define(Symbol.getInstance("define"), Define.getInstance());
		environment.define(Symbol.getInstance("lambda"), Lambda.getInstance());
		environment.define(Symbol.getInstance("if"), If.getInstance());
		environment.define(Symbol.getInstance("set!"), Set.getInstance());
		printGreetingMessage();
		
		BufferedReader bufferedReader;
		Reader reader;
		if(args.length != 0) {
			try {
				File file = new File(args[0]);
				bufferedReader = new BufferedReader(new FileReader(file));
				reader = new Reader(bufferedReader);
				while(true) {
					try {
						SExpression exp = reader.read();
						SExpression value = Evaluator.eval(exp, environment);
						if(value instanceof LispString) {
							System.out.println("\""+value+"\"");
							continue;
						}
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
		
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		reader = new Reader(bufferedReader);
		try {
			while(true) {
				try {
					System.out.print("lisp> ");
					SExpression exp = reader.read();
					SExpression value = Evaluator.eval(exp, environment);
					if(value instanceof LispString) {
						System.out.println("\""+value+"\"");
						continue;
					}
					System.out.println(value);
				} catch (EndOfFileException e) {
					break;
				} catch (LispException e) {
					System.err.println(e.getMessage());
				}
				
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		bufferedReader.close();
	}

}
