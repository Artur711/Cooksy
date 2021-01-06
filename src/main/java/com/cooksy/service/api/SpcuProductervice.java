package com.cooksy.service.api;

import com.cooksy.model.api.SpCuProduct;
import com.cooksy.repository.api.SpcuProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpcuProductervice {

    private SpcuProductRepository spcuProductRepository;

    public void addSpCuProduct(SpCuProduct spCuProduct){
        spcuProductRepository.save(spCuProduct);
    }

}
