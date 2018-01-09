package lisp.eval;

import java.util.HashMap;
import java.util.Map;

import lisp.exception.LispException;
import lisp.exception.UnboundException;

/**
 * 環境
 * @author sam0830
 * @version 1.0
 */
public class Environment {
	private final Map<Symbol, SExpression> frame = new HashMap<>();
	private final Environment next;
	
	/**
	 * 環境のコンストラクタ
	 * @param next 次に検索する環境
	 */
	public Environment(Environment next) {
		this.next = next;
	}
	
	/**
	 * 自身の環境のフレームを返す
	 * @return 環境のフレーム
	 */
	public Map<Symbol, SExpression> getFrame() {
		return this.frame;
	}
	
	/**
	 * どの環境に引数で指定されたシンボルがあるか検索する
	 * @param environment 検索開始の環境
	 * @param key 検索キー
	 * @return 検索結果の環境
	 * @throws LispException 環境にシンボルがない時
	 */
	private static Environment find(Environment environment, Symbol key) throws LispException {
		for(Environment e = environment; e != null; e=e.next) {
			if(e.frame.containsKey(key)) {
				return e;
			}
		}
		throw new UnboundException("unbound variable: "+key.toString());
	}
	
	/**
	 * getter
	 * 引数で指定されたシンボルに束縛されているS式を返す
	 * @param key 検索キー
	 * @return シンボルに束縛されたS式
	 * @throws LispException 環境にシンボルがない時
	 */
	public SExpression get(Symbol key) throws LispException {
		return find(this, key).getFrame().get(key);
	}
	
	/**
	 * setter
	 * 引数で指定されたシンボルに引数で指定されたS式を束縛する
	 * 環境にシンボルがない場合は束縛できない
	 * @param key 検索キー
	 * @param value 束縛するS式
	 * @return 束縛済みのS式
	 * @throws LispException 環境にシンボルがない時
	 */
	public SExpression set(Symbol key, SExpression value) throws LispException { 
		find(this, key).getFrame().put(key, value);
		return value;
	}
	
	/**
	 * 引数で指定されたシンボルに引数で指定されたS式を束縛する
	 * 環境にシンボルがなくても束縛できる
	 * @param key 検索キー
	 * @param value 束縛するS式
	 * @return 束縛済みのS式
	 */
	public SExpression define(Symbol key, SExpression value) {
		frame.put(key, value);
		return value;
	}
}