package response;

import response.Helper.BookingDates;

public class CreateBookingResponse {

    private int bookingid;
    private BookingDates bookingdates;
    private GetBookingResponse booking;

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public GetBookingResponse getBooking() {
        return booking;
    }

    public void setBooking(GetBookingResponse booking) {
        this.booking = booking;
    }
}
