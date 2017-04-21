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
                if (a > 0 && a < 5) {
                    switch (a) {

                        case 1:  //Просмотр БД
                            System.out.println("База Данных по прокату автомобилей состоит из 5 таблиц, посмотреть содержание таблиц" +
                                    "вы можете выбрав соответствующий таблице пункт меню :");
                            System.out.println(" 1: Автомобили; 2: Клиенты; 3: Сделки; 4: Сотрудники; " +
                                    "5: Доступность Автомобилей");
                            Scanner s0 = new Scanner(System.in);
                            if (s0.hasNextInt()) {
                                int z = s0.nextInt();
                                if (z >= 0 && z < 6) {
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
                                        case 0:
                                            continue;
                                    }
                                } else {
                                    System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                }

                            } else {
                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                            }
                            break;
                        case 2: // Выбор таблицы
                            System.out.println("Выберете таблицу: 1: Автомобили; 2: Клиенты; 3: Сделки; 4: Сотрудники; " +
                                    "5: Доступность Автомобилей");
                            System.out.println();
                            Scanner s1 = new Scanner(System.in);
                            if (s1.hasNextInt()) {
                                int c = s1.nextInt();
                                if (c > 0 && c < 6) {
                                    switch (c) {
                                        case 1: // таблица Автомобили
                                            System.out.println("Выберите пункт меню для изменения таблицы:" +
                                                    "\n 1:Посмотреть таблицу;  \t 2: Добавить запись ; \t 3: Удалить запись;" +
                                                    " \t 4: Изменить запись; \t 5: Изменить цвет; \t 6: Изменить пробег");
                                            System.out.println();
                                            Scanner s12 = new Scanner(System.in);
                                            if (s12.hasNextInt()) {
                                                int h1 = s12.nextInt();
                                                if (h1 >= 0 && h1 < 7) {
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
                                                    }
                                                } else {
                                                    System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                                }

                                            } else {
                                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                            }
                                            break;
                                        case 2: //таблица Клиенты
                                            List<Clients> clientss = dbConnection.getClient();
                                            for (Clients item : clientss) {
                                                System.out.println(item);
                                                System.out.println();
                                            }
                                            System.out.println("Выберите пункт меню для изменения таблицы:" +
                                                    "\n 1: добавить запись ; \t 2: Удалить запись; \t 3: Изменить запись;");
                                            System.out.println();
                                            Scanner s2 = new Scanner(System.in);
                                            if (s2.hasNextInt()) {
                                                int l = s2.nextInt();
                                                if (l > 0 && l < 4) {
                                                    switch (l) {
                                                        case 1:
                                                            dbConnection.insertClient();//добавляем новую запись в Клиенты
                                                            break;

                                                        case 2:
                                                            dbConnection.deleteClientById(); // удаляем запись в Клиенты
                                                            break;

                                                        case 3:
                                                            dbConnection.updateClients(); // изменяем запись в Клиенты
                                                            break;
                                                    }
                                                } else {
                                                    System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                                }

                                            } else {
                                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                            }
                                            break;
                                        case 3: //таблица Сделки
                                            List<Sdelki> sdelkis = dbConnection.getSdelki();
                                            for (Sdelki item : sdelkis) {
                                                System.out.println(item);
                                                System.out.println();
                                            }
                                            System.out.println("Выберите пункт меню для изменения таблицы:" +
                                                    "\n 1: добавить запись ; \t 2: Удалить запись; \t 3: Изменить запись;");
                                            System.out.println();
                                            Scanner sc3 = new Scanner(System.in);
                                            if (sc3.hasNextInt()) {
                                                int h3 = sc3.nextInt();
                                                if (h3 > 0 && h3 < 4) {
                                                    switch (h3) {
                                                        case 1:
                                                            dbConnection.insertSdelki();//добавляем новую запись в Сделки
                                                            break;

                                                        case 2:
                                                            dbConnection.deleteSdelkiById(); // удаляем запись в Сделки
                                                            break;

                                                        case 3:
                                                            dbConnection.updateSdelki(); // изменяем запись в Сделки
                                                            break;
                                                    }
                                                } else {
                                                    System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                                }

                                            } else {
                                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                            }
                                            break;
                                        case 4: //таблица Сотрудники
                                            List<Sotrudniki> sotrudnikis = dbConnection.getSotrudniki();
                                            for (Sotrudniki item : sotrudnikis) {
                                                System.out.println(item);
                                                System.out.println();
                                            }
                                            System.out.println("Выберите пункт меню для изменения таблицы:" +
                                                    "\n 1: добавить запись ; \t 2: Удалить запись; \t 3: Изменить запись;");
                                            System.out.println();
                                            Scanner sc4 = new Scanner(System.in);
                                            if (sc4.hasNextInt()) {
                                                int h4 = sc4.nextInt();
                                                if (h4 > 0 && h4 < 4) {
                                                    switch (h4) {
                                                        case 1:
                                                            dbConnection.insertSotrudniki();//добавляем новую запись в Сотрудники
                                                            break;

                                                        case 2:
                                                            dbConnection.deleteSotrudnikiById(); // удаляем запись в Сотрудники
                                                            break;

                                                        case 3:
                                                            dbConnection.updateSotrudniki(); // изменяем запись в Сотрудники
                                                            break;
                                                    }
                                                } else {
                                                    System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                                }

                                            } else {
                                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                            }
                                            break;
                                        case 5: //таблица Доступность автомобиля
                                            List<Dostup> dostup = dbConnection.getDostup();
                                            for (Dostup item : dostup) {
                                                System.out.println(item);
                                                System.out.println();
                                            }
                                            System.out.println("Выберите пункт меню для изменения таблицы:" +
                                                    "\n 1: добавить запись ; \t 2: Удалить запись; \t 3: Изменить запись;");
                                            System.out.println();

                                            Scanner sc5 = new Scanner(System.in);
                                            if (sc5.hasNextInt()) {
                                                int h5 = sc5.nextInt();
                                                if (h5 > 0 && h5 < 4) {
                                                    switch (h5) {
                                                        case 1:
                                                            dbConnection.insertDostup();//добавляем новую запись в Доступность автомобилей
                                                            break;

                                                        case 2:
                                                            dbConnection.deleteDostupById(); // удаляем запись в Доступность автомобилей
                                                            break;

                                                        case 3:
                                                            dbConnection.updateDotup(); // изменяем запись в Доступность автомобилей
                                                            break;
                                                    }

                                                } else {
                                                    System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                                }

                                            } else {
                                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                            }

                                    }
                                }
                            }
                            break;
                        case 3: //Импорт xml
                            System.out.println("Выберети пунк меню, соотвествующей таблицы, в которую хотите импортировать " +
                                    "файл xml.");
                            System.out.println("1: Автомобили ; \t 2: Клиенты; \t 3: Доступ автомобилей;  \t 4: Сделки; " +
                                    " \t 5: Сотрудники; ");
                            Scanner s03 = new Scanner(System.in);
                            if (s03.hasNextInt()) {
                                int g = s03.nextInt();
                                if (g > 0 && g < 6) {
                                    switch (g) {
                                        case 1: // импорт в таблицу Автомобили
                                            dbConnection.exportXmlAuto();
                                            break;
                                        case 2:// импорт в таблицу Клиенты
                                            dbConnection.exportXmlClients();
                                            break;
                                        case 3: //импорт в таблицу Доступность автомобилей
                                            dbConnection.exportXmlDostup();
                                            break;
                                        case 4: // импорт в таблицу Сделки
                                            dbConnection.exportXmlSdelki();
                                            break;
                                        case 5: //импорт в таблицу Сотрудники
                                            dbConnection.exportXmlSotrudniki();
                                            break;
                                    }

                                } else {
                                    System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                }

                            } else {
                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                            }
                            break;


                        case 4: //Экспорт xml
                            System.out.println("Выберите пункт меню для экспорта выбранной таблицы в файл XML:" +
                                    "\n 1: Автомобили ; \t 2: Клиенты; \t 3: Доступ автомобилей;  \t 4: Сделки; " +
                                    " \t 5: Сотрудники; ");
                            Scanner sc04 = new Scanner(System.in);
                            if (sc04.hasNextInt()) {
                                int h04 = sc04.nextInt();
                                if (h04 >= 1 && h04 < 6) {
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
                                    }
                                } else {
                                    System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                                }

                            } else {
                                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                            }
                            break;
                        default:
                            System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                    }
                } else {
                    System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
                }
            } else {
                System.out.println("\n Вы ввели не верное значение, попробуйте еще раз. \n");
            }

        }
    }
}


