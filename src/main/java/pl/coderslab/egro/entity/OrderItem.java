package pl.coderslab.egro.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String serialNumber;
    private double price;
    private int quantity;
    @ManyToOne
    private Order order;


}
