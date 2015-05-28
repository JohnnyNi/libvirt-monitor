package com.bjhit.martin.libvirt.util;

import org.libvirt.Connect;
import org.libvirt.ConnectAuth;
import org.libvirt.LibvirtException;

import com.bjhit.martin.libvirt.common.ConnectType;
import com.bjhit.martin.libvirt.common.PasswordConnectAuth;

public class ConnectUtil {

	public static Connect getConnect(String host, ConnectType type) throws LibvirtException {
		//windows run must set the path
		System.setProperty("jna.library.path", "C:\\Program Files\\VirtViewer v2.0256\\bin");
		switch (type) {
		case KVM_SSH:
			ConnectAuth auth = new PasswordConnectAuth("root", "bjhit2015");
			return new Connect("qemu+ssh://root@" + host + "/system", auth, 1);
		case KVM_TCP:
			return new Connect("qemu+tcp://" + host + "/system");
		default:
			throw new RuntimeException("unsuported type :" + type.toString());
		}
	}

	public static void close(Connect connect) {
		try {
			if (connect != null && connect.isConnected()) {
				connect.close();
			}
		} catch (LibvirtException e) {
			e.printStackTrace();
		}
	}
	
}
