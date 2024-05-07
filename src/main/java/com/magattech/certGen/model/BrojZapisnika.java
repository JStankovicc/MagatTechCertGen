package com.magattech.certGen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_broj_zapisnika")
public class BrojZapisnika {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int broj;
    private int godina;

    @Override
    public String toString() {
        String string = broj + "/" + godina + "-K";
        return string;
    }
}
