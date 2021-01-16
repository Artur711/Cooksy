package com.cooksy.util.converter.api;

import com.cooksy.dto.KrogerProductDto;
import com.cooksy.dto.KrogerResultDto;
import com.cooksy.model.api.KrogerResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class KrogerResultToKrogerResultDtoConverter {

    private final KrogerProductToKrogerProductDtoConverter krogerProductToKrogerProductDtoConverter;

    public KrogerResultDto convert(KrogerResult krogerResult) {
        List<KrogerProductDto> krogerProductsDto = krogerProductToKrogerProductDtoConverter.convertAll(krogerResult.getProductsList());

        return new KrogerResultDto(krogerResult.getMeta().getPagination().getStart(),
                krogerResult.getMeta().getPagination().getLimit(),
                krogerResult.getMeta().getPagination().getTotal(),
                krogerProductsDto);
    }

}
