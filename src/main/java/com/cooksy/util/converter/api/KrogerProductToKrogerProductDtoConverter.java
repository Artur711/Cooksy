package com.cooksy.util.converter.api;

import com.cooksy.dto.KrogerProductDto;
import com.cooksy.model.api.KrogerImage;
import com.cooksy.model.api.KrogerProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class KrogerProductToKrogerProductDtoConverter {

    public KrogerProductDto convert(KrogerProduct krogerProduct) {
        Optional<String> maybeUrl = krogerProduct.getKrogerImageList().stream()
                .filter(image -> image.getPerspective().equals("front"))
                .map(KrogerImage::getSizes)
                .findAny()
                .get()
                .stream()
                .filter(size -> size.getSize().equals("xlarge"))
                .map(KrogerImage.Size::getUrl)
                .findAny();

        return new KrogerProductDto(krogerProduct.getStrProductId(),
                krogerProduct.getDescription(),
                krogerProduct.getKrogerItems().get(0).getPrice().getRegularPrice(),
                krogerProduct.getKrogerItems().get(0).getPrice().getPromoPrice(),
                krogerProduct.getKrogerItems().get(0).getSize(),
                maybeUrl.orElse(""));
    }

    public List<KrogerProductDto> convertAll(List<KrogerProduct> products) {
        return products.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
