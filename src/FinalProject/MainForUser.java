package FinalProject;


import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.HashMap;

import java.util.List;

import java.util.Map;



import static FinalProject.Controller.*;

/**
 * Created by Aleksey on 28.01.2017.
 */

public class MainForUser {



    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        first:

        while (true) {

            System.out.println("1- войти, 2- зарегистрироваться, 0- завершить работу");

            String choice1 = bufferedReader.readLine();

            if (choice1.equals("0")) break;



            //Вход

            if (choice1.equals("1")) {

                User user;

                second:

                while (true){

                    System.out.println("Введите имя и пароль через пробел, либо: 1- выйти в главное меню, 0- завершить работу");

                    String choice2 = bufferedReader.readLine();

                    if (choice2.length() == 1) {

                        if (choice2.equals("0")) break first;

                        if (choice2.equals("1")) continue first;

                    }

                    String nameAndPass[] = choice2.split(" ");

                    if (nameAndPass.length != 2) {

                        System.out.println("Некорректный ввод");

                        continue;

                    }

                    user = Controller.enter(nameAndPass[0].toLowerCase(), nameAndPass[1]);



                    //юзер вошел под своим именем

                    if (user != null) {

                        third:

                        while (true){

                            System.out.println("\n1- поиск отеля по названию, 2- поиск отеля по городу, 3- поиск номеров, " +

                                    "\n4- бронирование номера, 5- список забронированных Вами номеров, 6- снятие брони," +

                                    "\n7- редактировать имя/пароль, 9- выход из учетной записи, 0- завершить работу");

                            String choice3 = bufferedReader.readLine();

                            if (choice3.equals("0")) break first;

                            if (choice3.equals("9")) { user = null; continue first;}



                            //редактировать имя/пароль

                            if (choice3.equals("7")) {

                                while (true) {

                                    System.out.println("Для начала редактирования имени/пароля ведите текущие имя и пароль, либо: 1- отмена, 0- завершить работу");

                                    String choice4 = bufferedReader.readLine();

                                    if (choice4.equals("0")) break first;

                                    if (choice4.equals("1")) continue third;

                                    String split[] = choice4.split(" ");

                                    if (split.length != 2) {

                                        System.out.println("Некорректный ввод");

                                        continue;

                                    }

                                    if (split[0].equals(user.getName()) && split[1].equals(user.getPassword())){



                                        while (true) {

                                            System.out.println("Введите новое имя и пароль через пробел, либо: 1- отмена, 0- завершить работу");

                                            String choice5 = bufferedReader.readLine();

                                            if (choice5.equals("0")) break first;

                                            if (choice5.equals("1")) continue third;

                                            String splitNewNameAndPass[] = choice5.split(" ");

                                            if (splitNewNameAndPass.length != 2){

                                                System.out.println("Некорректный ввод");

                                                continue;

                                            }

                                            if (Controller.editUser(new User(user.getId(), splitNewNameAndPass[0], splitNewNameAndPass[1]))) {

                                                System.out.println("Профиль успешно изменен. Ваш текущий профиль: " + user);

                                                continue third;

                                            }

                                            else {

                                                System.out.println("Что-то пошло не так! Попробуйте в другой раз.");

                                                continue third;

                                            }

                                        }

                                    }

                                    System.out.println("Неверный логин или пароль!");

                                    user = null;

                                    continue first;

                                }

                            }



                            /*поиск отеля по названию*/

                            if (choice3.equals("1")) {

                                while (true){

                                    System.out.println("Введите название отеля, или его часть;" +

                                            " получить весь список отелей- просто нажмите Enter; 1- предыдущее меню; 0- завершить работу");

                                    String choice4 = bufferedReader.readLine();

                                    if (choice4.equals("0")) break first;

                                    if (choice4.equals("1")) continue third;

                                    Controller.findHotelByName(choice4.trim()).forEach(System.out::println);

                                    System.out.println("Продолжить поиск?");

                                }

                            }



                            /*поиск отеля по городу*/

                            if (choice3.equals("2")) {

                                while (true) {

                                    System.out.println("Введите название города, или его часть;" +

                                            " получить весь список отелей- просто нажмите Enter; 1- предыдущее меню; 0- завершить работу");

                                    String choice4 = bufferedReader.readLine();

                                    if (choice4.equals("0")) break first;

                                    if (choice4.equals("1")) continue third;

                                    Controller.findHotelByCity(choice4.trim()).forEach(System.out::println);

                                    System.out.println("Продолжить поиск?");



                                }

                            }



                            //список забронированных номеров

                            if (choice3.equals("5")) {

                                System.out.println("Забронированные Вами номера:");

                                Controller.bookedByUser(user).forEach(System.out::println);

                                continue third;

                            }



                            //отмена бронирования

                            if (choice3.equals("6")) {

                                while (true) {

                                    System.out.println("Для снятия брони введите id номера и id отеля через пробел; 1- предыдущее меню; 0- завершить работу");

                                    String choice4 = bufferedReader.readLine();

                                    if (choice4.equals("0")) break first;

                                    if (choice4.equals("1")) continue third;

                                    String s[] = choice4.split(" ");

                                    if (s.length != 2) {System.out.println("Некорректный ввод id"); continue;}

                                    try {

                                        long roomId = Long.parseLong(s[0]);

                                        long hotelId = Long.parseLong(s[1]);

                                        if (Controller.cancelReservation(roomId, user.getId(), hotelId)) continue third;

                                    } catch (NumberFormatException e){

                                        System.out.println("Надо вводить целые числа!");

                                    }

                                }

                            }

                            // бронирование

                            if (choice3.equals("4")) {

                                while (true) {

                                    System.out.println("Для бронирования введите id номера и id отеля через пробел; 1- предыдущее меню; 0- завершить работу");

                                    String choice4 = bufferedReader.readLine();

                                    if (choice4.equals("0")) break first;

                                    if (choice4.equals("1")) continue third;

                                    String s[] = choice4.split(" ");

                                    if (s.length != 2) {System.out.println("Некорректный ввод id"); continue;}

                                    try {

                                        long roomId = Long.parseLong(s[0]);

                                        long hotelId = Long.parseLong(s[1]);

                                        if (Controller.bookRoom(roomId, user.getId(), hotelId)) continue third;

                                    } catch (NumberFormatException e){

                                        System.out.println("Надо вводить целые числа!");

                                    }

                                }

                            }



                            //поиск номеров

                            if (choice3.equals("3")){

                                while (true) {

                                    System.out.println("Для поиска номеров, введите параметры разделяя их символом / в таком формате:" +

                                            " город/название отеля/кол-во персон/цена от/цена до, образец: Kiev/Desna/2/200/350 \n" +

                                            "если параметр Вам не важен - поставьте вместо него символ *.   А если передумали искать, то: " +

                                            " 1- предыдущее меню; 0- завершить работу");

                                    String choice4 = bufferedReader.readLine();

                                    if (choice4.equals("0")) break first;

                                    if (choice4.equals("1")) continue third;

                                    String s[] = choice4.split("/");

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



                            System.out.println("Некорректный ввод");

                        }

                    }//юзер под своим именем тут заканчивается

                }



            }



            //регистрация

            if (choice1.equals("2")) {

                regist:

                while (true) {

                    System.out.println("Введите имя и пароль через пробел, либо: 1- выйти в главное меню, 0- завершить работу");

                    String choice2 = bufferedReader.readLine();

                    if (choice2.length() == 1) {

                        if (choice2.equals("0")) break first;

                        if (choice2.equals("1")) continue first;

                    }

                    String nameAndPass[] = choice2.split(" ");

                    if (nameAndPass.length != 2) {

                        System.out.println("Некорректный ввод");

                        continue;

                    }

                    boolean b = Controller.registerUser(new User(0, nameAndPass[0], nameAndPass[1]));

                    if (b) continue first;

                }

            }



            if (choice1.equals("777")){

                Admin.root();

                continue;

            }



            System.out.println("Некорректный ввод");

        }



        System.out.println("До встречи!");

        bufferedReader.close();

    }

}
