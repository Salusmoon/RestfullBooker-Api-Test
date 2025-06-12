package utils;

import request.CreateBookingRequest;
import response.CreateBookingResponse;
import response.CreateTokenResponse;
import response.GetBookingIdsResponse;
import response.GetBookingResponse;

import java.util.List;

public class DataProvider {


    public static CreateTokenResponse createTokenResponse;
    public static List<GetBookingIdsResponse> bookingIdList;
    public static GetBookingResponse bookingResponse;
    public static CreateBookingResponse createBookingResponse;
    public static GetBookingResponse uptadateBooking;
    public static GetBookingResponse particalUpdateBooking;
    public static CreateBookingRequest createBookingRequest;
}
