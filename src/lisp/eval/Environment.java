package lisp.eval;

import java.util.HashMap;
import java.util.Map;

import lisp.exception.LispException;
import lisp.exception.UnboundException;

/**
 * 環境
 * @author sam0830
 *
 */
public class Environment {
	private final Map<Symbol, SExpression> frame = new HashMap<>();
	
	private final Environment next;
	
	public Environment(Environment next) {
		this.next = next;
	}
	
	private static Environment find(Environment environment, Symbol key) throws LispException {
		for(Environment e = environment; e != null; e=e.next) {
			if(e.frame.containsKey(key)) {
				return e;
			}
		}
		throw new UnboundException("unbound variable: "+key.toString());
	}
	
	public SExpression get(Symbol key) throws LispException {
		return find(this, key).frame.get(key);
	}
	
	public SExpression set(Symbol key, SExpression value) throws LispException {
		find(this, key).frame.put(key, value);
		return value;
	}
	
	public SExpression define(Symbol key, SExpression value) {
		frame.put(key, value);
		return value;
	}
	
}
