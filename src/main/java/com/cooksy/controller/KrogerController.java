package com.cooksy.controller;

import com.cooksy.client.KrogerClient;
import com.cooksy.dto.KrogerResultDto;
import com.cooksy.model.api.KrogerResult;
import com.cooksy.util.converter.api.KrogerResultToKrogerResultDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("http://localhost:4200")
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class KrogerController {

    private final KrogerClient krogerClient;
    private final KrogerResultToKrogerResultDtoConverter converter;

    @GetMapping(value = "/{product}", produces = APPLICATION_JSON_VALUE)
    public KrogerResultDto getProducts(@PathVariable("product") String product,
                                       @RequestParam(required = false) String page) {
        KrogerResult krogerProducts = krogerClient.getKrogerProducts(product, page, KrogerResult.class);
        KrogerResultDto krogerResultDto = converter.convert(krogerProducts);
        log.info(String.format("Returned %s offset %s", product, page));
        return krogerResultDto;
    }

}
