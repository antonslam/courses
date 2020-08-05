package com.example.courses;

import com.example.courses.entities.Course;
import com.example.courses.entities.Currency;
import com.example.courses.repos.CourseRepo;
import com.example.courses.repos.CurrencyRepo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Get {

    private static Document GetDocument(String url_s) throws IOException, SAXException, ParserConfigurationException {

        StringBuilder responseBuilder = new StringBuilder();
        try {
            // Create a URLConnection object for a URL
            URL url = new URL(url_s);
            URLConnection conn = url.openConnection();
            HttpURLConnection httpConn;

            httpConn = (HttpURLConnection)conn;
            BufferedReader rd = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "windows-1251"));
            String line;

            while ((line = rd.readLine()) != null)
            {
                responseBuilder.append(line).append('\n');
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();


        InputSource is = new InputSource(new StringReader(responseBuilder.toString()));
        return builder.parse(is);
        
    }



    public static void GetCurrency(CurrencyRepo currencyRepo){
        Document document = null;

        try {
            document = GetDocument("http://www.cbr.ru/scripts/XML_valFull.asp");
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        assert document != null;

        NodeList Valuta = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < Valuta.getLength(); i++) {
            Node Items  = Valuta.item(i);
            NodeList Item = Items.getChildNodes();

            Long id = null;
            String Name = "";
            String Char = "";

            for (int j = 0; j < Item.getLength(); j++) {
                Node item = Item.item(j);
                String I_name = item.getNodeName();
                String I_val = item.getTextContent();

                if (I_name.equals("Name")){
                    Name = I_val;
                }else if (I_name.equals("ISO_Num_Code") && !I_val.equals("")){
                    id = Long.valueOf(I_val);
                }else if (I_name.equals("ISO_Char_Code") && !I_val.equals("")){
                    Char = I_val;
                }

            }
            if (Name.length() != 0 && id != null && Char.length() != 0){
                Currency currency = new Currency(id, Name, Char);
                currencyRepo.save(currency);
            }
        }
    }


    public static void GetCourse(CourseRepo courseRepo) {

        LocalDate date1 = LocalDate.now();
        Document document = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String date = format.format(new Date() );
        try {
            document = GetDocument("http://www.cbr.ru/scripts/XML_daily.asp?date_req="+date);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        assert document != null;

        NodeList Valuta = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < Valuta.getLength(); i++) {
            Node Items  = Valuta.item(i);
            NodeList Item = Items.getChildNodes();


            Long id = null;
            Integer Nominal = null;
            Float Value = null;

            for (int j = 0; j < Item.getLength(); j++) {
                Node item = Item.item(j);
                String I_name = item.getNodeName();
                String I_val = item.getTextContent();

                if (I_name.equals("NumCode")){
                    id = Long.valueOf(I_val);
                }else if (I_name.equals("Nominal") && !I_val.equals("")){
                    Nominal = Integer.valueOf(I_val);
                }else if (I_name.equals("Value") && !I_val.equals("")){
                    Value = Float.valueOf(I_val.replace(",", "."));
                }

            }
            if (Nominal != null && id != null && Value != null){
                Currency currency = new Currency();
                currency.setId(id);

                Course course = new Course(date1, Nominal, Value, currency);
                courseRepo.save(course);
            }
        }
    }
}
