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


    @Override
    public byte[] generateMernaLetva(MernaLetva mernaLetva) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "mernaLetvaTemplate.docx";

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
                                if (text.contains("brojZapisnika")) {
                                    replaceText(paragraph, "brojZapisnika", mernaLetva.getBrojZapisnika());
                                }
                                if (text.contains("[vrstaKontrolisanja]")) {
                                    replaceText(paragraph, "[vrstaKontrolisanja]", mernaLetva.getVrstaKontrolisanja());
                                }
                                if (text.contains("[podnosilacZahteva]")) {
                                    replaceText(paragraph, "[podnosilacZahteva]", mernaLetva.getPodnosilacZahteva());
                                }
                                if (text.contains("vlasnikKorisnik")) {
                                    replaceText(paragraph, "vlasnikKorisnik", mernaLetva.getKorisnik());
                                }
                                if (text.contains("serijskiBroj")) {
                                    replaceText(paragraph, "serijskiBroj", mernaLetva.getSerijskiBroj());
                                }
                                if (text.contains("[identifikacioniBroj]")) {
                                    replaceText(paragraph, "[identifikacioniBroj]", mernaLetva.getIdentifikacioniBroj());
                                }
                                if (text.contains("[proizvodjac]")) {
                                    replaceText(paragraph, "[proizvodjac]", mernaLetva.getProizvodjac());
                                }
                                if (text.contains("[oznakaTipa]")) {
                                    replaceText(paragraph, "[oznakaTipa]", mernaLetva.getOznakaTipa());
                                }
                                if (text.contains("sluzbenaOznakaTipa")) {
                                    replaceText(paragraph, "sluzbenaOznakaTipa", mernaLetva.getSluzbenaOznakaTipa());
                                }
                                if (text.contains("[merniOpseg]")) {
                                    replaceText(paragraph, "[merniOpseg]", mernaLetva.getMerniOpseg());
                                }
                                if (text.contains("najmanjiPodeljak")) {
                                    replaceText(paragraph, "najmanjiPodeljak", mernaLetva.getNajmanjiPodeljak());
                                }
                                if (text.contains("[klasaTacnosti]")) {
                                    replaceText(paragraph, "[klasaTacnosti]", mernaLetva.getKlasaTacnosti());
                                }
                                if (text.contains("temperatura")) {
                                    replaceText(paragraph, "temperatura", mernaLetva.getTemperatura());
                                }
                                if (text.contains("[vlaznost]")) {
                                    replaceText(paragraph, "[vlaznost]", mernaLetva.getVlaznostVazduha());
                                }

                                if(mernaLetva.isMeriloJeIspravno()){
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
                                    replaceText(paragraph, "[napomena1]", mernaLetva.getNapomena());
                                }
                                if (text.contains("brojMernogLenjira")) {
                                    replaceText(paragraph, "brojMernogLenjira", mernaLetva.getBrojMernogLenjira());
                                }
                                if (text.contains("brojMerneLupe")) {
                                    replaceText(paragraph, "brojMerneLupe", mernaLetva.getBrojMerneLupe());
                                }

                                List<String> skinuti = List.of(mernaLetva.getSkinutiZigovi().split(";"));
                                List<String> postavljeni = List.of(mernaLetva.getPostavljeniZigovi().split(";"));

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

                                if(mernaLetva.isMeriloIspunjavaZahteve()){
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
                                    replaceText(paragraph, "[komentar2]", mernaLetva.getKomentar2());
                                }

                                if (text.contains("[datum]")) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                                    Date datum = mernaLetva.getDatum();

                                    String formatiraniDatum = dateFormat.format(datum);

                                    replaceText(paragraph, "[datum]", formatiraniDatum);
                                }

                                if (text.contains("etalonirao")) {
                                    replaceText(paragraph, "etalonirao", mernaLetva.getEtalonirao());
                                }
                                if (text.contains("odobrio")) {
                                    replaceText(paragraph, "odobrio", mernaLetva.getOdobrio());
                                }

                                if (text.contains("Резултати контролисања:")){
                                    for (XWPFTable innerTable : cell.getTables()) {
                                        for (XWPFTableRow innerRow : innerTable.getRows()) {
                                            for (XWPFTableCell innerCell : innerRow.getTableCells()) {
                                                for (XWPFParagraph innerParagraph : innerCell.getParagraphs()) {
                                                    String innerText = innerParagraph.getText();
                                                    if (innerText != null) {
                                                        if (innerText.contains("Odstupanje1")) {
                                                            replaceText(innerParagraph, "Odstupanje1", mernaLetva.getOdstupanje1());
                                                        }if (innerText.contains("Odstupanje2")) {
                                                            replaceText(innerParagraph, "Odstupanje2", mernaLetva.getOdstupanje2());
                                                        }if (innerText.contains("Odstupanje3")) {
                                                            replaceText(innerParagraph, "Odstupanje3", mernaLetva.getOdstupanje3());
                                                        }if (innerText.contains("Odstupanje4")) {
                                                            replaceText(innerParagraph, "Odstupanje4", mernaLetva.getOdstupanje4());
                                                        }if (innerText.contains("Odstupanje5")) {
                                                            replaceText(innerParagraph, "Odstupanje5", mernaLetva.getOdstupanje5());
                                                        }if (innerText.contains("Odstupanje6")) {
                                                            replaceText(innerParagraph, "Odstupanje6", mernaLetva.getOdstupanje6());
                                                        }if (innerText.contains("Ndg1")) {
                                                            replaceText(innerParagraph, "Ndg1", mernaLetva.getNdg1());
                                                        }if (innerText.contains("Ndg2")) {
                                                            replaceText(innerParagraph, "Ndg2", mernaLetva.getNdg2());
                                                        }if (innerText.contains("Ndg3")) {
                                                            replaceText(innerParagraph, "Ndg3", mernaLetva.getNdg3());
                                                        }if (innerText.contains("Ndg4")) {
                                                            replaceText(innerParagraph, "Ndg4", mernaLetva.getNdg4());
                                                        }if (innerText.contains("Ndg5")) {
                                                            replaceText(innerParagraph, "Ndg5", mernaLetva.getNdg5());
                                                        }if (innerText.contains("Ndg6")) {
                                                            replaceText(innerParagraph, "Ndg6", mernaLetva.getNdg6());
                                                        }
                                                        if (innerText.contains("Greska1")) {
                                                            replaceText(innerParagraph, "Greska1", mernaLetva.getGreska1());
                                                        }
                                                        if (innerText.contains("Greska2")) {
                                                            replaceText(innerParagraph, "Greska2", mernaLetva.getGreska2());
                                                        }
                                                        if (innerText.contains("Greska3")) {
                                                            replaceText(innerParagraph, "Greska3", mernaLetva.getGreska3());
                                                        }
                                                        if (innerText.contains("Greska4")) {
                                                            replaceText(innerParagraph, "Greska4", mernaLetva.getGreska4());
                                                        }
                                                        if (innerText.contains("Greska5")) {
                                                            replaceText(innerParagraph, "Greska5", mernaLetva.getGreska5());
                                                        }
                                                        if (innerText.contains("Greska6")) {
                                                            replaceText(innerParagraph, "Greska6", mernaLetva.getGreska6());
                                                        }
                                                        if (innerText.contains("Greska7")) {
                                                            replaceText(innerParagraph, "Greska7", mernaLetva.getGreska7());
                                                        }
                                                        if (innerText.contains("Greska8")) {
                                                            replaceText(innerParagraph, "Greska8", mernaLetva.getGreska8());
                                                        }
                                                        if (innerText.contains("Gp1")) {
                                                            replaceText(innerParagraph, "Gp1", mernaLetva.getGreskaPodeljka1());
                                                        }
                                                        if (innerText.contains("Gp2")) {
                                                            replaceText(innerParagraph, "Gp2", mernaLetva.getGreskaPodeljka2());
                                                        }
                                                        if (innerText.contains("Gp3")) {
                                                            replaceText(innerParagraph, "Gp3", mernaLetva.getGreskaPodeljka3());
                                                        }
                                                        if (innerText.contains("Gp4")) {
                                                            replaceText(innerParagraph, "Gp4", mernaLetva.getGreskaPodeljka4());
                                                        }
                                                        if (innerText.contains("Gp5")) {
                                                            replaceText(innerParagraph, "Gp5", mernaLetva.getGreskaPodeljka5());
                                                        }
                                                        if (innerText.contains("Gp6")) {
                                                            replaceText(innerParagraph, "Gp6", mernaLetva.getGreskaPodeljka6());
                                                        }
                                                        if (innerText.contains("Gp7")) {
                                                            replaceText(innerParagraph, "Gp7", mernaLetva.getGreskaPodeljka7());
                                                        }
                                                        if (innerText.contains("Gp8")) {
                                                            replaceText(innerParagraph, "Gp8", mernaLetva.getGreskaPodeljka8());
                                                        }
                                                        if (innerText.contains("Rd1")) {
                                                            replaceText(innerParagraph, "Rd1", getRazlika(mernaLetva.getGreskaPodeljka1(), mernaLetva.getGreskaPodeljka2()));
                                                        }
                                                        if (innerText.contains("Rd2")) {
                                                            replaceText(innerParagraph, "Rd2", getRazlika(mernaLetva.getGreskaPodeljka3(), mernaLetva.getGreskaPodeljka4()));
                                                        }
                                                        if (innerText.contains("Rd3")) {
                                                            replaceText(innerParagraph, "Rd3", getRazlika(mernaLetva.getGreskaPodeljka5(), mernaLetva.getGreskaPodeljka6()));
                                                        }
                                                        if (innerText.contains("Rd4")) {
                                                            replaceText(innerParagraph, "Rd4", getRazlika(mernaLetva.getGreskaPodeljka7(), mernaLetva.getGreskaPodeljka8()));
                                                        }
                                                        if (innerText.contains("Ndg7")) {
                                                            replaceText(innerParagraph, "Ndg7", mernaLetva.getNdg7());
                                                        }
                                                        if (innerText.contains("Ndr1")) {
                                                            replaceText(innerParagraph, "Ndr1", mernaLetva.getNdr1());
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

                            Date datum = mernaLetva.getDatum();

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

    @Override
    public byte[] generateMernaTrakaSaViskom(MernaTrakaSaViskom mernaTrakaSaViskom) {

        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "mernaTrakaTemplate.docx";

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
                                if (text.contains("brojZapisnika")) {
                                    replaceText(paragraph, "brojZapisnika", mernaTrakaSaViskom.getBrojZapisnika());
                                }
                                if (text.contains("[vrstaKontrolisanja]")) {
                                    replaceText(paragraph, "[vrstaKontrolisanja]", mernaTrakaSaViskom.getVrstaKontrolisanja());
                                }
                                if (text.contains("[podnosilacZahteva]")) {
                                    replaceText(paragraph, "[podnosilacZahteva]", mernaTrakaSaViskom.getPodnosilacZahteva());
                                }
                                if (text.contains("vlasnikKorisnik")) {
                                    replaceText(paragraph, "vlasnikKorisnik", mernaTrakaSaViskom.getKorisnik());
                                }
                                if (text.contains("serijskiBroj")) {
                                    replaceText(paragraph, "serijskiBroj", mernaTrakaSaViskom.getSerijskiBroj());
                                }
                                if (text.contains("[identifikacioniBroj]")) {
                                    replaceText(paragraph, "[identifikacioniBroj]", mernaTrakaSaViskom.getIdentifikacioniBroj());
                                }
                                if (text.contains("[proizvodjac]")) {
                                    replaceText(paragraph, "[proizvodjac]", mernaTrakaSaViskom.getProizvodjac());
                                }
                                if (text.contains("[oznakaTipa]")) {
                                    replaceText(paragraph, "[oznakaTipa]", mernaTrakaSaViskom.getOznakaTipa());
                                }
                                if (text.contains("sluzbenaOznakaTipa")) {
                                    replaceText(paragraph, "sluzbenaOznakaTipa", mernaTrakaSaViskom.getSluzbenaOznakaTipa());
                                }
                                if (text.contains("[merniOpseg]")) {
                                    replaceText(paragraph, "[merniOpseg]", mernaTrakaSaViskom.getMerniOpseg());
                                }
                                if (text.contains("najmanjiPodeljak")) {
                                    replaceText(paragraph, "najmanjiPodeljak", mernaTrakaSaViskom.getNajmanjiPodeljak());
                                }
                                if (text.contains("[klasaTacnosti]")) {
                                    replaceText(paragraph, "[klasaTacnosti]", mernaTrakaSaViskom.getKlasaTacnosti());
                                }
                                if (text.contains("temperatura")) {
                                    replaceText(paragraph, "temperatura", mernaTrakaSaViskom.getTemperatura());
                                }
                                if (text.contains("[vlaznost]")) {
                                    replaceText(paragraph, "[vlaznost]", mernaTrakaSaViskom.getVlaznostVazduha());
                                }

                                if(mernaTrakaSaViskom.isMeriloJeIspravno()){
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
                                    replaceText(paragraph, "[napomena1]", mernaTrakaSaViskom.getNapomena());
                                }
                                if (text.contains("brojMernogLenjira")) {
                                    replaceText(paragraph, "brojMernogLenjira", mernaTrakaSaViskom.getBrojMernogLenjira());
                                }
                                if (text.contains("brojMerneLupe")) {
                                    replaceText(paragraph, "brojMerneLupe", mernaTrakaSaViskom.getBrojMerneLupe());
                                }

                                List<String> skinuti = List.of(mernaTrakaSaViskom.getSkinutiZigovi().split(";"));
                                List<String> postavljeni = List.of(mernaTrakaSaViskom.getPostavljeniZigovi().split(";"));

                                if (text.contains("Skinuti1") && skinuti.size() > 0) {
                                    replaceText(paragraph, "Skinuti1", skinuti.get(0));
                                }else {
                                    replaceText(paragraph,"Skinuti1","/");
                                }
                                if (text.contains("Skinuti2") && skinuti.size() > 1) {
                                    replaceText(paragraph, "Skinuti2", skinuti.get(1));
                                }else {
                                    replaceText(paragraph,"Skinuti2","");
                                }

                                if (text.contains("Postavljeni1") && postavljeni.size() > 0) {
                                    replaceText(paragraph, "Postavljeni1", postavljeni.get(0));
                                }else {
                                    replaceText(paragraph,"Postavljeni1", "/");
                                }
                                if (text.contains("Postavljeni2") && postavljeni.size() > 0) {
                                    replaceText(paragraph, "Postavljeni2", postavljeni.get(1));
                                }else {
                                    replaceText(paragraph, "Postavljeni2", "");
                                }

                                if(mernaTrakaSaViskom.isMeriloIspunjavaZahteve()){
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
                                    replaceText(paragraph, "[komentar2]", mernaTrakaSaViskom.getKomentar2());
                                }

                                if (text.contains("[datum]")) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                                    Date datum = mernaTrakaSaViskom.getDatum();

                                    String formatiraniDatum = dateFormat.format(datum);

                                    replaceText(paragraph, "[datum]", formatiraniDatum);
                                }

                                if (text.contains("etalonirao")) {
                                    replaceText(paragraph, "etalonirao", mernaTrakaSaViskom.getEtalonirao());
                                }
                                if (text.contains("odobrio")) {
                                    replaceText(paragraph, "odobrio", mernaTrakaSaViskom.getOdobrio());
                                }

                                if (text.contains("Резултати контролисања:")){
                                    for (XWPFTable innerTable : cell.getTables()) {
                                        for (XWPFTableRow innerRow : innerTable.getRows()) {
                                            for (XWPFTableCell innerCell : innerRow.getTableCells()) {
                                                for (XWPFParagraph innerParagraph : innerCell.getParagraphs()) {
                                                    String innerText = innerParagraph.getText();
                                                    if (innerText != null) {
                                                        if (innerText.contains("Odstupanje1")) {
                                                            replaceText(innerParagraph, "Odstupanje1", mernaTrakaSaViskom.getOdstupanje1());
                                                        }if (innerText.contains("Odstupanje2")) {
                                                            replaceText(innerParagraph, "Odstupanje2", mernaTrakaSaViskom.getOdstupanje2());
                                                        }if (innerText.contains("Odstupanje3")) {
                                                            replaceText(innerParagraph, "Odstupanje3", mernaTrakaSaViskom.getOdstupanje3());
                                                        }if (innerText.contains("Odstupanje4")) {
                                                            replaceText(innerParagraph, "Odstupanje4", mernaTrakaSaViskom.getOdstupanje4());
                                                        }if (innerText.contains("Odstupanje5")) {
                                                            replaceText(innerParagraph, "Odstupanje5", mernaTrakaSaViskom.getOdstupanje5());
                                                        }if (innerText.contains("Odstupanje6")) {
                                                            replaceText(innerParagraph, "Odstupanje6", mernaTrakaSaViskom.getOdstupanje6());
                                                        }if (innerText.contains("Odstupanje7")) {
                                                            replaceText(innerParagraph, "Odstupanje7", mernaTrakaSaViskom.getOdstupanje7());
                                                        }if (innerText.contains("Odstupanje8")) {
                                                            replaceText(innerParagraph, "Odstupanje8", mernaTrakaSaViskom.getOdstupanje8());
                                                        }if (innerText.contains("Odstupanje9")) {
                                                            replaceText(innerParagraph, "Odstupanje9", mernaTrakaSaViskom.getOdstupanje9());
                                                        }if (innerText.contains("[o10]")) {
                                                            replaceText(innerParagraph, "[o10]", mernaTrakaSaViskom.getOdstupanje10());
                                                        }if (innerText.contains("[o11]")) {
                                                            replaceText(innerParagraph, "[o11]", mernaTrakaSaViskom.getOdstupanje11());
                                                        }if (innerText.contains("Ndg1")) {
                                                            replaceText(innerParagraph, "Ndg1", mernaTrakaSaViskom.getNdg1());
                                                        }if (innerText.contains("Ndg2")) {
                                                            replaceText(innerParagraph, "Ndg2", mernaTrakaSaViskom.getNdg2());
                                                        }if (innerText.contains("Ndg3")) {
                                                            replaceText(innerParagraph, "Ndg3", mernaTrakaSaViskom.getNdg3());
                                                        }if (innerText.contains("Ndg4")) {
                                                            replaceText(innerParagraph, "Ndg4", mernaTrakaSaViskom.getNdg4());
                                                        }if (innerText.contains("Ndg5")) {
                                                            replaceText(innerParagraph, "Ndg5", mernaTrakaSaViskom.getNdg5());
                                                        }if (innerText.contains("Ndg6")) {
                                                            replaceText(innerParagraph, "Ndg6", mernaTrakaSaViskom.getNdg6());
                                                        }if (innerText.contains("Ndg7")) {
                                                            replaceText(innerParagraph, "Ndg7", mernaTrakaSaViskom.getNdg7());
                                                        }if (innerText.contains("Ndg8")) {
                                                            replaceText(innerParagraph, "Ndg8", mernaTrakaSaViskom.getNdg8());
                                                        }if (innerText.contains("Ndg9")) {
                                                            replaceText(innerParagraph, "Ndg9", mernaTrakaSaViskom.getNdg9());
                                                        }if (innerText.contains("[n10]")) {
                                                            replaceText(innerParagraph, "[n10]", mernaTrakaSaViskom.getNdg10());
                                                        }if (innerText.contains("[n11]")) {
                                                            replaceText(innerParagraph, "[n11]", mernaTrakaSaViskom.getNdg11());
                                                        }
                                                        if (innerText.contains("Greska1")) {
                                                            replaceText(innerParagraph, "Greska1", mernaTrakaSaViskom.getGreska1());
                                                        }
                                                        if (innerText.contains("Greska2")) {
                                                            replaceText(innerParagraph, "Greska2", mernaTrakaSaViskom.getGreska2());
                                                        }
                                                        if (innerText.contains("Greska3")) {
                                                            replaceText(innerParagraph, "Greska3", mernaTrakaSaViskom.getGreska3());
                                                        }
                                                        if (innerText.contains("Greska4")) {
                                                            replaceText(innerParagraph, "Greska4", mernaTrakaSaViskom.getGreska4());
                                                        }
                                                        if (innerText.contains("Greska5")) {
                                                            replaceText(innerParagraph, "Greska5", mernaTrakaSaViskom.getGreska5());
                                                        }
                                                        if (innerText.contains("Greska6")) {
                                                            replaceText(innerParagraph, "Greska6", mernaTrakaSaViskom.getGreska6());
                                                        }
                                                        if (innerText.contains("Greska7")) {
                                                            replaceText(innerParagraph, "Greska7", mernaTrakaSaViskom.getGreska7());
                                                        }
                                                        if (innerText.contains("Greska8")) {
                                                            replaceText(innerParagraph, "Greska8", mernaTrakaSaViskom.getGreska8());
                                                        }
                                                        if (innerText.contains("Gp1")) {
                                                            replaceText(innerParagraph, "Gp1", mernaTrakaSaViskom.getGreskaPodeljka1());
                                                        }
                                                        if (innerText.contains("Gp2")) {
                                                            replaceText(innerParagraph, "Gp2", mernaTrakaSaViskom.getGreskaPodeljka2());
                                                        }
                                                        if (innerText.contains("Gp3")) {
                                                            replaceText(innerParagraph, "Gp3", mernaTrakaSaViskom.getGreskaPodeljka3());
                                                        }
                                                        if (innerText.contains("Gp4")) {
                                                            replaceText(innerParagraph, "Gp4", mernaTrakaSaViskom.getGreskaPodeljka4());
                                                        }
                                                        if (innerText.contains("Gp5")) {
                                                            replaceText(innerParagraph, "Gp5", mernaTrakaSaViskom.getGreskaPodeljka5());
                                                        }
                                                        if (innerText.contains("Gp6")) {
                                                            replaceText(innerParagraph, "Gp6", mernaTrakaSaViskom.getGreskaPodeljka6());
                                                        }
                                                        if (innerText.contains("Gp7")) {
                                                            replaceText(innerParagraph, "Gp7", mernaTrakaSaViskom.getGreskaPodeljka7());
                                                        }
                                                        if (innerText.contains("Gp8")) {
                                                            replaceText(innerParagraph, "Gp8", mernaTrakaSaViskom.getGreskaPodeljka8());
                                                        }
                                                        if (innerText.contains("Rd1")) {
                                                            replaceText(innerParagraph, "Rd1", getRazlika(mernaTrakaSaViskom.getGreskaPodeljka1(), mernaTrakaSaViskom.getGreskaPodeljka2()));
                                                        }
                                                        if (innerText.contains("Rd2")) {
                                                            replaceText(innerParagraph, "Rd2", getRazlika(mernaTrakaSaViskom.getGreskaPodeljka3(), mernaTrakaSaViskom.getGreskaPodeljka4()));
                                                        }
                                                        if (innerText.contains("Rd3")) {
                                                            replaceText(innerParagraph, "Rd3", getRazlika(mernaTrakaSaViskom.getGreskaPodeljka5(), mernaTrakaSaViskom.getGreskaPodeljka6()));
                                                        }
                                                        if (innerText.contains("Rd4")) {
                                                            replaceText(innerParagraph, "Rd4", getRazlika(mernaTrakaSaViskom.getGreskaPodeljka7(), mernaTrakaSaViskom.getGreskaPodeljka8()));
                                                        }
                                                        if (innerText.contains("[n12]")) {
                                                            replaceText(innerParagraph, "[n12]", mernaTrakaSaViskom.getNdg12());
                                                        }
                                                        if (innerText.contains("Ndr1")) {
                                                            replaceText(innerParagraph, "Ndr1", mernaTrakaSaViskom.getNdr1());
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

                            Date datum = mernaTrakaSaViskom.getDatum();

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

    @Override
    public byte[] generateMasinaZaMerenje(MasinaZaMerenje masinaZaMerenje) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "masinaZaMerenjeTemplate.docx";

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
                                if (text.contains("brojZapisnika")) {
                                    replaceText(paragraph, "brojZapisnika", masinaZaMerenje.getBrojZapisnika());
                                }
                                if (text.contains("[vrstaKontrolisanja]")) {
                                    replaceText(paragraph, "[vrstaKontrolisanja]", masinaZaMerenje.getVrstaKontrolisanja());
                                }
                                if (text.contains("[podnosilacZahteva]")) {
                                    replaceText(paragraph, "[podnosilacZahteva]", masinaZaMerenje.getPodnosilacZahteva());
                                }
                                if (text.contains("vlasnikKorisnik")) {
                                    replaceText(paragraph, "vlasnikKorisnik", masinaZaMerenje.getKorisnik());
                                }
                                if (text.contains("serijskiBroj")) {
                                    replaceText(paragraph, "serijskiBroj", masinaZaMerenje.getSerijskiBroj());
                                }
                                if (text.contains("[identifikacioniBroj]")) {
                                    replaceText(paragraph, "[identifikacioniBroj]", masinaZaMerenje.getIdentifikacioniBroj());
                                }
                                if (text.contains("[proizvodjac]")) {
                                    replaceText(paragraph, "[proizvodjac]", masinaZaMerenje.getProizvodjac());
                                }
                                if (text.contains("[oznakaTipa]")) {
                                    replaceText(paragraph, "[oznakaTipa]", masinaZaMerenje.getOznakaTipa());
                                }
                                if (text.contains("sluzbenaOznakaTipa")) {
                                    replaceText(paragraph, "sluzbenaOznakaTipa", masinaZaMerenje.getSluzbenaOznakaTipa());
                                }
                                if (text.contains("[merniOpseg]")) {
                                    replaceText(paragraph, "[merniOpseg]", masinaZaMerenje.getMerniOpseg());
                                }
                                if (text.contains("najmanjiPodeljak")) {
                                    replaceText(paragraph, "najmanjiPodeljak", masinaZaMerenje.getNajmanjiPodeljak());
                                }
                                if (text.contains("[klasaTacnosti]")) {
                                    replaceText(paragraph, "[klasaTacnosti]", masinaZaMerenje.getKlasaTacnosti());
                                }
                                if (text.contains("temperatura")) {
                                    replaceText(paragraph, "temperatura", masinaZaMerenje.getTemperatura());
                                }
                                if (text.contains("[vlaznost]")) {
                                    replaceText(paragraph, "[vlaznost]", masinaZaMerenje.getVlaznostVazduha());
                                }

                                if(masinaZaMerenje.isMeriloJeIspravno()){
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
                                    replaceText(paragraph, "[napomena1]", masinaZaMerenje.getNapomena());
                                }
                                if (text.contains("brojMernogLenjira")) {
                                    replaceText(paragraph, "brojMernogLenjira", masinaZaMerenje.getBrojMernogLenjira());
                                }
                                if (text.contains("brojPomicnogMerila")) {
                                    replaceText(paragraph, "brojPomicnogMerila", masinaZaMerenje.getBrojPomicnogMerila());
                                }

                                List<String> skinuti = null;
                                if(masinaZaMerenje.getSkinutiZigovi() != null){
                                skinuti = List.of(masinaZaMerenje.getSkinutiZigovi().split(";"));
                                }
                                List<String> postavljeni = null;
                                if(masinaZaMerenje.getPostavljeniZigovi() != null){
                                    postavljeni = List.of(masinaZaMerenje.getPostavljeniZigovi().split(";"));
                                }

                                if (text.contains("Skinuti1") && skinuti!= null) {
                                    replaceText(paragraph, "Skinuti1", skinuti.get(0));
                                }else {
                                    replaceText(paragraph,"Skinuti1","/");
                                }
                                if (text.contains("Skinuti2") && skinuti!= null && skinuti.size() > 1) {
                                    replaceText(paragraph, "Skinuti2", skinuti.get(1));
                                }else {
                                    replaceText(paragraph,"Skinuti2","");
                                }

                                if (text.contains("Postavljeni1") && postavljeni != null) {
                                    replaceText(paragraph, "Postavljeni1", postavljeni.get(0));
                                }else {
                                    replaceText(paragraph,"Postavljeni1", "/");
                                }
                                if (text.contains("Postavljeni2") && postavljeni != null) {
                                    replaceText(paragraph, "Postavljeni2", postavljeni.get(1));
                                }else {
                                    replaceText(paragraph, "Postavljeni2", "");
                                }

                                if(masinaZaMerenje.isMeriloIspunjavaZahteve()){
                                    if (text.contains("[cb8]")) {
                                        replaceText(paragraph, "[cb8]", "☒");
                                    }
                                    if (text.contains("[cb9]")) {
                                        replaceText(paragraph, "[cb9]", "☐");
                                    }
                                }else {
                                    if (text.contains("[cb8]")) {
                                        replaceText(paragraph, "[cb8]", "☐");
                                    }
                                    if (text.contains("[cb9]")) {
                                        replaceText(paragraph, "[cb9]", "☒");
                                    }
                                }

                                if (text.contains("[komentar2]")) {
                                    replaceText(paragraph, "[komentar2]", masinaZaMerenje.getKomentar2());
                                }

                                if (text.contains("[datum]")) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                                    Date datum = masinaZaMerenje.getDatum();

                                    String formatiraniDatum = dateFormat.format(datum);

                                    replaceText(paragraph, "[datum]", formatiraniDatum);
                                }

                                if (text.contains("etalonirao")) {
                                    replaceText(paragraph, "etalonirao", masinaZaMerenje.getEtalonirao());
                                }
                                if (text.contains("odobrio")) {
                                    replaceText(paragraph, "odobrio", masinaZaMerenje.getOdobrio());
                                }

                                if (text.contains("Резултати контролисања:")){
                                    for (XWPFTable innerTable : cell.getTables()) {
                                        for (XWPFTableRow innerRow : innerTable.getRows()) {
                                            for (XWPFTableCell innerCell : innerRow.getTableCells()) {
                                                for (XWPFParagraph innerParagraph : innerCell.getParagraphs()) {
                                                    String innerText = innerParagraph.getText();
                                                    if (innerText != null) {
                                                        if (innerText.contains("Merenje1")) {
                                                            replaceText(innerParagraph, "Merenje1", masinaZaMerenje.getMerenje1());
                                                        }if (innerText.contains("Merenje2")) {
                                                            replaceText(innerParagraph, "Merenje2", masinaZaMerenje.getMerenje2());
                                                        }if (innerText.contains("Merenje3")) {
                                                            replaceText(innerParagraph, "Merenje3", masinaZaMerenje.getMerenje3());
                                                        }if (innerText.contains("srv")) {
                                                            String average = calculateAverage(masinaZaMerenje.getMerenje1(), masinaZaMerenje.getMerenje2(), masinaZaMerenje.getMerenje3());
                                                            replaceText(innerParagraph,"srv",average);
                                                        }if (innerText.contains("ot")) {
                                                            String average = calculateAverage(masinaZaMerenje.getMerenje1(), masinaZaMerenje.getMerenje2(), masinaZaMerenje.getMerenje3());
                                                            String result = calculateWithPi(average);
                                                            replaceText(paragraph,"ot",result);
                                                        }

                                                        if(masinaZaMerenje.getProveraIspravnogVodjenja().equals("испуњавa")){
                                                            if (text.contains("[cb3]")) {
                                                                replaceText(paragraph, "[cb3]", "☒");
                                                            }
                                                            if (text.contains("[cb4]")) {
                                                                replaceText(paragraph, "[cb4]", "☐");
                                                            }
                                                            if (text.contains("[cb5]")) {
                                                                replaceText(paragraph, "[cb5]", "☐");
                                                            }
                                                        }else if (masinaZaMerenje.getProveraIspravnogVodjenja().equals("не испуњава")){
                                                            if (text.contains("[cb3]")) {
                                                                replaceText(paragraph, "[cb3]", "☐");
                                                            }
                                                            if (text.contains("[cb4]")) {
                                                                replaceText(paragraph, "[cb4]", "☒");
                                                            }
                                                            if (text.contains("[cb5]")) {
                                                                replaceText(paragraph, "[cb5]", "☐");
                                                            }
                                                        }else {
                                                            if (text.contains("[cb3]")) {
                                                                replaceText(paragraph, "[cb3]", "☐");
                                                            }
                                                            if (text.contains("[cb4]")) {
                                                                replaceText(paragraph, "[cb4]", "☐");
                                                            }
                                                            if (text.contains("[cb5]")) {
                                                                replaceText(paragraph, "[cb5]", "☒");
                                                            }
                                                        }

                                                        if(masinaZaMerenje.isProveraIspravnostiPokaznogUredjaja()){
                                                            if (text.contains("[cb6]")) {
                                                                replaceText(paragraph, "[cb6]", "☒");
                                                            }
                                                            if (text.contains("[cb7]")) {
                                                                replaceText(paragraph, "[cb7]", "☐");
                                                            }
                                                        } else {
                                                            if (text.contains("[cb6]")) {
                                                                replaceText(paragraph, "[cb6]", "☐");
                                                            }
                                                            if (text.contains("[cb7]")) {
                                                                replaceText(paragraph, "[cb7]", "☒");
                                                            }
                                                        }

                                                        if (innerText.contains("duzina")) {
                                                            replaceText(innerParagraph, "duzina", masinaZaMerenje.getDuzinaUzorka());
                                                        }if (innerText.contains("debljina")) {
                                                            replaceText(innerParagraph, "debljina", masinaZaMerenje.getDebljinaUzorka());
                                                        }if (innerText.contains("pokazivanje")) {
                                                            replaceText(innerParagraph, "pokazivanje", masinaZaMerenje.getPokazivanjeMasine());
                                                        }if (innerText.contains("odstupanje")) {
                                                            replaceText(innerParagraph, "odstupanje", masinaZaMerenje.getOdstupanjeOdPraveVrednostiDuzine());
                                                        }if (innerText.contains("greska")) {
                                                            replaceText(innerParagraph, "greska", masinaZaMerenje.getRelativnaGreskaIzmereneDuzine());
                                                        }if (innerText.contains("Ndg1")) {
                                                            replaceText(innerParagraph, "Ndg1", masinaZaMerenje.getNdg1());
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

                            Date datum = masinaZaMerenje.getDatum();

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


    private String getRazlika(String value1, String value2) {
        char decimalSeparator = ',';
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
                System.out.println("Text: " + text);
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

    private String calculateAverage(String value1, String value2, String value3) {
        char decimalSeparator = ',';
        if (value1.contains(",")) {
            decimalSeparator = ',';
        }

        String val1 = value1.replace(decimalSeparator, '.');
        String val2 = value2.replace(decimalSeparator, '.');
        String val3 = value3.replace(decimalSeparator, '.');

        double merenje1 = Double.parseDouble(val1);
        double merenje2 = Double.parseDouble(val2);
        double merenje3 = Double.parseDouble(val3);

        double average = (merenje1 + merenje2 + merenje3) / 3.0;

        return String.format("%.2f", average);
    }

    private String calculateWithPi(String averageValue) {
        char decimalSeparator = ',';
        if (averageValue.contains(",")) {
            decimalSeparator = ',';
        }

        String average = averageValue.replace(decimalSeparator, '.');

        double avg = Double.parseDouble(average);
        double result = avg * 3.14;

        return String.format("%.2f", result);
    }


}


