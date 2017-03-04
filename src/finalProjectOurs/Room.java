package finalProjectOurs;

/**
 * Created by Aleksey on 29.01.2017.
 */
public class Room {
    private long roomId;
    private int person;
    private int price;
    private Hotel hotel;
    private long reservedUserId;
    private String reservedUserName;
    private BookingState bookingState = BookingState.NOT_RESERVED;


    public Room(long id, int person, int price, Hotel hotel) {
        this.roomId = id;
        this.person = person;
        this.price = price;
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", person=" + person +
                ", price=" + price +
                ", hotel=" + hotel +

                ",[ reservedUserId=" + reservedUserId +
                ", reservedUserName='" + reservedUserName +  '\'' + " ]" +
                ", bookingState=" + bookingState +
                '}';
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public long getReservedUserId() {
        return reservedUserId;
    }

    public void setReservedUserId(long reservedUserId) {
        this.reservedUserId = reservedUserId;
    }

    public String getReservedUserName() {
        return reservedUserName;
    }

    public void setReservedUserName(String reservedUserName) {
        this.reservedUserName = reservedUserName;
    }

    public BookingState getBookingState() {
        return bookingState;
    }

    public void setBookingState(BookingState bookingState) {
        this.bookingState = bookingState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (getRoomId() != room.getRoomId()) return false;
        if (getPerson() != room.getPerson()) return false;
        if (getPrice() != room.getPrice()) return false;
        if (getReservedUserId() != room.getReservedUserId()) return false;
        if (!getHotel().equals(room.getHotel())) return false;
        if (!getReservedUserName().equals(room.getReservedUserName())) return false;
        return getBookingState() == room.getBookingState();

    }

    @Override
    public int hashCode() {
        int result = (int) (getRoomId() ^ (getRoomId() >>> 32));
        result = 31 * result + getPerson();
        result = 31 * result + getPrice();
        result = 31 * result + getHotel().hashCode();
        result = 31 * result + (int) (getReservedUserId() ^ (getReservedUserId() >>> 32));
        result = 31 * result + getReservedUserName().hashCode();
        result = 31 * result + getBookingState().hashCode();
        return result;
    }
}
