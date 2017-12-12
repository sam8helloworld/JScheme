package lisp.eval;

/**
 * Cons cell (ドット対)
 * @author sam0830
 *
 */
public class ConsCell implements SExpression {
	private SExpression car;
	private SExpression cdr;

	public SExpression getCar() {
		return this.car;
	}
	public SExpression getCdr() {
		return this.cdr;
	}
	
	public void setCar(SExpression sexp) {
		this.car = sexp;
	}
	
	public void setCdr(SExpression sexp) {
		this.cdr = sexp;
	}
	
	public static ConsCell getInstance(SExpression car, SExpression cdr) {
		return new ConsCell(car, cdr);
	}
	
	private ConsCell(SExpression car, SExpression cdr) {
		this.car = car;
		this.cdr = cdr;
	}
	
	@Override
	public String toString() {
		return "(" + this.car + " . " + this.cdr + ")";
	}
	
	
	public static class ListBuilder {
		SExpression head = EmptyList.getInstance();
		SExpression tail = EmptyList.getInstance();
		
		public ListBuilder head(SExpression sexp) {
			head = ConsCell.getInstance(sexp, head);
			if(!(tail instanceof ConsCell)) {
				tail = head;
			}
            return this;
        }
		
		public ListBuilder tail(SExpression sexp) {
			if(tail instanceof ConsCell) {
				ConsCell consCell = (ConsCell)tail;
				consCell.setCdr(ConsCell.getInstance(sexp, consCell.getCdr()));
				tail = ((ConsCell) tail).getCdr();
			} else {
				tail = ConsCell.getInstance(sexp, tail);
				head = tail;
			}
			return this;
        }
		
		public ListBuilder last(SExpression sexp) {
            if (tail instanceof ConsCell) {
            	((ConsCell) tail).setCdr(sexp);
            } else {
            	tail = sexp;
            	head = tail;
            }
            return this;
        }
		
		public SExpression build() {
            return head;
        }
	}
	
	public static ListBuilder builder() { 
		return new ListBuilder(); 
	}
}
