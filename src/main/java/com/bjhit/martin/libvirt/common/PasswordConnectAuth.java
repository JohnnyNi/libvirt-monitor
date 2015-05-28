package com.bjhit.martin.libvirt.common;

import org.libvirt.ConnectAuth;

public class PasswordConnectAuth extends ConnectAuth {
	private String userName;
	private String password;

	public PasswordConnectAuth(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.credType = new ConnectAuth.CredentialType[] {
				ConnectAuth.CredentialType.VIR_CRED_AUTHNAME,
				ConnectAuth.CredentialType.VIR_CRED_ECHOPROMPT,
				ConnectAuth.CredentialType.VIR_CRED_REALM,
				ConnectAuth.CredentialType.VIR_CRED_PASSPHRASE,
				ConnectAuth.CredentialType.VIR_CRED_NOECHOPROMPT };
	}

	@Override
	public int callback(Credential[] paramArrayOfCredential) {
		for (Credential credential : paramArrayOfCredential) {
			String param = "";
			switch (credential.type) {
			case VIR_CRED_USERNAME:
			case VIR_CRED_AUTHNAME:
			case VIR_CRED_LANGUAGE:
			case VIR_CRED_CNONCE:
				param = userName;
				break;
			case VIR_CRED_PASSPHRASE:
			case VIR_CRED_ECHOPROMPT:
				param = password;
				break;
			}
			if ("".equals(param) && (!("".equals(credential.defresult)))){
				credential.result = credential.defresult;
			}else{
				credential.result = param;
			}
			if ("".equals(credential.result)){
				return -1;
			}
		}
		return 0;
	}

}
