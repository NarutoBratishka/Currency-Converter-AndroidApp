package ru.alexeysekatskiy.currencyconverter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;


//@Root(name = "ValCurs", strict = false)
public class Post {
//    @Transient
//    @Path("Valute")
//    @Element(name = "NumCode", type = String.class)
    private String numCode;
//    @Path("Valute")
//    @Element(name = "CharCode", type = String.class)
    private String charCode;
//    @Path("Valute")
//    @Element(name = "Nominal", type = String.class)
    private String nominal;
//    @Path("Valute")
//    @Element(name = "Name", type = String.class)
    private String name;
//    @Path("Valute")
//    @Element(name = "Value", type = String.class)
    private String value;

//    public double getNumCode() {
//        return numCode;
//    }
//
//    public void setNumCode(String sNumCode) {
//        numCode = Double.parseDouble(sNumCode.replace(',', '.'));
//    }
//
//    public String getCharCode() {
//        return charCode;
//    }
//
//    public void setCharCode(String charCode) {
//        this.charCode = charCode;
//    }
//
//    public double getNominal() {
//        return nominal;
//    }
//
//    public void setNominal(String sNominal) {
//        nominal = Double.parseDouble(sNominal.replace(',', '.'));
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public double getValue() {
//        return value;
//    }
//
//    public void setValue(String sValue) {
//        value = Double.parseDouble(sValue.replace(',', '.'));
//    }


    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
