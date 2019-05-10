package com.example.service;

import java.util.List;

import com.example.pojo.UsersPojo;
import com.example.util.Output;
import com.example.util.Users;

public interface userService {
	public Output addInfoDate(Users user);
	public Output updateInfoData(Users user);
	public Output delete(Users ms);
	/*public Output addInfoDate(Users ms);
	public HashMap<Integer, Users> getInfoData() ;
	String updateInfoData(Users ms);
	public Output delete(Users ms);
	public HashMap<String,Users> getMonthDate(Month mo);
	public HashMap<Object, Users> getList();*/
	public List<UsersPojo> getAllUser();
	public List<UsersPojo> currentMonthBday(String lowerCase);
	List<UsersPojo> getAllDeactiveUser();
	public long currentMonthBdayCount(String lowerCase);
	
   /*public List<User> getUser();
    public User findById(long id);
    public User update(User user, long l);
    public void deleteUserById(long id);
    public User updatePartially(User user, long id);*/
}
