package com.company.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@NamedEntityGraph(
        name = "product-graph-entity-with-product-info",
        attributeNodes = {
                @NamedAttributeNode("productInfo")
        }
)
public class ProductEntity {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_slug", nullable = false, unique = true)
    private String url;

    @Column(name = "price")
    private String price;

    @Column(name = "product_photo")
    private String photoUrl;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "cart_id")
    private Long cartId;

    // Rating Service connection

    // Review Service connection

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
    private ProductInfoEntity productInfo;

    @ManyToOne
    @JoinColumn(name = "brand_slug", referencedColumnName = "brand_slug", nullable = false)
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "category_slug", referencedColumnName = "category_slug", nullable = false)
    private CategoryEntity category;
}
