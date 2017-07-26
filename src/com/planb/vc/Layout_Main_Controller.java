package com.planb.vc;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.planb.main.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Layout_Main_Controller implements Initializable {
	@FXML
	private TextField filepathField;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void openFileButtonOnAction(ActionEvent e) {
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
	}
	
	public void openFolderButtonOnAction(ActionEvent e) {
		// Dialog show
	}
	
	
	public void sendButtonOnAction(ActionEvent e) {
		
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
	}
}
