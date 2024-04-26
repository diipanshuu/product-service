package dev.dipanshu.productservice.repositories.projections;

// Similar to DTOs except to get data from db to our app
public interface ProductWithTitleAndId {
    Long getId();
    String getTitle();
}
