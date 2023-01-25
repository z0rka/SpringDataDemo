package com.example.springdatademo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.List;

/**
 * Class Product that works as entity for table product
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "my_store")
public class Product {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "my_store.my_store_id_seq")
            }
    )
    private Integer id;
    private String name;
    private float cost;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    @JsonIgnore
    List<OrderProduct> orderProducts;
}
