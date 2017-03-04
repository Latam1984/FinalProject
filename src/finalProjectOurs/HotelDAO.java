package finalProjectOurs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksey on 30.01.2017.
 */
public class HotelDAO implements DAO<Hotel> {
    private File hotelBase2;
    private static List<Hotel> hotels = new ArrayList<>();
    private  List<String> hotelsStr = new ArrayList<>();



    public HotelDAO() {
        hotelBase2 = new File("src\\finalProjectOurs\\HotelBase.txt");
        try {
            hotelBase2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHotelsStr(List<String> hotelsStr) {
        this.hotelsStr = hotelsStr;
    }

    @Override
    public void add(Hotel hotel) {
        if (hotels.stream().allMatch(hotel1 -> hotel1.getHotelID() != hotel.getHotelID())) {
            hotels.add(hotel);
//            System.out.println("добавлен в базу");
        } else {
            System.out.println("отель найден в базе! Он не может быть добавлен снова");
        }
    }


    @Override
    public void delete(Hotel hotel) {
        if (find(hotel)) {
            hotels.removeIf(hotel1 -> hotel.getHotelID() == hotel1.getHotelID());
            System.out.println(hotel + " удален из базы!");
        } else
            System.out.println("не может быть удален");
    }

    @Override
    public boolean find(Hotel hotel) {
        boolean value = false;
        if (hotels.stream().anyMatch(hotel1 -> hotel1.getHotelID() == hotel.getHotelID())) {
            System.out.println(hotel + " найден в базе!");
            value = true;
        } else {
            System.out.println(hotel + " Отель не найден в базе!");
        }
        return value;
    }

    @Override
    public void addToBase(Hotel hotel) {

        try (FileWriter fr = new FileWriter(hotelBase2, true);
             BufferedWriter bw = new BufferedWriter(fr)) {
            String someString = hotel.getHotelID() + " " + hotel.getName() + " " + hotel.getCity();
            if (hotelsStr==null){
                bw.write(someString + System.lineSeparator());
            }
            else if (readBase().stream().allMatch(s -> !s.equals(someString))) {
                bw.write(someString + System.lineSeparator());
            }else
                System.out.println("This string is already added");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  List<String> getHotelsStr() {
        return hotelsStr;
    }

    public static List<Hotel> getHotels() {
        return hotels;
    }

    public List<String> readBase() {
        List<String> strings = new ArrayList<>();
        try (FileReader fr = new FileReader(hotelBase2);
                BufferedReader br = new BufferedReader(fr)) {
            String someString;
            while ((someString = br.readLine()) != null) {
                strings.add(someString);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hotelsStr = strings;
    }
}
