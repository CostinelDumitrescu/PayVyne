package co.du.pay.vyne.service;

import co.du.pay.vyne.exception.ResourceNotFoundException;
import co.du.pay.vyne.model.CreateTransactionResult;
import co.du.pay.vyne.model.Transaction;
import co.du.pay.vyne.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@Slf4j
public class DefaultTransactionService implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public DefaultTransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * @see TransactionService#retrieve(java.lang.Long)
     */
    @Override
    public Mono<Transaction> retrieve(Long id) {
        return transactionRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException()));
    }

    /**
     * @see TransactionService#create(co.du.pay.vyne.model.Transaction)
     */
    @Override
    public Mono<CreateTransactionResult> create(Transaction transaction) {
        transaction.setDate(Instant.now());
        return transactionRepository
                .save(transaction)
                .flatMap(tr -> Mono.just(CreateTransactionResult.builder().id(tr.getId()).build()));
    }

    /**
     * @see TransactionService#update(java.lang.Long, co.du.pay.vyne.model.Transaction)
     */
    @Override
    public void update(Long id, Transaction newTransaction) {
        Mono<Transaction> existingTransaction = this.retrieve(id);
        existingTransaction.subscribe(
                value -> {
                    value.setStatus(newTransaction.getStatus());
                    value.setDate(Instant.now());
                    value.setAmount(newTransaction.getAmount());
                    value.setCurrency(newTransaction.getCurrency());
                    value.setDescription(newTransaction.getDescription());
                    transactionRepository.save(value).subscribe();
                }
        );
    }

    /**
     * @see TransactionService#remove(java.lang.Long)
     */
    @Override
    public void remove(Long id) {
        transactionRepository.deleteById(id).subscribe();
    }
}
