package com.nathanael.florcreation.users.services;

import com.nathanael.florcreation.errors.EntityExceptions;
import com.nathanael.florcreation.users.dtos.Rider;
import com.nathanael.florcreation.users.mappers.UsersMapper;
import com.nathanael.florcreation.users.models.RiderTable;
import com.nathanael.florcreation.users.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderServices {
    @Autowired private UsersMapper usersMapper;
    @Autowired private RiderRepository riderRepository;

    public List<Rider> getAllRiders() {
        return usersMapper.riderTableListToRiderList(
                riderRepository.findAllRider()
        );
    }

    public Rider getRiderById(Long riderId) {
        if (riderId == null) return null;
        return usersMapper.riderTableToRider(
                riderRepository.findRiderById(riderId)
        );
    }

    public Rider createNewRider(Rider rider) {
        RiderTable exists = riderRepository.findRiderByName(rider.getRiderName());

        if (exists != null || rider.getRiderId() != null) return updateRider(rider);

        return usersMapper.riderTableToRider(
                riderRepository.createNewRider(
                        rider.getRiderName(),
                        rider.getRiderContact(),
                        rider.getRiderCity(),
                        rider.getRiderProvince()
                )
        );
    }

    public Rider updateRider(Rider rider) {
        RiderTable exists = riderRepository.findRiderByName(rider.getRiderName());

        if (exists == null) throw new EntityExceptions("Rider", "Rider does not exists.");

        return usersMapper.riderTableToRider(
                riderRepository.updateRider(
                        rider.getRiderId(),
                        rider.getRiderName(),
                        rider.getRiderContact(),
                        rider.getRiderCity(),
                        rider.getRiderProvince(),
                        rider.getRiderImage(),
                        rider.getAccountUid()
                )
        );
    }

    public Rider deleteRider(Long riderId) {
        RiderTable exists = riderRepository.findRiderById(riderId);

        if (exists == null) throw new EntityExceptions("Rider", "Rider does not exists.");

        riderRepository.deleteRider(riderId);

        return usersMapper.riderTableToRider(exists);
    }

    public List<Rider> getRidersByArea(String city, String province) {
        return usersMapper.riderTableListToRiderList(
                riderRepository.findByRiderArea(
                        "%" + city + "%",
                        "%" + province + "%"
                )
        );
    }
}
