package no.systema.aespedsgtestersuite.controller;

import java.net.HttpURLConnection;
import java.net.URL;

public class UrlAliveTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String value = "http://gw.systema.no/sycgip/TNOG005R.pgm";
		//String value = "http://gw.systema.no/sycgip/TNOG005R.pgm";
		try{
			URL u = new URL ( value);
			HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
			huc.setConnectTimeout(3000); // 3 seconds connectTimeout
			huc.setReadTimeout(3000);
			huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD"); 
			huc.connect () ; 
			int code = huc.getResponseCode() ;
			System.out.println("CODE:" + code);
		}catch(Exception e){
			e.toString();
		}
	}

}
