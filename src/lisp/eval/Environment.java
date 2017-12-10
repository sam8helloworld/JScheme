package lisp.eval;

import java.util.HashMap;
import java.util.Map;

/**
 * 環境
 * @author sam0830
 *
 */
public class Environment {
	private Map<Symbol, SExpression> frame = new HashMap<>();
	
	//private Environment next;
	
	public Environment(Environment next) {
		//this.next = next;
	}
	
	private static Environment find(Environment environment, Symbol key) {
//		for(Environment e = environment; e != null; e=e.next) {
//			if(e.frame.containsKey(key)) {
//				return e;
//			}
//		}
		//return this;
		throw new RuntimeException("Variable " + key + " not found");
	}
	
	public SExpression get(Symbol key) {
		//return find(this, key).frame.get(key);
		return frame.get(key);
	}
	
	public SExpression set(Symbol key, SExpression value) {
		find(this, key).frame.put(key, value);
		return value;
	}
	
	public SExpression define(Symbol key, SExpression value) {
		frame.put(key, value);
		return value;
	}
	
}
