package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.AssignmentBuildingConverter;
import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.entity.view.StaffEntity;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
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
    private UserRepository userRepository;

    @Autowired
    private AssignmentBuildingConverter converter;

    public List<StaffBuildingResponse> findAllStaff() {
        List<UserEntity> entities = userRepository.getStaffs();
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
    /*
     * @Override public List<StaffBuildingResponse> getStaffsAssignment(Long
     * buildingId) { List<StaffBuildingResponse> staffsAll = findAllStaff();
     * List<AssignmentBuildingEntity> staffsOfBuilding =
     * assignmentBuildingRepository.findAllByBuildingId(buildingId);
     *
     * List<StaffBuildingResponse> result = new ArrayList<>();
     *
     * //convert to staffResponse for (int i = 0; i < staffsAll.size(); i++) {
     * result.add(staffsAll.get(i)); for (AssignmentBuildingEntity staff :
     * staffsOfBuilding) { if (staffsAll.get(i).getId() == staff.getUser().getId())
     * { result.get(i).setChecked("checked"); staffsOfBuilding.remove(staff);// to
     * reduce loop numbers break;// exit for outside loop } } }
     *
     * return result; }
     */

    /**
     * Get All Staff and set status is "checked" if staff is managing building
     *
     * @param buildingId
     * @return all staff available
     */
    @Override
    public List<StaffBuildingResponse> getStaffsAssignment(Long buildingId) {
        List<StaffEntity> staffsAll = assignmentBuildingRepository.findAllCustom(buildingId);
        List<StaffBuildingResponse> result = staffsAll.stream()
                .map(StaffBuildingResponse::new).collect(Collectors.toList());

        return result;
    }

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
     * ý tưởng:
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
    public void updateAssignment(StaffBuildingRequest request) {
        List<AssignmentBuildingEntity> staffsOld = assignmentBuildingRepository
                .findByBuildingId(request.getBuildingId());
        List<Long> staffsIdChecked = request.getStaffIds();
        List<AssignmentBuildingEntity> staffsFromRequest = createStaffs(request.getBuildingId(), staffsIdChecked);
        if(staffsOld.size() == 0 &&(staffsIdChecked.size() == 0 || staffsIdChecked == null)) { return;}
        if(staffsOld.size() == 0 && staffsIdChecked.size() != 0){assignmentBuildingRepository.save(staffsFromRequest); return;}
        if(staffsOld.size() != 0 && staffsIdChecked.size() == 0){assignmentBuildingRepository.delete(staffsOld); return;}
        for (int i = 0; i < staffsOld.size(); i++) {
            for (int j = 0; j < staffsFromRequest.size(); j++) {
                if (staffsOld.get(i).getUser().getId()
                        .equals(staffsFromRequest.get(j).getUser().getId())) {
                    staffsOld.remove(i);
                    staffsFromRequest.remove(j);
                    i--;
                    break;
                }
            }
        }
        assignmentBuildingRepository.delete(staffsOld);
        assignmentBuildingRepository.save(staffsFromRequest);
    }

    /**
     * Ý tưởng: chia ra thành 2 danh sách:  Old là bị bỏ chọn, new là được chọn mới
     * @param request
     */
//    @Override
//    @Transactional
//    public void updateAssignment(StaffBuildingRequest request) {
//
//		// Step 1: get staffs Old
//		List<AssignmentBuildingEntity> staffsOld = assignmentBuildingRepository
//				.findAllByBuildingId(request.getBuildingId());
//
//        // Step 2: get staffs is checked
//        List<Long> staffsIdChecked = request.getStaffIds();
//
//		//Step 2: case 1: unchecked all
//		if(staffsIdChecked == null || staffsIdChecked.size() == 0){
//            if(staffsOld.size() == 0){return;}// do nothing
//
//		    //remove all list staffs Old
//            assignmentBuildingRepository.delete(staffsOld);
//            return;
//
//		}else{//case 2: not unchecked all
//
//            // B2.1: load staffs data from db
//            List<AssignmentBuildingEntity> staffsNew = loadStaffData(request.getBuildingId(), staffsIdChecked);
//            if(staffsOld.size() == 0){
//                //save all staffs checked
//                assignmentBuildingRepository.save(staffsNew);
//                return;
//            }else{// old and new list size != 0
//                // find element duplicate and remove it
//                for (int i = 0; i < staffsOld.size(); i++) {
//                    for (int j = 0; j < staffsNew.size(); j++) {
//                        if (staffsOld.get(i).getUser().getId()
//                                .equals(staffsNew.get(j).getUser().getId())) {
//                            staffsOld.remove(i);
//                            staffsNew.remove(j);
//                            i--;
//                            break;
//                        }
//                    }
//                }
//                // delete list is unchecked
//                assignmentBuildingRepository.delete(staffsOld);
//
//                // save list is checked
//                assignmentBuildingRepository.save(staffsNew);
//            }
//        }
//
//    }

}
