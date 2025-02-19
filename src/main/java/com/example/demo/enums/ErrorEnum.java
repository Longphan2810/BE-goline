package com.example.demo.enums;


public enum ErrorEnum {
	NULL_FT(1101,"First test null"),

    // error code apartment : 11**
	Id_Apartment_Not_null(1101,"ID apartment not null"),
	Name_Not_Blank(1102,"Name_Not_Blank"),
	Address_Not_Blank(1103,"Address_Not_Blank"),
	Available_not_null(1104,"Available_not_null"),
	The_Whole_House_not_null(1105,"The_Whole_House_not_null"),
	Apartment_not_found(1106,"Apartment_not_found"),
	Nothing_to_update(1107,"Nothing_to_update"),
	// error code room : 12**

	Name_Room_Not_Blank(1201,"Name_Not_Blank"),
	Available_Room_Not_null(1202,"Available_Room_Not_null"),
	Price_Room_Not_null(1203,"Price_Room_Not_null"),
	Room_not_found(1204,"Room_not_found"),
	Not_thing_to_update_room(1205,"Not_thing_to_update_room"),
	;
	private int code;
	private String message;
	private ErrorEnum(int code, String message) { 
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	

}
