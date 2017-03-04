package finalProjectOurs;

/**
 * Created by Aleksey on 07.02.2017.
 */
public abstract  class MainWithTxtBase {
    public static void main(String[] args) {
//        HotelDAO hotelDAO = new HotelDAO();
//        Hotel hotel1 = new Hotel(1, "Dnipro", "Kiev");
//        Hotel hotel2 = new Hotel(2, "Desna", "Kiev");
//        Hotel hotel3 = new Hotel(3, "Slavutich", "Cherkasy");
//        Hotel hotel4 = new Hotel(4, "Lybid", "Sumy");
//        Hotel hotel5 = new Hotel(5, "Jutomir", "Jutomir");
//        hotelDAO.addToBase(hotel1);
//        hotelDAO.addToBase(hotel2);
//        hotelDAO.addToBase(hotel3);
//        hotelDAO.addToBase(hotel4);
//        hotelDAO.addToBase(hotel5);
////        hotelDAO.readBase().forEach(s-> System.out.println(s));
////        System.out.println( new HotelDAO().getHotelsStr().size());
////        hotelDAO.getHotelsStr().forEach(s -> System.out.println(s));
//        System.out.println(hotelDAO.getHotels().size());
//        hotelDAO.getHotelsStr().forEach(s -> System.out.println(s));


//        System.out.println();
//        HotelDAO.getHotels().forEach(System.out::println);
//        System.out.println();
//        hotelDAO.delete(hotel4);
//===================================================================
        UserDAO userDAO = new UserDAO();
        userDAO.addToBase(new User(1, "Andrey", "123"));
        userDAO.addToBase(new User(2, "Aleksey", "123"));
        userDAO.addToBase(new User(3, "Gusar", "123"));
        System.out.println(userDAO.readBaseUsers().size());
        userDAO.readBaseUsers().forEach(user -> System.out.println(user));



    }
}
