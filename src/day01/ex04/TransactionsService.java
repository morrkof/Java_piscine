package day01.ex04;

import java.util.UUID;

public class TransactionsService {
    private UsersList list;

    public TransactionsService() {
        list = new UsersArrayList();
    }

    public void addUser(User user) {
        list.addUser(user);
    }

    public int getUserBalance(int id) {
        return list.getUserById(id).getBalance();
    }

    public void transferTransaction(int idRecipient, int idSender, int amount) {
        UUID uuidId = UUID.randomUUID();
        Transaction t1 = new Transaction(uuidId, list.getUserById(idSender), list.getUserById(idRecipient), TransferCategory.CREDITS, amount * -1);
        Transaction t2 = new Transaction(uuidId, list.getUserById(idRecipient), list.getUserById(idSender), TransferCategory.DEBITS, amount);
        TransactionsList tlistRecipient = new TransactionsLinkedList();
        tlistRecipient.add(t1);
        list.getUserById(idRecipient).setTransactionsList(tlistRecipient);

        TransactionsList tlistSender = new TransactionsLinkedList();
        tlistSender.add(t2);
        list.getUserById(idSender).setTransactionsList(tlistSender);
    }

    public Transaction [] getUserTransactions(int id) {
       return list.getUserById(id).getTransactionsList().toArray();
    }

    public void removeUserTransaction(int userId, UUID transactionId) {
        list.getUserById(userId).getTransactionsList().removeById(transactionId);
    }

    public Transaction [] checkValidity() {
        TransactionsList invalid = new TransactionsLinkedList();
        for (int i = 0; i < list.getNumberOfUsers(); i++) {
            Transaction [] tmp = list.getUserByIndex(i).getTransactionsList().toArray();
            for (Transaction t : tmp) {
                boolean valid = false;
                for (Transaction t2 : t.getRecipient().getTransactionsList().toArray()) {
                    if (t.getId() == t2.getId()) {
                        valid = true;
                        break;
                    }
                }
                if (!valid)
                    invalid.add(t);
            }
        }
        return invalid.toArray();
    }
}
