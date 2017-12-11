package lisp.eval;

/**
 * Closure
 * @author sam0830
 *
 */
public class Closure implements SExpression {	
	private SExpression body;
	private SExpression params;
	private Environment environment;
	
	
	public SExpression apply(SExpression sexp, Environment environment) {
		// 環境をもうひとつ作成
		Environment env = new Environment(this.environment);
		// 引数を環境にセット
		SExpression tmpParams = sexp; // 実引数
		SExpression tmpParamsVirtual = params; // 仮引数
		while(true) {
			env.define(Symbol.getInstance((((ConsCell)tmpParamsVirtual).getCar()).toString()), ((ConsCell)tmpParams).getCar());
			if((((ConsCell)tmpParamsVirtual).getCdr() instanceof EmptyList)) {
				break;
			}
			tmpParamsVirtual = ((ConsCell)tmpParamsVirtual).getCdr();
			tmpParams = ((ConsCell)tmpParams).getCdr();
		}
		SExpression result = Evaluator.eval(this.body, env);
		return result;
	}
	
	
	private Closure(SExpression body, SExpression params, Environment env) {
		this.body = body;
		this.params = params;
		this.environment = env;
	}
	
	public static Closure getInstance(SExpression body, SExpression params, Environment env) {
		return new Closure(body, params, env);
	} 
}
