package com.example.f;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button button;
    private static final int STORAGE_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);


        //handle button click
        button.setOnClickListener(v -> {

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){

                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED){

                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions, STORAGE_CODE);
                }
                else {

                    savePdf();
                }
            }
            else {

                savePdf();
            }
        });

    }

    private void savePdf() {
        Document mDoc = new Document();

        String mFileName = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());

        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf";
        try {

            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));

            mDoc.open();

            PdfPTable heading1 = new PdfPTable(1); //one page contains 15 records
            heading1.setWidthPercentage(100);
            heading1.setWidths(new float[] { 5});
            heading1.addCell(getIRDCell4("ADHITYA SEMAN CENTER"));
            heading1.addCell(getIRDCell7("1-4-27 A2 pillaiyar kovil street,Sankari Mani"));
            heading1.addCell(getIRDCell7("KONGANAPURAM,SALEM-637102"));
            PdfPTable heading2 = new PdfPTable(1); //one page contains 15 records
            heading2.setWidthPercentage(100);
            heading2.setWidths(new float[] { 5});
            heading2.addCell(getIRDCell4("TAX/IN"));
            heading2.addCell(getIRDCell7("\nBILL NO:B/11"));
            PdfPTable heading3 = new PdfPTable(1); //one page contains 15 records
            heading3.setWidthPercentage(100);
            heading3.setWidths(new float[] { 5});
            heading3.addCell(getIRDCell4("LAKSHMI MEDICAL"));
            heading3.addCell(getIRDCell7("\nCOIMBATORE"));
            PdfPTable heading4 = new PdfPTable(1); //one page contains 15 records
            heading4.setWidthPercentage(100);
            heading4.setWidths(new float[] { 7});
            heading4.addCell(getIRDCell7(" TERM "));
            heading4.addCell(getIRDCell7("INVOICE "));
            PdfPTable heading5 = new PdfPTable(1); //one page contains 15 records
            heading5.setWidthPercentage(100);
            heading5.setWidths(new float[] { 3});
            heading5.addCell(getIRDCell7("mb no:\n1234567809,1234567809,1234567809 "));
            heading5.addCell(getIRDCell7("GSTIN:\n123456780912345678091234567809 "));
            PdfPTable heading6 = new PdfPTable(1); //one page contains 15 records
            heading6.setWidthPercentage(100);
            heading6.setWidths(new float[] { 3});
            heading6.addCell(getIRDCell7("Date:\n22/22/2222 "));
            heading6.addCell(getIRDCell7("invoice no:\n12 "));

            PdfPTable heading9 = new PdfPTable(1); //one page contains 15 records
            heading9.setWidthPercentage(100);
            heading9.setWidths(new float[] { 5});
            heading9.addCell(getIRDCell7("Mobile:\n1234567809 "));
            heading9.addCell(getIRDCell7("GSTIN:\n12345678091234567809"));


            PdfPTable table2 = new PdfPTable(6);

            table2.setWidthPercentage(110);
            table2.setWidths(new float[] { 3,3,2,2,8,5});
           table2.addCell("NO TAX");
            table2.addCell("54995.00");
            table2.addCell("CGST");
            table2.addCell("SGST");
            table2.addCell("BANK DEATAILS");
            table2.addCell("GROSS:");
            table2.addCell(getBillRowCell("GST5%"));
            table2.addCell(getBillRowCell("0.00"));
            table2.addCell(getBillRowCell("0.00"));
            table2.addCell(getBillRowCell("0.00"));
            table2.addCell(getIRDCell7("A/C Name: ADHITYA SEMAN BANK"));
            table2.addCell(("DISCOUNT:"));
            table2.addCell(getBillRowCell("GST12%"));
            table2.addCell(getBillRowCell("0.00"));
            table2.addCell(getBillRowCell("0.00"));
            table2.addCell(getBillRowCell("0.00"));
            table2.addCell(getIRDCell7("A/C NO:12345667890 IFSC:AIIA0212680"));
            table2.addCell(("CGST:"));
            table2.addCell(getBillRowCell("GST18%"));
            table2.addCell(getBillRowCell("0.00"));
            table2.addCell(getBillRowCell("0.00"));
            table2.addCell(getBillRowCell("0.00"));
            table2.addCell(getIRDCell7("ALLAHABAD BANK, KANNADHERI BRANCH"));
            table2.addCell(("SGST:"));
            table2.addCell(getBillRowCell2("GST28%"));
            table2.addCell(getBillRowCell2("0.00"));
            table2.addCell(getBillRowCell2("0.00"));
            table2.addCell(getBillRowCell2("0.00"));
            table2.addCell(getBillRowCell3("PH NO:122567890"));
            table2.addCell(("PRE\nBAL:"));
            table2.addCell(("NO TAX"));
            table2.addCell(("54995.00"));
            table2.addCell(("0.00"));
            table2.addCell(("0.00"));
            table2.addCell("GROSS:");
            PdfPTable table3= new PdfPTable(3);

            table3.setWidthPercentage(110);
            table3.setWidths(new float[] { 8,6,4});
            table3.addCell(getIRDCell5("TWENTY FIVE THOUSAND ONLY /-"));
            table3.addCell(("Total quantity :123"));
            table3.addCell(("TOTAL:76877"));
            PdfPTable heading = new PdfPTable(4);

            heading.setWidthPercentage(110);
            heading.setWidths(new float[] { 2,7,3,5});

            PdfPTable billTable = new PdfPTable(12);
            PdfPCell cell2;
            cell2 = new PdfPCell(getBillHeaderCell1(new Paragraph("CGST")));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setColspan(2);
           //one page contains 15 records
            billTable.setWidthPercentage(110);
            billTable.setWidths(new float[] { 1,5,2,2,2,2,2,2,2,2,2,4 });

            billTable.addCell(getBillHeaderCell("no"));
            billTable.addCell(getBillHeaderCell("particulars"));
            billTable.addCell(getBillHeaderCell("HSN"));
            billTable.addCell(getBillHeaderCell("qty"));
            billTable.addCell(getBillHeaderCell("Rate"));
            billTable.addCell(getBillHeaderCell("Dis%"));
            billTable.addCell(getBillHeaderCell("Nett"));
            billTable.addCell((cell2));
            cell2 = new PdfPCell(getBillHeaderCell1(new Paragraph("SGST")));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setColspan(2);
            billTable.addCell((cell2));
            billTable.addCell(getBillHeaderCell2("AMOUNT"));
            billTable.addCell(getBillHeaderCell2(""));
            billTable.addCell(getBillHeaderCell2(""));
            billTable.addCell(getBillHeaderCell2(""));
            billTable.addCell(getBillHeaderCell2(""));
            billTable.addCell(getBillHeaderCell2(""));
            billTable.addCell(getBillHeaderCell2(""));
            billTable.addCell(getBillHeaderCell2(""));
            billTable.addCell(getBillHeaderCell2("TAX"));
            billTable.addCell(getBillHeaderCell2("AMT"));
            billTable.addCell(getBillHeaderCell2("TAX"));
            billTable.addCell(getBillHeaderCell2("AMT"));
            billTable.addCell(getBillHeaderCell2(""));

            billTable.addCell(getBillRowCell("1"));
            billTable.addCell(getBillRowCell1("IPHONE XR 2020"));
            billTable.addCell(getBillRowCell1("511"));
            billTable.addCell(getBillRowCell("1500 DOS"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("12"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("12"));
            billTable.addCell(getBillRowCell("121"));
            billTable.addCell(getBillRowCell("13"));
            billTable.addCell(getBillRowCell("312"));
            billTable.addCell(getBillRowCell("21780.00"));
            billTable.addCell(getBillRowCell("1"));
            billTable.addCell(getBillRowCell1("IPHONE XR 2020"));
            billTable.addCell(getBillRowCell1("511"));
            billTable.addCell(getBillRowCell("1500 DOS"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("12"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("2"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("3"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("21780.00"));
            billTable.addCell(getBillRowCell("1"));
            billTable.addCell(getBillRowCell1("IPHONE XR 2020"));
            billTable.addCell(getBillRowCell1("511"));
            billTable.addCell(getBillRowCell("1500 DOS"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("12"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("2"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("3"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("21780.00"));
            billTable.addCell(getBillRowCell("1"));
            billTable.addCell(getBillRowCell1("IPHONE XR 2020"));
            billTable.addCell(getBillRowCell1("511"));
            billTable.addCell(getBillRowCell("1500 DOS"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("12"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("2"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("3"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("21780.00"));
            billTable.addCell(getBillRowCell("1"));
            billTable.addCell(getBillRowCell1("IPHONE XR 2020"));
            billTable.addCell(getBillRowCell1("511"));
            billTable.addCell(getBillRowCell("1500 DOS"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("12"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("2"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("3"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("21780.00"));
            billTable.addCell(getBillRowCell("1"));
            billTable.addCell(getBillRowCell1("IPHONE XR 2020"));
            billTable.addCell(getBillRowCell1("511"));
            billTable.addCell(getBillRowCell("1500 DOS"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("12"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("2"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("3"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("21780.00"));
            billTable.addCell(getBillRowCell("1"));
            billTable.addCell(getBillRowCell1("IPHONE XR 2020"));
            billTable.addCell(getBillRowCell1("511"));
            billTable.addCell(getBillRowCell("1500 DOS"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("12"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("2"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("3"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("21780.00"));
            billTable.addCell(getBillRowCell("1"));
            billTable.addCell(getBillRowCell1("IPHONE XR 2020"));
            billTable.addCell(getBillRowCell1("511"));
            billTable.addCell(getBillRowCell("1500 DOS"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("12"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("2"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("3"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("21780.00"));
            billTable.addCell(getBillRowCell("1"));
            billTable.addCell(getBillRowCell1("IPHONE XR 2020"));
            billTable.addCell(getBillRowCell1("511"));
            billTable.addCell(getBillRowCell("1500 DOS"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("12"));
            billTable.addCell(getBillRowCell("14.50"));
            billTable.addCell(getBillRowCell("2"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("3"));
            billTable.addCell(getBillRowCell("123"));
            billTable.addCell(getBillRowCell("21780.00"));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));
            billTable.addCell(getBillRowCell(""));

            billTable.addCell(getBillRowCell(" "));






            Image image = null;
            try {
                Drawable d = getResources().getDrawable(R.drawable.s);
                BitmapDrawable bitDw = ((BitmapDrawable) d);
                Bitmap bmp = bitDw.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                image = Image.getInstance(stream.toByteArray());
                image.scaleAbsolute(40f, 2f);//image width,height

            } catch (Exception e) {
                image = null;
            }

            heading.addCell(image);

          heading.addCell(heading1);
            heading.addCell(heading2);
            heading.addCell(heading3);
            heading.addCell(heading4);
            heading.addCell( heading5);
            heading.addCell( heading6);
            heading.addCell(heading9);
            mDoc.add(heading);
            mDoc.add(billTable);

mDoc.add(table2);
mDoc.add(table3);
            mDoc.close();
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public static PdfPCell getIRHCell(String text, int alignment) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
        /*	font.setColor(BaseColor.GRAY);*/
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell getIRDCell6(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setPadding (3.0f);
        cell.setBorderColor(BaseColor.WHITE);

        return cell;
    }

    public static PdfPCell getIRDCell(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        return cell;
    }
    public static PdfPCell getIRDCell4(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
        font.setColor(BaseColor.RED);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);

        cell.setPadding (5.0f);
        cell.setPadding (5.0f);
        cell.setBorderColor(BaseColor.WHITE);
        return cell;
    }
    public static PdfPCell getIRDCell7(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
        font.setColor(BaseColor.BLACK);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment (Element.ALIGN_LEFT);
        cell.setPadding (5.0f);
        cell.setPadding (5.0f);
        cell.setBorderColor(BaseColor.WHITE);
        return cell;
    }
    public static PdfPCell getIRDCell5(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12);
        font.setColor(BaseColor.RED);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);



        return cell;
    }
    public static PdfPCell getIRDCell14(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setPadding (5.0f);
        cell.setBorderColor(BaseColor.WHITE);

        cell.setHorizontalAlignment (Element.ALIGN_LEFT);
        cell.setPadding (5.0f);
        cell.setPadding (5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);

        return cell;
    }
    public static PdfPCell getBillRowCell(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
        cell.setPadding (5.0f);

        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }
    public static PdfPCell getIRDCell15(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setPadding (5.0f);
        cell.setBorderColor(BaseColor.WHITE);

        cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
        cell.setPadding (5.0f);
        cell.setPadding (5.0f);


        return cell;
    }
    public static PdfPCell getIRDCell10(Paragraph text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setPadding (5.0f);
        cell.setBorderColor(BaseColor.WHITE);
        cell.setFixedHeight(80);
        cell.setPadding (5.0f);
        cell.setPadding (5.0f);

        return cell;
    }
    public static PdfPCell getIRDCell3(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setPadding (3.0f);
        cell.setBorderColor(BaseColor.WHITE);

        cell.setBackgroundColor(new BaseColor(253, 243, 198));
        return cell;
    }
    public static PdfPCell getIRDCell9(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setPadding (5.0f);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setFixedHeight(80);
        cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
        cell.setPadding (5.0f);
        cell.setPadding (5.0f);
        cell.setBackgroundColor(new BaseColor(0, 0, 0));
        return cell;
    }
    public static PdfPCell getIRDCell1(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setPadding (3.0f);
        cell.setBorderColor(BaseColor.BLACK);
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        cell = new PdfPCell(phrase);

        cell.setPadding (5.0f);
        cell.setBackgroundColor(new BaseColor(224, 224, 224));
        return cell;
    }
    public static PdfPCell getIRDCell12(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setPadding (3.0f);
        BaseColor O = new BaseColor(111,45,168);
        cell.setBorderColor(O);
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
        BaseColor O1 = new BaseColor(255,255,255);
        font.setColor(O1);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        BaseColor O2 = new BaseColor(130,111,155);
        cell.setBackgroundColor(O2);
        return cell;
    }
    public static PdfPCell getIRDCell13(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setPadding (3.0f);
        BaseColor O = new BaseColor(111,45,168);
        cell.setBorderColor(O);
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
        BaseColor O1 = new BaseColor(0,0,0);
        font.setColor(O1);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        BaseColor O2 = new BaseColor(190, 153, 215);
        cell.setBackgroundColor(O2);
        return cell;
    }

    public static PdfPCell getBillHeaderCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 13);
        font.setColor(BaseColor.RED);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }
    public static PdfPCell getBillHeaderCell1(Paragraph text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 13);
        font.setColor(BaseColor.RED);
        fs.addFont(font);
        Phrase phrase = fs.process(String.valueOf(text));
        PdfPCell cell = new PdfPCell (phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);

        return cell;
    }
    public static PdfPCell getBillHeaderCell2(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 13);
        font.setColor(BaseColor.RED);
        fs.addFont(font);
        Phrase phrase = fs.process(String.valueOf(text));
        PdfPCell cell = new PdfPCell (phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);

        return cell;
    }


    public static PdfPCell getBillRowCell2(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
        cell.setPadding (5.0f);
        cell.setBorderWidthTop(0);
        return cell;
    }
    public static PdfPCell getBillRowCell4(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
        cell.setPadding (5.0f);
        cell.setBorder(PdfPCell.NO_BORDER);


        return cell;
    }
    public static PdfPCell getBillRowCell3(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_LEFT);
        cell.setPadding (5.0f);
        cell.setBorderWidthTop(0);
        return cell;
    }
    public static PdfPCell getBillRowCell1(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_LEFT);
        cell.setPadding (5.0f);

        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    public static PdfPCell getBillFooterCell(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    public static PdfPCell getValidityCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setBorder(0);
        return cell;
    }

    public static PdfPCell getAccountsCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthTop(0);
        cell.setPadding (5.0f);
        return cell;
    }
    public static PdfPCell getAccountsCellR(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthTop(0);
        cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
        cell.setPadding (5.0f);
        cell.setPaddingRight(20.0f);
        return cell;
    }

    public static Paragraph getdescCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        Paragraph cell = new Paragraph (phrase);

        return cell;
    }



    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                savePdf();

            } else {

                Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
            }
        }
    }


}




