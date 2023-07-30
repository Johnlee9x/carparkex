package com.laptrinh.carparkex.service;

import com.laptrinh.carparkex.Mapper.ConfigMapperModel;
import com.laptrinh.carparkex.dto.ParkingLotRequest;
import com.laptrinh.carparkex.entity.ParkingLot;
import com.laptrinh.carparkex.exception.ParkingLotNotFoundException;
import com.laptrinh.carparkex.repository.IParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParkingLotService {
    @Autowired
    private IParkingLotRepo parkingLot;
    public List<ParkingLot> getAll(){
        return parkingLot.findAll();
    }
    public ParkingLot findByParkId(Long id) throws ParkingLotNotFoundException {
        if(parkingLot.findById(id).isPresent()){
            return parkingLot.getReferenceById(id);
        }
        else{
            throw new ParkingLotNotFoundException("ParkingLot Not Found With Id: "+ id);
        }
    }
    public ParkingLot saveNewParking(ParkingLotRequest parkingLotRequest){
        return parkingLot.save(ConfigMapperModel.getMapper().map(parkingLotRequest, ParkingLot.class));
    }
    public ParkingLot updateParkingLot(Long id, ParkingLotRequest requestParkingLot) throws ParkingLotNotFoundException {
        ParkingLot parkingLot1 = findByParkId(id);
        parkingLot1.setParkArea(requestParkingLot.getParkArea());
        parkingLot1.setParkName(requestParkingLot.getParkName());
        parkingLot1.setParkPrice(requestParkingLot.getParkPrice());
        parkingLot1.setParkPlace(requestParkingLot.getParkPlace());
        return parkingLot.save(parkingLot1);
    }

    public void deleteAParkingLotById(Long id) throws ParkingLotNotFoundException {
        ParkingLot parking = findByParkId(id);
        parkingLot.delete(parking);
    }

}
