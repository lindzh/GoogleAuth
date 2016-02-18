package com.warrenstrange.googleauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 测试通过
 * @author lindezhi
 * 2016年2月18日 下午8:15:35
 */
public class AuthTest {

	public static void main(String[] args) throws IOException {
		
        System.setProperty(CredentialRepositoryMock.MOCK_SECRET_KEY_NAME,"A6K7FTQ2WBAK3ZZN");
        
        System.setProperty(GoogleAuthenticator.CREDENTIAL_REPOSITORY_PROVIDER,"com.warrenstrange.googleauth.SimpleCredentialRepository");
        
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		
		final GoogleAuthenticatorKey authKey = gAuth.createCredentials("leili");
		
		String key = authKey.getKey();
		int code = authKey.getVerificationCode();
		System.out.println("key:"+key+" code:"+code);
		String authTotpURL = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("leili", "lindezhi@aidaojia.com", authKey);
		System.out.println("generated:"+authTotpURL);
		
		//生产环境通过用户输入用户名获取authKey，之前生成保存在db中
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			System.out.println("please input verify code:");
			String line = reader.readLine();
			int verifyCode = Integer.parseInt(line);
			boolean authorize = gAuth.authorize(authKey.getKey(), verifyCode);
			System.out.println("verify result:"+authorize);
		}
	}
}
