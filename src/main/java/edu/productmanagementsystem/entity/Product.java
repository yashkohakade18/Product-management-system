package edu.productmanagementsystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private int id;
private String productname;
private String brand;
private double  price;

@CreationTimestamp
private LocalDateTime createdAt;

@UpdateTimestamp LocalDate updatedAt;
}
