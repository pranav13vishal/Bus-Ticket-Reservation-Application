package com.Pranav.RedBus.ControllerRestApi;

import com.Pranav.RedBus.Dto.BusDataDto;
import com.Pranav.RedBus.Dto.UserDto;
import com.Pranav.RedBus.Entity.Bookings;
import com.Pranav.RedBus.Service.BookingService;
import com.Pranav.RedBus.Service.BusDataService;
import com.Pranav.RedBus.Service.UserService;
import com.Pranav.RedBus.exception.DuplicateBusNumberException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "ADMIN MANAGEMENT API - CRED API ",
        description = "Endpoints for managing buses "
)
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final BusDataService busDataService;
    private final UserService userService;
    private final BookingService bookingService;

    @GetMapping
    public String showAdmin() {
        return "Admin dashboard API active!";
    }

    @Operation(summary = "Create a new Bus Route", description = "Creating a new valid Bus Route in the database for the users to search and book")
    @PostMapping("/addBus")
    public ResponseEntity<?> registerBus(@RequestBody BusDataDto busDataDto) {
        try {
            busDataService.addBus(busDataDto);
            return ResponseEntity.ok("Bus added successfully");
        } catch (DuplicateBusNumberException ex) {
            return ResponseEntity.badRequest().body("BUS NUMBER ALREADY PRESENT");
        }
    }

    @Operation(summary = "Get all buses", description = "Fetches all buses from the database")
    @GetMapping("/viewBuses")
    public ResponseEntity<List<BusDataDto>> viewAllBuses() {
        return ResponseEntity.ok(busDataService.viewAllBus());
    }

    @Operation(summary = "Get all users", description = "Fetches all users from the database")
    @GetMapping("/viewUsers")
    public ResponseEntity<List<UserDto>> viewAllUsers() {
        return ResponseEntity.ok(userService.viewAllUsers());
    }

    @Operation(summary = "Delete a bus route", description = "Deletes a bus route from the database")
    @DeleteMapping("/deleteBus/{id}")
    public ResponseEntity<?> deleteBus(@PathVariable Long id) {
        busDataService.deleteBus(id);
        return ResponseEntity.ok("Bus deleted successfully");
    }

    @Operation(summary = "Update a current bus route", description = "Updates an existing bus route in the database")
    @PutMapping("/updateBus/{id}")
    public ResponseEntity<?> updateBus(@PathVariable Long id, @RequestBody BusDataDto busDataDto) {
        try {
            busDataService.updateBus(busDataDto, id);
            return ResponseEntity.ok("Bus updated successfully");
        } catch (DuplicateBusNumberException ex) {
            return ResponseEntity.badRequest().body("BUS NUMBER ALREADY PRESENT");
        }
    }

    @Operation(summary = "View all bookings", description = "View all the bookings made by the passengers")
    @GetMapping("/viewBookings")
    public ResponseEntity<List<Bookings>> viewAllBookings() {
        return ResponseEntity.ok(bookingService.viewAllBus());
    }
}


//{
//        "busNumber":"TN48AS1234",
//        "busName": "pranavtravels",
//        "capacity": 25,
//        "source" : "cbe",
//        "destination":"mas",
//        "price": 200,
//        "date":"05-11-2024",
//        "time": "11:30",
//        "duration":6,
//        "availableSeats":20
//        }

