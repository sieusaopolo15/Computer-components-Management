package Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import BUS.Order_BUS;
import BUS.Product_BUS;
import BUS.Product_Detail_BUS;
import DTO.Customer;
import DTO.Order;
import DTO.Product;
import DTO.Product_Detail;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Products_Controller implements Initializable {
	
	@FXML
	private TextField search_Box;
	
	//TABLE VIEW & TABLE COLUMN
		@FXML
		private TableView<Product> product_Table;
		
		@FXML
		private TableColumn<Product, ImageView> image;
		
		@FXML
		private TableColumn<Product, String> tenSP;
		
		@FXML
		private TableColumn<Product, String> loaiSP;
		
		@FXML
		private TableColumn<Product, String> thuongHieu;
		
		@FXML
		private TableColumn<Product, Integer> soLuongConLai;
		
		@FXML
		private TableColumn<Product, String> giaTien;
	//========================================================
	
	//DESCRIPTION
		@FXML
		private ImageView info_Image;
		
		@FXML
		private Text amount;
		
		@FXML
		private TextField amount_Tf;
		
		@FXML
		private FontAwesomeIconView up;
		
		@FXML
		private FontAwesomeIconView down;
		
		@FXML
		private Label info_Text;
	//=========================================================
	
	//OTHER VARIABLES
		private Product_BUS pb = new Product_BUS();
		private Product_Detail_BUS pbd = new Product_Detail_BUS();
		private Order_BUS ob = new Order_BUS();
		
		private HashMap<Integer, Product_Detail> product_details;
		private HashMap<Integer, Product> products;
		private HashMap<Integer, Order> orders;
		
		private ObservableList<Product> oblist = FXCollections.observableArrayList();
		
		private int c;
		private	Alert alert;
		private DialogPane dp;
	//==========================================================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		products = pb.getList();
		product_details = pbd.getList();
		for(Integer i : products.keySet()) {
			Product p = products.get(i);
			if(p.getSoLuongConLai() == 0) {
				p.setTrangThai("Hết Hàng");
			}
			p.getHinhAnh().setFitWidth(130);
			p.getHinhAnh().setFitHeight(130);
			oblist.add(p);
		}
		
		image.setCellValueFactory(new PropertyValueFactory<Product, ImageView>("hinhAnh"));
		tenSP.setCellValueFactory(new PropertyValueFactory<Product, String>("tenSP"));
		loaiSP.setCellValueFactory(new PropertyValueFactory<Product, String>("loaiSP"));
		thuongHieu.setCellValueFactory(new PropertyValueFactory<Product, String>("thuongHieu"));
		soLuongConLai.setCellValueFactory(new PropertyValueFactory<Product, Integer>("soLuongConLai"));
		giaTien.setCellValueFactory(new PropertyValueFactory<Product, String>("giaTien"));
		
		product_Table.setItems(oblist);
		product_Table.setOnMousePressed(e -> {
			if(e.isPrimaryButtonDown() && e.getClickCount() == 1) {
				Product p = product_Table.getSelectionModel().getSelectedItem();	
				if(p != null) {
					Image image = new Image(getClass().getResourceAsStream(p.getImage()));
					info_Image.setImage(image);
					find_Detail(p);
					amount_Tf.setText("0");
				}
			}
		});
	}
	
	
	//SEARCH
		@FXML
		private void Search(KeyEvent event) {
			product_Table.getItems().stream().filter(item -> item.getID().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				product_Table.getSelectionModel().select(item);
				product_Table.scrollTo(item);
			});
			
			product_Table.getItems().stream().filter(item -> item.getTenSP().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				product_Table.getSelectionModel().select(item);
				product_Table.scrollTo(item);
			});
			
			product_Table.getItems().stream().filter(item -> item.getLoaiSP().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				product_Table.getSelectionModel().select(item);
				product_Table.scrollTo(item);
			});
			
			product_Table.getItems().stream().filter(item -> item.getGiaTien().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				product_Table.getSelectionModel().select(item);
				product_Table.scrollTo(item);
			});
		}
	//====================================================================================
	
		
	//BUY A PRODUCT
		@FXML
		private void Buy() throws Exception {
			orders = ob.getList();
			Product p = product_Table.getSelectionModel().getSelectedItem();
			
			if(p != null) {
				String tongTien = p.getGiaTien();
				Order o;
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				String date = df.format(cal.getTime());
		
				int a = Integer.parseInt(amount_Tf.getText());
				
				if(a == 0) {
					return;
				}
				else if(p.getSoLuongConLai() < a) {
					Error("Số Lượng", "Bạn mua hàng với số lượng nhiều hơn số lượng còn lại", "");
					return;
				}
				else {
					if(orders.size() == 0) {
						o = new Order(001, this.c, "", date, Calculate(tongTien, Integer.parseInt(amount_Tf.getText())).replaceAll(" đ", ""), "Chưa Phê Duyệt");
						if(p.getSoLuongConLai() == a) {
							p.setTrangThai("Hết Hàng");
						}
			
						ob.Insert(o);
						createDetail(001, p.getMaSP(), a, p.getGiaTien(), Calculate(tongTien, Integer.parseInt(amount_Tf.getText())), p.getSoLuongConLai());
					}
					else {
						for(int i = 1; i <= orders.size(); i++) {
							if(i != orders.get(i).getMaDH()) {
								o = new Order(i, this.c, "", date, Calculate(tongTien, Integer.parseInt(amount_Tf.getText())), "Chưa Phê Duyệt");
								o.setMaNV(i);
								
								if(p.getSoLuongConLai() == a) {
									
									p.setTrangThai("Hết Hàng");
								}
								
								ob.Insert(o);
								
								createDetail(o.getMaDH(), p.getMaSP	(), a, p.getGiaTien(), Calculate(tongTien, Integer.parseInt(amount_Tf.getText())), p.getSoLuongConLai());
								
								
								
								
								break;
							}
							if(i >= orders.size()) {
								o = new Order(i + 1, this.c, "", date, Calculate(tongTien, Integer.parseInt(amount_Tf.getText())), "Chưa Phê Duyệt");
								
								if(p.getSoLuongConLai() == a) {
									
									p.setTrangThai("Hết Hàng");
								}
								
								ob.Insert(o);
								pb.Update(p);
								createDetail(o.getMaDH(), p.getMaSP	(), a, p.getGiaTien(), Calculate(tongTien, Integer.parseInt(amount_Tf.getText())), p.getSoLuongConLai());
							}
						}
					}
					Refresh();
				}
			}
			else {
				Error("Lỗi Thông Tin Mua Hàng", "Bạn chưa chọn sản phẩm nên chưa mua được", "");
				return;
			}
		}
	//==============================================================================================
	
		
	private void Refresh() {
		products = pb.getList();
		
		ObservableList<Product> oblist = FXCollections.observableArrayList();
		for(Integer i : products.keySet()) {
			Product p = products.get(i);
			if(p.getSoLuongConLai() == 0) {
				p.setTrangThai("Hết Hàng");
			}
			p.getHinhAnh().setFitWidth(130);
			p.getHinhAnh().setFitHeight(130);
			oblist.add(p);
		}
		
		
		product_Table.setItems(oblist);
		info_Text.setText("");
		amount_Tf.setText("0");
		
		Image image = new Image(getClass().getResourceAsStream("../Icons/image_missing.png"));
		info_Image.setImage(image);
	}
		
	//CREATE PRODUCT'S DETAILS
		private void find_Detail(Product p) {
			String []a = null;
			String s = "";
			for(int i = 0; i < product_details.size(); i++) {
				if(p.getMaSP() == product_details.get(i + 1).getMaSP()) {
					a = product_details.get(i + 1).getChiTietSP().split(", ");
				}
			}
			if(a != null)
				for(int i = 0; i < a.length; i++) {
					s += a[i] + "\n";
				}
			info_Text.setText(s);
		}
	//===============================================================
	
	@FXML
	private void Increase(MouseEvent event) {
		up.setOnMousePressed(e -> {
			if(!amount_Tf.getText().equals("")) {
				int a = Integer.parseInt(amount_Tf.getText());
				amount_Tf.setText("" + (a + 1));
				return;
			}
		});
	}
	
	
	//CHUYỂN DỮ LIỆU TỪ BuyingController sang Products_Controller
		public void Data_Transfer(int c) {
			this.c = c;
		}
	//============================================================
	
	@FXML
	private void Decrease(MouseEvent event) {
		down.setOnMousePressed(e -> {
			if(!amount_Tf.getText().equals("")) {
				int a = Integer.parseInt(amount_Tf.getText());
				if(a == 0){
					amount_Tf.setText("0");
					return;
				}
				amount_Tf.setText("" + (a - 1));
			}
		});
	}
	
	private String Calculate(String money, int amount) throws ParseException {
		Locale localeVN = new Locale("vi", "VN");
		money = money.replace(".", "");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String str = currencyVN.format(Double.parseDouble(money) * amount);
		return str;
	}
	
	private void createDetail(int maDH, int maSP, int soLuong, String donGia, String tongTien, int amount) throws IOException {
		Stage stage = new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Buying/Detail.fxml"));
		Parent root = loader.load();
		
		
		Detail_Control dc = loader.<Detail_Control>getController();
		dc.TransferData(maDH, maSP, soLuong, donGia, tongTien, amount);
		
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		stage.setScene(scene);
	
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Launch.stage);
		
		stage.showAndWait();
		
		Refresh();
	}
	
	 private void Error(String errorName, String text, String contentText) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle(errorName);
			alert.setHeaderText(text);
			alert.setContentText(contentText);
			
			dp = alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("../GUI/CSS/style.css").toExternalForm());
			
			alert.showAndWait();
		}
}
