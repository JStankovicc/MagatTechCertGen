package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.service.PDFGeneratorService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.awt.Desktop;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.html2pdf.HtmlConverter;

@Service
@RequiredArgsConstructor
public class PDFGeneratorServiceImpl implements PDFGeneratorService {
    @Override
    public byte[] generateJednodelnoMerilo(JednodelnoMerilo jednodelnoMerilo) {

        return null;

    }
}
