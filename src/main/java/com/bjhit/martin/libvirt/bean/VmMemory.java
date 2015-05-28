package com.bjhit.martin.libvirt.bean;

public class VmMemory {
	private String name;
	private int totalMem;
	private int usedMem;
	private int freeMem;
	private int minMem;

	public VmMemory(String name,int totalMem) {
		this.totalMem = totalMem;
		this.name = name;
	}

	public VmMemory(String name,int totalMem, int usedMem) {
		this(name,totalMem);
		this.usedMem = usedMem;
	}

	public VmMemory(String name,int totalMem, int usedMem, int freeMem) {
		this(name,totalMem, usedMem);
		this.freeMem = freeMem;
	}

	public VmMemory(String name,int totalMem, int usedMem, int freeMem, int minMem) {
		this(name,totalMem, usedMem, freeMem);
		this.minMem = minMem;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalMem() {
		return totalMem;
	}

	public void setTotalMem(int totalMem) {
		this.totalMem = totalMem;
	}

	public int getUsedMem() {
		return usedMem;
	}

	public void setUsedMem(int usedMem) {
		this.usedMem = usedMem;
	}

	public int getFreeMem() {
		return freeMem;
	}

	public void setFreeMem(int freeMem) {
		this.freeMem = freeMem;
	}

	public int getMinMem() {
		return minMem;
	}

	public void setMinMem(int minMem) {
		this.minMem = minMem;
	}

}
