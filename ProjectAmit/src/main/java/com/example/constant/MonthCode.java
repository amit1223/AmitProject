package com.example.constant;

public enum MonthCode {
January("jan") ,
February("feb") , 
March("mar") ,
April("apr") ,
May("may") ,
June("jun") , 
July("jul") ,
August("aug") , 
September("sep") ,
October("oct") ,
November("nov") ,
December("dec");
	
public final String month;

private MonthCode(String month) {
	this.month = month;
}

public String getMonth() {
	return month;
}

}
