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
        Integer total = krogerResult.getMeta().getPagination().getTotal();

        return new KrogerResultDto(krogerResult.getMeta().getPagination().getStart(),
                krogerResult.getMeta().getPagination().getLimit(),
                (total > 1000) ? 1000 : total,
                krogerProductsDto);
    }

}
