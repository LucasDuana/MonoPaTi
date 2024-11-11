package org.example.adminmicroservice.service;

import org.example.adminmicroservice.classes.TravelDTO;
import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.dtos.UserDTO;
import org.example.adminmicroservice.model.Bill;
import org.example.adminmicroservice.model.Tariff;
import org.example.adminmicroservice.repositories.BillRepository;
import org.example.adminmicroservice.repositories.TariffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private RestTemplate restTemplate;  //CRUD operations for Bill entity
    @Autowired
    private ModelMapper modelMapper;

    //Get bills in range
    public List<Bill> getBillsInRange(int year, int startMonth, int endMonth) {
        return billRepository.getBillsInRange(year,startMonth, endMonth);
    }

    //get all bills
    public List<BillDTO> getAllBills() {
        return billRepository.findAll().stream().map(bill -> new BillDTO(bill.getUserId(), bill.getTripId(), bill.getTotalCost(), bill.getDate(), bill.getTariff())).collect(Collectors.toList());
    }

    private static long calculateDurationInMinutes(LocalTime startTime, LocalTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toMinutes();
    }

    //create a bill
    /*public BillDTO createBill(Long travelId) {
        String url = "http://localhost:8082/travels/" + travelId;

        TravelDTO travelDTO = restTemplate.getForObject(url, TravelDTO.class);
        Tariff actualTariff = this.tariffRepository.findLatestTariff(LocalDate.now()).get();

        long minutes = calculateDurationInMinutes(travelDTO.getStartTime(), travelDTO.getEndTime());

        double priceTrip = actualTariff.getCostPerKm() * travelDTO.getKilomenters() + actualTariff.getCostPerMinute() * minutes;

        Bill bill = new Bill();
        bill.setUserId(travelDTO.getUserId());
        bill.setTripId(travelId);
        bill.setTotalCost(priceTrip);
        bill.setDate(LocalDate.now());
        bill.setTariff(actualTariff);

        //falta actualizar dinero en la/s cuenta/s afectada/s
        String urlSetDiscountPrice = "http://localhost:8080/users/" + travelDTO.getUserId() + "/discount?amount=" + priceTrip;

        boolean chargeSuccess = restTemplate.getForObject(urlSetDiscountPrice, Boolean.class);

        if (chargeSuccess && chargeSuccess) {
            //guardo la factura
            return new BillDTO(billRepository.save(bill).getUserId(), billRepository.save(bill).getTripId(), billRepository.save(bill).getTotalCost(), billRepository.save(bill).getDate(), billRepository.save(bill).getTariff());
        } else {
            throw new RuntimeException("No se pudo aplicar el cargo en la cuenta del usuario.");
        }
    }*/

    public BillDTO createBill(Long travelId){
        String urlGetTripAsociated = "http://localhost:8082/travels/"+travelId;
        TravelDTO travelDTO = restTemplate.getForObject(urlGetTripAsociated, TravelDTO.class);

        Tariff actualTariff=this.tariffRepository.findLatestTariff(LocalDate.now()).get();

        long minutes = calculateDurationInMinutes(travelDTO.getStartTime(), travelDTO.getEndTime());

        double priceTrip=actualTariff.getCostPerKm()*travelDTO.getKilomenters()+actualTariff.getCostPerMinute()*minutes+travelDTO.getLongPauses()*actualTariff.getCostPerExtensePause();

        Bill bill = new Bill();
        bill.setUserId(travelDTO.getUserId());
        bill.setTripId(travelId);
        bill.setTariff(actualTariff);
        bill.setTotalCost(priceTrip);
        bill.setDate(LocalDate.now());

        String urlDiscountPriceUser= "http://localhost:8080/users/"+travelDTO.getUserId()+"/discount?amount="+priceTrip;

        boolean chargeSuccess = restTemplate.getForObject(urlDiscountPriceUser, Boolean.class);
        if (chargeSuccess)
            return modelMapper.map(billRepository.save(bill), BillDTO.class);
        else{
            throw new RuntimeException("No se pudo aplicar el cargo en la cuenta del usuario.");
        }
    }
}
