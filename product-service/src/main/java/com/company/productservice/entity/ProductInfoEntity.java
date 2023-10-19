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
@Table(name = "products_info")
public class ProductInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "series")
    private String series;

    @Column(name = "height")
    private String height;

    @Column(name = "width")
    private String width;

    @Column(name = "weight")
    private String weight;

    @Column(name = "os")
    private String os;

    @Column(name = "display")
    private String display;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "graphic_card")
    private String graphicCard;

    @Column(name = "gpu")
    private String gpu;

    @Column(name = "ram_type")
    private String ramType;

    @Column(name = "ram")
    private String ram;

    @Column(name = "memory_type")
    private String memoryType;

    @Column(name = "memory")
    private String memory;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "product_slug", referencedColumnName = "product_slug")
    private ProductEntity product;
}
