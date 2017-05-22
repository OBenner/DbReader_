import Entities.Dostup;

import java.util.List;
import java.util.Scanner;

/**
 * Created by omyag on 20.05.2017.
 */
public class MenuDostup {

    public static void MD() {
        ConnectDB dbConnection = new ConnectDB();
        boolean showdostup = true;
        while (showdostup) {

            System.out.println("Выберите пункт меню для изменения таблицы:" +
                    "\n 1: Посмотреть таблицу ; \t 2: добавить запись ; \t 3: Удалить запись; \t 4: Изменить запись;" +
                    "\t 5: Изменить Доступность автомобиля; \t 6: Изменить Автомобиль; \t 7: Назад;");
            System.out.println();

            Scanner sc5 = new Scanner(System.in);
            if (sc5.hasNextInt()) {
                int h5 = sc5.nextInt();
                switch (h5) {
                    case 1:
                        List<Dostup> dostup = dbConnection.getDostup();
                        for (Dostup item : dostup) {
                            System.out.println(item);
                            System.out.println();
                        }
                        break;
                    case 2:
                        dbConnection.insertDostup();//добавляем новую запись в Доступность автомобилей
                        break;

                    case 3:
                        dbConnection.deleteDostupById(); // удаляем запись в Доступность автомобилей
                        break;

                    case 4:
                        dbConnection.updateDostup(); // изменяем запись в Доступность автомобилей
                        break;
                    case 5:
                        dbConnection.updateDostupFree(); //изменяем  Доступность автомобилей
                        break;
                    case 6:
                        dbConnection.updateDostupAuto(); // изменяем автомобиль
                        break;
                    case 7:
                        showdostup = false;
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
