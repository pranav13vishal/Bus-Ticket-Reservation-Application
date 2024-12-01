package com.Pranav.RedBus.Repository;

import com.Pranav.RedBus.Entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookingRepo extends JpaRepository<Bookings,Long> {
    List<Bookings> findByEmail(String email);
}

