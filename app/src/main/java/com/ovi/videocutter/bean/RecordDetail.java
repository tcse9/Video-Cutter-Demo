package com.ovi.videocutter.bean;

import java.util.ArrayList;
import java.util.List;

public class RecordDetail {
	private String name;
	private String path;
	private String format;
	private ArrayList<Integer> pauses;
	private ArrayList<Integer> marks;
	private List<Integer> flags;
	
	public List<Integer> getFlags() {
		return flags;
	}
	public void setFlags(List<Integer> flags) {
		this.flags = flags;
	}
	public ArrayList<Integer> getPauses() {
		return pauses;
	}
	public void setPauses(ArrayList<Integer> pauses) {
		this.pauses = pauses;
	}
	public ArrayList<Integer> getMarks() {
		return marks;
	}
	public void setMarks(ArrayList<Integer> marks) {
		this.marks = marks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	
	
	
}
