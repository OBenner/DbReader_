//package UI;
//
//import Entities.Auto;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * Created by omyag on 08.04.2017.
// */
//public class logicJDOM  {
//    Connection connection;
//    connection = DriverManager.getConnection("jdbc:sqlite:db.db");
//        public static Document create(List<Auto> auto) {
////создание корневого элемента <Auto>
//            Element root = new Element("studentsnew");
//            Iterator<Auto> studentIterator =
//                    list.iterator();
//            while(studentIterator.hasNext()) {
//                Auto autos = studentIterator.next();
////создание элемента <student> и его содержимого
//                Element studentElement = new Element("student");
////создание атрибутов и передача им значений
//                studentElement.setAttribute("login",
//                        student.getLogin());
//                studentElement.setAttribute("phone",
//                        student.getTelephone());
//                Element faculty = new Element("faculty");
//                faculty.setText(student.getFaculty());
////«вложение» элемента <faculty> в элемент <student>
//                studentElement.addContent(faculty);
//                Element name = new Element("name");
//                name.setText(student.getName());
//                studentElement.addContent(name);
////создание элемента <address>
//                Element addressElement = new Element("address");
//                Student.Address address = student.getAddress();
//                Element country = new Element("country");
//                country.setText(address.getCountry());
//                addressElement.addContent(country);
//                Element city = new Element("city");
//                city.setText(address.getCity());
//                addressElement.addContent(city);
//                Element street = new Element("street");
//                street.setText(address.getStreet());
//// «вложение» элемента <street> в элемент <address>
//                addressElement.addContent(street);
////«вложение» элемента <address> в элемент <student>
//                studentElement.addContent(addressElement);
////«вложение» элемента <student> в элемент <students>
//                root.addContent(studentElement);
//            }
////создание основного дерева XML-документа
//            return new Document(root);
//        }
//        public static boolean saveDocument(String fileName,
//                                           Document doc) {
//            boolean complete = true;
//            XMLOutputter outputter = new XMLOutputter();
//// запись XML-документа
//            try {
//                outputter.output(doc, new FileOutputStream(fileName));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                complete = false;
//            } catch (IOException e) {
//                e.printStackTrace();
//                complete = false;
//            }
//
//            return complete;
//        }
//
//    }
