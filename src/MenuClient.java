import Entities.Clients;

import java.util.List;
import java.util.Scanner;

/**
 * Created by omyag on 20.05.2017.
 */
public class MenuClient {


    public static void MC() {
        ConnectDB dbConnection = new ConnectDB();
        boolean showclient = true;
        while (showclient) {

            System.out.println("Выберите пункт меню для изменения таблицы:" +
                    "\n 1: Посмотреть таблицу; ; \t 2: добавить запись ; \t 3: Удалить запись; \t 4: Изменить запись;" +
                    "\t 5: Изменить ФИО; \t6: Изменить Документ; \t7: Назад;");
            System.out.println();
            Scanner s2 = new Scanner(System.in);
            if (s2.hasNextInt()) {
                int l = s2.nextInt();
                switch (l) {
                    case 1:
                        List<Clients> clientss = dbConnection.getClient();

                        for (Clients item : clientss) {
                            System.out.println(item);
                            System.out.println();
                        }
                        break;
                    case 2:
                        dbConnection.insertClient();//добавляем новую запись в Клиенты
                        break;

                    case 3:
                        dbConnection.deleteClientById(); // удаляем запись в Клиенты
                        break;

                    case 4:
                        dbConnection.updateClients(); // изменяем запись в Клиенты
                        break;
                    case 5:
                        dbConnection.updateClientFIO(); // изменяем ФИО в Клиенты
                        break;
                    case 6:
                        dbConnection.updateClientDoc(); // изменяем Документы в Клиенты
                        break;
                    case 7:
                        showclient = false;
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