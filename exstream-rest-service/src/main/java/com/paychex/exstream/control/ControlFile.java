package com.paychex.exstream.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControlFile {
	
	private static final String Key = "B2PnvCXOpoMc6aoOA5jlZaHHjic4iwQdRCql8QBOaCgyDSsUfFhDc9Ph18mb7BMc6aoOA5jlZ3W0pkL4iNQdRCqh2Krz1HgyDSsVf9zMgXP9n8mb7BMc6aoOA5jlZ3W0pkL4iNQdRCqh2Krz1HgyDSsUfxX";
	
	private String appPackageFiles;
	private String messageFile;
	private Map<String,String> fileMap;
	
	public ControlFile() {
		this.fileMap = new HashMap<String,String>();
	}
	public void setAppPackageFiles(String appPackageFiles) {
		this.appPackageFiles = appPackageFiles;
	}
	public void setMessageFile(String messageFile) {
		this.messageFile = messageFile;
	}
	public Map<String, String> getFileMap() {
		return fileMap;
	}
	public void writeControlFile(String fileName) throws IOException {
		
		File file = new File(fileName);
	
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		bw.append("-KEY=" + this.Key);
		bw.newLine();
		bw.append("-APP_PACKAGEFILES=" + this.appPackageFiles);
		bw.newLine();
		
		for (Map.Entry<String, String> entry : fileMap.entrySet()) {
			bw.append("-FILEMAP=" + entry.getKey() + "," + entry.getValue());
			bw.newLine();
		}
		
		bw.append("-MESSAGEFILE=" + this.messageFile);
		bw.close();

	}

}

