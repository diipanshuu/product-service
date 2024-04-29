package dev.dipanshu.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Category extends BaseModel{
    private String title;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Product> products;

    /*
    *   When it comes to JOIN the type doesn't matter,
    *   but SELECT and SUBSELECT do care. When we do
    *   select modes between select or join as modes SELECT is mostly used for queries.
    *   SUBSELECT which occurs in two queries, whether it is lazy or eager.
    *   If we prefer not to access the data immediately, so SUBSELECT also considers lazy loading.
    */

}
