package com.example.vrv_assignment.vrv_assignment.item;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {
    @Id
    private Long id;
    private String name;
    private String description;
    private String category;
}
