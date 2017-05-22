/**
 * Created by omyag on 17.03.2017.
 */

import Entities.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.lang.annotation.Documented;
import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.scene.input.KeyCode.R;


public class ConnectDB {
    Connection connection;


    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ConnectDB() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:db.db");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //-----------------------------------------------------------------
    //Метод записывающий значения таблицы в лист
    public List<Auto> getAuto() {
        if (connection == null) return null;
        try {
            List<Auto> autos = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select  id_auto, Model, Data_vipuska, Color, Probeg, Price from Auto");
            while (rs.next()) {

                int id = rs.getInt(1);
                String Model = rs.getString(2);
                String Data_vipuska = rs.getString(3);
                String Color = rs.getString(4);
                int Probeg = rs.getInt(5);
                int Price = rs.getInt(6);
                autos.add(new Auto(id, Model, Data_vipuska, Color, Probeg, Price));
            }
            rs.close();
            statement.close();
            return autos;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /// Метод, добавляющий запись в таблицу Автомобили
    public void insertAuto() {
        if (connection == null) return;
        try {
            System.out.println("Введите данные в формате:\n" +
                    "Модель \n" +
                    "Дата Выпуска\n" +
                    "Цвет\n" +
                    "Пробег\n" +
                    "Цена.\n");


            PreparedStatement preparedStatement = connection.prepareStatement("insert into Auto (Model,Data_vipuska,Color,Probeg,Price) values (?,?,?,?,?) ");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            if (sc.hasNextInt()) {
                int s3 = sc.nextInt();
                if (sc.hasNextInt()) {
                    int s4 = sc.nextInt();
                    preparedStatement.setString(1, s);
                    preparedStatement.setString(2, s1);
                    preparedStatement.setString(3, s2);
                    preparedStatement.setInt(4, s3);
                    preparedStatement.setInt(5, s4);
                    preparedStatement.execute();
                    preparedStatement.close();
                    System.out.println("Выполнено");

                } else {
                    System.out.println("Вы ввели неправильную цену автомобиля ");
                }
            } else {
                System.out.println("Вы ввели неправильный пробег");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }


    ///Метод, удаляющий запись из таблицы Автомобили
    public void deleteAutoById() {
        if (connection == null) return;
        try {
            System.out.println("Введите id автомобиля, который хотите удалить.");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int s = sc.nextInt();
                PreparedStatement preparedStatement = connection.prepareStatement("delete from Auto where id_auto = ?");
                preparedStatement.setInt(1, s);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Удалено");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
           // e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }


    /// Метод, изменяющий запись в таблице Автомобили
    public void updateAuto() throws SQLException {
        if (connection == null) return;
        System.out.println("Для изменения данных в таблице Автомобили, введите новые в формате:\n" +
                "Модель\n" +
                "Дата выпуска\n" +
                "Цвет\n" +
                "Пробег\n" +
                "Цена\n" +
                "В конце укажите id, который хотите изменить.");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update Auto set Model=?,Data_vipuska=?,Color=?,Probeg=?,Price=? where id_auto=?");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //модель
            String s1 = sc.nextLine(); //дата выпуска
            String s2 = sc.nextLine(); //цвет
            if (sc.hasNextInt()) {
                int s3 = sc.nextInt();// пробег
                if (sc.hasNextInt()) {
                    int s4 = sc.nextInt(); // цена
                    if (sc.hasNextInt()) {
                        int s5 = sc.nextInt();// id
                        preparedStatement.setString(1, s);
                        preparedStatement.setString(2, s1);
                        preparedStatement.setString(3, s2);
                        preparedStatement.setInt(4, s3);
                        preparedStatement.setInt(5, s4);
                        preparedStatement.setInt(6, s5);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                        System.out.println("Выполнено");

                    } else {
                        System.out.println("Вы ввели неправильный id");
                    }
                } else {
                    System.out.println("Вы ввели неправильную цену");
                }

            } else {
                System.out.println("Вы ввели неправильный пробег");

            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");

        }
        System.out.println("Данные изменены");
        return;
    }


/// Метод, изменяющий запись цвет в таблице Автомобили

    public void updateAutoColor() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения цвета в таблице Автомобили, введите новые в формате:\n" +
                    "Цвет\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //цвет
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Auto set Color=? where id_auto=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Цвет Изменен.");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий запись Пробег в таблице Автомобили
    public void updateAutoProbeg() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения пробега в таблице Автомобили, введите новые в формате:\n" +
                    "Пробег\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int s = sc.nextInt();// пробег
                if (sc.hasNextInt()) {
                    int s1 = sc.nextInt();// id
                    PreparedStatement preparedStatement = connection.prepareStatement("update  Auto set Probeg=? where id_auto=? ");
                    preparedStatement.setInt(1, s);
                    preparedStatement.setInt(2, s1);
                    preparedStatement.execute();
                    preparedStatement.close();
                    System.out.println("Пробег Изменен.");
                } else {
                    System.out.println("Вы ввели неправильные данные, они должны быть в числовом варианте");
                }
            } else {
                System.out.println("Вы ввели неправильные данные, они должны быть в числовом варианте");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий запись Цену в таблице Автомобили
    public void updateAutoPrice() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения цены в таблице Автомобили, введите новые в формате:\n" +
                    "Новая цена\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int s = sc.nextInt();// цена
                if (sc.hasNextInt()) {
                    int s1 = sc.nextInt();// id
                    PreparedStatement preparedStatement = connection.prepareStatement("update  Auto set Price=? where id_auto=? ");
                    preparedStatement.setInt(1, s);
                    preparedStatement.setInt(2, s1);
                    preparedStatement.execute();
                    preparedStatement.close();
                    System.out.println("Цена Изменен.");
                } else {
                    System.out.println("Вы ввели неправильные данные, они должны быть в числовом варианте");
                }
            } else {
                System.out.println("Вы ввели неправильные данные, они должны быть в числовом варианте");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий запись модель в таблице Автомобили
    public void updateAutoModel() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения модели в таблице Автомобили, введите новые в формате:\n" +
                    "Новая модель\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //модель
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Auto set Model=? where id_auto=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Модель Изменена.");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий запись модель в таблице Автомобили
    public void updateAutoData() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения даты выпуска автомобиля в таблице Автомобили, введите новые в формате:\n" +
                    "Новая дата выпуска автомобиля\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //дата выпуска
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Auto set Data_vipuska=? where id_auto=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Дата выпуска Изменена.");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    //--------------------------------------------------------------------
//Метод, запс значения таблицы Клиенты в лист
    public List<Clients> getClient() {
        if (connection == null) return null;
        try {
            List<Clients> clientss = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id_client, FIO,Document from Clients");
            while (rs.next()) {

                int id = rs.getInt(1);
                String FIO = rs.getString(2);
                String Document = rs.getString(3);
                clientss.add(new Clients(id, FIO, Document));
            }
            rs.close();
            statement.close();
            return clientss;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Метод,добавляющий запись в таблицу Клиенты
    public void insertClient() {
        if (connection == null) return;
        try {
            System.out.println("Введите данные в формате:\n" +
                    "Фамилия Имя Отчество\n" +
                    "Документ");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String s1 = sc.nextLine();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Clients (FIO,Document) values (?,?) ");
            preparedStatement.setString(1, s);
            preparedStatement.setString(2, s1);
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Выполнено");
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    ///Метод, удаляющий запись из таблицы Клиенты
    public void deleteClientById() {
        if (connection == null) return;
        try {
            System.out.println("Введите id клиента, которого хотите удалить.");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int s = sc.nextInt();
                PreparedStatement preparedStatement = connection.prepareStatement("delete from Clients where id_client = ?");
                preparedStatement.setInt(1, s);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Удаление выполнено");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий запись в таблице Клиенты
    public void updateClients() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения данных в таблице Клиенты, введите новые в формате:\n" +
                    "Фамилия Имя Отчество\n" +
                    "Документ\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //ФИО
            String s1 = sc.nextLine(); //Документ
            if (sc.hasNextInt()) {
                int s2 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Clients set FIO=?,Document=? where id_client=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setString(2, s1);
                preparedStatement.setInt(3, s2);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Данные изменены");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий запись модель в таблице Клиенты
    public void updateClientFIO() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения ФИО в таблице Клиенты, введите новые в формате:\n" +
                    "Новвые данные ФИО\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //модель
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Clients set FIO=? where id_client=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("ФИО Изменены.");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий запись документ в таблице Клиенты
    public void updateClientDoc() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения документа в таблице Клиенты, введите новые в формате:\n" +
                    "Новые данные документов\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //документы
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Clients set Document=? where id_client=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Документы Изменены.");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    //----------------------------------------------------------------------
//Метод записывающий значения таблицы в лист Доступность автомобилей
    public List<Dostup> getDostup() {
        if (connection == null) return null;
        try {
            List<Dostup> dostup = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select  Nomer,Auto,Free from Dostup");
            while (rs.next()) {

                int Nomer = rs.getInt(1);
                int Auto = rs.getInt(2);
                String Free = rs.getString(3);
                dostup.add(new Dostup(Nomer, Auto, Free));
            }
            rs.close();
            statement.close();
            return dostup;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /// Метод, добавляющий запись в таблицу Доступность Автомобилей
    public void insertDostup() {
        if (connection == null) return;
        try {
            System.out.println("Введите данные в формате:\n" +
                    "Доступность автомобиля(да/нет)\n" +
                    "id Автомобиля");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();

                PreparedStatement preparedStatement = connection.prepareStatement("insert into Dostup (Auto,Free) values (?,?) ");
                preparedStatement.setInt(1, s1);
                preparedStatement.setString(2, s);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Выполнено");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    ///Метод, удаляющий запись из таблицы Доступ Автомобилей
    public void deleteDostupById() {
        if (connection == null) return;
        try {
            System.out.println("Введите номер, который хотите удалить.");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int s = sc.nextInt();
                PreparedStatement preparedStatement = connection.prepareStatement("delete from Dostup where Nomer = ?");
                preparedStatement.setInt(1, s);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Удаление выполнено");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий запись в таблице Доступность Автомобилей
    public void updateDostup() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения данных в таблице Доступность автомобилей, введите новые в формате:\n" +
                    "Доступность автомобиля(да/нет)\n" +
                    "id Автомобиля \n" +
                    "В конце укажите номер, который хотите изменить.\n");
            Scanner sc = new Scanner(System.in);
            String s1 = sc.nextLine(); //Доступность
            if (sc.hasNextInt()) {
                int s = sc.nextInt();// Автомобиль
                if (sc.hasNextInt()) {
                int s2 = sc.nextInt(); // Номер
                    PreparedStatement preparedStatement = connection.prepareStatement("update  Dostup set Auto=?,Free=? where Nomer=? ");
                    preparedStatement.setInt(1, s);
                    preparedStatement.setString(2, s1);
                    preparedStatement.setInt(3, s2);
                    preparedStatement.execute();
                    preparedStatement.close();
                    System.out.println("Выполнено");
                } else {
                    System.out.println("Вы ввели неправильный номер");
                }
            } else {
                System.out.println("Вы ввели неправильный id автомобиля");
            }
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий запись доступность в таблице Доступность Автомобилей
    public void updateDostupFree() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения доступности автомобиля в таблице Доступ, введите новые в формате:\n" +
                    "Новые данные доступности\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //документы
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Dostup set Free=? where Nomer=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Доступность Изменена.");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий автомобиль  в таблице Доступность  Автомобилей
    public void updateDostupAuto() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения данных автомобиля в таблице Доступ автомобилей, введите новые в формате:\n" +
                    "Новый автомобиль (номер id)\n" +
                    "В конце укажите id записи в таблице, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int s = sc.nextInt();//автомобиль id
                if (sc.hasNextInt()) {
                    int s1 = sc.nextInt();// id
                    PreparedStatement preparedStatement = connection.prepareStatement("update  Dostup set Auto=? where Nomer=? ");
                    preparedStatement.setInt(1, s);
                    preparedStatement.setInt(2, s1);
                    preparedStatement.execute();
                    preparedStatement.close();
                    System.out.println("Автомобиль Изменен.");
                } else {
                    System.out.println("Вы ввели неправильные данные, они должны быть в числовом варианте");
                }
            } else {
                System.out.println("Вы ввели неправильные данные, они должны быть в числовом варианте");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    //-------------------------------------------------------------------
//Метод записывающий значения таблицы в лист Сотрудников
    public List<Sotrudniki> getSotrudniki() {
        if (connection == null) return null;
        try {
            List<Sotrudniki> sotrudnikis = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select  id_sotr,FIO,Data_priema,Doljnost from Sotrudniki");
            while (rs.next()) {

                int id_sotr = rs.getInt(1);
                String FIO = rs.getString(2);
                String Data_priema = rs.getString(3);
                String Dokjnost = rs.getString(4);
                sotrudnikis.add(new Sotrudniki(id_sotr, FIO, Data_priema, Dokjnost));
            }
            rs.close();
            statement.close();
            return sotrudnikis;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /// Метод, добавляющий запись в таблицу Сотрудники
    public void insertSotrudniki() {
        if (connection == null) return;
        try {
            System.out.println("Введите данные в формате:\n" +
                    "Фамилия Имя Отчество\n" +
                    "Дата приема\n" +
                    "Должность\n");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Sotrudniki (FIO,Data_priema,Doljnost) values (?,?,?) ");
            preparedStatement.setString(1, s);
            preparedStatement.setString(2, s1);
            preparedStatement.setString(3, s2);
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Выполнено");
        } catch (SQLException e) {
         //   e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    ///Метод, удаляющий запись из таблицы Доступ
    public void deleteSotrudnikiById() {
        if (connection == null) return;
        try {
            System.out.println("Введите id сотрудника, которого хотите удалить.");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int s = sc.nextInt();
                PreparedStatement preparedStatement = connection.prepareStatement("delete from Sotrudniki where id_sotr = ?");
                preparedStatement.setInt(1, s);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Удаление выполнено");
            } else {
                System.out.println("Вы ввели неправильные данные id, они должны быть в числовом варианте");
            }
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий запись в таблице Сотрудники
    public void updateSotrudniki() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения данных в таблице Сотрудники, введите новые в формате:\n" +
                    "Фамилия Имя Отчество\n" +
                    "Дата приема\n" +
                    "Должность\n" +
                    "В конце укажите id сотрудника, который хотите изменить.\n");
            Scanner sc = new Scanner(System.in);
            String s1 = sc.nextLine(); //Fio
            String s2 = sc.nextLine(); //Дата приема
            String s3 = sc.nextLine(); //Должность
            if (sc.hasNextInt()) {
                int s4 = sc.nextInt(); // Номер
                PreparedStatement preparedStatement = connection.prepareStatement("update  Sotrudniki set FIO=?,Data_priema=?,Doljnost=? where id_sotr=? ");
                preparedStatement.setString(1, s1);
                preparedStatement.setString(2, s2);
                preparedStatement.setString(3, s3);
                preparedStatement.setInt(4, s4);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Выполнено");
            } else {
                System.out.println("Вы ввели неправильный номер");
            }
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }


    // Метод, изменяющий ФИО  в таблице Сотрудники
    public void updateSotrudnikiFIO() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения ФИО в таблице Сотрудники, введите новые  данные в формате:\n" +
                    "Новые ФИО\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //документы
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Sotrudniki set FIO=? where id_sotr=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("ФИО Изменены.");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    // Метод, изменяющий Дату приема на работу  в таблице Сотрудники
    public void updateSotrudnikiData() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения Даты приема в таблице Сотрудники, введите новые  данные в формате:\n" +
                    "Новую дату приема на работу\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //документы
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Sotrudniki set Data_priema=? where id_sotr=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Дата приема Изменена.");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    // Метод, изменяющий Должность  в таблице Сотрудники
    public void updateSotrudnikiDoljnost() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения Должности в таблице Сотрудники, введите новые  данные в формате:\n" +
                    "Новую должность\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //документы
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Sotrudniki set Doljnost=? where id_sotr=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Должность Изменена.");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }
//-------------------------------------------------------------------

    //Метод записывающий значения таблицы в лист Сделки
    public List<Sdelki> getSdelki() {
        if (connection == null) return null;
        try {
            List<Sdelki> sdelkis = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select  Nomer,Data_vidachi,Data_sdachi,Client,Auto,Vidal,Free from Sdelki");
            while (rs.next()) {

                int Nomer = rs.getInt(1);
                String Data_vidachi = rs.getString(2);
                String Data_sdachi = rs.getString(3);
                int Client = rs.getInt(4);
                int Auto = rs.getInt(5);
                int Vidal = rs.getInt(6);
                String Free = rs.getString(7);
                sdelkis.add(new Sdelki(Nomer, Data_vidachi, Data_sdachi, Client, Auto, Vidal, Free));
            }
            rs.close();
            statement.close();
            return sdelkis;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /// Метод, добавляющий запись в таблицу Сделки
    public void insertSdelki() {
        if (connection == null) return;
        try {
            System.out.println("Введите данные в формате:\n" +
                    "Дата выдачи\n" +
                    "Дата сдачи\n" +
                    "Доступность автомобиля\n" +
                    "id Клиента\n" +
                    "id Автомобиля\n" +
                    "Кто выдал(id Сотрудника)");


            PreparedStatement preparedStatement = connection.prepareStatement("insert into Sdelki (Data_vidachi,Data_sdachi,Client,Auto,Vidal,Free) values (?,?,?,?,?,?) ");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();//дата выдачи
            String s1 = sc.nextLine();//дата сдачи
            String s5 = sc.nextLine();//доступность
            if (sc.hasNextInt()) {
                int s2 = sc.nextInt();//клиент
                if (sc.hasNextInt()) {
                    int s3 = sc.nextInt();//автомобиль
                    if (sc.hasNextInt()) {
                        int s4 = sc.nextInt();//кто выдал
                        preparedStatement.setString(1, s);
                        preparedStatement.setString(2, s1);
                        preparedStatement.setInt(3, s2);
                        preparedStatement.setInt(4, s3);
                        preparedStatement.setInt(5, s4);
                        preparedStatement.setString(6, s5);
                        preparedStatement.execute();
                        preparedStatement.close();
                        System.out.println("Выполнено");
                    } else {
                        System.out.println("Вы ввели неправильный id менеджера");
                    }
                } else {
                    System.out.println("Вы ввели неправильный id автомобиля");
                }
            } else {
                System.out.println("Вы ввели неправильный id клиента");
            }


        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    ///Метод, удаляющий запись из таблицы Сделки
    public void deleteSdelkiById() {
        if (connection == null) return;
        try {
            System.out.println("Введите id сделки, которую хотите удалить.");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int s = sc.nextInt();

                PreparedStatement preparedStatement = connection.prepareStatement("delete from Sdelki where Nomer = ?");
                preparedStatement.setInt(1, s);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Выполнено");
            } else {
                System.out.println("Вы ввели неправильный id сделки");

            }
        } catch (SQLException e) {
           // e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }


    /// Метод, изменяющий запись в таблице Сделки
    public void updateSdelki() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения данных в таблице Сотрудники, введите новые в формате:\n" +
                    "Дата выдачи\n" +
                    "Дата сдачи\n" +
                    "Доступность автомобиля\n" +
                    "id Автомобиля\n" +
                    "id Сотрудника\n" +
                    "Номер сделки, которую хотите изменить\n" +
                    "В конце укажите номер, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();//дата выдачи
            String s1 = sc.nextLine();//дата сдачи
            String s5 = sc.nextLine();//доступность
            if (sc.hasNextInt()) {
                int s2 = sc.nextInt();//клиент
                if (sc.hasNextInt()) {
                    int s3 = sc.nextInt();//автомобиль
                    if (sc.hasNextInt()) {
                        int s4 = sc.nextInt();//кто выдал
                        if (sc.hasNextInt()) {
                            int s6 = sc.nextInt();// номер
                            PreparedStatement preparedStatement = connection.prepareStatement("update  Sdelki set Data_vidachi=?,Data_sdachi=?,Client=?,Auto=?,Vidal=?,Free=? where Nomer=? ");
                            preparedStatement.setString(1, s);
                            preparedStatement.setString(2, s1);
                            preparedStatement.setInt(3, s2);
                            preparedStatement.setInt(4, s3);
                            preparedStatement.setInt(5, s4);
                            preparedStatement.setString(6, s5);
                            preparedStatement.setInt(7, s6);
                            preparedStatement.execute();
                            preparedStatement.close();
                            System.out.println("Выполнено");
                        } else {
                            System.out.println("Вы ввели неправильный номер сделки");

                        }
                    } else {
                        System.out.println("Вы ввели неправильный id менеджера выдавшего автомобиль");

                    }
                } else {
                    System.out.println("Вы ввели неправильный id автомобиля");

                }
            } else {
                System.out.println("Вы ввели неправильный id клиента");

            }
        } catch (SQLException e) {
         //   e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    // Метод, изменяющий Дату выдачи автомобиля  в таблице Сделки
    public void updateSdelkiDataV() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения Даты выдачи автомобия в таблице Сделки, введите новые  данные в формате:\n" +
                    "Новую дату выдачи автомобиля\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //дата выдачи
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Sdelki set Data_vidachi=? where Nomer=? ");

                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Дата выдачи автомобиля Изменена.");
            } else {
                System.out.println("Вы ввели неправильный номер");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    // Метод, изменяющий Дату сдачи автомобиля  в таблице Сделки
    public void updateSdelkiDataS() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения Даты сдачи автомобия в таблице Сделки, введите новые  данные в формате:\n" +
                    "Новую дату сдачи автомобиля\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //дата сдачи
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Sdelki set Data_sdachi=? where Nomer=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Дата сдачи автомобиля Изменена.");
            } else {
                System.out.println("Вы ввели неправильный номер");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий Клиента  в таблице Сделки
    public void updateSdelkiClient() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения данных клиента в таблице Сделки , введите новые в формате:\n" +
                    "Новый клиент (номер id)\n" +
                    "В конце укажите id записи в таблице, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int s = sc.nextInt();//клиент id
                if (sc.hasNextInt()) {
                    int s1 = sc.nextInt();// id
                    PreparedStatement preparedStatement = connection.prepareStatement("update  Sdelki set Client=? where Nomer=? ");
                    preparedStatement.setInt(1, s);
                    preparedStatement.setInt(2, s1);
                    preparedStatement.execute();
                    preparedStatement.close();
                    System.out.println("Клиент Изменен.");
                } else {
                    System.out.println("Вы ввели неправильные данные, они должны быть в числовом варианте");
                }
            } else {
                System.out.println("Вы ввели неправильные данные, они должны быть в числовом варианте");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий Автомобиль  в таблице Сделки
    public void updateSdelkiAuto() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения данных автомобиля в таблице Сделки , введите новые в формате:\n" +
                    "Новый клиент (номер id)\n" +
                    "В конце укажите id записи в таблице, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int s = sc.nextInt();//автомобиль id
                if (sc.hasNextInt()) {
                    int s1 = sc.nextInt();// id
                    PreparedStatement preparedStatement = connection.prepareStatement("update  Sdelki set Auto=? where Nomer=? ");
                    preparedStatement.setInt(1, s);
                    preparedStatement.setInt(2, s1);
                    preparedStatement.execute();
                    preparedStatement.close();
                    System.out.println("Автомобиль Изменен.");
                } else {
                    System.out.println("Вы ввели неправильные данные, они должны быть в числовом варианте");
                }
            } else {
                System.out.println("Вы ввели неправильные данные, они должны быть в числовом варианте");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    /// Метод, изменяющий менеджера выдавшего автомобиль  в таблице Сделки
    public void updateSdelkiVidal() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения данных менеджера выдавшего автомобиль в таблице Сделки , введите новые в формате:\n" +
                    "Новый менеджер (номер id)\n" +
                    "В конце укажите id записи в таблице, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int s = sc.nextInt();//менеджер id
                if (sc.hasNextInt()) {
                    int s1 = sc.nextInt();// id
                    PreparedStatement preparedStatement = connection.prepareStatement("update  Sdelki set Vidal=? where Nomer=? ");
                    preparedStatement.setInt(1, s);
                    preparedStatement.setInt(2, s1);
                    preparedStatement.execute();
                    preparedStatement.close();
                    System.out.println("Менеджер Изменен.");
                } else {
                    System.out.println("Вы ввели неправильные данные id менеджера, они должны быть в числовом варианте");
                }
            } else {
                System.out.println("Вы ввели неправильные данные номера сделки, они должны быть в числовом варианте");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    // Метод, изменяющий  доступность автомобиля  в таблице Сделки
    public void updateSdelkiFree() {
        if (connection == null) return;
        try {
            System.out.println("Для изменения доступности автомобия в таблице Сделки, введите новые  данные в формате:\n" +
                    "Новые данные доступности автомобиля\n" +
                    "В конце укажите id, который хотите изменить.");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine(); //дата сдачи
            if (sc.hasNextInt()) {
                int s1 = sc.nextInt();// id
                PreparedStatement preparedStatement = connection.prepareStatement("update  Sdelki set Free=? where Nomer=? ");
                preparedStatement.setString(1, s);
                preparedStatement.setInt(2, s1);
                preparedStatement.execute();
                preparedStatement.close();
                System.out.println("Доступность автомобиля Изменена.");
            } else {
                System.out.println("Вы ввели неправильный id");
            }
        } catch (SQLException e) {
            //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
        return;
    }

    // -------------------------------------------------
//экспорт XML таблицы Автомобили
    public void xmlbuildAuto() throws
            ParserConfigurationException, TransformerException, FileNotFoundException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element Auto = document.createElement("Auto");
        document.appendChild(Auto);
        List<Auto> autos = getAuto();
        for (int i = 0; i < autos.size(); i++) {
            Element auto = document.createElement("auto");
            Auto.appendChild(auto);

            Element id_auto = document.createElement("id_auto");
            id_auto.setTextContent(String.valueOf(autos.get(i).getId()));
            auto.appendChild(id_auto);

            Element model = document.createElement("Model");
            model.setTextContent(String.valueOf(autos.get(i).getModel()));
            auto.appendChild(model);

            Element data_vipuska = document.createElement("Data_vipuska");
            data_vipuska.setTextContent(String.valueOf(autos.get(i).getDataV()));
            auto.appendChild(data_vipuska);

            Element color = document.createElement("Color");
            color.setTextContent(String.valueOf(autos.get(i).getColor()));
            auto.appendChild(color);

            Element probeg = document.createElement("Probeg");
            probeg.setTextContent(String.valueOf(autos.get(i).getProbeg()));
            auto.appendChild(probeg);

            Element price = document.createElement("Price");
            price.setTextContent(String.valueOf(autos.get(i).getPrice()));
            auto.appendChild(price);
        }

        DOMSource domSource = new DOMSource(document);
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("xmlOut\\Auto.xml"))) {
            StreamResult streamResult = new StreamResult(fileOutputStream);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(domSource, streamResult);
            System.out.println("Файл создан");
        } catch (IOException e) {
          //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }


    }


    //экспорт XML таблицы Клиенты
    public void xmlbuildClients() throws
            ParserConfigurationException, TransformerException, FileNotFoundException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element Clients = document.createElement("Clients");
        document.appendChild(Clients);
        List<Clients> clientss = getClient();
        for (int i = 0; i < clientss.size(); i++) {
            Element client = document.createElement("client");
            Clients.appendChild(client);

            Element id_client = document.createElement("id_client");
            id_client.setTextContent(String.valueOf(clientss.get(i).getId()));
            client.appendChild(id_client);

            Element FIO = document.createElement("FIO");
            FIO.setTextContent(String.valueOf(clientss.get(i).getFio()));
            client.appendChild(FIO);

            Element Document = document.createElement("Document");
            Document.setTextContent(String.valueOf(clientss.get(i).getDoc()));
            client.appendChild(Document);

        }

        DOMSource domSource = new DOMSource(document);
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("xmlOut\\Clients.xml"))) {
            StreamResult streamResult = new StreamResult(fileOutputStream);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(domSource, streamResult);
            System.out.println("Файл создан");
        } catch (IOException e) {
         //   e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
    }

    //экспорт XML таблицы Доступ
    public void xmlbuildDostup() throws
            ParserConfigurationException, TransformerException, FileNotFoundException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element Dostup = document.createElement("Dostup");
        document.appendChild(Dostup);
        List<Dostup> dostup = getDostup();
        for (int i = 0; i < dostup.size(); i++) {
            Element dostup_auto = document.createElement("dostup_auto");
            Dostup.appendChild(dostup_auto);

            Element Nomer = document.createElement("Nomer");
            Nomer.setTextContent(String.valueOf(dostup.get(i).getNumber()));
            dostup_auto.appendChild(Nomer);

            Element Auto = document.createElement("Auto");
            Auto.setTextContent(String.valueOf(dostup.get(i).getAuto()));
            dostup_auto.appendChild(Auto);

            Element Free = document.createElement("Free");
            Free.setTextContent(String.valueOf(dostup.get(i).getFree()));
            dostup_auto.appendChild(Free);

        }

        DOMSource domSource = new DOMSource(document);
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("xmlOut\\Dostup.xml"))) {
            StreamResult streamResult = new StreamResult(fileOutputStream);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(domSource, streamResult);
            System.out.println("Файл создан");
        } catch (IOException e) {
           // e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }
    }


    //экспорт XML таблицы Сделки
    public void xmlbuildSdelki() throws
            ParserConfigurationException, TransformerException, FileNotFoundException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element Sdelki = document.createElement("Sdelki");
        document.appendChild(Sdelki);
        List<Sdelki> sdelkis = getSdelki();
        for (int i = 0; i < sdelkis.size(); i++) {
            Element sdelka = document.createElement("sdelka");
            Sdelki.appendChild(sdelka);

            Element Nomer = document.createElement("Nomer");
            Nomer.setTextContent(String.valueOf(sdelkis.get(i).getNumber()));
            sdelka.appendChild(Nomer);

            Element Data_vidachi = document.createElement("Data_vidachi");
            Data_vidachi.setTextContent(String.valueOf(sdelkis.get(i).getdVidachi()));
            sdelka.appendChild(Data_vidachi);

            Element Data_sdachi = document.createElement("Data_sdachi");
            Data_sdachi.setTextContent(String.valueOf(sdelkis.get(i).getdSdachi()));
            sdelka.appendChild(Data_sdachi);

            Element Client = document.createElement("Client");
            Client.setTextContent(String.valueOf(sdelkis.get(i).getClient()));
            sdelka.appendChild(Client);

            Element Auto = document.createElement("Auto");
            Auto.setTextContent(String.valueOf(sdelkis.get(i).getAuto()));
            sdelka.appendChild(Auto);

            Element Vidal = document.createElement("Vidal");
            Vidal.setTextContent(String.valueOf(sdelkis.get(i).getManager()));
            sdelka.appendChild(Vidal);

            Element Free = document.createElement("Free");
            Free.setTextContent(String.valueOf(sdelkis.get(i).getFree()));
            sdelka.appendChild(Free);
        }

        DOMSource domSource = new DOMSource(document);
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("xmlOut\\Sdelki.xml"))) {
            StreamResult streamResult = new StreamResult(fileOutputStream);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(domSource, streamResult);
            System.out.println("Файл создан");
        } catch (IOException e) {
          //  e.printStackTrace();
            System.out.println("Вы совершили ошибку, попробуйте еще раз");
        }

    }

    //экспорт XML таблицы Сотрудники
    public void xmlbuildSotrudniki() throws
            ParserConfigurationException, TransformerException, FileNotFoundException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element Sotrudniki = document.createElement("Sotrudniki");
        document.appendChild(Sotrudniki);
        List<Sotrudniki> sotrudnikis = getSotrudniki();
        for (int i = 0; i < sotrudnikis.size(); i++) {
            Element sotrudnik = document.createElement("sotrudnik");
            Sotrudniki.appendChild(sotrudnik);

            Element id_sotr = document.createElement("id_sotr");
            id_sotr.setTextContent(String.valueOf(sotrudnikis.get(i).getId()));
            sotrudnik.appendChild(id_sotr);

            Element FIO = document.createElement("FIO");
            FIO.setTextContent(String.valueOf(sotrudnikis.get(i).getFio()));
            sotrudnik.appendChild(FIO);

            Element Data_priema = document.createElement("Data_priema");
            Data_priema.setTextContent(String.valueOf(sotrudnikis.get(i).getdPriema()));
            sotrudnik.appendChild(Data_priema);

            Element Doljnost = document.createElement("Doljnost");
            Doljnost.setTextContent(String.valueOf(sotrudnikis.get(i).getDoljnost()));
            sotrudnik.appendChild(Doljnost);

        }

        DOMSource domSource = new DOMSource(document);
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("xmlOut\\Sotrudniki.xml"))){;
        StreamResult streamResult = new StreamResult(fileOutputStream);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(domSource, streamResult);
        System.out.println("Файл создан");
    } catch (IOException e) {
        //  e.printStackTrace();
        System.out.println("Вы совершили ошибку, попробуйте еще раз");
    }

    }

    //------------------------------------------------------
//импорт XML таблицы Автомобили
    public void importXmlAuto() {
        Statement statement = null;
        Schema schema = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Скопируйте файл в директорию, где находится программа" +
                "\n Введите имя файла xml соответствующего для импорта в таблицу Автомобили");
        String string = scanner.nextLine();
        String FILENAME = string + ".xml";

        try {

            final File xmlFile = new File(System.getProperty("user.dir")
                    + File.separator + FILENAME);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);

            doc.getDocumentElement().normalize();
            try {
                SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
                schema = factory.newSchema(new File("xsd\\Auto.xsd"));
                Validator validator = schema.newValidator();
                validator.validate(new DOMSource(doc));


                System.out.println("Файл xml соответствует схемы данной таблицы");

            } catch (Exception e) {
                System.out.println("Файл xml не соответствует  xsd схеме данной таблицы");
                return;
                //   e.printStackTrace();
            }

            NodeList nodeList = doc.getElementsByTagName("auto");


            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);


                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    String Model = element.getElementsByTagName("Model").item(0).getTextContent();
                    String Data_vipuska = element.getElementsByTagName("Data_vipuska").item(0).getTextContent();
                    String Color = element.getElementsByTagName("Color").item(0).getTextContent();
                    String Probeg = element.getElementsByTagName("Probeg").item(0).getTextContent();
                    String Price = element.getElementsByTagName("Price").item(0).getTextContent();

                    statement.execute("INSERT INTO 'Auto'('Model','Data_vipuska','Color','Probeg','Price')" +
                            " VALUES ('" + Model + "','" + Data_vipuska + "','" +
                            Color + "','" + Probeg + "','" + Price + "'); ");
                    System.out.println("Данные импортированы в таблицу");

                }
            }
        } catch (ParserConfigurationException | SAXException
                | IOException ex) {
            Logger.getLogger(ConnectDB.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
           // e.printStackTrace();
            System.out.println("Произошла ошибка, попробуйте еще раз");
        }
    }

    //импорт XML таблицы Клиенты
    public void importXmlClients() {
        Statement statement = null;
        Schema schema = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Скопируйте файл в директорию, где находится программа" +
                "\n Введите имя файла xml соответствующего для импорта в таблицу Клиенты");
        String string = scanner.nextLine();
        String FILENAME = string + ".xml";

        try {

            final File xmlFile = new File(System.getProperty("user.dir")
                    + File.separator + FILENAME);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);

            doc.getDocumentElement().normalize();
            try {
                SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
                schema = factory.newSchema(new File("xsd\\Clients.xsd"));
                Validator validator = schema.newValidator();
                validator.validate(new DOMSource(doc));


                System.out.println("Файл xml соответствует схемы данной таблицы");

            } catch (Exception e) {
                System.out.println("Файл xml не соответствует  xsd схеме данной таблицы");
                return;
                //   e.printStackTrace();
            }
            NodeList nodeList = doc.getElementsByTagName("client");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);


                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    String FIO = element.getElementsByTagName("FIO").item(0).getTextContent();
                    String Document = element.getElementsByTagName("Document").item(0).getTextContent();
                    statement.execute("INSERT INTO 'Clients'('FIO','Document')" +
                            " VALUES ('" + FIO + "','" + Document + "'); ");
                    System.out.println("Данные импортированы в таблицу");

                }
            }
        } catch (ParserConfigurationException | SAXException
                | IOException ex) {
            Logger.getLogger(ConnectDB.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
           // e.printStackTrace();
            System.out.println("Произошла ошибка, попробуйте еще раз");
        }
    }

    //импорт XML таблицы Доступность автомобилей
    public void importXmlDostup() {
        Statement statement = null;
        Schema schema = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Скопируйте файл в директорию, где находится программа" +
                "\n Введите имя файла xml соответствующего для импорта в таблицу Доступность автомобилей");
        String string = scanner.nextLine();
        String FILENAME = string + ".xml";

        try {

            final File xmlFile = new File(System.getProperty("user.dir")
                    + File.separator + FILENAME);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);

            doc.getDocumentElement().normalize();
            try {
                SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
                schema = factory.newSchema(new File("xsd\\Dostup.xsd"));
                Validator validator = schema.newValidator();
                validator.validate(new DOMSource(doc));


                System.out.println("Файл xml соответствует схемы данной таблицы");

            } catch (Exception e) {
                System.out.println("Файл xml не соответствует  xsd схеме данной таблицы");
                return;
            }
            NodeList nodeList = doc.getElementsByTagName("dostup_auto");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);


                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    String Auto = element.getElementsByTagName("Auto").item(0).getTextContent();
                    String Free = element.getElementsByTagName("Free").item(0).getTextContent();
                    statement.execute("INSERT INTO 'Dostup'('Auto','Free')" +
                            " VALUES ('" + Auto + "','" + Free + "'); ");
                    System.out.println("Данные импортированы в таблицу");

                }
            }
        } catch (ParserConfigurationException | SAXException
                | IOException ex) {
            Logger.getLogger(ConnectDB.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
         //   e.printStackTrace();
            System.out.println("Произошла ошибка, попробуйте еще раз");
        }
    }

    //импорт XML таблицы Сотрудники
    public void importXmlSotrudniki() {
        Statement statement = null;
        Schema schema = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Скопируйте файл в директорию, где находится программа" +
                "\n Введите имя файла xml соответствующего для импорта в таблицу Сотрудники");
        String string = scanner.nextLine();
        String FILENAME = string + ".xml";

        try {

            final File xmlFile = new File(System.getProperty("user.dir")
                    + File.separator + FILENAME);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);

            doc.getDocumentElement().normalize();
            try {
                SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
                schema = factory.newSchema(new File("xsd\\Sotrudniki.xsd"));
                Validator validator = schema.newValidator();
                validator.validate(new DOMSource(doc));


                System.out.println("Файл xml соответствует схемы данной таблицы");

            } catch (Exception e) {
                System.out.println("Файл xml не соответствует  xsd схеме данной таблицы");
                return;
            }
            NodeList nodeList = doc.getElementsByTagName("sotrudnik");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);


                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    String FIO = element.getElementsByTagName("FIO").item(0).getTextContent();
                    String Data_priema = element.getElementsByTagName("Data_priema").item(0).getTextContent();
                    String Doljnost = element.getElementsByTagName("Doljnost").item(0).getTextContent();
                    statement.execute("INSERT INTO 'Sotrudniki'('FIO','Data_priema','Doljnost')" +
                            " VALUES ('" + FIO + "','" + Data_priema + "','" + Doljnost + "'); ");
                    System.out.println("Данные импортированы в таблицу");

                }
            }
        } catch (ParserConfigurationException | SAXException
                | IOException ex) {
            Logger.getLogger(ConnectDB.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.out.println("Произошла ошибка, попробуйте еще раз");
        }
    }

    //импорт XML таблицы Сделки
    public void importXmlSdelki() {
        Statement statement = null;
        Schema schema = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Скопируйте файл в директорию, где находится программа" +
                "\n Введите имя файла xml соответствующего для импорта в таблицу Сделки");
        String string = scanner.nextLine();
        String FILENAME = string + ".xml";

        try {

            final File xmlFile = new File(System.getProperty("user.dir")
                    + File.separator + FILENAME);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);

            doc.getDocumentElement().normalize();
            try {
                SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
                schema = factory.newSchema(new File("xsd\\Sdelki.xsd"));
                Validator validator = schema.newValidator();
                validator.validate(new DOMSource(doc));


                System.out.println("Файл xml соответствует схемы данной таблицы");

            } catch (Exception e) {
                System.out.println("Файл xml не соответствует  xsd схеме данной таблицы");
                return;
            }
            NodeList nodeList = doc.getElementsByTagName("sdelka");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);


                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    String Data_vidachi = element.getElementsByTagName("Data_vidachi").item(0).getTextContent();
                    String Data_sdachi = element.getElementsByTagName("Data_sdachi").item(0).getTextContent();
                    String Client = element.getElementsByTagName("Client").item(0).getTextContent();
                    String Auto = element.getElementsByTagName("Auto").item(0).getTextContent();
                    String Vidal = element.getElementsByTagName("Vidal").item(0).getTextContent();
                    String Free = element.getElementsByTagName("Free").item(0).getTextContent();

                    statement.execute("INSERT INTO 'Sdelki'('Data_vidachi','Data_sdachi','Client','Auto','Vidal','Free')" +
                            " VALUES ('" + Data_vidachi + "','" + Data_sdachi + "','" +
                            Client + "','" + Auto + "','" + Vidal + "','" + Free + "'); ");
                    System.out.println("Данные импортированы в таблицу");

                }
            }
        } catch (ParserConfigurationException | SAXException
                | IOException ex) {
            Logger.getLogger(ConnectDB.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
           // e.printStackTrace();
            System.out.println("Произошла ошибка, попробуйте еще раз");
        }
    }
}


