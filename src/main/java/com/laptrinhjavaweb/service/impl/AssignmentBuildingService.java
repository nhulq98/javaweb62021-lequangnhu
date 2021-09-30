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

	public List<AssignmentBuildingEntity> loadStaffData(Long buildingId, List<Long> idStaffs) {
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
	 *
	 * @param request
	 */
//	@Override
//	@Transactional
//	public void updateAssignment(StaffBuildingRequest request) {
//
//		// B1: get List1 staffs is managing this building from database
//		List<AssignmentBuildingEntity> staffsOfBuildingFromDB = assignmentBuildingRepository
//				.findAllByBuildingId(request.getBuildingId());
//
//		// B2: get List2 staff is sent from front-end
//		List<Long> idStaffsFromRequest = request.getStaffIds();
//		// B2.1: load staff data by id
//		List<AssignmentBuildingEntity> staffsFromRequest = loadStaffData(request.getBuildingId(), idStaffsFromRequest);
//
//		// Step1: find element duplicate and remove it
//		for (int i = 0; i < staffsOfBuildingFromDB.size(); i++) {
//			for (int j = 0; j < staffsFromRequest.size(); j++) {
//				if (staffsOfBuildingFromDB.get(i).getUser().getId()
//						.equals(staffsFromRequest.get(j).getUser().getId())) {
//					staffsOfBuildingFromDB.remove(i);
//					staffsFromRequest.remove(j);
//					i--;
//					break;
//				}
//			}
//		}
//
//		deleteAssignmentStaffs(staffsOfBuildingFromDB);
//
//		saveAssignmentStaffs(staffsFromRequest);
//
//		/*
//		 * Mong muốn cải thiện: chỉ dùng 1 vòng lặp để xử lý bài toán
//		 *
//		 * cùng lúc lặp qua 2 list số nguyên và tìm xóa được
//		 * các tu trùng nhau bỏ 2 ds vào 1 mảng kiểm tra nếu 2ptu liên tiếp == nhau thì
//		 * ta bỏ vào 1 mảng trùng. chạy qua mảng trùng và xóa cùng lúc các phần tử trong
//		 * cả 2 danh sách
//		 */
//	}

	@Override
	public void deleteAssignmentStaffs(List<AssignmentBuildingEntity> entities) {
		for (AssignmentBuildingEntity entity : entities) {
			assignmentBuildingRepository.delete(entity.getId());
		}
	}

	@Override
	public void saveAssignmentStaffs(List<AssignmentBuildingEntity> entities) {
		if (entities.size() > 0) {
			//entities.stream().map(item2 -> assignmentBuildingRepository.save(item2)).;
			for (AssignmentBuildingEntity item : entities) {
				assignmentBuildingRepository.save(item);
			}
		}

	}

    /**
     * Ý tưởng: chia ra thành 2 danh sách:  Old là bị bỏ chọn, new là được chọn mới
     * @param request
     */
    @Override
    @Transactional
    public void updateAssignment(StaffBuildingRequest request) {

		// B1: get staffs Old
		List<AssignmentBuildingEntity> staffsOld = assignmentBuildingRepository
				.findAllByBuildingId(request.getBuildingId());

		// B2: get staffs is checked
		List<Long> idStaffsFromRequest = request.getStaffIds();
		// B2.1: load staffs data from db
		List<AssignmentBuildingEntity> staffsNew = loadStaffData(request.getBuildingId(), idStaffsFromRequest);

		// find element duplicate and remove it
		for (int i = 0; i < staffsOld.size(); i++) {
			for (int j = 0; j < staffsNew.size(); j++) {
				if (staffsOld.get(i).getUser().getId()
						.equals(staffsNew.get(j).getUser().getId())) {
                    staffsOld.remove(i);
                    staffsNew.remove(j);
					i--;
					break;
				}
			}
		}

		// delete list is unchecked
		deleteAssignmentStaffs(staffsOld);

        // save list is checked
		saveAssignmentStaffs(staffsNew);
    }

}
