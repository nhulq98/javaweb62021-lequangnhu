package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.entity.view.StaffEntity;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<StaffBuildingResponse> findAllStaff() {
        List<UserEntity> entities = userRepository.getAllStaffs();
        List<StaffBuildingResponse> result = new ArrayList<>();

        for (UserEntity item : entities) {
            StaffBuildingResponse response = new StaffBuildingResponse();
            response.setId(item.getId());
            response.setFullName(item.getFullName());
            result.add(response);
        }
        return result;
    }

    /**
     * Get All Staff and set status is "checked" if staff is managing building
     *
     * @param buildingId
     * @return all staff available
     */
    @Override
    public List<StaffBuildingResponse> getStaffsAssignment(Long buildingId) {
        try{
            List<StaffEntity> staffsAll = assignmentBuildingRepository.findAllCustom(buildingId);
            if(staffsAll != null && staffsAll.size() > 0){
                List<StaffBuildingResponse> result = staffsAll.stream()
                        .map(StaffBuildingResponse::new).collect(Collectors.toList());
                return result;
            }

            return new ArrayList<>();

        }catch (RuntimeException e){
            e.printStackTrace();
            return new ArrayList<> ();
        }
    }

    @Override
    public List<AssignmentBuildingEntity> createStaffs(Long buildingId, List<Long> idStaffs) {
        List<AssignmentBuildingEntity> result = new LinkedList<>();
        for (Long item : idStaffs) {
            AssignmentBuildingEntity entity = new AssignmentBuildingEntity();
            UserEntity userEntity = new UserEntity();
            BuildingEntity buildingEntity = new BuildingEntity();

            userEntity.setId(item);
            buildingEntity.setId(buildingId);

            entity.setUser(userEntity);
            entity.setBuilding(buildingEntity);

            result.add(entity);
        }

        return result;
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
    public void updateAssignment(StaffRequest request) {
        // apply cascade
        BuildingEntity buildingEntity = buildingRepository.findOne(request.getId());
        buildingEntity.setStaffs(userRepository.findByIdIn(request.getStaffIds()));

        buildingRepository.save(buildingEntity);
    }

    /*@Override
    @Transactional
    public void updateAssignment(StaffRequest request) {
        List<Long> staffsIdChecked = request.getStaffIds();
        List<AssignmentBuildingEntity> staffsFromRequest = createStaffs(request.getBuildingId(), staffsIdChecked);
        List<AssignmentBuildingEntity> staffsOld = assignmentBuildingRepository
                .findByBuildingId(request.getBuildingId());
        if(testSpecialCases(staffsFromRequest, staffsOld)){return;}

        //another cases
        removeDuplicate(staffsOld, staffsFromRequest);
        assignmentBuildingRepository.delete(staffsOld);
        assignmentBuildingRepository.save(staffsFromRequest);

        //Utils.destroyReference(staffsOld, staffsIdChecked, staffsFromRequest);
    }*/

    /*@Override
    public void removeDuplicate(List<AssignmentBuildingEntity> staffsOld, List<AssignmentBuildingEntity> staffsNew){
        int lenghtNew = staffsNew.size();
        int lenghtOld = staffsOld.size();
        //==============solution 1: iterator numbers: 2n==================
        Map<Long, Object> map = new HashMap<>();
        //loop through list time: O(n)
        for(AssignmentBuildingEntity a: staffsOld){
            map.put(a.getId(), a);
        }
        //iterator numbers: O(n)
        for (int i = 0; i < lenghtNew; i++) {
            if (map.get(staffsNew.get(i).getId()) != null) {
                map.remove(staffsNew.get(i).getId());
                staffsNew.remove(i);

                lenghtNew--;
            }
        }
        // Solution 1: Total: O(n + n).
        // VD: n = 9 ==> 18 iterator numbers


        // ======Solution 2: iterator numbers: O(n^2).=============
*//*        for (int i = 0; i < lenghtOld; i++) {
            for (int j = 0; j < lenghtNew; j++) {
                if (staffsOld.get(i).getUser().getId() == staffsNew.get(j).getUser().getId()) {
                    staffsOld.remove(i);
                    staffsNew.remove(j);

                    i--, lenghtOld--, lenghtNew--;
                    break;
                }
            }
        }*//*
        // Total = O(n^2) ==> VD: n = 9 ==> 82 iterator numbers
    }*/

    /**
     * those cases which we can guess. If return True ==> done process
     * @param rentAreaFromView
     * @param rentAreasOld
     * @return
     */
    @Override
    public boolean testSpecialCases(List<AssignmentBuildingEntity> rentAreaFromView, List<AssignmentBuildingEntity> rentAreasOld){
        if(rentAreasOld.size() == 0 &&(rentAreaFromView.size() == 0 || rentAreaFromView == null)) { return true;}
        if(rentAreasOld.size() == 0 && rentAreaFromView.size() != 0){assignmentBuildingRepository.save(rentAreaFromView); return true;}
        if(rentAreasOld.size() != 0 && rentAreaFromView.size() == 0){assignmentBuildingRepository.delete(rentAreasOld);; return true;}
        return false;
    }
}
