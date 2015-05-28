package com.bjhit.martin.libvirt.vm;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.DomainInfo;
import org.libvirt.LibvirtException;
import org.libvirt.VcpuInfo;

import com.bjhit.martin.libvirt.bean.VmMemory;
import com.bjhit.martin.libvirt.common.BaseException;

public class VMStatistic {
	
	private Connect conn;
	
	public VMStatistic(Connect conn) {
		this.conn = conn;
	}
	/**
	 * 
	 * @param vmName  virtual machine name
	 * @param intervalMillis static interval time
	 * @return Map<Integer, Double>  key is cpu nubmer,value is the cpu rate
	 * @throws LibvirtException
	 */
	public Map<Integer, Double> staticVmCpuRate(String vmName,int intervalMillis) throws LibvirtException {
		Map<Integer, Double> cpusRate = new HashMap<Integer, Double>();
		Domain vm = getDomain(vmName);
		if (vm == null) {
			throw new BaseException("", "Can not find vm :"+vmName);
		}
		
		long startTime = System.currentTimeMillis();
		VcpuInfo[] vcpuInfos = vm.getVcpusInfo();
		try {
			Thread.sleep(intervalMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VcpuInfo[] endVcpuInfos = vm.getVcpusInfo();
		long endTime = System.currentTimeMillis();
		VcpuInfo endVcpuInfo;
		for(VcpuInfo vcpuInfo :vcpuInfos){
			endVcpuInfo = getVcpuInfo(vcpuInfo.number,endVcpuInfos);
			if (endVcpuInfo == null) {
				continue;
			}
			//calculate the cpu used rate
			cpusRate.put(vcpuInfo.number, new BigDecimal(((endVcpuInfo.cpuTime-vcpuInfo.cpuTime)*1.0/10000)/(endTime-startTime)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		return cpusRate;
	}
	
	private VcpuInfo getVcpuInfo(int number, VcpuInfo[] endVcpuInfos) {
		for(VcpuInfo vcpuInfo :endVcpuInfos){
			if (number == vcpuInfo.number) {
				return vcpuInfo;
			}
		}
		return null;
	}
	/**
	 * 
	 * @param vmName virtual machine name
	 * @return virtual machine entity
	 * @throws LibvirtException
	 */
	public Domain getDomain(String vmName) throws LibvirtException {
		return conn.domainLookupByName(vmName);
	}
	
	public VmMemory staticVmMemory (String vmName) throws LibvirtException {
		Domain vm = getDomain(vmName);
		if (vm == null) {
			throw new BaseException("", "Can not find vm :"+vmName);
		}
		VmMemory memory = new VmMemory(vmName,(int) (vm.getMaxMemory()/1024));
		DomainInfo domainInfo = vm.getInfo();
		memory.setUsedMem((int) (domainInfo.memory/1024));
		
		return memory;
	}
}
