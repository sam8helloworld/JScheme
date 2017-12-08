package lisp.eval;

import java.util.HashMap;
import java.util.Map;

/**
 * 環境
 * @author sam0830
 *
 */
public class Environment {
	//private final Map<Symbol, SExpression> frame = new HashMap<>();
	
	//private final Environment next;
	/*
	public Environment(Environment next) {
		this.next = next;
	}
	*/
	
	public Environment() {
		
	}
	
	/*
	private static Environment find(Environment environment, Symbol key) {
		for(Environment e = environment; e != null; e=e.next) {
			if(e.frame.containsKey(key)) {
				return e;
			}
		}
		throw new RuntimeException("Variable " + key + " not found");
	}
	
	public SExpression get(Symbol key) {
		return find(this, key).frame.get(key);
	}
	
	public SExpression set(Symbol key, SExpression value) {
		find(this, key).frame.put(key, value);
		return value;
	}
	
	public SExpression define(Symbol key, SExpression value) {
		frame.put(key, value);
		return value;
	}
	*/
	
}
