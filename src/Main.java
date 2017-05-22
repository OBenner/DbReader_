import Entities.*;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        while (true) {
            ConnectDB dbConnection = new ConnectDB();
            if (dbConnection != null) {
                System.out.println("База данных подключена.");
            } else {
                System.out.println("База данных  не подключена.");
            }
            System.out.println("Для начала работы с программой, выберете определенный пункт меню:");
            System.out.println(" 1: Просмотр Базы Данных.");
            System.out.println(" 2: Выбрать таблицу. ");
            System.out.println(" 3: Произвести импорт xml в БД. ");
            System.out.println(" 4:Сделать экспорт таблицы xml. \n\n");
            Scanner s = new Scanner(System.in);
            if (s.hasNextInt()) {
                int a = s.nextInt();
                switch (a) {
                    case 1:  //Просмотр БД
                        boolean showbd = true;
                        while (showbd) {
                            System.out.println("База Данных по прокату автомобилей состоит из 5 таблиц, посмотреть содержание таблиц" +
                                    "вы можете выбрав соответствующий таблице пункт меню :");
                            System.out.println(" 1: Автомобили; 2: Клиенты; 3: Сделки; 4: Сотрудники; " +
                                    "5: Доступность Автомобилей 6: Назад");
                            Scanner s0 = new Scanner(System.in);
                            if (s0.hasNextInt()) {
                                int z = s0.nextInt();
                                switch (z) {
                                    case 1:
                                        List<Auto> autos = dbConnection.getAuto();
                                        for (Auto item : autos) {
                                            System.out.println(item);
                                            System.out.println();
                                        }
                                        break;
                                    case 2:
                                        List<Clients> clientss = dbConnection.getClient();
                                        for (Clients item : clientss) {
                                            System.out.println(item);
                                            System.out.println();
                                        }
                                        break;
                                    case 3:
                                        List<Sdelki> sdelkis = dbConnection.getSdelki();
                                        for (Sdelki item : sdelkis) {
                                            System.out.println(item);
                                            System.out.println();
                                        }
                                        break;
                                    case 4:
                                        List<Sotrudniki> sotrudnikis = dbConnection.getSotrudniki();
                                        for (Sotrudniki item : sotrudnikis) {
                                            System.out.println(item);
                                            System.out.println();
                                        }
                                        break;
                                    case 5:
                                        List<Dostup> dostup = dbConnection.getDostup();
                                        for (Dostup item : dostup) {
                                            System.out.println(item);
                                            System.out.println();
                                        }
                                        break;
                                    case 6:
                                        showbd = false;
                                        break;
                                    default:
                                        System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                        break;
                                }
                            } else {
                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                            }
                        }
                        break;
                    case 2: // Выбор таблицы
                        boolean showtable = true;
                        while (showtable) {
                            System.out.println("Выберете таблицу: 1: Автомобили; 2: Клиенты; 3: Сделки; 4: Сотрудники; " +
                                    "5: Доступность Автомобилей  6: Назад;");
                            System.out.println();
                            Scanner s1 = new Scanner(System.in);
                            if (s1.hasNextInt()) {
                                int c = s1.nextInt();
                                switch (c) {
                                    case 1: // таблица Автомобили
                                        MenuAuto.MA();
                                        break;
                                    case 2:
                                        MenuClient.MC();
                                        break;
                                    case 3: //таблица Сделки
                                        MenuSdelki.MS();
                                        break;
                                    case 4: //таблица Сотрудники
                                        MenuSotrudniki.MSot();
                                        break;
                                    case 5: //таблица Доступность автомобиля
                                        MenuDostup.MD();
                                        break;
                                    case 6:
                                        showtable = false;
                                        break;
                                    default:
                                        System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                        break;
                                }
                            } else {
                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                            }
                        }
                        break;
                    case 3: //Импорт xml
                        boolean showimport = true;
                        while (showimport) {
                            System.out.println("Выберети пунк меню, соотвествующей таблицы, в которую хотите импортировать " +
                                    "файл xml.");
                            System.out.println("1: Автомобили ; \t 2: Клиенты; \t 3: Доступ автомобилей;  \t 4: Сделки; " +
                                    " \t 5: Сотрудники; \t 6: Назад; ");
                            Scanner s03 = new Scanner(System.in);
                            if (s03.hasNextInt()) {
                                int g = s03.nextInt();
                                switch (g) {
                                    case 1: // импорт в таблицу Автомобили
                                        dbConnection.importXmlAuto();
                                        break;
                                    case 2:// импорт в таблицу Клиенты
                                        dbConnection.importXmlClients();
                                        break;
                                    case 3: //импорт в таблицу Доступность автомобилей
                                        dbConnection.importXmlDostup();
                                        break;
                                    case 4: // импорт в таблицу Сделки
                                        dbConnection.importXmlSdelki();
                                        break;
                                    case 5: //импорт в таблицу Сотрудники
                                        dbConnection.importXmlSotrudniki();
                                        break;
                                    case 6:
                                        showimport = false;
                                        break;
                                    default:
                                        System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                        break;
                                }
                            } else {
                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                            }
                        }
                        break;
                    case 4: //Экспорт xml
                        boolean showexport = true;
                        while (showexport) {
                            System.out.println("Выберите пункт меню для экспорта выбранной таблицы в файл XML:" +
                                    "\n 1: Автомобили ; \t 2: Клиенты; \t 3: Доступ автомобилей;  \t 4: Сделки; " +
                                    " \t 5: Сотрудники; \t 6: Назад;");
                            Scanner sc04 = new Scanner(System.in);
                            if (sc04.hasNextInt()) {
                                int h04 = sc04.nextInt();
                                switch (h04) {
                                    case 1:
                                        dbConnection.xmlbuildAuto();
                                        break;
                                    case 2:
                                        dbConnection.xmlbuildClients();
                                        break;
                                    case 3:
                                        dbConnection.xmlbuildDostup();
                                        break;
                                    case 4:
                                        dbConnection.xmlbuildSdelki();
                                        break;
                                    case 5:
                                        dbConnection.xmlbuildSotrudniki();
                                        break;
                                    case 6:
                                        showexport = false;
                                        break;
                                    default:
                                        System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                        break;
                                }
                            } else {
                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                            }
                        }
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


