package com.sambatech.projeto.model;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/***
 * 	Model de Video
 * @author Talita
 * 
 */
public class Video {
	// Nome do arquivo
	private String name;
	
	// Arquivo de video
	private File fileVideo;
	
	// Arquivo de video
	private MultipartFile multipartFile;
	
	/**
	 * Contrutor
	 * @param name Nome do video
	 * @param multipartFile Arquivo
	 */
	public Video(String name, MultipartFile multipartFile) {
		this.name = name;
		this.multipartFile = multipartFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getFile() {
		return fileVideo;
	}

	public void setFile(File fileVideo) {
		this.fileVideo = fileVideo;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
}
