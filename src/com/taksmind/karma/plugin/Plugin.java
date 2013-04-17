package com.taksmind.karma.plugin;

public class Plugin {
	private String name;
	private String extention;
	private String path;
	private String command;
	private String description;
	private String log;

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getExtention() {
		return this.extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public String getLogLocation() {
		return this.log;
	}

	public void setLogLocation(String log) {
		this.log = log;
	}
}
