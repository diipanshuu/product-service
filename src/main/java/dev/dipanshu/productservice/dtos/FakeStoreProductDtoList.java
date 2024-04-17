package dev.dipanshu.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FakeStoreProductDtoList {
    private List<FakeStoreProductDto> products;

    public List<FakeStoreProductDto> getProducts() {
        return this.products;
    }
}
