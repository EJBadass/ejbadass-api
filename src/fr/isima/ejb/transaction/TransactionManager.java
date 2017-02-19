package fr.isima.ejb.transaction;

import java.util.Stack;

import fr.isima.ejb.injection.IInterceptor;

public class TransactionManager implements ITransactionManager {

	private static final ThreadLocal<Stack<Transaction>> transactions = new ThreadLocal<Stack<Transaction>>() {
		@Override
        protected Stack<Transaction> initialValue() {
            return new Stack<Transaction>();
        }
	};
	
	@Override
	public Transaction getTransaction(IInterceptor inter, boolean isRequired) {
		Transaction tr = null;
		
		if(transactions.get().size()>0 && isRequired) {
			tr = transactions.get().peek();
		} else {
			if(transactions.get().size()>0) transactions.get().peek().sleep();
			tr = new Transaction();
			tr.setCaller(inter);
			transactions.get().push(tr);
		}
		
		return tr;
	}
	
	public void close() {
		transactions.get().pop().sleep();
		if(!transactions.get().isEmpty())
			transactions.get().peek().awake();
	}
	
}


/*******************

1- etat des lieux
2- taux de completude
3- reussi pas reussi
4- amelioration
5- nombre de lignes de code

********************/

// TODO voir les problemes d'annotaions sur les interfaces