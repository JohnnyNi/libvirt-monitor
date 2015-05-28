package com.bjhit.martin.libvirt;

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
		
	}

}
