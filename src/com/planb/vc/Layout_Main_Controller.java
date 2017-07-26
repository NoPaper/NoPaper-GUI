package com.planb.vc;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class Layout_Main_Controller implements Initializable {
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void openFileButtonOnAction(ActionEvent e) {
		// Dialog show
	}
	
	public void sendButtonOnAction(ActionEvent e) {
		
	}
	
	private boolean isPaired() {
		return true;
	}
	
	private void getKeyFromServer() {
		// 서버에 파일을 전송하고 고유 id 받기
	}
	
	private void sendViaBeacon() {
		if(!isPaired()) {
			return;
		}
		
		// 비콘을 통해 전송
	}
}
