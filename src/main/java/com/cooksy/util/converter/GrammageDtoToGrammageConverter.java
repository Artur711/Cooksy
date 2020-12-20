package com.cooksy.util.converter;

import com.cooksy.dto.GrammageDto;
import com.cooksy.model.Grammage;
import org.springframework.stereotype.Component;

@Component
public class GrammageDtoToGrammageConverter {

    public Grammage convert(GrammageDto grammageDto) {
        return new Grammage(grammageDto.getGmId(),
                grammageDto.getQuantity(),
                grammageDto.getGrammage());
    }
}
