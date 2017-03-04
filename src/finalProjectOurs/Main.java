//package finalProjectOurs;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static finalProjectOurs.Controller.*;
//
///**
// * Created by Aleksey on 29.01.2017.
// */
//public class Main {
//
//
//    public static void main(String[] args) {
//        HotelDAO hotelDAO = new HotelDAO();
//        Hotel hotel1 = new Hotel(1, "Dnipro", "Kiev");
//        Hotel hotel2 = new Hotel(2, "Desna", "Kiev");
//        Hotel hotel3 = new Hotel(3, "Slavutich", "Cherkasy");
//        Hotel hotel4 = new Hotel(4, "Lybid", "Sumy");
//        hotelDAO.add(hotel1);
//        hotelDAO.add(hotel2);
//        hotelDAO.add(hotel3);
//        System.out.println();
//        HotelDAO.getHotels().forEach(System.out::println);
//        System.out.println();
//        hotelDAO.delete(hotel4);
//
//        hotelDAO.addToBase();
//
//        Controller controller = new Controller();
//
//
//        RoomDAO roomDAO = controller.roomDAO;
//        Room room1 = new Room(1, 2, 100, hotel1);
//        Room room2 = new Room(2, 3, 180, hotel2);
////        Room room21 = new Room(2, 3, 180, hotel2);
//        Room room3 = new Room(3, 2, 150, hotel3);
//        Room room4 = new Room(4, 1, 60, hotel4);
//        Room room5 = new Room(4, 2, 100, hotel2);
//        roomDAO.add(room1);
//        roomDAO.add(room2);
////        roomDAO.add(room21);
//        roomDAO.add(room3);
//        roomDAO.add(room4);
//        roomDAO.add(room5);
//        roomDAO.delete(room2);
//
//        roomDAO.getRooms().forEach(System.out::println);
//        roomDAO.addToBase();
//        UserDAO userDAO = controller.userDAO;
//        User user1 = new User(1, "Andrew", "123");
//        User user2 = new User(2, "Aleks", "111");
//        User user3 = new User(3, "Gusar", "321");
//        User user4 = new User(3, "Gusar", "321");
//        User user5 = new User(4, "Olga", "qwert");
//
//        userDAO.add(user1);
//        userDAO.add(user2);
//        userDAO.add(user3);
//        userDAO.add(user4);
////        userDAO.add(user5);
////        userDAO.find(user1);
////        userDAO.delete(user3);
//        userDAO.addToBase();
//
//        userDAO.getUsers().forEach(System.out::println);
////        Controller.findHotelByName("Dnipro");
////        Controller.findHotelByName("Dnio");
//
//        controller.bookRoom(1, 1, 1);
//        controller.cancelReservation(1, 1, 1);
//        controller.ROOMS.forEach(s -> System.out.println(s));
//
////        controller.signIn();
////
////        controller.USERS.forEach(s -> System.out.println(s));
//        System.out.println("======================");
//        Map<String, String> map = new HashMap<>();
//        map.put(CITY, "Kiev");
//        map.put(HOTELNAME, "Desna");
//        map.put(PERSON, "2");
//        map.put(PRICE, "100");
//        controller.findRoom(map).forEach(s -> System.out.println(s));
//    }
//
//
//}
