package com.magattech.certGen.model.request;

import com.magattech.certGen.model.enums.OpremaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpremaRequest {
    private OpremaType tip;
    private String serBrEtalona;
}
