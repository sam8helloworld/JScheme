package lisp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lisp.eval.Add;
import lisp.eval.Append;
import lisp.eval.Car;
import lisp.eval.Cdr;
import lisp.eval.Define;
import lisp.eval.Divide;
import lisp.eval.Environment;
import lisp.eval.EqualNumber;
import lisp.eval.Evaluator;
import lisp.eval.Exit;
import lisp.eval.Lambda;
import lisp.eval.List;
import lisp.eval.Map;
import lisp.eval.Multiply;
import lisp.eval.Quote;
import lisp.eval.SExpression;
import lisp.eval.Sub;
import lisp.eval.Symbol;
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
		printGreetingMessage();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		Reader reader = new Reader(bufferedReader);
		Environment environment = new Environment(null);
		
		environment.define(Symbol.getInstance("car"), Car.getInstance());
		environment.define(Symbol.getInstance("cdr"), Cdr.getInstance());
		environment.define(Symbol.getInstance("list"), List.getInstance());
		environment.define(Symbol.getInstance("append"), Append.getInstance());
		environment.define(Symbol.getInstance("+"), Add.getInstance());
		environment.define(Symbol.getInstance("-"), Sub.getInstance());
		environment.define(Symbol.getInstance("*"), Multiply.getInstance());
		environment.define(Symbol.getInstance("/"), Divide.getInstance());
		environment.define(Symbol.getInstance("map"), Map.getInstance());
		environment.define(Symbol.getInstance("="), EqualNumber.getInstance());
		environment.define(Symbol.getInstance("exit"), Exit.getInstance());
		environment.define(Symbol.getInstance("quote"), Quote.getInstance());
		environment.define(Symbol.getInstance("define"), Define.getInstance());
		environment.define(Symbol.getInstance("lambda"), Lambda.getInstance());
		try {
			while(true) {
				try {
					System.out.print("lisp> ");
					SExpression exp = reader.read();
					SExpression value = Evaluator.eval(exp, environment);
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
