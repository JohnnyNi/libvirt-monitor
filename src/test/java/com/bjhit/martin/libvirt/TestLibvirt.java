package com.bjhit.martin.libvirt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

import org.libvirt.Connect;
import org.libvirt.LibvirtException;

import com.bjhit.martin.libvirt.common.ConnectType;
import com.bjhit.martin.libvirt.util.ConnectUtil;
import com.bjhit.martin.libvirt.vm.VMStatistic;

public class TestLibvirt {
	public static void main(String[] args) throws LibvirtException {
		TestLibvirt libvirt = new TestLibvirt();
		Connect conn = ConnectUtil.getConnect("172.19.106.245",ConnectType.KVM_TCP);
		try {
			VMStatistic vmStatistic = new VMStatistic(conn);
			Map<Integer, Double> vmCpusRate = vmStatistic.staticVmCpuRate("windows-xp", 2000);
			Iterator<Integer> cpuNumbers = vmCpusRate.keySet().iterator();
			while (cpuNumbers.hasNext()) {
				Integer integer = (Integer) cpuNumbers.next();
				System.out.println(integer+" cpu used rate: "+vmCpusRate.get(integer)+"%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectUtil.close(conn);
		}
		DataOutputStream out = new DataOutputStream(System.out);
		DataInputStream in = new DataInputStream(System.in);
		try {
			out.writeInt(2);;
			out.flush();
			out.close();
			int c = in.readInt();
			System.out.println("read :"+c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
