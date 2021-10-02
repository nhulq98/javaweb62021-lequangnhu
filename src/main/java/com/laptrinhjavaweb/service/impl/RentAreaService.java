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

        List<RentAreaEntity> rentAreasOld = rentAreaRepository.findAllByBuildingId(newBuilding.getId());
        List<RentAreaEntity> rentAreaFromView = loadRentAreaFromRequest(newBuilding.getRentAreas(), newBuilding);

        if(rentAreasOld.size() == 0 &&(rentAreaFromView.size() == 0 || rentAreaFromView == null)) { return;}
        if(rentAreasOld.size() == 0 && rentAreaFromView.size() != 0){rentAreaRepository.save(rentAreaFromView); return;}
        if(rentAreasOld.size() != 0 && rentAreaFromView.size() == 0){rentAreaRepository.delete(rentAreasOld);; return;}

        for (int i = 0; i < rentAreasOld.size(); i++) {
            for (int j = 0; j < rentAreaFromView.size(); j++) {
                if (rentAreasOld.get(i).getValue()
                        == rentAreaFromView.get(j).getValue()) {
                    rentAreasOld.remove(i);
                    rentAreaFromView.remove(j);
                    i--;
                    break;
                }
            }
        }
        rentAreaRepository.delete(rentAreasOld);
		rentAreaRepository.save(rentAreaFromView);
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
