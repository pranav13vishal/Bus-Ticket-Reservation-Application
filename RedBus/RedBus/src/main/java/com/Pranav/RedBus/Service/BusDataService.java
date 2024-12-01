package com.Pranav.RedBus.Service;

import com.Pranav.RedBus.Dto.BusDataDto;


import java.util.List;
import java.util.zip.DataFormatException;

public interface BusDataService {

    BusDataDto addBus(BusDataDto busDataDto) ;
    BusDataDto updateBus(BusDataDto busDataDto, Long id);
    void deleteBus(Long id) ;
    BusDataDto viewBus(Long id) ;
    List<BusDataDto> viewAllBus();
    List<BusDataDto> searchBuses(String source, String destination, String date);

//

}
