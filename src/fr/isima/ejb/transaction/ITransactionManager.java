package fr.isima.ejb.transaction;

import fr.isima.ejb.injection.IInterceptor;

public interface ITransactionManager {

	public Transaction getTransaction(IInterceptor inter, boolean isRequired);
	public void close();	
	
}


/*******************

1- etat des lieux
2- taux de completude
3- reussi pas reussi
4- amelioration
5- nombre de lignes de code

********************/

// TODO voir les problemes d'annotaions sur les interfaces