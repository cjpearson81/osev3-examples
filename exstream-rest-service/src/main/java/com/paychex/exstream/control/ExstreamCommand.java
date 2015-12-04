package com.paychex.exstream.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

import org.apache.commons.io.IOUtils;

import com.paychex.exstream.message.ExstreamRequest;

public class ExstreamCommand {
	
	private static final String command = "/opt/exstream/8.0/Engine -CONTROLFILE=";
	
	private ExstreamRequest request;
	private String path;
	private String templateXml;
	
	public ExstreamCommand (ExstreamRequest request) {
		this.request = request;
		this.templateXml = request.getTemplate() + ".xml";
		this.path = "/Users/Chris/Paychex/ENS/exstream/ExstreamRest" + "/" + this.request.getGuid() + "/";
	}
	
	public void runExstream () throws IOException, InterruptedException {
		
		// create directory for control files
		new File(path).mkdir();
		
		// create control file
		ControlFile controlFile = createControlFile();
		controlFile.writeControlFile(path + request.getTemplate() + ".control");
		
		// write data file
		InputStream is = this.getClass().getResourceAsStream("/TPS/TPS_SPRDR1.xml");
		String content = IOUtils.toString(is);
		content = content.replace("{INSERT GUID}", request.getGuid());
		
		StringReader sr = new StringReader(content);
		OutputStream os = new FileOutputStream(new File(path + templateXml));
		
		IOUtils.copy(sr, os);
		
		// run exstream
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		
		process = runtime.exec(command);
		
		int exitCode = process.waitFor();
		
		
		
	}
	
	private ControlFile createControlFile () {
		
		ControlFile cf = new ControlFile();
		cf.setAppPackageFiles(path + "publist.txt");
		cf.setMessageFile(path + this.request.getGuid() + "_msgs.txt");
		cf.getFileMap().put("filepath", path + "pathList.txt");
		cf.getFileMap().put("SORTINDEX", path + "SORTINDEX_PS.DAT");
		cf.getFileMap().put("SORTDATA", path + "SORTDATA_PS.DAT");
		cf.getFileMap().put("OMRPageCount", path + "publist.txt");
		cf.getFileMap().put("Report", path + "COUNT_DATA.DAT");
		cf.getFileMap().put(templateXml, path + templateXml);
		return cf;
	}

}

