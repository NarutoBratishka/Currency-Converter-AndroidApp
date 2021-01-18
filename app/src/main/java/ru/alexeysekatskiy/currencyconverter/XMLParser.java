package ru.alexeysekatskiy.currencyconverter;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParser implements Runnable{

    public XMLParser() {
        super();
    }

    public void run() {

        try {

            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory
                    .newDocumentBuilder();
            Document document = documentBuilder.parse(new InputSource(url
                    .openStream()));

            NodeList nodlist = document.getElementsByTagName("Valute");

            if (CurrencyList.valute[0] != null) CurrencyList.clear();
            CurrencyList.add(new CurrencyBucket("RUB", 1, "Рубль"));

            for (int i = 0; i < nodlist.getLength(); i++) {
                Element element = (Element) nodlist.item(i);

                NodeList nodlistCharCode = element.getElementsByTagName("CharCode");
                Element _charCode = (Element) nodlistCharCode.item(0);


                NodeList nodlistNominal = element.getElementsByTagName("Nominal");
                Element _nominal = (Element) nodlistNominal.item(0);

                NodeList nodlistName = element.getElementsByTagName("Name");
                Element _name = (Element) nodlistName.item(0);

                NodeList nodlistValue = element.getElementsByTagName("Value");
                Element _value = (Element) nodlistValue.item(0);


                String charCode = _charCode.getChildNodes().item(0).getNodeValue();
                double value = Double.parseDouble(_value.getChildNodes().item(0).getNodeValue().replace(',', '.'));
                double nominal = Double.parseDouble(_nominal.getChildNodes().item(0).getNodeValue().replace(',', '.'));
                double resultValue = value/nominal;
                String name = _name.getChildNodes().item(0).getNodeValue();

                CurrencyBucket temp = new CurrencyBucket(charCode, resultValue, name);
                CurrencyList.add(temp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
