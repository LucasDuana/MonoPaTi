package org.example.adminmicroservice.service;

import org.example.adminmicroservice.classes.AccountDTO;
import org.example.adminmicroservice.classes.TravelDTO;
import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.dtos.UserDTO;
import org.example.adminmicroservice.model.Bill;
import org.example.adminmicroservice.model.Tariff;
import org.example.adminmicroservice.repositories.BillRepository;
import org.example.adminmicroservice.repositories.TariffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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



    //get all bills
    public List<BillDTO> getAllBills() {
        return billRepository.findAll().stream().map(bill -> new BillDTO(bill.getUserId(), bill.getTripId(), bill.getTotalCost(), bill.getDate(), bill.getTariff())).collect(Collectors.toList());
    }

    private static long calculateDurationInMinutes(LocalTime startTime, LocalTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toMinutes();
    }


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


        String urlDiscountPriceUser = "http://localhost:8080/users/" + travelDTO.getUserId() + "/discount?amount=" + priceTrip;


        HttpEntity<Void> requestEntity = new HttpEntity<>(null);


        ResponseEntity<AccountDTO> responseEntity = restTemplate.exchange(
                urlDiscountPriceUser,
                HttpMethod.PUT,
                requestEntity,
                AccountDTO.class
        );


        AccountDTO accountDTOResponse = responseEntity.getBody();

        return modelMapper.map(billRepository.save(bill), BillDTO.class);
    }
}
