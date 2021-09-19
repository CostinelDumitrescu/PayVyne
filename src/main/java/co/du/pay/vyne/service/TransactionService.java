package co.du.pay.vyne.service;

import co.du.pay.vyne.model.CreateTransactionResult;
import co.du.pay.vyne.model.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionService {

    /**
     * Retrieves a transaction
     *
     * @param id - a transaction id
     * @return a Mono emitting a Transaction entity
     */
    Mono<Transaction> retrieve(Long id);

    /**
     * Creates a transaction
     *
     * @param transaction - a Transaction entity
     * @return Mono emitting a CreateTransactionResult
     */
    Mono<CreateTransactionResult> create(Transaction transaction);

    /**
     * Updates a transaction
     *
     * @param id - a transaction id
     * @param transaction - a Transaction entity
     */
    void update(Long id, Transaction transaction);

    /**
     * Removes a transaction
     *
     * @param id - a transaction id
     */
    void remove(Long id);
}
