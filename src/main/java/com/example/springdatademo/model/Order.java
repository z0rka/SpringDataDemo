package com.example.springdatademo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDate;
import java.util.List;

/**
 * Class Order that works as entity for table order
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "my_store")
public class Order {

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
    private LocalDate creationDate;
    private float cost;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderProduct> orderProducts;
}
