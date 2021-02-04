package com.laptrinhjavaweb.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;

@RestController
@RequestMapping("/api/demo")
public class DemoAPI {
	
	@GetMapping
	public List<UserDTO> getStaff() {
		
		List<UserDTO> result = new ArrayList<>();
		
		UserDTO user = new UserDTO();
		user.setFullName("le quang nhu");
		user.setChecked("checked");
		result.add(user);
		
		UserDTO user2 = new UserDTO();
		user2.setFullName("le quang tien");
		user2.setChecked("checked");
		result.add(user2);
		UserDTO user3 = new UserDTO();
		user3.setFullName("le quang phuc");
		user3.setChecked("");
		result.add(user3);
//		System.out.println(result);
//		System.out.println(result.toArray());
//		System.out.println(result.toString());

		// call database and query data(list user)
		
		// assignment data into result
		
		
		return result;
	}
}
