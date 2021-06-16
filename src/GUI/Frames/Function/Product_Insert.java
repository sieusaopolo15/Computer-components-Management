package GUI.Frames.Function;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import BUS.Category_BUS;
import BUS.Product_BUS;
import BUS.Product_Detail_BUS;
import DTO.Category;
import DTO.Product;
import DTO.Product_Detail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class Product_Insert implements Initializable {

	@FXML
	private AnchorPane anchor_Pane;
	
	//CÁC GIÁ TRỊ TRONG BIỂU MẪU
		@FXML
		private TextField maSP;
		
		@FXML
		private TextField tenSP;
		
		@FXML
		private ChoiceBox<String> category;
		
		@FXML
		private TextField brand;
		
		@FXML
		private TextField price;
		
		@FXML
		private TextField image_URL;
		
		@FXML
		private TextField save_URL;
		
		@FXML
		private TextArea details;
	//================================================
	
	//CÁC BIẾN KHÁC
		@FXML
		private ImageView image;
		
		private HashMap<Integer, Product> products;
		private HashMap<Integer, Category> categories;
		
		private Product_BUS pb = new Product_BUS();
		private Product_Detail_BUS pdb = new Product_Detail_BUS();
		private Category_BUS cb = new Category_BUS();
		
		private ObservableList<String> cat = FXCollections.observableArrayList();
		
		private Alert alert;
		private DialogPane dp;
		
		private String regex = "[^0-9\\.]+$";
		private String regex_2 = "[^\r\n" + 
				"]+$";
	//================================================
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		getProductID();
		getCategory();
	}
	
	private void getProductID() {
		products = pb.getList();
		maSP.setText("" + (products.size() + 1));
	}
	
	private void getCategory() {
		categories = cb.getList();
		for(Integer i : categories.keySet()) {
			Category c = categories.get(i);
			cat.add(c.getTenLoai());
		}
		category.setItems(cat);
		category.getSelectionModel().selectFirst();
	}
	
	//SỰ KIỆN LẤY HÌNH ẢNH
		@FXML
		private void pick_Image() {
			FileChooser	fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("JPG", "*.jpg"),
					new FileChooser.ExtensionFilter("PNG", "*.png")
			);
			File file = fileChooser.showOpenDialog(null);
			if(file != null) {
				image_URL.setText(file.getAbsolutePath());
				Image image = new Image(file.toURI().toString());
				this.image.setImage(image);
			}
		}
		
		@FXML
		private void Save_Image() {
			if(image_URL.getText().equals("")) {
				Error("Lỗi Chọn Hình Ảnh", "Bạn chưa chọn hình ảnh nên không thể chọn thư mục lưu", "");
				return;
			}
			else {
				DirectoryChooser dc = new DirectoryChooser();
				File dir = dc.showDialog(null);
				if(dir != null) {
					Path source = FileSystems.getDefault().getPath(image_URL.getText());
					save_URL.setText(dir.getPath() + "\\" + source.getFileName());
					Path target = FileSystems.getDefault().getPath(save_URL.getText());
						
					if(save_URL.getText().indexOf(source.getParent().toString()) == -1) {
						try {
							Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		}
	//===============================================================
		
	//VALIDATE
		private boolean Validate() {
			if(tenSP.getText().equals("")) {
				Error("Lỗi Nhập Liệu", "Tên không được để trống", "");
				tenSP.requestFocus();
				return false;
			}
			else if(brand.getText().equals("")) {
				Error("Lỗi Nhập Liệu", "Thương hiệu không được để trống", "");
				brand.requestFocus();
				return false;
			}
			else if(price.getText().equals("")) {
				Error("Lỗi Nhập Liệu", "Đơn giá không được để trống", "");
				price.requestFocus();
				return false;
			}
			else if(price.getText().matches(regex)) {
				Error("Lỗi Nhập Liệu", "Đơn giá không hợp lệ", "");
				price.requestFocus();
				return false;
			}
			else if(image_URL.getText().equals("")) {
				Error("Lỗi Nhập Liệu", "Link file ảnh chưa có", "");
				return false;
			}
			else if(save_URL.getText().equals("")) {
				Error("Lỗi Nhập Liệu", "Thư mục lưu ảnh chưa có", "");
				return false;
			}
			else if(details.getText().equals("")) {
				Error("Lỗi Nhập Liệu", "Chi tiết sản phẩm không được để trống", "");
				details.requestFocus();
				return false;
			}
			else if(details.getText().indexOf(", ") == -1) {
				Error("Lỗi Nhập Liệu", "Chi tiết sản phẩm phải có cách và phẩy", "");
				details.requestFocus();
				return false;
			}
			else if(!details.getText().matches(regex_2)) {
				Error("Lỗi Nhập Liệu", "Chi tiết sản phẩm không được xuống dòng", "");
				details.requestFocus();
				return false;
			}
			return true;
		}
		
		private void Error(String errorName, String text, String contentText) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle(errorName);
			alert.setHeaderText(text);
			alert.setContentText(contentText);
			
			dp = alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("../../CSS/style.css").toExternalForm());
			
			alert.showAndWait();
		}
		
		private void Information(String informationName, String text, String contentText) {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(informationName);
			alert.setHeaderText(text);
			alert.setContentText(contentText);
			
			dp = alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("../../CSS/style.css").toExternalForm());
			
			alert.showAndWait();
		}
	//===============================================================
	
		
	//CANCLE
		@FXML
		private void Cancle() {
			Stage stage = (Stage) anchor_Pane.getScene().getWindow();
			stage.close();
		}
	//===============================================================
	
	//INSERT
		@FXML
		private void Insert() throws Exception {
			if(Validate()) {
				String image = save_URL.getText().replaceAll("\\\\", "/");
				String text = "";
				String []a = image.split("/");
				for(int i = 0; i < a.length; i++) {
					if(a[i].indexOf("Images") != -1) {
						text = "../Images/" + a[i + 1] + "/" + a[i + 2]; 
						break;
					}
				}
				//System.out.println(text);
				Product p = new Product(
						Integer.parseInt(maSP.getText()), tenSP.getText(), category.getSelectionModel().getSelectedItem().toString(),
						brand.getText(), text, 10, price.getText(), "Còn Hàng"
				);
				
				Product_Detail pd = new Product_Detail(
						Integer.parseInt(maSP.getText()), details.getText()
				);
				
				pb.Insert(p);
				pdb.Insert(pd);
				
				Information("Thêm Sản Phẩm", "Thêm sản phẩm thành công", "");
				getProductID();
			}
		}
	//===============================================================
}
