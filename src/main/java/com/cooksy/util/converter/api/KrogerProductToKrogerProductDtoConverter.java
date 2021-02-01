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
        return new KrogerProductDto(krogerProduct.getStrProductId(),
                krogerProduct.getDescription(),
                getRegularPrice(krogerProduct),
                getPromoPrice(krogerProduct),
                krogerProduct.getKrogerItems().get(0).getSize(),
                getURL(krogerProduct).orElse(""));
    }

    public List<KrogerProductDto> convertAll(List<KrogerProduct> products) {
        return products.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private Double getRegularPrice(KrogerProduct krogerProduct) {
        try {
            return krogerProduct.getKrogerItems().get(0).getPrice().getRegularPrice();
        }
        catch (NullPointerException e) {
            return 0D;
        }
    }

    private Double getPromoPrice(KrogerProduct krogerProduct) {
        try {
            return krogerProduct.getKrogerItems().get(0).getPrice().getPromoPrice();
        }
        catch (NullPointerException e) {
            return  0D;
        }
    }

    private Optional<String> getURL(KrogerProduct krogerProduct) {
        return krogerProduct.getKrogerImageList().stream()
                .filter(image -> image.getPerspective().equals("front"))
                .map(KrogerImage::getSizes)
                .findAny()
                .get()
                .stream()
                .filter(size -> size.getSize().equals("xlarge"))
                .map(KrogerImage.Size::getUrl)
                .findAny();
    }
}
