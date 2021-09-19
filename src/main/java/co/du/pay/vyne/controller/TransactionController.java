package co.du.pay.vyne.controller;

import co.du.pay.vyne.exception.ResourceNotFoundException;
import co.du.pay.vyne.model.CreateTransactionResult;
import co.du.pay.vyne.model.Transaction;
import co.du.pay.vyne.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping(ControllerApiHelper.BASE_PATH)
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Retrieves a transaction by ID
     *
     * V1
     *
     * @param id The ID
     * @return The transaction.
     * @throws ResourceNotFoundException
     */
    @GetMapping(
            value= ControllerApiHelper.RETRIEVE,
            produces = ControllerApiHelper.V1_HEADER)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public Mono<Transaction> retrieveV1(
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        log.info("Received request to retrieve transaction - id={}", id);
        return transactionService.retrieve(id).flatMap(transaction -> {
            transaction.setDescription(transaction.getDescription() + "_V1");
            log.info("transaction = {} ", transaction);
            return Mono.just(transaction);
        });
    }

    /**
     * Retrieves a transaction by ID
     *
     * V2
     *
     * @param id The ID
     * @return The transaction.
     * @throws ResourceNotFoundException
     */
    @GetMapping(
            value= ControllerApiHelper.RETRIEVE,
            produces = ControllerApiHelper.V2_HEADER)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public Mono<Transaction> retrieveV2(
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        log.info("Received request to retrieve transaction - id={}", id);
        return transactionService.retrieve(id).flatMap(transaction -> {
            transaction.setDescription(transaction.getDescription() + "_V2");
            log.info("transaction = {} ", transaction);
            return Mono.just(transaction);
        });
    }

    /**
     * Creates a transaction
     *
     * @param transaction The transaction
     * @return A CreateTransactionResult instance
     */
    @PostMapping(ControllerApiHelper.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public Mono<CreateTransactionResult> create(
            @RequestBody Transaction transaction) {
        log.info("Received request to create transaction");
        return transactionService.create(transaction).flatMap(createTransactionResult -> {
            log.info("createTransactionResult = {} ", createTransactionResult);
            return Mono.just(createTransactionResult);
        });
    }

    /**
     * Updates an existing transaction by id
     *
     * @param id The transaction's id
     * @param transaction The new entity
     * @throws ResourceNotFoundException
     */
    @PutMapping(ControllerApiHelper.UPDATE)
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public void update(
            @PathVariable(value = "id") Long id,
            @RequestBody Transaction transaction)
            throws ResourceNotFoundException {
        log.info("Received request to update transaction - id={}", id);
        transactionService.update(id, transaction);
    }

    /**
     * Deletes a transaction
     *
     * @return The transaction.
     * @throws ResourceNotFoundException
     */
    @DeleteMapping(ControllerApiHelper.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void remove(
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        log.info("Received request to remove transaction - id={}", id);
        this.transactionService.remove(id);
    }
}
