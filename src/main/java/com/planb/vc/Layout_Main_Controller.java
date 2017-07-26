package com.planb.vc;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
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
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Layout_Main_Controller implements Initializable {
	@FXML
	private TextField filepathField;
	
	@FXML
	private TextField gradeField;
	
	@FXML
	private TextField classField;
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void openFileButtonOnAction(ActionEvent e) {
		FileManager.files = new File[500];
		
		// Dialog show
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("??? ?????.");

		// add extension filters
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("?? ??", "*.txt", "*.rtf", "*.hwp", "*.pdf", "*.doc", "*.ppt"),
				new ExtensionFilter("??? ??", "*.png", "*.jpg", "*.gif", "*.bmp", "*.psd", "*.pdd", "*.tif"),
				new ExtensionFilter("??? ??", "*.wav", "*.mp3", "*.aac"),
				new ExtensionFilter("??? ??", "*.avi", "*.mp4", "*.mpeg", "*.mkv", "*.flv", "*.mov"),
				new ExtensionFilter("?? ??", "*.zip", "*.egg", "*.rar", "*.tgz", "*.gz"),
				new ExtensionFilter("?? ??", "*.*"));

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
		directoryChooser.setTitle("??? ?????.");
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
		File file;
		if(FileManager.compressZip(FileManager.files)) {
			// ??? 2? ????? ???
			file = new File("temp.zip");
		} else {
			// ??? ???
			file = FileManager.files[0];
		}

		sendViaBeacon(getKeyFromServer(file));
	}

	private boolean isPaired() {
		return true;
	}

	private String getKeyFromServer(File file) {
		// ??? ??? ???? ?? id ??
        CloseableHttpClient client = HttpClients.createDefault();

        HttpEntity data = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addBinaryBody("upload", file, ContentType.DEFAULT_BINARY, file.getName())
                .build();

        HttpUriRequest request = RequestBuilder
                .post("http://localhost:3306/upload")
                .setEntity(data)
                .build();

        ResponseHandler<String> responseHandler = response -> {
            return EntityUtils.toString(response.getEntity());
        };

        try {
            System.out.println(client.execute(request, responseHandler));
        } catch(IOException e) {

        }

		return "";
	}

	private void sendViaBeacon(String key) {
		if(!isPaired()) {
			return;
		}

		// ??? ?? ??
		new File("temp.zip").delete();
	}
}
