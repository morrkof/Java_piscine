package day01.ex04;

import java.util.UUID;

public interface TransactionsList {
    void add(Transaction transaction);
    void removeById(UUID id);
    Transaction[] toArray();
}
