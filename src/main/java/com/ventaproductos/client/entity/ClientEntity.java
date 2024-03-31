package com.ventaproductos.client.entity;
import com.ventaproductos.order.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")

public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String address;

    @OneToMany(mappedBy = "client")
    private List<OrderEntity> orders;

}


//función que altera una clase dentro del código