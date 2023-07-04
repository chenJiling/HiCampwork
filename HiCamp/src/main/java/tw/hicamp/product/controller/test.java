package tw.hicamp.product.controller;

import java.util.UUID;

public class test {
	
	public static void main(String[] args) {
		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
		System.out.println(uuId);
		
	}
}
