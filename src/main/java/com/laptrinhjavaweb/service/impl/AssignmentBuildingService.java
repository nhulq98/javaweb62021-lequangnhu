package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.StaffBuildingResponseConverter;
import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.jdbc.impl.AssignmentBuildingJDBCImpl;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingJDBCImpl;
import com.laptrinhjavaweb.repository.jdbc.impl.UserJDBCImpl;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffBuildingResponseConverter staffBuildingResponseConverter;

    private AssignmentBuildingJDBCImpl assignmentBuildingJDBC = new AssignmentBuildingJDBCImpl();

    @Override
    public List<StaffBuildingResponse> getAllStaffs() {
        List<StaffBuildingResponse> result = new ArrayList<>();
        UserJDBCImpl userJDBC = new UserJDBCImpl();
        List<UserEntity> entities = userJDBC.getStaffs();

        for(UserEntity userEntity: entities){
            StaffBuildingResponse staffBuildingResponse = staffBuildingResponseConverter.convertEntityToResponse(userEntity);
            result.add(staffBuildingResponse);
        }
        return result;
    }

    @Override
    public List<Long> getIdStaffsByBuildingId(Long buildingId) {
        List<Long> result = new LinkedList<>();
        BuildingJDBCImpl buildingJDBC = new BuildingJDBCImpl();
        List<StaffBuildingResponse> staffs = getStaffsOfBuildingById(buildingId);
        for (StaffBuildingResponse response : staffs) {
            result.add(response.getId());
        }
        return result;
    }

    @Override
    public List<StaffBuildingResponse> getStaffsOfBuildingById(Long buildingId) {
        // call db and get data
        List<StaffBuildingResponse> result = new ArrayList<>();
        List<UserEntity> entities = assignmentBuildingJDBC.findStaffById(buildingId);
        for(UserEntity userEntity: entities){
            StaffBuildingResponse staffBuildingResponse = staffBuildingResponseConverter.convertEntityToResponse(userEntity);
//            staffBuildingResponse.setChecked("checked");
            result.add(staffBuildingResponse);
        }
        return result;
    }

    @Override
    public List<StaffBuildingResponse> getStaffsAssignment(Long buildingId) {
        List<StaffBuildingResponse> staffAll = getAllStaffs();
        List<StaffBuildingResponse> staffsOfBuilding = getStaffsOfBuildingById(buildingId);

        for(int i = 0; i < staffAll.size(); i++){
            for(StaffBuildingResponse staff: staffsOfBuilding){
                if(staffAll.get(i).getId() == staff.getId()){
                    staffAll.get(i).setChecked("checked");
                    staffsOfBuilding.remove(staff);// to reduce loop numbers
                    break;// exit for outside loop
                }
            }
        }

        return staffAll;
    }

    //For change data
    @Override
    @Transactional
    public void updateAssignment(StaffBuildingRequest request) {
        //B1: lấy ra ds1 những staff đang ql tòa nhà này từ database
        List<Long> staffsOfBuildingFromDB = getIdStaffsByBuildingId(request.getBuildingId());
        //B2: lấy ds2 nhân viên được gửi từ front-end
        List<Long> staffsFromRequest = request.getStaffIds();
        System.out.println("Building current Staffs : "+staffsOfBuildingFromDB);
        System.out.println("Staffs want update : "+staffsFromRequest);
//        //solution 1: find element duplicate
//        Long[] idsRemove = new Long[staffsAllFromDB.size() + staffsFromRequest.size()];
//        int i = 0;
//        for(Long staffDB: staffsAllFromDB){
//            for(Long staffRequest: staffsFromRequest){
//                if(staffDB.equals(staffRequest)){
//                    idsRemove[i] = staffDB;
////                    staffsAllFromDB.remove(staffDB);
////                    staffsFromRequest.remove(staffRequest);
////                    staffsAllFromDB.p
//                    break;
//                }
//            }
//        }
//        //remove element duplicate
//        for(int k = 0; k < idsRemove.length; i++){
//            staffsAllFromDB.remove(idsRemove[k]);
//            staffsFromRequest.remove(idsRemove[k]);
//        }

        //solution 2:
        for (int i = 0; i < staffsOfBuildingFromDB.size(); i++)
        {
            for (int j = 0; j < staffsFromRequest.size(); j++)
            {
                if (staffsOfBuildingFromDB.get(i).equals(staffsFromRequest.get(j)))
                {
                    staffsOfBuildingFromDB.remove(i);
                    staffsFromRequest.remove(j);
                    i--;
                    break;
                }
            }
        }
        //Mục đích: dùng 1 vòng lặp lặp qua 2 list số nguyên và tìm xóa được các tu trùng nhau
        //Step 1: bỏ 2 ds vào 1 mảng
//        Long[] ids = new Long[staffsAllFromDB.size() + staffsFromRequest.size()];
//        for(int i = 0; i < staffsAllFromDB.size() + staffsFromRequest.size(); i++){
//            if(i < staffsAllFromDB.size()){
//                ids[i] = staffsAllFromDB.get(i);
//            }
//            if(i < staffsFromRequest.size()){
//                ids[i] = staffsFromRequest.get(i);
//            }
//        }
//        Long[] elemetDuplicate = new Long[staffsAllFromDB.size() + staffsFromRequest.size()];
//        //find duplicate element
//        int k = 0;
//        for(int i = 0; i < ids.length - 1; i++){
//            for(int j = i + 1; j < ids.length; j++){
//                if(ids[i] == ids[j]){
//                    //
//                    elemetDuplicate[k] = ids[i];
//                    //remove element duplicate to reduce loop numbers
//
//                    //ids[]
//                    // remove 2 list
//                    staffsAllFromDB.remove()
//                }
//            }
//        }

        //Step 2: tìm
        //kiểm tra nếu 2ptu liên tiếp == nhau thì ta bỏ vào 1 mảng trùng.
        //chạy qua mảng trùng và xóa cùng lúc các phần tử trong cả 2 danh sách
//        HashMap<Long, Long> hashMap = new HashMap<>();
//        hashMap.

        /* ý tưởng:
         * là tìm những phần tử cùng tồn tại ở cả 2 ds thì xóa ra. Bởi vì các ptu này không thay đổi(là bị bỏ tích)
         * còn lại ta sẽ xóa hết all ptu của danh sách staff load từ DB. Bởi vì những ptu trong ds này không có trong ds mà front-end gửi về, có nghĩa là user đã bỏ tích những đối tượng này
         * và thêm all phần tử của ds staff lấy từ request(front-end). Vì những ptu trong ds này không có trong ds load từ DB thì có nghĩa là nó mới được tích vào
         */
        //step 1:
//        for(Long staff: staffsAllFromDB){
//            for(Long staffUpdate: staffsFromRequest){
//                //B1: Tìm những phần tử giữ nguyên và xóa ra khỏi mảng
//                if(staff == staffUpdate){ // tồn tại ở 2 ds ==> giữ nguyên
//                    staffsAllFromDB.remove(staff);
//                    staffsFromRequest.remove(staffUpdate);
//                    break;
//                }
//            }
//        }
        //Step 2:
        deleteAssignmentStaffs(request.getBuildingId(), staffsOfBuildingFromDB);
        //Step 3:
        saveAssignmentStaffs(request.getBuildingId(), staffsFromRequest);
    }

    @Override
    @Transactional
    public void deleteAssignmentStaffs(Long buildingId, List<Long> ids) {
        for(Long id: ids){
            assignmentBuildingJDBC.deleteStaffById(buildingId, id);
        }
    }

    @Override
    @Transactional
    public void saveAssignmentStaffs(Long buildingId, List<Long> ids) {
        for(Long id: ids){
            assignmentBuildingJDBC.insertStaffById(buildingId, id);
        }
    }

}
