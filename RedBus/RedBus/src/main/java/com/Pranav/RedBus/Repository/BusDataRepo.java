package com.Pranav.RedBus.Repository;

import com.Pranav.RedBus.Dto.BusDataDto;
import com.Pranav.RedBus.Entity.BusData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BusDataRepo extends JpaRepository<BusData,Long> {

    List<BusData> findBySourceAndDestinationAndDate(String source, String destination, String date);
    boolean existsByBusNumber(String busNumber);
}
