package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.StaffBuildingResponseConverter;
import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.jdbc.impl.AssignmentBuildingJDBCImpl;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingJDBCImpl;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {

    @Autowired
    private StaffBuildingResponseConverter staffBuildingResponseConverter;

    private AssignmentBuildingJDBCImpl assignmentBuildingJDBC = new AssignmentBuildingJDBCImpl();

    @Override
    public List<StaffBuildingResponse> getAllStaffs() {
        List<StaffBuildingResponse> result = new ArrayList<>();
        List<UserEntity> entities = assignmentBuildingJDBC.getAllStaffs();

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
        List<UserEntity> entities = assignmentBuildingJDBC.findStaffsByBuildingId(buildingId);
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
        /* ý tưởng:
         * step1: Tìm những phần tử cùng tồn tại ở cả 2 ds thì xóa ra. Bởi vì các ptu này không thay đổi(là không bị bỏ tích ở front-end)
         * step2: Còn lại ta sẽ xóa hết all ptu của danh sách staff load từ DB. Bởi vì những ptu trong ds này không có trong ds mà front-end gửi về, có nghĩa là user đã bỏ tích những đối tượng này
         * step3: Thêm all phần tử của ds staff lấy từ request(front-end). Vì những ptu trong ds này không có trong ds load từ DB thì có nghĩa là nó mới được tích vào
         */

        //B1: lấy ra ds1 những staff đang ql tòa nhà này từ database
        List<Long> staffsOfBuildingFromDB = getIdStaffsByBuildingId(request.getBuildingId());
        //B2: lấy ds2 nhân viên được gửi từ front-end
        List<Long> staffsFromRequest = request.getStaffIds();

        // Step1: find element duplicate
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
        //Step 2:
        deleteAssignmentStaffs(request.getBuildingId(), staffsOfBuildingFromDB);
        //Step 3:
        saveAssignmentStaffs(request.getBuildingId(), staffsFromRequest);

        // Mong muốn cải thiện: dùng 1 vòng lặp lặp qua 2 list số nguyên và tìm xóa được các tu trùng nhau
        // bỏ 2 ds vào 1 mảng
        // kiểm tra nếu 2ptu liên tiếp == nhau thì ta bỏ vào 1 mảng trùng.
        // chạy qua mảng trùng và xóa cùng lúc các phần tử trong cả 2 danh sách
    }

    @Override
    @Transactional
    public void deleteAssignmentStaffs(Long buildingId, List<Long> ids) {
        for(Long id: ids){
            assignmentBuildingJDBC.deleteStaffOfBuildingById(buildingId, id);
        }
    }

    @Override
    @Transactional
    public void saveAssignmentStaffs(Long buildingId, List<Long> ids) {
        for(Long id: ids){
            assignmentBuildingJDBC.insertStaffOfBuildingById(buildingId, id);
        }
    }

}
