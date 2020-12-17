package com.cooksy.util.converter;

import com.cooksy.dto.GrammageDto;
import com.cooksy.model.Grammage;
import org.springframework.stereotype.Component;

@Component
public class GrammageToGrammageDtoConverter {

    public GrammageDto convert(Grammage grammage) {
        return new GrammageDto(grammage.getGmId(),
                grammage.getQuantity(),
                grammage.getGrammage());
    }
}
