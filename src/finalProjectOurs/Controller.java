package finalProjectOurs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static finalProjectOurs.BookingState.NOT_RESERVED;
import static finalProjectOurs.BookingState.RESERVED;

/**
 * Created by Aleksey on 29.01.2017.
 * Collection<Hotel> findHotelByName(String name)
 * Collection<Hotel> findHotelDyCity(String city)
 * void bookRoom(long roomId, long userId, long hotelId)
 * void cancelReservation(long roomId, long userId, long hotelId)
 * Collection<Room> findRoom(Map<String, String> params)
 */
public class Controller {
    static Collection<Room> reservedRoom = new ArrayList<>();
    public static final List<Hotel> HOTELS = HotelDAO.getHotels();
    RoomDAO roomDAO = new RoomDAO();
    public List<Room> ROOMS = roomDAO.getRooms();
    UserDAO userDAO = new UserDAO();
    public List<User> USERS = userDAO.getUsers();
    public static final String CITY = "CITY";
    public static final String HOTELNAME = "HOTELNAME";
    public static final String PERSON = "PERSON";
    public static final String PRICE = "PRISE";


    public static Collection<Hotel> findHotelByName(String name) {
        if (HOTELS.stream().anyMatch(hotel -> hotel.getName().equals(name))) {
            System.out.println("Отель найден " + name);
        } else
            System.out.println("Отель в базе не найден. ");
        return HOTELS;
    }

    public static Collection<Hotel> findHotelByCity(String city) {
        if (HOTELS.stream().anyMatch(hotel -> hotel.getCity().equals(city))) {
            System.out.println("Отели в городе " + city);
        } else
            System.out.println("Нет отелей в таком городе. ");
        return HOTELS;
    }

    public void bookRoom(long hotelId, long roomId, long userId) {
        ROOMS.stream().filter(room -> room.getRoomId() == roomId && room.getHotel().getHotelID() == hotelId);
        //      System.out.println();
        ROOMS.get(0).setBookingState(RESERVED);
        userReservation(roomId, userId, hotelId);
    }


    public void cancelReservation(long roomId, long userId, long hotelId) {
        ROOMS.stream().filter(room -> room.getRoomId() == roomId && room.getHotel().getHotelID() == hotelId);
        ROOMS.get(0).setBookingState(NOT_RESERVED);
        ROOMS.get(0).setReservedUserId(0);
        ROOMS.get(0).setReservedUserName(null);
    }

    private void userReservation(long roomId, long userId, long hotelId) {
        ROOMS.stream().filter(room -> room.getRoomId() == roomId && room.getHotel().getHotelID() == hotelId);
        Room room = ROOMS.get(0);
        if (room.getBookingState() == RESERVED) {
            room.setReservedUserId(userId);
//            room.setReservedUserName();
            USERS.stream().filter(user -> user.getUserID() == userId);
            room.setReservedUserName(USERS.get(0).getLogin());
        }

    }


    public Collection<Room> findRoom(Map<String, String> params) {
       Collection <Room> findRooms = new ArrayList<>();
        boolean result =
        ROOMS.stream().
                anyMatch(room -> room.getHotel().getCity().equals(params.get(CITY)) && room.getHotel().getName().equals(params.get(HOTELNAME))
        && room.getPerson() == Integer.parseInt(params.get(PERSON)) && room.getPrice() == Integer.parseInt(params.get(PRICE)));

        if (result)
        return ROOMS;
        return null;
    }

    public void registerUser(User user) {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        userDAO.add(user);
    }

    public void signIn() {
        User user = createUser();
        if (USERS.stream().anyMatch(user1 -> user1.getLogin().equals(user.getLogin()) && user1.getPassword().equals(user.getPassword()))) {
            System.out.println("You successful signIn");
        } else {
            registerUser(user);
        }
    }

    private User createUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your login");
        String login = sc.nextLine();
        System.out.println("Enter your password");
        String pass = sc.nextLine();
        long userId = USERS.get(USERS.size() - 1).getUserID();
        userId++;
        User user = new User(userId, login, pass);

        return user;
    }

}
