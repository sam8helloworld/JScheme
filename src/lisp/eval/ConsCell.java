package lisp.eval;

/**
 * ConsCell(ドット対)のS式を表す
 * cdrが空リストの時はリスト
 * @author sam0830
 * @version 1.0
 */
public class ConsCell implements SExpression {
	private SExpression car;
	private SExpression cdr;
	
	/**
	 * getter
	 * carを返す
	 * @return carのS式
	 */
	public SExpression getCar() {
		return this.car;
	}
	
	/**
	 * getter
	 * cdrを返す
	 * @return cdrのS式
	 */
	public SExpression getCdr() {
		return this.cdr;
	}
	
	/**
	 * setter
	 * carにS式をセットする
	 * @param sexp セットするS式
	 */
	public void setCar(SExpression sexp) {
		this.car = sexp;
	}
	
	/**
	 * setter
	 * cdrにS式をセットする
	 * @param sexp セットするS式
	 */
	public void setCdr(SExpression sexp) {
		this.cdr = sexp;
	}
	
	/**
	 * 指定されたインデックスのS式を取得する
	 * @param n インデックス
	 * @return 指定されたインデックスのS式
	 */
	public SExpression get(int n) {
		if(n==0) {
			return this.car;
		}
		if(this.cdr instanceof EmptyList) {
			return EmptyList.getInstance();
		}
		return ((ConsCell)this.cdr).get(n-1);
	}
	
	/**
	 * リストのサイズを返す
	 * @return リストのサイズを表す整数
	 */
	public int size() {
		int size = 0;
		SExpression sexp = this;
		while(!(sexp instanceof EmptyList)) {
			size++;
			sexp = ((ConsCell)sexp).getCdr();
		}
		return size;
	}
	
	/**
	 * ConsCellがリストかどうかを返す
	 * @return リストかどうかの真理値
	 */
	public boolean isList() {
		SExpression tmp = this;
		while(tmp instanceof ConsCell) {
			tmp = ((ConsCell)tmp).getCdr();
		}
		if(tmp instanceof EmptyList) {
			return true;
		}
		return false;
	}
	
	/**
	 * Cdrのインスタンスを返す
	 * @return 組み込み手続きCdr
	 */
	public static ConsCell getInstance(SExpression car, SExpression cdr) {
		return new ConsCell(car, cdr);
	}
	
	/**
	 * ConsCellのコンストラクタ
	 * @param car carに設定するS式
	 * @param cdr cdrに設定するS式
	 */
	private ConsCell(SExpression car, SExpression cdr) {
		this.car = car;
		this.cdr = cdr;
	}
	
	@Override
	public String toString() {
		/*
		 * 純粋なConsCellの時
		 * (1 . 2)
		 * (1)
		 * (() . 1)
		 */
		if(!(this.car instanceof ConsCell)&&!(this.cdr instanceof ConsCell)) {
			// cdrが空リスト
			if(this.cdr instanceof EmptyList) {
				return "(" + this.car + ")";
			}
			// cdrが空リストでない
			return "(" + this.car + " . " + this.cdr + ")";
		}
		/*
		 * リスト
		 */
		SExpression tmp = this;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("(");
		while(tmp instanceof ConsCell) {
			stringBuilder.append(((ConsCell)tmp).getCar());
			stringBuilder.append(" ");
			tmp = ((ConsCell)tmp).getCdr();
		}
		if(!(tmp instanceof EmptyList)) {
			stringBuilder.append(".");
			stringBuilder.append(" ");
			stringBuilder.append(tmp);
			stringBuilder.append(" ");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		stringBuilder.append(")");
		return stringBuilder.toString();
		
	}
	
	/**
	 * ConsCellの中でもリストの作成をするクラス
	 * @author sam0830
	 * @version 1.0
	 */
	public static class ListBuilder {
		SExpression head = EmptyList.getInstance();
		SExpression tail = EmptyList.getInstance();
		
	    /**
	     * リストの先頭にS式を追加する
	     * @param sexp リストの先頭に追加するS式
	     * @return 更新済みのListBuilder
	     */
		public ListBuilder head(SExpression sexp) {
			head = ConsCell.getInstance(sexp, head);
			if(!(tail instanceof ConsCell)) {
				tail = head;
			}
            return this;
        }
		
		/**
		 * リストの末尾にS式を追加する
	     * @param sexp リストの末尾に追加するS式
	     * @return 更新済みのListBuilder
		 */
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
		
		/**
		 * リストの末尾のcdrにS式を追加する
		 * @param sexp リストの末尾のcdrに追加するS式
		 * @return 更新済みのListBuilder
		 */
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
	
	/**
	 * ListBuilderのインスタンスを返す
	 * @return ListBuilderのインスタンス
	 */
	public static ListBuilder builder() { 
		return new ListBuilder(); 
	}
}
