import Entities.Auto;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by omyag on 20.05.2017.
 */
public class MenuAuto {
    public static void MA() throws SQLException {
        ConnectDB dbConnection = new ConnectDB();
        boolean showauto = true;
        while (showauto) {
            System.out.println("Выберите пункт меню для изменения таблицы:" +
                    "\n 1:Посмотреть таблицу;  \t 2: Добавить запись ; \t 3: Удалить запись;" +
                    " \t 4: Изменить запись; \t 5: Изменить цвет; \t 6: Изменить пробег;" +
                    " \n 7: Изменить цену; \t 8: Изменить модель; \t 9: Изменить дату выпуска;  \t 10: назад;");
            System.out.println();
            Scanner s12 = new Scanner(System.in);
            if (s12.hasNextInt()) {
                int h1 = s12.nextInt();
                switch (h1) {
                    case 1:
                        List<Auto> autos = dbConnection.getAuto();
                        for (Auto item : autos) {
                            System.out.println(item);
                            System.out.println();
                        }
                        break;
                    case 2:
                        dbConnection.insertAuto(); //добавляем новую запись в Автомобили
                        break;
                    case 3:
                        dbConnection.deleteAutoById(); // удаляем запись в Автомобили
                        break;
                    case 4:
                        dbConnection.updateAuto(); // изменяем запись в Автомобили
                        break;
                    case 5:
                        dbConnection.updateAutoColor(); //изменяем цвет в Автомобили
                        break;
                    case 6:
                        dbConnection.updateAutoProbeg(); // изменяем пробег в Автомобили
                        break;
                    case 7:
                        dbConnection.updateAutoPrice(); //изменяем цену автомобиля
                        break;
                    case 8:
                        dbConnection.updateAutoModel(); //изменяем модель автомобиля
                        break;
                    case 9:
                        dbConnection.updateAutoData(); //изменяем дату выпуска
                        break;
                    case 10:
                        showauto = false;
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
