package com.magattech.certGen.model.request.print;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JednodelnoMeriloPrintRequest {
    private String brojZapisnika;
}
