package com.ftn.sbnz.model.models;

import javax.persistence.*;

import org.mvel2.util.Make.List;

import com.ftn.sbnz.model.models.enums.SkinBenefit;
import com.ftn.sbnz.model.models.enums.SkinType;

@Entity
public class Product {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

    private double price;

    private String instruction;

    private boolean vegan;

    //@ElementCollection(targetClass = SkinType.class)
    @Enumerated(EnumType.STRING)
    private List<SkinType> skinTypes;
 
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cream_id")
    private List<SkinBenefit> benefits;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Feedback> reviews;

    @ManyToMany
    @JoinTable(
        name = "product_ingredient",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;
    
}
