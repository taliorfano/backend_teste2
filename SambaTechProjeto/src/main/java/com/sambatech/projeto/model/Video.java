package com.sambatech.projeto.model;

import java.io.File;

/***
 * 	Model de Video
 * @author Talita
 * 
 */
public class Video {
	// Nome do arquivo
	private String name;
	
	// Arquivo de video
	private File file;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
