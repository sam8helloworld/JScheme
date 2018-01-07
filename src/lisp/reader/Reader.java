package lisp.reader;

import java.io.BufferedReader;
import java.io.IOException;
import lisp.eval.Bool;
import lisp.eval.ConsCell;
import lisp.eval.Int;
import lisp.eval.LispString;
import lisp.eval.SExpression;
import lisp.eval.Symbol;
import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 構文解析器
 * @author sam0830
 * @version 1.0
 */
public class Reader {
	/**
	 * 字句解析器
	 */
	private Lexer lexer;

	/**
	 * 先読みの字句
	 */
	private Token token = null;
	
	/**
	 * 括弧の入れ子レベル
	 * 式を読み終えた時にnestingLevelが0ならば、そこまでの式を評価する
	 */
	private int nestingLevel;
	
	/**
	 * コンストラクタ
	 * @param bufferedReader 入力
	 */
	public Reader(BufferedReader bufferedReader) {
		this.lexer = new Lexer(bufferedReader);
	}
	
	/**
	 * <pre>
	 * {@literal <S式>} ::= {@literal <整数値>} | {@literal <記号>} | {@literal <真理値>} | '(' ({@literal <S式>} '.' {@literal <S式>})? ')' 
	 * </pre>
	 * @return S式
	 * @throws LispException 
	 * @throws IOException 
	 */
	SExpression sExpression() throws IOException, LispException {
		// 整数値
		if(this.token.getKind() == Token.Kind.INTEGER) {
			int value = this.token.getIntValue();
			if(this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
			}
			return Int.valueOf(value);
		}
		
		// 実数
		if(this.token.getKind() == Token.Kind.REALNUMBER) {
			double value = this.token.getDoubleValue();
			if(this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
			}
			return lisp.eval.Double.valueOf(value);
		}
		
		// 文字列
		if(this.token.getKind() == Token.Kind.STRING) {
			String value = this.token.getStringValue();
			if(this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
			}
			return LispString.getInstance(value);
		}
		
		// 記号
		if (this.token.getKind() == Token.Kind.SYMBOL) {
			String value = this.token.getSymbolValue();
			if (this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
			}
			return Symbol.getInstance(value);
		}
		
		// 真理値
		if (this.token.getKind() == Token.Kind.BOOLEAN) {
			boolean value = this.token.getBooleanValue();
			if (this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
			}
			return Bool.valueOf(value);
		}
		
		// '(クォート)
		if(this.token.getKind() == Token.Kind.QUOTE) {
			this.token = this.lexer.getNextToken();
			ConsCell.ListBuilder listBuilder = ConsCell.builder();
			listBuilder.tail(Symbol.getInstance("quote"));
			listBuilder.tail(sExpression());
			return listBuilder.build();
		}
		
		// '(' ')' or '(' <S式> . <S式> ')' or '(' <S式> <S式> ...<S式> ')'
		if (this.token.getKind() == Token.Kind.LEFTPAR) {
			this.nestingLevel++;
			this.token = this.lexer.getNextToken();
			
			ConsCell.ListBuilder listBuilder = ConsCell.builder();
			while(true) {
				// 右括弧
				if(this.token.getKind() == Token.Kind.RIGHTPAR) {
					this.nestingLevel--;
					if (this.nestingLevel != 0) { // 式が未完成
						this.token = this.lexer.getNextToken();
					}
					return listBuilder.build();
				}
				// car
				SExpression car = sExpression();
				
				// ドット対
				if (this.token.getKind() == Token.Kind.DOT) {
					this.token = this.lexer.getNextToken();
					// cdr
					SExpression cdr = sExpression();
					if (this.token.getKind() != Token.Kind.RIGHTPAR) {
						throw new SyntaxErrorException("')' expected");
					}
					this.nestingLevel--;
					if (this.nestingLevel != 0) { // 式が未完成
						this.token = this.lexer.getNextToken();
					}
					return ConsCell.getInstance(car, cdr);
				}
				listBuilder.tail(car);
			}
		}
		
		throw new SyntaxErrorException("Invalid expression:" + this.token.getKind());
	}
	
	/**
	 * 
	 * @return 構文解析後のS式
	 * @throws IOException 
	 * @throws LispException
	 */
	public SExpression read() throws IOException, LispException {
		this.nestingLevel = 0;
		this.token =  this.lexer.getNextToken();
		return sExpression();
	}
}
