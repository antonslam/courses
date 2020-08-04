package com.example.courses;

import com.example.courses.entities.Currency;
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

public class Get {

    public static void GetCurrency(CurrencyRepo currencyRepo) throws ParserConfigurationException, IOException, SAXException {

        StringBuilder responseBuilder = new StringBuilder();
        try {
            // Create a URLConnection object for a URL
            URL url = new URL( "http://www.cbr.ru/scripts/XML_valFull.asp" );
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
        Document document = builder.parse(is);

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



}
