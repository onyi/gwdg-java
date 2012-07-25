package com.google.iapsample;

public class Request {
	private String price;
	private String currencyCode;
	private String name;
	private String description;
	private String sellerData;
	public String getRequest(){return price+currencyCode+name+description+sellerData;}
	public String price_getter(){return price;}
	public String currencyCode_getter(){return currencyCode;}
	public String name_getter(){return name;}
	public String description_getter(){return description;}
	public String sellerData_getter(){return sellerData;}
}
