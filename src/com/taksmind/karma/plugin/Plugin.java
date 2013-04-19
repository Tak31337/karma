package com.taksmind.karma.plugin;

public class Plugin {
	private String name;
	private String command;
	private String description;
	private String trigger;

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
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
	
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	
	public String getTrigger() {
		return this.trigger;
	}
}
