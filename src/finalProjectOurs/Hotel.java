package finalProjectOurs;

/**
 * Created by Aleksey on 29.01.2017.
 */
public class Hotel {
    private long hotelID;
    private String name;
    private String city;

    public Hotel(long hotelID, String name, String city) {
        this.hotelID = hotelID;
        this.name = name;
        this.city = city;

    }

    public long getHotelID() {
        return hotelID;
    }

    public void setHotelID(long hotelID) {
        this.hotelID = hotelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return hotelID + " " + name + " " + city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        if (getHotelID() != hotel.getHotelID()) return false;
        if (!getName().equals(hotel.getName())) return false;
        return getCity().equals(hotel.getCity());

    }

    @Override
    public int hashCode() {
        int result = (int) (getHotelID() ^ (getHotelID() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + getCity().hashCode();
        return result;
    }
}