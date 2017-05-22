import Entities.Sotrudniki;

import java.util.List;
import java.util.Scanner;

/**
 * Created by omyag on 20.05.2017.
 */
public class MenuSotrudniki {
    public static void MSot() {
        ConnectDB dbConnection = new ConnectDB();
        boolean showsotr = true;
        while (showsotr) {

            System.out.println("Выберите пункт меню для изменения таблицы:" +
                    "\n 1: Посмотреть таблицу \t 2: добавить запись ; \t 3: Удалить запись; \t 4: Изменить запись; \n" +
                    " 5: Изменить ФИО; \t6: Изменить Дату приема на работу; \t7: Изменить должность  \t8: Назад;");
            System.out.println();
            Scanner sc4 = new Scanner(System.in);
            if (sc4.hasNextInt()) {
                int h4 = sc4.nextInt();
                switch (h4) {
                    case 1:
                        List<Sotrudniki> sotrudnikis = dbConnection.getSotrudniki();
                        for (Sotrudniki item : sotrudnikis) {
                            System.out.println(item);
                            System.out.println();
                        }
                        break;
                    case 2:
                        dbConnection.insertSotrudniki();//добавляем новую запись в Сотрудники
                        break;

                    case 3:
                        dbConnection.deleteSotrudnikiById(); // удаляем запись в Сотрудники
                        break;

                    case 4:
                        dbConnection.updateSotrudniki(); // изменяем запись в Сотрудники
                        break;
                    case 5:
                        dbConnection.updateSotrudnikiFIO(); //изменяем ФИО в Сотрудники
                        break;
                    case 6:
                        dbConnection.updateSotrudnikiData(); // измняем Дату приема на работу в Сотрудники
                        break;
                    case 7:
                        dbConnection.updateSotrudnikiDoljnost(); // изменяем должность в Сотрудники
                        break;
                    case 8:
                        showsotr = false;
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
