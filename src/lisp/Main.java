package lisp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lisp.eval.SExpression;
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
	}

	public static void main(String[] args) throws IOException {
		printGreetingMessage();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		Reader reader = new Reader(bufferedReader);
		//TODO:Environment environment = new Environment();
		
		try {
			while(true) {
				try {
					System.out.print("lisp> ");
					SExpression exp = reader.read();
					System.out.println(exp);
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
