package finalProjectOurs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Aleksey on 29.01.2017.
 */
public class RoomDAO implements DAO<Room> {
    private File roomBase;
    private  List<Room> rooms = new ArrayList<>();

    public  List<Room> getRooms() {
        return rooms;
    }

    public RoomDAO() {
        roomBase = new File("src\\finalProjectOurs\\RoomBase.txt");
        try {
            roomBase.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "RoomDAO{" +
                "roomBase=" + roomBase +
                ", rooms=" + rooms +
                '}';
    }

    @Override
    public void add(Room room) {
        if (rooms.stream().allMatch(room1 -> room1.getRoomId() != room.getRoomId())) {
            if (getHotelBase().stream().anyMatch(hotel -> room.getHotel().equals(hotel))) {
                rooms.add(room);
            } else
                System.out.println("Отеля с таким названием нет в базе!");
        } else {
            System.out.println("Такая комната уже добавлена в базу.");
        }
//        for (Hotel hotel : getHotelBase()) {
//            if (room.hotel.getName().equals(hotel.getName())) {
//                rooms.add(room);
//
//            }
//        }
    }

    private static   List<Hotel> getHotelBase() {
        return HotelDAO.getHotels();
    }

    @Override
    public void delete(Room room) {
        if (rooms.stream().anyMatch(room::equals)) {
            rooms.remove(room);
            System.out.println("Комната удалена.");
        }
//               rooms.removeIf(room1 -> room.equals(room1));
    }

    @Override
    public boolean find(Room room) {
        boolean result = false;
        if (rooms.stream().anyMatch(room1 -> room.getRoomId() == room1.getRoomId())) {
            result = true;
            System.out.println("Комната найдена.");
        } else {
            System.out.println("Комната не найдена в базе.");
        }
        return result;
    }

    @Override
    public void addToBase(Room room1) {
        try (FileWriter fr = new FileWriter(roomBase, true);
             BufferedWriter bw = new BufferedWriter(fr);
        ) {
            rooms.forEach(room -> {
                try {
                    bw.write(room.toString() + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
