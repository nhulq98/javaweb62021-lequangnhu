package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.service.IRentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class RentAreaService implements IRentAreaService {

    @Autowired
    RentAreaRepository rentAreaRepository;

    @Autowired
    BuildingConverter buildingConverter;

    /**
     * ý tưởng: step1: Tìm những phần tử cùng tồn tại ở cả 2 ds thì xóa ra. Bởi vì
     * các ptu này không thay đổi
     * step2: Còn lại ta sẽ xóa hết all ptu của danh sách rentArea load từ DB. Bởi vì những ptu trong ds
     * này không có trong ds mà front-end gửi về, có nghĩa là user đã xóa những
     * đối tượng này
     * step3: Thêm all phần tử của ds rentArea lấy từ request(front-end).
     * Vì những ptu trong ds này không có trong ds load từ DB thì có nghĩa là nó mới
     * được tích vào
     *
     * @param newBuilding
     */
    @Override
    public void updateRentArea(BuildingDTO newBuilding) {

        // B1: get List1 rentAreaEntity of this building from database
        List<RentAreaEntity> rentAreaOfBuildingFromDB = rentAreaRepository.findAllByBuildingId(newBuilding.getId());

        // B2: get List2 rentAreaEntity is sent from front-end
        List<RentAreaEntity> rentAreaFromRequest = loadRentAreaFromRequest(newBuilding.getRentAreas(), newBuilding);

        // Step1: find element duplicate and remove it
		for (int i = 0; i < rentAreaOfBuildingFromDB.size(); i++) {
            for (int j = 0; j < rentAreaFromRequest.size(); j++) {
                if (rentAreaOfBuildingFromDB.get(i).getValue()
                        == rentAreaFromRequest.get(j).getValue()) {
                    rentAreaOfBuildingFromDB.remove(i);
                    rentAreaFromRequest.remove(j);
                    i--;
                    break;
                }
            }
        }

        // step 2,3:
		deleteRentAreas(rentAreaOfBuildingFromDB);
		saveRentAreas(rentAreaFromRequest);
    }

    @Override
    public void deleteRentAreas(List<RentAreaEntity> rentAreaEntities){
        for(RentAreaEntity item: rentAreaEntities){
            rentAreaRepository.delete(item.getId());
        }
    }

    @Override
    public void saveRentAreas (List<RentAreaEntity> rentAreaEntities){
        for(RentAreaEntity item: rentAreaEntities){
            rentAreaRepository.save(item);
        }
    }

    @Override
    public List<RentAreaEntity> loadRentAreaFromRequest(String rentAreas, BuildingDTO newBuilding) {
        List<RentAreaEntity> rentAreaFromRequest = new LinkedList<>();
        String[] rentAreaStrs = rentAreas.split(",");
        String regex = "[0-9]+";
        for (String item : rentAreaStrs) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            if(item.trim().matches(regex)){// is number
                rentAreaEntity.setValue(Integer.parseInt(item.trim()));
                rentAreaEntity.setBuilding(buildingConverter.convertDTOToEntity(newBuilding));

                rentAreaFromRequest.add(rentAreaEntity);
            }
        }
        return rentAreaFromRequest;
    }
}
