package com.example.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.constant.MonthCode;
import com.example.constant.ResponseCode;
import com.example.pojo.UsersPojo;
import com.example.repository.UserRepository;
import com.example.service.userService;
import com.example.util.ValErrores;
import com.example.util.Month;
import com.example.util.Output;
import com.example.util.Users;

@Service
@Transactional
public class userServiceImpl implements userService {

	public static final HashMap<Integer, Users> map = new HashMap<>();
	public static final HashMap<String, Integer> emailMap = new HashMap<>();
	public static final HashMap<Integer, Users> unactiveUser = new HashMap<>();
	public static final HashMap<String, Users> monthUsers = new HashMap<>();

	public static Integer userId = new Integer(0001);

	@Autowired
	UserRepository userRepository;

	@Override
	public Output addInfoDate(Users ms) {
		List<Object> outValueArray = new ArrayList<>();
		Output out = new Output();
		ValErrores valErrors = new ValErrores();
		String bdate = ms.getBirthDate();
		String regex = "^(([0-9])|([0-2][0-9])|([3][0-1]))\\-(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)\\-\\d{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(bdate);
		if (matcher.matches() == true) {
			boolean dateChek = isBefore(ms.getBirthDate());
			System.out.println(dateChek);
			if (dateChek == true) {
				boolean u = findByEmail(ms.getEmail());
				if (u != true) {
					ms.setStatus(1);
					userRepository.save(ms);
					out.setResMsg(ResponseCode.NEW_USER_CREATED.getDescription());
					out.setUserId(ms.getId().toString());
					valErrors.setCode(ResponseCode.NEW_USER_CREATED.getCode());
					outValueArray.add(valErrors);
					out.setValErrors(outValueArray);
				} else {
					out.setResMsg(ResponseCode.EMIAL_ID_EXIST.getDescription());
					valErrors.setCode(ResponseCode.EMIAL_ID_EXIST.getCode());
					outValueArray.add(valErrors);
					out.setValErrors(outValueArray);
					System.out.println(out);
					return out;
				}
			} else {
				out.setResMsg(ResponseCode.DATE_TIME.getDescription());
				valErrors.setCode(ResponseCode.DATE_TIME.getCode());
				outValueArray.add(valErrors);
				out.setValErrors(outValueArray);
				System.out.println(out);
				return out;
			}
		} else {
			out.setResMsg(ResponseCode.DATE_TIME.getDescription());
			valErrors.setCode(ResponseCode.DATE_TIME.getCode());
			outValueArray.add(valErrors);
			out.setValErrors(outValueArray);
			System.out.println(out);
			return out;
		}
		return out;
	}

	private boolean isBefore(String birthDate) {
		DateFormat parser = new SimpleDateFormat("dd-MMM-yyyy");
		Date convertedDate;
		try {
			convertedDate = parser.parse(birthDate);
			System.out.println(convertedDate);

			System.out.println(convertedDate.after(new Date()));
			if (convertedDate.before(new Date()) || convertedDate.equals(new Date())) {
				return true;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Output updateInfoData(Users user) {
		List<Object> outValueArray = new ArrayList<>();
		Output out = new Output();
		ValErrores valErrors = new ValErrores();
		System.out.println(user.getId());
		long findId = userRepository.chekId(user.getId());
		System.out.println(findId + "amit");
		if (findId != 0) {
			Users users = userRepository.findById((user.getId()));
			users.setBirthDate(user.getBirthDate());
			users.setPinCode(user.getPinCode());
			userRepository.save(users);
			out.setResMsg(ResponseCode.USER_UPDATED.getDescription());
			out.setUserId(user.getId().toString());
			valErrors.setCode(ResponseCode.USER_UPDATED.getCode());
			outValueArray.add(valErrors);
			out.setValErrors(outValueArray);
			return out;
		}
		out.setResMsg(ResponseCode.USER_DOESNT_EXIST.getDescription());
		valErrors.setCode(ResponseCode.USER_DOESNT_EXIST.getCode());
		outValueArray.add(valErrors);
		out.setValErrors(outValueArray);
		return out;
	}

	@Override
	public Output delete(Users user) {
		List<Object> outValueArray = new ArrayList<>();
		Output out = new Output();
		ValErrores valErrors = new ValErrores();

		long findId = userRepository.chekId(user.getId());
		System.out.println(findId + "amit");
		if (findId != 0) {
			{
				Users users = userRepository.findById((user.getId()));
				users.setStatus(0);
				userRepository.save(users);
				out.setResMsg(ResponseCode.USER_DEACTIVE.getDescription());
				out.setUserId(user.getId().toString());
				valErrors.setCode(ResponseCode.USER_DEACTIVE.getCode());
				outValueArray.add(valErrors);
				out.setValErrors(outValueArray);
				return out;
			}

		} else {
			System.out.println(findId);
			out.setResMsg(ResponseCode.USER_DOESNT_EXIST.getDescription());
			valErrors.setCode(ResponseCode.USER_DOESNT_EXIST.getCode());
			outValueArray.add(valErrors);
			out.setValErrors(outValueArray);
			return out;
		}

	}

	@Override
	public List<UsersPojo> getAllUser() {
		List<Users> u = new ArrayList<>();
		u = (List<Users>) userRepository.findAllActiveUsers();
		List<UsersPojo> list = new ArrayList<>();
		for (Users user : u) {
			list.add(new UsersPojo(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthDate(),
					user.getEmail(), user.getPinCode()));
		}
		System.out.println(u);
		return list;
	}

	@Override
	public List<UsersPojo> getAllDeactiveUser() {
		List<Users> u = new ArrayList<>();
		u = (List<Users>) userRepository.findAlldeActiveUsers();
		List<UsersPojo> list = new ArrayList<>();
		for (Users user : u) {
			list.add(new UsersPojo(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthDate(),
					user.getEmail(), user.getPinCode()));
		}
		System.out.println(u);
		return list;
	}

	@Override
	public List<UsersPojo> currentMonthBday(String month) {
		List<Users> u = new ArrayList<>();
		u = (List<Users>) userRepository.currentmonthBdayUserList(month);
		List<UsersPojo> list = new ArrayList<>();
		for (Users user : u) {
			System.out.println(user.getId() + "" + user.getFirstName() + user.getLastName() + user.getBirthDate()
					+ user.getEmail() + user.getPinCode());
			list.add(new UsersPojo(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthDate(),
					user.getEmail(), user.getPinCode()));
		}
		return list;
	}

	@Override
	public long currentMonthBdayCount(String month) {
		long count = userRepository.countByBday(month);// TODO Auto-generated method stub
		return count;
	}
	/*
	 * @Override public HashMap<Integer, Users> getInfoData() {
	 * 
	 * System.err.println("MAP: "+map); return map; }
	 * 
	 * 
	 * @Override public String updateInfoData(Users ms) {
	 * if(map.containsKey(ms.getId())) { Integer id=ms.getId();
	 * map.remove(ms.getId()); ms.setId(id); map.put(id, ms); return "user updated";
	 * 
	 * }else {return "No userid present";}
	 * 
	 * }
	 * 
	 * 
	 * @Override public Output delete(Users ms) { Output out= new Output();
	 * ValErrores valErrors = new ValErrores(); List<Object> outValueArray = new
	 * ArrayList<>();
	 * 
	 * if(map.containsKey(ms.getId())) { Integer id=ms.getId();
	 * map.remove(ms.getId()); ms.setId(id); unactiveUser.put(id, ms);
	 * out.setResMsg(ResponseCode.USER_DEACTIVE.getDescription());
	 * out.setUserId(userId.toString());
	 * 
	 * valErrors.setCode(ResponseCode.USER_DEACTIVE.getCode());
	 * outValueArray.add(valErrors); out.setValErrors(outValueArray); return out;
	 * 
	 * } else { out.setResMsg(ResponseCode.USER_DOESNT_EXIST.getDescription());
	 * out.setUserId(userId.toString());
	 * valErrors.setCode(ResponseCode.USER_DOESNT_EXIST.getCode());
	 * outValueArray.add(valErrors); out.setValErrors(outValueArray); return out; }
	 * 
	 * }
	 * 
	 * @Override public HashMap<String,Users> getMonthDate(Month mo) { Output out=
	 * new Output(); ValErrores valErrors = new ValErrores(); List<Object>
	 * outValueArray = new ArrayList<>(); boolean
	 * b=Stream.of(MonthCode.values()).anyMatch(e ->
	 * e.getMonth().equals(mo.getMonth())); //out.setValErrors(monthUsers);
	 * 
	 * 
	 * return monthUsers; }
	 * 
	 * @Override public HashMap<Object, Users> getList() { Calendar cal =
	 * Calendar.getInstance(); String month = new
	 * SimpleDateFormat("MMM").format(cal.getTime());
	 * System.out.println(month.toLowerCase()); Users list =
	 * monthUsers.get(month.toLowerCase()); HashMap<Object, Users> a=new
	 * HashMap<Object, Users>(); a.put(list.getId(), list); return a; } public
	 * boolean chekMonthDateYear(String date) { return false;
	 * 
	 * }
	 */

	public boolean findByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

}
