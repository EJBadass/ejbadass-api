package fr.isima.ejb.transaction;

public interface ITransaction {
	void begin();
	void commit();
	void rollback();
}
