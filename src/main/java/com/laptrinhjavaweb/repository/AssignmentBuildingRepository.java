package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
//    @Query(value = "SELECT US.id, US.fullname, US.createdby, US.createddate, US.modifiedby, US.modifieddate" +
//            ",(case WHEN USR.userid in(select SB.staffid FROM staff_building SB WHERE SB.buildingid = ?1" +
//            ") THEN 'checked' ELSE 'NULL' END) AS checked " +
//            " FROM user US, user_role USR WHERE US.id = USR.userid AND USR.roleid = 2 ", nativeQuery = true)
//    List<StaffEntity> findAllCustom(Long buildingId);
}
