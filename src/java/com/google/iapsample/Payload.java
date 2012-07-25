package com.google.iapsample;

public class Payload {
	private String iss;
	private String typ;
	private String aud;
	private Request request;
	private int iat;
	private int exp;
	private Response response;
	public String iss_getter(){return this.iss;}
	public String typ_getter(){return this.typ;}
	public String aud_getter(){return this.aud;}
	public int iat_getter(){return this.iat;}
	public int exp_getter(){return this.exp;}
	public Request request_getter(){return this.request;} 
	public Response response_getter(){return this.response;}
	

}



