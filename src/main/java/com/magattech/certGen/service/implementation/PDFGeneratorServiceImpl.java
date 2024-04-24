package com.magattech.certGen.service.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.magattech.certGen.model.merila.*;
import com.magattech.certGen.service.PDFGeneratorService;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PDFGeneratorServiceImpl implements PDFGeneratorService {

    @Override
    public byte[] generateJednodelnoMerilo(JednodelnoMerilo jednodelnoMerilo) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "jednodelnoMeriloTemplate.docx";

        File wordFile = new File(wordFilePath);

        if (!wordFile.exists()) {
            System.err.println("Word file not found: " + wordFilePath);
            return null;
        }

        try {
            XWPFDocument doc = new XWPFDocument(new FileInputStream(wordFile));

            for (XWPFTable table : doc.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            String text = paragraph.getText();
                            if (text != null) {
                                if (text.contains("[brojZapisnika]")) {
                                    replaceText(paragraph, "[brojZapisnika]", jednodelnoMerilo.getBrojZapisnika());
                                }
                                if (text.contains("[vrstaKontrolisanja]")) {
                                    replaceText(paragraph, "[vrstaKontrolisanja]", jednodelnoMerilo.getVrstaKontrolisanja());
                                }
                                if (text.contains("[podnosilacZahteva]")) {
                                    replaceText(paragraph, "[podnosilacZahteva]", jednodelnoMerilo.getPodnosilacZahteva());
                                }
                                if (text.contains("[vlasnikKorisnik]")) {
                                    replaceText(paragraph, "[vlasnikKorisnik]", jednodelnoMerilo.getKorisnik());
                                }
                                if (text.contains("[serijskiBroj]")) {
                                    replaceText(paragraph, "[serijskiBroj]", jednodelnoMerilo.getSerijskiBroj());
                                }
                                if (text.contains("[identifikacioniBroj]")) {
                                    replaceText(paragraph, "[identifikacioniBroj]", jednodelnoMerilo.getIdentifikacioniBroj());
                                }
                                if (text.contains("[proizvodjac]")) {
                                    replaceText(paragraph, "[proizvodjac]", jednodelnoMerilo.getProizvodjac());
                                }
                                if (text.contains("[oznakaTipa]")) {
                                    replaceText(paragraph, "[oznakaTipa]", jednodelnoMerilo.getOznakaTipa());
                                }
                                if (text.contains("sluzbenaOznakaTipa")) {
                                    replaceText(paragraph, "sluzbenaOznakaTipa", jednodelnoMerilo.getSluzbenaOznakaTipa());
                                }
                                if (text.contains("[merniOpseg]")) {
                                    replaceText(paragraph, "[merniOpseg]", jednodelnoMerilo.getMerniOpseg());
                                }
                                if (text.contains("najmanjiPodeljak")) {
                                    replaceText(paragraph, "najmanjiPodeljak", jednodelnoMerilo.getNajmanjiPodeljak());
                                }
                                if (text.contains("[klasaTacnosti]")) {
                                    replaceText(paragraph, "[klasaTacnosti]", jednodelnoMerilo.getKlasaTacnosti());
                                }
                                if (text.contains("temperatura")) {
                                    replaceText(paragraph, "temperatura", jednodelnoMerilo.getTemperatura());
                                }
                                if (text.contains("[vlaznost]")) {
                                    replaceText(paragraph, "[vlaznost]", jednodelnoMerilo.getVlaznostVazduha());
                                }

                                if(jednodelnoMerilo.isMeriloJeIspravno()){
                                    if (text.contains("[cb1]")) {
                                        replaceText(paragraph, "[cb1]", "☒");
                                    }
                                    if (text.contains("[cb2]")) {
                                        replaceText(paragraph, "[cb2]", "☐");
                                    }
                                }else {
                                    if (text.contains("[cb1]")) {
                                        replaceText(paragraph, "[cb1]", "☐");
                                    }
                                    if (text.contains("[cb2]")) {
                                        replaceText(paragraph, "[cb2]", "☒");
                                    }
                                }

                                if (text.contains("[napomena1]")) {
                                    replaceText(paragraph, "[napomena1]", jednodelnoMerilo.getNapomena());
                                }
                                if (text.contains("brojMernogLenjira")) {
                                    replaceText(paragraph, "brojMernogLenjira", jednodelnoMerilo.getBrojMernogLenjira());
                                }
                                if (text.contains("brojMerneLupe")) {
                                    replaceText(paragraph, "brojMerneLupe", jednodelnoMerilo.getBrojMerneLupe());
                                }

                                List<String> skinuti = List.of(jednodelnoMerilo.getSkinutiZigovi().split(";"));
                                List<String> postavljeni = List.of(jednodelnoMerilo.getPostavljeniZigovi().split(";"));

                                if (text.contains("Skinuti1")) {
                                    replaceText(paragraph, "Skinuti1", skinuti.get(0));
                                }
                                if (text.contains("Skinuti2")) {
                                    replaceText(paragraph, "Skinuti2", skinuti.get(1));
                                }

                                if (text.contains("Postavljeni1")) {
                                    replaceText(paragraph, "Postavljeni1", postavljeni.get(0));
                                }
                                if (text.contains("Postavljeni2")) {
                                    replaceText(paragraph, "Postavljeni2", postavljeni.get(1));
                                }

                                if(jednodelnoMerilo.isMeriloIspunjavaZahteve()){
                                    if (text.contains("[cb3]")) {
                                        replaceText(paragraph, "[cb3]", "☒");
                                    }
                                    if (text.contains("[cb4]")) {
                                        replaceText(paragraph, "[cb4]", "☐");
                                    }
                                }else {
                                    if (text.contains("[cb3]")) {
                                        replaceText(paragraph, "[cb3]", "☐");
                                    }
                                    if (text.contains("[cb4]")) {
                                        replaceText(paragraph, "[cb4]", "☒");
                                    }
                                }

                                if (text.contains("[komentar2]")) {
                                    replaceText(paragraph, "[komentar2]", jednodelnoMerilo.getKomentar2());
                                }

                                if (text.contains("[datum]")) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                                    Date datum = jednodelnoMerilo.getDatum();

                                    String formatiraniDatum = dateFormat.format(datum);

                                    replaceText(paragraph, "[datum]", formatiraniDatum);
                                }

                                if (text.contains("etalonirao")) {
                                    replaceText(paragraph, "etalonirao", jednodelnoMerilo.getEtalonirao());
                                }
                                if (text.contains("odobrio")) {
                                    replaceText(paragraph, "odobrio", jednodelnoMerilo.getOdobrio());
                                }


                                if (text.contains("Резултати контролисања:")){
                                    for (XWPFTable innerTable : cell.getTables()) {
                                        for (XWPFTableRow innerRow : innerTable.getRows()) {
                                            for (XWPFTableCell innerCell : innerRow.getTableCells()) {
                                                for (XWPFParagraph innerParagraph : innerCell.getParagraphs()) {
                                                    String innerText = innerParagraph.getText();
                                                    if (innerText != null) {

                                                        if (innerText.contains("odstupanje1")) {
                                                            replaceText(innerParagraph, "odstupanje1", jednodelnoMerilo.getOdstupanje1());
                                                        }if (innerText.contains("odstupanje2")) {
                                                            replaceText(innerParagraph, "odstupanje2", jednodelnoMerilo.getOdstupanje2());
                                                        }if (innerText.contains("odstupanje3")) {
                                                            replaceText(innerParagraph, "odstupanje3", jednodelnoMerilo.getOdstupanje3());
                                                        }if (innerText.contains("odstupanje4")) {
                                                            replaceText(innerParagraph, "odstupanje4", jednodelnoMerilo.getOdstupanje4());
                                                        }if (innerText.contains("odstupanje5")) {
                                                            replaceText(innerParagraph, "odstupanje5", jednodelnoMerilo.getOdstupanje5());
                                                        }if (innerText.contains("ndg1")) {
                                                            replaceText(innerParagraph, "ndg1", jednodelnoMerilo.getNdg1());
                                                        }


                                                        if (innerText.contains("greska1")) {
                                                            replaceText(innerParagraph, "greska1", jednodelnoMerilo.getGreska1());
                                                        }
                                                        if (innerText.contains("greska2")) {
                                                            replaceText(innerParagraph, "greska2", jednodelnoMerilo.getGreska2());
                                                        }
                                                        if (innerText.contains("greska3")) {
                                                            replaceText(innerParagraph, "greska3", jednodelnoMerilo.getGreska3());
                                                        }
                                                        if (innerText.contains("greska4")) {
                                                            replaceText(innerParagraph, "greska4", jednodelnoMerilo.getGreska4());
                                                        }
                                                        if (innerText.contains("greska5")) {
                                                            replaceText(innerParagraph, "greska5", jednodelnoMerilo.getGreska5());
                                                        }
                                                        if (innerText.contains("greska6")) {
                                                            replaceText(innerParagraph, "greska6", jednodelnoMerilo.getGreska6());
                                                        }
                                                        if (innerText.contains("greska7")) {
                                                            replaceText(innerParagraph, "greska7", jednodelnoMerilo.getGreska7());
                                                        }
                                                        if (innerText.contains("greska8")) {
                                                            replaceText(innerParagraph, "greska8", jednodelnoMerilo.getGreska8());
                                                        }

                                                        if (innerText.contains("gp1")) {
                                                            replaceText(innerParagraph, "gp1", jednodelnoMerilo.getGreskaPodeljka1());
                                                        }
                                                        if (innerText.contains("gp2")) {
                                                            replaceText(innerParagraph, "gp2", jednodelnoMerilo.getGreskaPodeljka2());
                                                        }
                                                        if (innerText.contains("gp3")) {
                                                            replaceText(innerParagraph, "gp3", jednodelnoMerilo.getGreskaPodeljka3());
                                                        }
                                                        if (innerText.contains("gp4")) {
                                                            replaceText(innerParagraph, "gp4", jednodelnoMerilo.getGreskaPodeljka4());
                                                        }
                                                        if (innerText.contains("gp5")) {
                                                            replaceText(innerParagraph, "gp5", jednodelnoMerilo.getGreskaPodeljka5());
                                                        }
                                                        if (innerText.contains("gp6")) {
                                                            replaceText(innerParagraph, "gp6", jednodelnoMerilo.getGreskaPodeljka6());
                                                        }
                                                        if (innerText.contains("gp7")) {
                                                            replaceText(innerParagraph, "gp7", jednodelnoMerilo.getGreskaPodeljka7());
                                                        }
                                                        if (innerText.contains("gp8")) {
                                                            replaceText(innerParagraph, "gp8", jednodelnoMerilo.getGreskaPodeljka8());
                                                        }

                                                        if (innerText.contains("rd1")) {
                                                            replaceText(innerParagraph, "rd1", getRazlika(jednodelnoMerilo.getGreskaPodeljka1(), jednodelnoMerilo.getGreskaPodeljka2()));
                                                        }
                                                        if (innerText.contains("rd2")) {
                                                            replaceText(innerParagraph, "rd2", getRazlika(jednodelnoMerilo.getGreskaPodeljka3(), jednodelnoMerilo.getGreskaPodeljka4()));
                                                        }
                                                        if (innerText.contains("rd3")) {
                                                            replaceText(innerParagraph, "rd3", getRazlika(jednodelnoMerilo.getGreskaPodeljka5(), jednodelnoMerilo.getGreskaPodeljka6()));
                                                        }
                                                        if (innerText.contains("rd4")) {
                                                            replaceText(innerParagraph, "rd4", getRazlika(jednodelnoMerilo.getGreskaPodeljka7(), jednodelnoMerilo.getGreskaPodeljka8()));
                                                        }

                                                        if (innerText.contains("ndg2")) {
                                                            replaceText(innerParagraph, "ndg2", jednodelnoMerilo.getNdg2());
                                                        }
                                                        if (innerText.contains("ndr1")) {
                                                            replaceText(innerParagraph, "ndr1", jednodelnoMerilo.getNdr1());
                                                        }




                                                        if (innerText.contains("odstupanje6")) {
                                                            replaceText(innerParagraph, "odstupanje6", jednodelnoMerilo.getOdstupanje6());
                                                        }if (innerText.contains("odstupanje7")) {
                                                            replaceText(innerParagraph, "odstupanje7", jednodelnoMerilo.getOdstupanje7());
                                                        }if (innerText.contains("odstupanje8")) {
                                                            replaceText(innerParagraph, "odstupanje8", jednodelnoMerilo.getOdstupanje8());
                                                        }if (innerText.contains("odstupanje9")) {
                                                            replaceText(innerParagraph, "odstupanje9", jednodelnoMerilo.getOdstupanje9());
                                                        }if (innerText.contains("Odstupanje10")) {
                                                            replaceText(innerParagraph, "Odstupanje10", jednodelnoMerilo.getOdstupanje10());
                                                        }if (innerText.contains("ndg3")) {
                                                            replaceText(innerParagraph, "ndg3", jednodelnoMerilo.getNdg3());
                                                        }

                                                        if (innerText.contains("Greska9")) {
                                                            replaceText(innerParagraph, "Greska9", jednodelnoMerilo.getGreska9());
                                                        }
                                                        if (innerText.contains("Greska10")) {
                                                            replaceText(innerParagraph, "Greska10", jednodelnoMerilo.getGreska10());
                                                        }
                                                        if (innerText.contains("Greska11")) {
                                                            replaceText(innerParagraph, "Greska11", jednodelnoMerilo.getGreska11());
                                                        }
                                                        if (innerText.contains("Greska12")) {
                                                            replaceText(innerParagraph, "Greska12", jednodelnoMerilo.getGreska12());
                                                        }
                                                        if (innerText.contains("Greska13")) {
                                                            replaceText(innerParagraph, "Greska13", jednodelnoMerilo.getGreska13());
                                                        }
                                                        if (innerText.contains("Greska14")) {
                                                            replaceText(innerParagraph, "Greska14", jednodelnoMerilo.getGreska14());
                                                        }
                                                        if (innerText.contains("Greska15")) {
                                                            replaceText(innerParagraph, "Greska15", jednodelnoMerilo.getGreska15());
                                                        }
                                                        if (innerText.contains("Greska16")) {
                                                            replaceText(innerParagraph, "Greska16", jednodelnoMerilo.getGreska16());
                                                        }

                                                        if (innerText.contains("Gp9")) {
                                                            replaceText(innerParagraph, "Gp9", jednodelnoMerilo.getGreskaPodeljka9());
                                                        }
                                                        if (innerText.contains("Gp10")) {
                                                            replaceText(innerParagraph, "Gp10", jednodelnoMerilo.getGreskaPodeljka10());
                                                        }
                                                        if (innerText.contains("Gp11")) {
                                                            replaceText(innerParagraph, "Gp11", jednodelnoMerilo.getGreskaPodeljka11());
                                                        }
                                                        if (innerText.contains("Gp12")) {
                                                            replaceText(innerParagraph, "Gp12", jednodelnoMerilo.getGreskaPodeljka12());
                                                        }
                                                        if (innerText.contains("Gp13")) {
                                                            replaceText(innerParagraph, "Gp13", jednodelnoMerilo.getGreskaPodeljka13());
                                                        }
                                                        if (innerText.contains("Gp14")) {
                                                            replaceText(innerParagraph, "Gp14", jednodelnoMerilo.getGreskaPodeljka14());
                                                        }
                                                        if (innerText.contains("Gp15")) {
                                                            replaceText(innerParagraph, "Gp15", jednodelnoMerilo.getGreskaPodeljka15());
                                                        }
                                                        if (innerText.contains("Gp16")) {
                                                            replaceText(innerParagraph, "Gp16", jednodelnoMerilo.getGreskaPodeljka16());
                                                        }

                                                        if (innerText.contains("rd5")) {
                                                            replaceText(innerParagraph, "rd5", getRazlika(jednodelnoMerilo.getGreskaPodeljka9(), jednodelnoMerilo.getGreskaPodeljka10()));
                                                        }
                                                        if (innerText.contains("rd6")) {
                                                            replaceText(innerParagraph, "rd6", getRazlika(jednodelnoMerilo.getGreskaPodeljka11(), jednodelnoMerilo.getGreskaPodeljka12()));
                                                        }
                                                        if (innerText.contains("rd7")) {
                                                            replaceText(innerParagraph, "rd7", getRazlika(jednodelnoMerilo.getGreskaPodeljka13(), jednodelnoMerilo.getGreskaPodeljka14()));
                                                        }
                                                        if (innerText.contains("rd8")) {
                                                            replaceText(innerParagraph, "rd8", getRazlika(jednodelnoMerilo.getGreskaPodeljka15(), jednodelnoMerilo.getGreskaPodeljka16()));
                                                        }

                                                        if (innerText.contains("ndg4")) {
                                                            replaceText(innerParagraph, "ndg4", jednodelnoMerilo.getNdg4());
                                                        }
                                                        if (innerText.contains("ndr2")) {
                                                            replaceText(innerParagraph, "ndr2", jednodelnoMerilo.getNdr2());
                                                        }

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }


                            }
                        }
                    }
                }
            }

            List<XWPFFooter> footers = doc.getFooterList();

            for (XWPFFooter footer : footers) {
                List<XWPFParagraph> paragraphs = footer.getParagraphs();
                for (XWPFParagraph paragraph : paragraphs) {
                    String text = paragraph.getText();
                    if (text != null) {
                        if (text.contains("date")) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                            Date datum = jednodelnoMerilo.getDatum();

                            String formatiraniDatum = dateFormat.format(datum);

                            replaceText(paragraph, "date", formatiraniDatum);

                            XWPFRun run = paragraph.createRun();
                            run.addTab();
                        }
                    }
                }
            }



            String workingFilePath = staticResourcePath + "workingJednodelnoMerilo.docx";
            FileOutputStream fos = new FileOutputStream(workingFilePath);
            doc.write(fos);
            fos.close();
            doc.close();

            File workingFile = new File(workingFilePath);
            byte[] workingDocumentBytes = Files.readAllBytes(workingFile.toPath());

            workingFile.delete();

            return workingDocumentBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getRazlika(String value1, String value2) {
        char decimalSeparator = '.';
        if (value1.contains(",")) {
            decimalSeparator = ',';
        }

        String value3 = value1.replace(decimalSeparator, '.');
        String value4 = value2.replace(decimalSeparator, '.');

        double val1 = Double.parseDouble(value3);
        double val2 = Double.parseDouble(value4);

        double razlika;

        if (val1 >= val2) {
            razlika = val1 - val2;
        } else {
            razlika = val2 - val1;
        }

        return String.valueOf(razlika);
    }



    private void replaceText(XWPFParagraph paragraph, String placeholder, String replacement) {
        StringBuilder textBuilder = new StringBuilder();
        boolean placeholderFound = false;

        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.getText(0);
            if (text != null) {
                textBuilder.append(text);
                if (text.contains(placeholder)) {
                    placeholderFound = true;
                }
            }
        }

        String text = textBuilder.toString();

        if (placeholderFound) {
            String newText = text.replace(placeholder, replacement);

            for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
                paragraph.removeRun(i);
            }

            XWPFRun newRun = paragraph.createRun();
            newRun.setText(newText, 0);
        }
    }




    @Override
    public byte[] generateMernaLetva(MernaLetva mernaLetva) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "45-23-К1 Zapisnik o kontrolisanju-overavanju (merna letva).docx";

        File wordFile = new File(wordFilePath);

        if (!wordFile.exists()) {
            System.err.println("Word file not found: " + wordFilePath);
            return null;
        }

        try {
            return Files.readAllBytes(wordFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateMernaTrakaSaViskom(MernaTrakaSaViskom mernaTrakaSaViskom) {

            String staticResourcePath = "src/main/resources/static/";
            String wordFilePath = staticResourcePath + "46-23-K1 Zapisnik o kontrolisanju-overavanju (merna traka s viskom).docx";

            File wordFile = new File(wordFilePath);

            if (!wordFile.exists()) {
                System.err.println("Word file not found: " + wordFilePath);
                return null;
            }

            try {
                return Files.readAllBytes(wordFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
    }

    @Override
    public byte[] generateMasinaZaMerenje(MasinaZaMerenje masinaZaMerenje) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "39-23-K1 Zapisnik o kontrolisanju-overavanju (mašina za merenje žice i k....docx";

        File wordFile = new File(wordFilePath);

        if (!wordFile.exists()) {
            System.err.println("Word file not found: " + wordFilePath);
            return null;
        }

        try {
            return Files.readAllBytes(wordFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateSlozivoMerilo(SlozivoMerilo slozivoMerilo) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "slozivo merilo.docx";

        File wordFile = new File(wordFilePath);

        if (!wordFile.exists()) {
            System.err.println("Word file not found: " + wordFilePath);
            return null;
        }

        try {
            return Files.readAllBytes(wordFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateMetriZaTekstil(MetriZaTekstil metriZaTekstil) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "metri za tekstil.docx";

        File wordFile = new File(wordFilePath);

        if (!wordFile.exists()) {
            System.err.println("Word file not found: " + wordFilePath);
            return null;
        }

        try {
            return Files.readAllBytes(wordFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
