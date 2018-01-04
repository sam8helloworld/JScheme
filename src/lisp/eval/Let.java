package lisp.eval;

import lisp.exception.LispException;

/**
 * let
 * @author sam0830
 *
 */
public class Let implements SpecialForm{
	private static final int BINDS_SIZE = 2;
	private static final int DAMMY_ARGUMENT_NUMBER = 0;
	private static final int ACTUAL_ARGUMENT_NUMBER = 0;
	private static final Let let = new Let();
	
	public static Let getInstance() {
		return let;
	}
	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		if(!(sexp instanceof ConsCell)) {
			throw new RuntimeException("引数");
		}
		SExpression binds = ((ConsCell)sexp).getCar();
		SExpression body = EmptyList.getInstance();
		if(((ConsCell)sexp).getCdr() instanceof ConsCell) {
			body = ((ConsCell)sexp).get(1);
		}
		if(!(binds instanceof ConsCell)) {
			throw new RuntimeException("引数がリストでない");
		}
		int listSize = ((ConsCell)binds).size();
		ConsCell.ListBuilder dammyArg = ConsCell.builder();
		ConsCell.ListBuilder actualArg = ConsCell.builder();
		for(int i=0;i<listSize;i++) {
			SExpression list = ((ConsCell)binds).get(i);
			if(!(list instanceof ConsCell)) {
				throw new RuntimeException("引数がリストでない");
			}
			if(((ConsCell)list).size() != BINDS_SIZE) {
				throw new RuntimeException("引数が2個でない");
			}
			dammyArg.tail(((ConsCell)list).get(DAMMY_ARGUMENT_NUMBER));
			actualArg.tail(((ConsCell)list).get(ACTUAL_ARGUMENT_NUMBER));
		}
		ConsCell.ListBuilder listBuilder = ConsCell.builder();
		ConsCell.ListBuilder lambdaListBuilder = ConsCell.builder();
		lambdaListBuilder.tail(Symbol.getInstance("lambda"));
		lambdaListBuilder.tail(dammyArg.build());
		lambdaListBuilder.tail(body);
		listBuilder.tail(lambdaListBuilder.build());
		listBuilder.last(actualArg.build());
		return Evaluator.eval(listBuilder.build(), environment);
	}

}
