package com.example.web_app.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name can not be empty")
    @Size(min = 3, max = 50)
    private String name;

    @NotEmpty(message = "description can not be empty")
    @Column(length = 1024)
    private String description;

    @NotEmpty(message = "price can not be empty")
    private Double price;

    @NotEmpty(message = "is_sale can not be empty")
    private Boolean isSale;

}
