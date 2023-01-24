package com.example.springdatademo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "my_store")
public class OrderProduct {
    @Id
    @GeneratedValue(generator = "sequence-generator12")
    @GenericGenerator(
            name = "sequence-generator12",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "my_store.my_store_id_seq")
            }
    )

    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_order_id",nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "fk_product_id",nullable = false)
    private Product product;
}
