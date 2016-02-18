package com.warrenstrange.googleauth;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 全内存保持credential
 * @author lindezhi
 * 2016年2月18日 下午8:50:56
 */
public class SimpleCredentialRepository implements ICredentialRepository{
	
	private ConcurrentHashMap<String, Credencital> credentialMap = new ConcurrentHashMap<String, Credencital>();
	
	private static class Credencital{
		
		private String name;
		
		private String secret;
		
		private int validation;
		
		private List<Integer> scratchCodes;
		
	}

	public String getSecretKey(String userName) {
		Credencital credencital = credentialMap.get(userName);
		if(credencital!=null){
			return credencital.secret;
		}else{
			throw new GoogleAuthenticatorException("can't find credential for : "+userName);
		}
	}

	public void saveUserCredentials(String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
		Credencital credencital = new Credencital();
		credencital.name = userName;
		credencital.secret = secretKey;
		credencital.validation = validationCode;
		credencital.scratchCodes = scratchCodes;
		credentialMap.put(userName, credencital);
	}

}
