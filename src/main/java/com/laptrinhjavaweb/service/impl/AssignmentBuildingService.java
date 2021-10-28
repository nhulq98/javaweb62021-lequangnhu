package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<StaffBuildingResponse> findAllStaffs() {
        List<UserEntity> entities = Optional.ofNullable(userRepository.findByStatusAndRoles_Code(1, "STAFF"))
                .orElseThrow(() -> new NotFoundException("Staffs not found!"));

        List<StaffBuildingResponse> result = entities.stream().map(StaffBuildingResponse::new)
                .collect(Collectors.toList());

        return result;
    }

    /**
     * Get All Staff and set status is "checked" if staff is managing building
     *
     * @param buildingId
     * @return all staff available
     */
    @Override
    public List<StaffBuildingResponse> findAllStaffsByBuildingId(Long buildingId) {

        BuildingEntity buildingEntity = Optional.ofNullable(buildingRepository.findOne(buildingId))
                .orElseThrow(() -> new NotFoundException("Building not found!"));

        List<UserEntity> buildingManagementStaffs = buildingEntity.getStaffs();

        return Utils.setCheckedForStaffs(buildingManagementStaffs);
    }

    // For change data

    /**
     * ý tưởng: chia ra thành 2 danh sách:  Old là bị bỏ chọn, new là được chọn mới
     * step1: Tìm những phần tử cùng tồn tại ở cả 2 ds thì bỏ ra.
     * Bởi vì các ptu này không thay đổi(là không bị bỏ tích ở front-end)
     * step2: Còn lại ta sẽ xóa hết all ptu của danh sách staff load từ DB.
     * Bởi vì những ptu trong ds này không có trong ds mà front-end gửi về,
     * có nghĩa là user đã bỏ tích những đối tượng này
     * step3: Thêm all phần tử của ds staff lấy từ request(front-end).
     * Vì những ptu trong ds này không có trong ds load từ DB thì có nghĩa là nó mới
     * được tích vào
     *
     * @param request
     */
    @Override
    @Transactional
    public void updateBuildingManagementStaffs(StaffRequest request) {
        List<Long> listId = request.getStaffIds();
        int size = userRepository.countByIdIn(listId);

        // apply cascade
        BuildingEntity buildingEntity = Optional.ofNullable(buildingRepository.findOne(request.getId()))
                .orElseThrow(() -> new NotFoundException("Building not found!"));

        if(size != listId.size()){
            throw new NotFoundException("staff not found!");
        }

        buildingEntity.setStaffs(userRepository.findByIdIn(listId));

        buildingRepository.save(buildingEntity);
    }
}
