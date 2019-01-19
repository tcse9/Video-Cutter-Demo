package com.ovi.videocutter.bean;

import java.io.Serializable;
import java.util.ArrayList;


public class ComposeInfo implements Serializable{
	private String name;
	private String videoName;
	private ArrayList<Float> pausePoints;
	private ArrayList<Integer> tips;
	private ArrayList<Integer> flags;
	
	private int resolution;
	private ArrayList<String> list;
	private String path;
	private String final_path;
	
	public ArrayList<Integer> getFlags() {
		return flags;
	}
	public void setFlags(ArrayList<Integer> flags) {
		this.flags = flags;
	}
	public ArrayList<Integer> getTips() {
		return tips;
	}
	public void setTips(ArrayList<Integer> tips) {
		this.tips = tips;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFinal_path() {
		return final_path;
	}
	public void setFinal_path(String final_path) {
		this.final_path = final_path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public ArrayList<Float> getPausePoints() {
		return pausePoints;
	}
	public void setPausePoints(ArrayList<Float> pausePoints) {
		this.pausePoints = pausePoints;
	}
	public int getResolution() {
		return resolution;
	}
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}
	public ArrayList<String> getList() {
		return list;
	}
	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	
	
}
