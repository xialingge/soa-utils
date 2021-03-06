package com.carlgira.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.OutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by carlgira on 08/03/2016.
 */
public class Utils {

    public static void marshall(Object object, OutputStream outputStream){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(object, outputStream );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Object unmarshallFileContent(String fileName, Class oClass ){
        Object result = null;
        try {
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(oClass);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            result = jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Object unmarshallString(String xmlContent, Class oClass ){
        Object result = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(oClass);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(xmlContent);
            result = jaxbUnmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date parseDate(String dateString){
        Date date = null;
        try{
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            date = dt.parse(dateString.substring(0,19));
        }
        catch (Exception e){
            return null;
        }
        return  date;
    }
}
