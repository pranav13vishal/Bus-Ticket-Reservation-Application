//package com.Pranav.RedBus.Controller;
//
//import com.Pranav.RedBus.Dto.BookDto;
//import com.Pranav.RedBus.Dto.BusDataDto;
//import com.Pranav.RedBus.Entity.Bookings;
//import com.Pranav.RedBus.Service.BookingServiceImpl;
//import com.Pranav.RedBus.Service.BusDataService;
//import com.Pranav.RedBus.Service.UserService;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.io.IOException;
//
//@AllArgsConstructor
//@Controller
//public class BookingController {
//
//
//    private BookingServiceImpl bookingsService;
//    private BusDataService busDataService;
//    private UserService userService;
//
//    @GetMapping("/bookings/new")
//    public String showBookingForm(@RequestParam Long busId1, Model model) {
//        BusDataDto bus = busDataService.viewBus(busId1);
//        model.addAttribute("bus", bus);
//        return "booking-form";
//    }
//
//    @PostMapping("/bookings/confirm")
//    public String confirmBooking(@RequestParam Long busId, @ModelAttribute BookDto bookDto, HttpServletResponse response) throws  IOException {
//        // Save the booking to the database
//
//        BusDataDto bus = busDataService.viewBus(busId);
//        Bookings bookings = new Bookings();
//        bookings.setBusName(bus.getBusName());
//        bookings.setBusNumber(bus.getBusNumber());
//        bookings.setDate(bus.getDate());
//        bookings.setDestination(bus.getDestination());
//        bookings.setTime(bus.getTime());
//        bookings.setDuration(bus.getDuration());
//        bookings.setPrice(bus.getPrice());
//        bookings.setSource(bus.getSource());
//
//        bookings.setName(bookDto.getName());
//        bookings.setEmail(bookDto.getEmail());
//        bookings.setPhoneNumber(bookDto.getPhoneNumber());
//        bookings.setAge(bookDto.getAge());
//        bookings.setGender(bookDto.getGender());
//
//        bookingsService.saveBooking(bookings);
//
//        // Generate and send the PDF
//        bookingsService.generatePdfWithPDFBox(bookings, response);
//
//        return "redirect:/home";
//    }
//
//}
//
