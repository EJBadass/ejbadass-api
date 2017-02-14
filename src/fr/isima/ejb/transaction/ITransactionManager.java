package fr.isima.ejb.transaction;

import java.util.Stack;

public class ITransactionManager {
	// see java thread local
	private static final ThreadLocal<Stack<Transaction>> transactions = new ThreadLocal<Stack<Transaction>>() {
		@Override
        protected Stack<Transaction> initialValue()
        {
            return new Stack<Transaction>();
        }
	};
	
	
	
}


/*******************

1- etat des lieux
2- taux de completude
3- reussi pas reussi
4- amelioration
5- nombre de lignes de code

********************/

// TODO voir les problemes d'annotaions sur les interfaces