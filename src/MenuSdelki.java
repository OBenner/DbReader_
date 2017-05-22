import Entities.Sdelki;

import java.util.List;
import java.util.Scanner;

/**
 * Created by omyag on 20.05.2017.
 */
public class MenuSdelki {
    public static void MS() {
        ConnectDB dbConnection = new ConnectDB();
        boolean showsdelki = true;
        while (showsdelki) {


            System.out.println("Выберите пункт меню для изменения таблицы:" +
                    "\n 1: Посмотреть таблицу ; \t 2: добавить запись ; \t 3: Удалить запись; \t 4: Изменить запись;" +
                    "\t 5: Изменить Дату Выдачи автомобиля; \t 6: Изменить Дату сдачи Автомобиля; " +
                    "\n \t 7: Изменить клиента; \t 8: Изменить Автомобиль; \t 9: Изменить Менеджера,выдавшего автомобиль;" +
                    "\n 10: Изменить доступность автомобиля;\t 11: Назад;");
            System.out.println();
            Scanner sc3 = new Scanner(System.in);
            if (sc3.hasNextInt()) {
                int h3 = sc3.nextInt();

                switch (h3) {
                    case 1:
                        List<Sdelki> sdelkis = dbConnection.getSdelki();
                        for (Sdelki item : sdelkis) {
                            System.out.println(item);
                            System.out.println();
                        }
                        break;
                    case 2:
                        dbConnection.insertSdelki();//добавляем новую запись в Сделки
                        break;

                    case 3:
                        dbConnection.deleteSdelkiById(); // удаляем запись в Сделки
                        break;

                    case 4:
                        dbConnection.updateSdelki(); // изменяем запись в Сделки
                        break;
                    case 5:
                        dbConnection.updateSdelkiDataV(); //изменяем дату выдачи в Сделки
                        break;
                    case 6:
                        dbConnection.updateSdelkiDataS(); //изменяем дату сдачи в Сделки
                        break;
                    case 7:
                        dbConnection.updateSdelkiClient(); //изменяем клиента в Сделки
                        break;
                    case 8:
                        dbConnection.updateSdelkiAuto(); //изменяем автомобиль в Сделки
                        break;
                    case 9:
                        dbConnection.updateSdelkiVidal(); //изменяем менеджера выдавшего автомобиль в Сделки
                        break;
                    case 10:
                        dbConnection.updateSdelkiFree(); //изменяем доступность автомобиля в Сделки
                        break;
                    case 11:
                        showsdelki = false;
                        break;
                        default:
                            System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                            break;
                }


            } else {
                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
            }
        }
    }
}