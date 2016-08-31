package com.example.android_contacts4;

public class UsersInformation
{

	private String userName;
	private String phoneNum;
	private String email;
	private String py;

	public UsersInformation()
	{
	}

	public UsersInformation(String userName , String phoneNum , String email , String py)
	{
		super();
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.py = py;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName )
	{
		this.userName = userName;
	}

	public String getPhoneNum()
	{
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum )
	{
		this.phoneNum = phoneNum;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email )
	{
		this.email = email;
	}

	public String getPy()
	{
		return py;
	}

	public void setPy(String py )
	{
		this.py = py;
	}

	@Override
	public String toString()
	{
		return "UserInfo [userName=" + userName + ", phoneNum=" + phoneNum + ", py=" + py + "]";
	}

}
