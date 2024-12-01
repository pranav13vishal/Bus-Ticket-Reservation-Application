package com.Pranav.RedBus.serviceTest;

import com.Pranav.RedBus.Dto.BusDataDto;
import com.Pranav.RedBus.Entity.BusData;
import com.Pranav.RedBus.Repository.BusDataRepo;
import com.Pranav.RedBus.Service.BusDataServiceImpl;
import com.Pranav.RedBus.exception.DuplicateBusNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BusDataServiceImplTest {

    @Mock
    private BusDataRepo busDataRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BusDataServiceImpl busDataService;

    private BusDataDto busDataDto;
    private BusData busData;

    @BeforeEach
    void setUp() {
        busDataDto = new BusDataDto();
        busDataDto.setBusNumber("BUS123");
        busDataDto.setBusName("Test Bus");
        busDataDto.setCapacity(40L);
        busDataDto.setSource("City A");
        busDataDto.setDestination("City B");
        busDataDto.setPrice(500L);
        busDataDto.setDate("2024-12-01");
        busDataDto.setTime("10:00 AM");
        busDataDto.setDuration(5L);
        busDataDto.setAvailableSeats(30L);

        busData = new BusData();
        busData.setId(1L);
        busData.setBusNumber("BUS123");
        busData.setBusName("Test Bus");
        busData.setCapacity(40L);
        busData.setSource("City A");
        busData.setDestination("City B");
        busData.setPrice(500L);
        busData.setDate("2024-12-01");
        busData.setTime("10:00 AM");
        busData.setDuration(5L);
        busData.setAvailableSeats(30L);
    }

    @Test
    void givenBusDataDto_whenAddBus_thenReturnSavedBusDataDto() {
        // given
        given(busDataRepo.existsByBusNumber(busDataDto.getBusNumber())).willReturn(false);
        given(modelMapper.map(busDataDto, BusData.class)).willReturn(busData);
        given(busDataRepo.save(busData)).willReturn(busData);
        given(modelMapper.map(busData, BusDataDto.class)).willReturn(busDataDto);

        // when
        BusDataDto savedBus = busDataService.addBus(busDataDto);

        // then
        assertThat(savedBus).isNotNull();
        assertThat(savedBus.getBusNumber()).isEqualTo(busDataDto.getBusNumber());
        verify(busDataRepo, times(1)).save(busData);
    }

    @Test
    void givenExistingBusNumber_whenAddBus_thenThrowDuplicateBusNumberException() {
        // given
        given(busDataRepo.existsByBusNumber(busDataDto.getBusNumber())).willReturn(true);

        // when/then
        assertThatThrownBy(() -> busDataService.addBus(busDataDto))
                .isInstanceOf(DuplicateBusNumberException.class)
                .hasMessage("Bus number already exists.");
        verify(busDataRepo, never()).save(any(BusData.class));
    }

    @Test
    void givenBusDataDtoAndId_whenUpdateBus_thenReturnUpdatedBusDataDto() {
        // given
        given(busDataRepo.findById(busData.getId())).willReturn(Optional.of(busData));
        given(busDataRepo.existsByBusNumber(busDataDto.getBusNumber())).willReturn(false);
        given(busDataRepo.save(busData)).willReturn(busData);
        given(modelMapper.map(busData, BusDataDto.class)).willReturn(busDataDto);

        // when
        BusDataDto updatedBus = busDataService.updateBus(busDataDto, busData.getId());

        // then
        assertThat(updatedBus).isNotNull();
        assertThat(updatedBus.getBusName()).isEqualTo(busDataDto.getBusName());
        verify(busDataRepo, times(1)).save(busData);
    }

    @Test
    void givenBusId_whenDeleteBus_thenVerifyDeletion() {
        // given
        Long busId = 1L;

        // when
        busDataService.deleteBus(busId);

        // then
        verify(busDataRepo, times(1)).deleteById(busId);
    }

    @Test
    void givenBusId_whenViewBus_thenReturnBusDataDto() {
        // given
        given(busDataRepo.findById(busData.getId())).willReturn(Optional.of(busData));
        given(modelMapper.map(busData, BusDataDto.class)).willReturn(busDataDto);

        // when
        BusDataDto fetchedBus = busDataService.viewBus(busData.getId());

        // then
        assertThat(fetchedBus).isNotNull();
        assertThat(fetchedBus.getBusNumber()).isEqualTo(busDataDto.getBusNumber());
    }

    @Test
    void whenViewAllBuses_thenReturnListOfBusDataDtos() {
        // given
        List<BusData> buses = List.of(busData);
        List<BusDataDto> busDtos = List.of(busDataDto);

        given(busDataRepo.findAll()).willReturn(buses);
        given(modelMapper.map(busData, BusDataDto.class)).willReturn(busDataDto);

        // when
        List<BusDataDto> fetchedBuses = busDataService.viewAllBus();

        // then
        assertThat(fetchedBuses).isNotNull();
        assertThat(fetchedBuses).hasSize(1);
        verify(busDataRepo, times(1)).findAll();
    }

    @Test
    void givenSourceDestinationAndDate_whenSearchBuses_thenReturnListOfBusDataDtos() {
        // given
        List<BusData> buses = List.of(busData);
        List<BusDataDto> busDtos = List.of(busDataDto);

        given(busDataRepo.findBySourceAndDestinationAndDate("City A", "City B", "2024-12-01")).willReturn(buses);
        given(modelMapper.map(busData, BusDataDto.class)).willReturn(busDataDto);

        // when
        List<BusDataDto> searchedBuses = busDataService.searchBuses("City A", "City B", "2024-12-01");

        // then
        assertThat(searchedBuses).isNotNull();
        assertThat(searchedBuses).hasSize(1);
        verify(busDataRepo, times(1)).findBySourceAndDestinationAndDate("City A", "City B", "2024-12-01");
    }
}
