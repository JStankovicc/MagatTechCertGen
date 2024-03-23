package com.magattech.certGen.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OdobrenjeRequest {

    String token;
    String tip;
    String brojZapisnika;

}
