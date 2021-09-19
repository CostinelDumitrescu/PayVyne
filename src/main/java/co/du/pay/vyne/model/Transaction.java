package co.du.pay.vyne.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("PAY_VYNE_TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @Column("ID")
    private Long id;

    @Column("STATUS")
    private String status;

    @Column("aDATE")
    private Instant date;

    @Column("AMOUNT")
    private Double amount;

    @Column("CURRENCY")
    private String currency;

    @Column("DESCRIPTION")
    private String description;
}