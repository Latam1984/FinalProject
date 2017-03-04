package FinalProject;


import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.HashMap;

import java.util.List;

import java.util.Map;



import static FinalProject.Controller.*;

import static FinalProject.Controller.MAX_PRICE;

/**
 * Created by Aleksey on 28.01.2017.
 */

public class Admin {

    public static void root () throws IOException{

        System.out.println("Вы вошли как администратор.");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        first:

        while (true){

            System.out.println("1- поиск пользователей по имени, 2- поиск отелей по названию, 3- поиск отелей по городу, 4- поиск комнат," +

                    "\n5- удаление пользователей, 6- удаление отелей, 7- удаление комнат," +

                    "\n8- редактирование отелей, 9- редактирование комнат, " +

                    "\n10- добавление отелей, 11- добавление комнат, \n0- выход из режима администрирования");

            String choice1 = br.readLine();

            if (choice1.equals("0")) break;



            //  1- поиск пользователей по имени

            if (choice1.equals("1")){

                while (true) {

                    System.out.println("Введите имя пользователя, или часть имени; либо 1- предыдущее меню, 0- выход из режима администрирования");

                    String choice2 = br.readLine();

                    if (choice2.equals("0")) break first;

                    if (choice2.equals("1")) continue first;

                    Controller.findUsersByName(choice2.trim()).forEach(System.out::println);

                    System.out.println("Продолжить поиск?");

                }

            }



            // 2- поиск отелей по названию

            if (choice1.equals("2")){

                while (true) {

                    System.out.println("Введите название отеля, или часть названия; либо 1- предыдущее меню, 0- выход из режима администрирования");

                    String choice2 = br.readLine();

                    if (choice2.equals("0")) break first;

                    if (choice2.equals("1")) continue first;

                    Controller.findHotelByName(choice2.trim()).forEach(System.out::println);

                    System.out.println("Продолжить поиск?");

                }

            }



            // 3- поиск отелей по городу

            if (choice1.equals("3")){

                while (true) {

                    System.out.println("Введите название города, или часть названия; либо 1- предыдущее меню, 0- выход из режима администрирования");

                    String choice2 = br.readLine();

                    if (choice2.equals("0")) break first;

                    if (choice2.equals("1")) continue first;

                    Controller.findHotelByCity(choice2.trim()).forEach(System.out::println);

                    System.out.println("Продолжить поиск?");

                }

            }



            // 4- поиск комнат

            if (choice1.equals("4")) {

                while (true) {

                    System.out.println("Для поиска номеров, введите параметры разделяя их символом / в таком формате:" +

                            " город/название отеля/кол-во персон/цена от/цена до, образец: Kiev/Desna/2/200/350 \n" +

                            "если параметр Вам не важен - поставьте вместо него символ *.   А если передумали искать," +

                            " то: 1- предыдущее меню, 0- выход из режима администрирования");

                    String choice2 = br.readLine();

                    if (choice2.equals("0")) break first;

                    if (choice2.equals("1")) continue first;

                    String s[] = choice2.split("/");

                    if (s.length !=5) {System.out.println("Некорректный ввод!"); continue;}

                    Map<String,String> map = new HashMap<>();

                    map.put(CITY, s[0].trim());

                    map.put(HOTEL_NAME, s[1].trim());

                    map.put(PERSONS, s[2].trim());

                    map.put(MIN_PRICE, s[3].trim());

                    map.put(MAX_PRICE, s[4].trim());

                    List<Room> rooms = Controller.findRoom(map);

                    if (rooms.size() == 0) System.out.println("Ничего не найдено. Возможно параметры поиска слишком строгие.");

                    rooms.forEach(System.out::println);

                    System.out.println("Повторить поиск?");

                }

            }

            // 5- удаление пользователей

            if (choice1.equals("5")) {

                while (true){

                    System.out.println("Для удаления пользователя введите его id; либо: 1- предыдущее меню, 0- выход из режима администрирования");

                    String choice2 = br.readLine();

                    if (choice2.equals("0")) break first;

                    if (choice2.equals("1")) continue first;

                    long id;

                    try {

                        id = Long.parseLong(choice2.trim());

                    } catch (NumberFormatException e) {

                        System.out.println("Некорректный ввод! Введите целое число.");

                        continue;

                    }

                    User user = Controller.findUserById(id);

                    if (user == null){

                        System.out.println("Пользователь с таким id не найден.");

                        continue;

                    }

                    System.out.println("Вы уверены, что хотите удалить пользователя " + user + "? Введите 1 для подтверждения, " +

                            "или что-либо другое для отмены");

                    String ch = br.readLine();

                    if (ch.equals("1")){

                        boolean b = Controller.removeUser(user);

                        if (b) System.out.println("Пользователь " + user + " удален.");

                        else System.out.println("Что-то пошло не так");

                    }

                    System.out.println("Продолжаем удалять пользователей?");

                }

            }



            // 6- удаление отелей

            if (choice1.equals("6")) {

                while (true) {

                    System.out.println("Для удаления отеля введите его id; либо: 1- предыдущее меню, 0- выход из режима администрирования");

                    String choice2 = br.readLine();

                    if (choice2.equals("0")) break first;

                    if (choice2.equals("1")) continue first;

                    long id;

                    try {

                        id = Long.parseLong(choice2.trim());

                    } catch (NumberFormatException e) {

                        System.out.println("Некорректный ввод! Введите целое число");

                        continue;

                    }

                    Hotel hotel = Controller.findHotelById(id);

                    if (hotel == null) continue;



                    System.out.println("Вы уверены, что хотите удалить отель " + hotel +

                            "? Все его комнаты также будут удалены. \n Введите 1 для подтверждения, " +

                            "или что-либо другое для отмены");

                    String ch = br.readLine();

                    if (ch.equals("1")) {

                        boolean b = Controller.removeHotel(hotel);

                        if (b) System.out.println("Отель " + hotel + " удален.");

                        else System.out.println("Что-то пошло не так");

                    }

                    System.out.println("Продолжаем удалять отели?");

                }

            }



            // 7- удаление комнат

            if (choice1.equals("7")) {

                while (true){

                    System.out.println("Для удаления комнаты введите ее id; либо: 1- предыдущее меню, 0- выход из режима администрирования");

                    String choice2 = br.readLine();

                    if (choice2.equals("0")) break first;

                    if (choice2.equals("1")) continue first;

                    long id;

                    try {

                        id = Long.parseLong(choice2.trim());

                    } catch (NumberFormatException e) {

                        System.out.println("Некорректный ввод! Введите целое число");

                        continue;

                    }

                    Room room = Controller.findRoomById(id);

                    if (room == null) {

                        System.out.println("Комнаты с таким id не найдено");

                        continue;

                    }

                    System.out.println("Вы уверены, что хотите удалить комнату " + room +

                            "?  \n Введите 1 для подтверждения, или что-либо другое для отмены");

                    String ch = br.readLine();

                    if (ch.equals("1")) {

                        boolean b = Controller.removeRoom(room);

                        if (b) System.out.println("Комната " + room + " удалена.");

                        else System.out.println("Что-то пошло не так");

                    }

                    System.out.println("Продолжаем удалять комнаты?");

                }

            }



            // 8- редактирование отелей

            if (choice1.equals("8")) {

                while (true) {

                    System.out.println("Для редактирования отеля введите его id; либо: 1- предыдущее меню, 0- выход из режима администрирования");

                    String choice2 = br.readLine();

                    if (choice2.equals("0")) break first;

                    if (choice2.equals("1")) continue first;

                    long id;

                    try {

                        id = Long.parseLong(choice2.trim());

                    }catch (NumberFormatException e){

                        System.out.println("Некорректный ввод! Введите целое число.");

                        continue;

                    }

                    Hotel hotel = Controller.findHotelById(id);

                    if (hotel == null) continue;

                    while (true) {

                        System.out.println("Редактируем отель " + hotel + "\nВведите новое имя отеля и город чрез разделитель /, " +

                                "пример: Radisson/Kiev ;   0- отмена");

                        String choce3 = br.readLine();

                        if (choce3.equals("0")) break;

                        String s[] = choce3.split("/");

                        if (s.length != 2) {

                            System.out.println("Некорректный ввод!");

                            continue;

                        }

                        Hotel newHotel = Controller.editHotel(new Hotel(id, s[0].trim(), s[1].trim()));

                        if (newHotel != null) {

                            System.out.println("Отель отредактирован: " + newHotel +System.lineSeparator());

                            break;

                        }

                        System.out.println("Что-то пошло не так...");

                    }

                    System.out.println("Продолжаем редактировать отели?");

                }

            }





            // 10- добавление отелей

            if (choice1.equals("10")) {

                while (true) {

                    System.out.println("Для добаления отеля введите его название и город таким образом: название/город, например Radisson/Kiev" +

                            "\nлибо: 1- предыдущее меню, 0- выход из режима администрирования");

                    String choice2 = br.readLine();

                    if (choice2.equals("0")) break first;

                    if (choice2.equals("1")) continue first;

                    String s[] = choice2.split("/");

                    if (s.length != 2) {

                        System.out.println("Некорректный ввод!");

                        continue;

                    }

                    Hotel hotel = Controller.addHotel(new Hotel(0, s[0].trim(), s[1].trim()));

                    if (hotel != null) System.out.println("Отель добавлен:" + hotel);

                    System.out.println("Продолжить добавление отелей?");

                }

            }





            // 11- добавление комнат

            if (choice1.equals("11")) {

                while (true) {

                    System.out.println("Для добавления комнаты введите данные в таком формате: id отеля/кол-во мест/цена, например 999/2/450, " +

                            "\nлибо: 1- предыдущее меню, 0- выход из режима администрирования");

                    String choice2 = br.readLine();

                    if (choice2.equals("0")) break first;

                    if (choice2.equals("1")) continue first;

                    String s[] = choice2.split("/");

                    if (s.length !=3) {System.out.println("Некорректный ввод!"); continue;}

                    long hotelId; int persons; int price;

                    try {

                        hotelId = Long.parseLong(s[0].trim());

                        persons = Integer.parseInt(s[1].trim());

                        price = Integer.parseInt(s[2].trim());

                    }catch (NumberFormatException e){

                        System.out.println("Некорректный ввод! Вводите целые числа");

                        continue;

                    }

                    Hotel hotel = Controller.findHotelById(hotelId);

                    if (hotel == null) continue;

                    Room room = Controller.addRoom(new Room(0, price, persons, hotel));

                    if (room != null) System.out.println("Комната добавлена:" + room);

                    System.out.println("Продолжить добавление комнат?");

                }

            }



            System.out.println("Некорректный ввод! \n");

        }

    }

}
