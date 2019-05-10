package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.constant.MonthCode;
import com.example.pojo.UsersPojo;
import com.example.service.userService;
import com.example.util.Output;
import com.example.util.Users;

@RestController
public class UserController {
	@Autowired
	private userService userService;
/*	@Autowired
	UserRepository userRepository;*/
	 
	@PostMapping("/dataPost")
	public Output addPostData(@Validated @RequestBody Users input) {
        System.out.println("save data");
		Output out = userService.addInfoDate(input);
		// return ResponseEntity.ok(out);*/
		System.err.println(out);
		  return out;
		
	}
	@PostMapping("/updatePost")
	@ResponseBody
	public Output updatePostData(@RequestBody Users input) {
		System.out.println(input.getId());
		Output out = userService.updateInfoData(input);
		return out;
		
	}
	@PostMapping("/deleteId")
	@ResponseBody
	public Output deletePostData(@RequestBody Users input) {
		Output out = userService.delete(input);
		return out;
	}
	@GetMapping("/getData")
	public List<UsersPojo> getAllActiveUser() {
		List<UsersPojo> out = userService.getAllUser();
		return out;
		
	}
	@GetMapping("/getAllDeactiveUser")
	public List<UsersPojo> getDeactiveUser() {
		List<UsersPojo> out = userService.getAllDeactiveUser();
		return out;
		
	}
	@GetMapping("/currentMonthBdayUser")
	public List<UsersPojo> currentMonthBdayUser() {
		Calendar cal = Calendar.getInstance();
		System.out.println(new SimpleDateFormat("MMM").format(cal.getTime()));
		String month=new SimpleDateFormat("MMM").format(cal.getTime());
		List<UsersPojo> out = userService.currentMonthBday(month.toLowerCase());
		return out;
		
	}
	@GetMapping("/contMonthBdayUser")
	public Map<String ,Long> countMonthBdayUser() {
		Map<String ,Long> map=new HashMap<>();
		long out1 = userService.currentMonthBdayCount(MonthCode.January.month);
		long out2 = userService.currentMonthBdayCount(MonthCode.February.month);
		long out3 = userService.currentMonthBdayCount(MonthCode.March.month);
		long out4 = userService.currentMonthBdayCount(MonthCode.April.month);
		long out5 = userService.currentMonthBdayCount(MonthCode.May.month);
		long out6 = userService.currentMonthBdayCount(MonthCode.June.month);
		long out7 = userService.currentMonthBdayCount(MonthCode.July.month);
		long out8 = userService.currentMonthBdayCount(MonthCode.August.month);
		long out9 = userService.currentMonthBdayCount(MonthCode.September.month);
		long out10 = userService.currentMonthBdayCount(MonthCode.October.month);
		long out11 = userService.currentMonthBdayCount(MonthCode.November.month);
		long out12 = userService.currentMonthBdayCount(MonthCode.December.month);
		map.put("January" ,out1);
		map.put("February" ,out2);
		map.put("March" ,out3);
		map.put("April" ,out4);
		map.put("May" ,out5);
		map.put("June" ,out6);
		map.put("July" ,out7);
		map.put("August" ,out8);
		map.put("September" ,out9);
		map.put("October" ,out10);
		map.put("November" ,out11);
		map.put("December" ,out12);
		return map;
		
	}
	/*@PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }*/
/*	@GetMapping("/getData")
	public ResponseEntity<?> getPostData() {
		
		HashMap<Integer, Users> mer= userService.getInfoData();
		return ResponseEntity.ok(mer);
		
	}
	@PostMapping("/updatePost")
	@ResponseBody
	public ResponseEntity<?> updatePostData(@RequestBody Users input) {

		String out = userService.updateInfoData(input);
		
		return ResponseEntity.ok(out);
		
	}
	@PostMapping("/deleteId")
	@ResponseBody
	public ResponseEntity<?> deletePostData(@RequestBody Users input) {

		Output out = userService.delete(input);
		
		return ResponseEntity.ok(out);
		
	}
	@GetMapping("/getMonth")
	public ResponseEntity<?> getMonthData(@RequestBody Month input) {
		
		HashMap<String,Users> mer= userService.getMonthDate(input);
		return ResponseEntity.ok(mer);
		
	}
	@GetMapping("/getCurrentMonthBdayUserList")
	public ResponseEntity<?> getCurrentMonthBdayUserList() {
		
		HashMap<Object,Users> mer= userService.getList();
		return ResponseEntity.ok(mer);
		
	}*/
}
