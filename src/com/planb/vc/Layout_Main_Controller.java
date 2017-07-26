package com.planb.vc;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.planb.main.Main;
import com.planb.support.FileManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Layout_Main_Controller implements Initializable {
	@FXML
	private TextField filepathField;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void openFileButtonOnAction(ActionEvent e) {
		FileManager.files = new File[500];
		
		// Dialog show
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("������ �����ϼ���.");

		// add extension filters
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("���� ����", "*.txt", "*.rtf", "*.hwp", "*.pdf", "*.doc", "*.ppt"),
				new ExtensionFilter("�̹��� ����", "*.png", "*.jpg", "*.gif", "*.bmp", "*.psd", "*.pdd", "*.tif"),
				new ExtensionFilter("����� ����", "*.wav", "*.mp3", "*.aac"),
				new ExtensionFilter("������ ����", "*.avi", "*.mp4", "*.mpeg", "*.mkv", "*.flv", "*.mov"),
				new ExtensionFilter("���� ����", "*.zip", "*.egg", "*.rar", "*.tgz", "*.gz"),
				new ExtensionFilter("��� ����", "*.*"));

		List<File> files = fileChooser.showOpenMultipleDialog(Main.stage);
		for(int i = 0; i < files.size(); i++) {
			// List to array
			FileManager.files[i] = files.get(i);
		}
		
		filepathField.setText(makeFileInfoText(files));
	}
	
	public void openFolderButtonOnAction(ActionEvent e) {
		FileManager.files = new File[500];
		
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("������ �����ϼ���.");
		File dir = directoryChooser.showDialog(Main.stage);

		FileManager.exploreFile(dir);
		// FileManager manages
		
		filepathField.setText("\"" + dir.getAbsolutePath() + "\"");
	}
	
	private String makeFileInfoText(List<File> files) {
		StringBuilder sb = new StringBuilder();
		
		for(File file: files) {
			sb.append("\"").append(file.getName()).append("\"").append(", ");
		}
		
		return sb.substring(0, sb.length() - 2).toString();
	}
	
	public void sendButtonOnAction(ActionEvent e) {
		
		if(FileManager.compressZip(FileManager.files)) {
			// ������ 2�� �̻��̿��� �����
			System.out.println("zip");
		} else {
			// ������ �ϳ���
			System.out.println("one");
		}
		getKeyFromServer();
	}
	
	private boolean isPaired() {
		return true;
	}
	
	private void getKeyFromServer() {
		// ������ ������ �����ϰ� ���� id �ޱ�
	}
	
	private void sendViaBeacon() {
		if(!isPaired()) {
			return;
		}
		
		// ������ ���� ����
		new File("temp.zip").delete();
	}
}
