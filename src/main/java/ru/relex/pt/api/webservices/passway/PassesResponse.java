package ru.relex.pt.api.webservices.passway;

import java.util.List;

public class PassesResponse {
	private List<Pass> passes;
	private long lastUpdateTime;
	
	public PassesResponse()
	{
		
	}
	
	public PassesResponse(List<Pass> passes, long lastUpdateTime) {
		super();
		this.passes = passes;
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public List<Pass> getPasses() {
		return passes;
	}
	public void setPasses(List<Pass> passes) {
		this.passes = passes;
	}
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
