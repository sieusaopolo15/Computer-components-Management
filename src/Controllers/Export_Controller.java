package Controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import BUS.Export_BUS;
import BUS.Import_BUS;
import BUS.Product_BUS;
import BUS.Supplier_BUS;
import DTO.Supplier;
import DTO.Export;
import DTO.Import;
import DTO.Product;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Export_Controller implements Initializable {

	@FXML
	private AnchorPane anchor_Pane;
	
	//TABLEVIEW & TABLE COLUMNS
		@FXML
		private TableView<Import> storage_Table;
		
		@FXML
		private TableColumn<Integer, Import> maKho;
		
		@FXML
		private TableColumn<Integer, Import> maSP;
		
		@FXML
		private TableColumn<Integer, Import> NCC;
		
		@FXML
		private TableColumn<Integer, Import> amount;
		
		@FXML
		private TableColumn<String, Import> ngayNhap;
		
		@FXML
		private TableColumn<String, Import> price;
		
		@FXML
		private TableColumn<String, Import> money;
	//=================================================
	
	//DATA VARIABLES
		@FXML
		private TextField maKho_Tf;
		
		@FXML
		private TextField maSP_Tf;
		
		@FXML
		private TextField NCC_Tf;
		
		@FXML
		private TextField amount_Tf;
		
		@FXML
		private FontAwesomeIconView up;
		
		@FXML
		private FontAwesomeIconView down;
		
		@FXML
		private TextField price_Tf;
		
		@FXML
		private TextField money_Tf;
		
		@FXML
		private TextArea reason;
	//===================================================
	
	//OTHER VARIABLES
		private HashMap<Integer, Import> imports;
		private HashMap<Integer, Export> exports;
		private HashMap<Integer, Supplier> suppliers;
		private HashMap<Integer, Product> products;
		
		private Import_BUS ib = new Import_BUS();
		private Supplier_BUS sb = new Supplier_BUS();
		private Export_BUS eb = new Export_BUS();
		private Product_BUS pb = new Product_BUS();
		
		private ObservableList<Import> oblist = FXCollections.observableArrayList();
		
		private int id;
		
		private Alert alert;
		private DialogPane dp;
	//===================================================
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		imports = ib.getList();
		for(Integer i : imports.keySet()) {
			Import sd = imports.get(i);
			sd.setTenNCC(getSupplierName(sd.getMaNCC()));
			oblist.add(sd);
		}
		
		amount_Tf.setText("1");
		
		maKho.setCellValueFactory(new PropertyValueFactory<Integer, Import>("maKho"));
		maSP.setCellValueFactory(new PropertyValueFactory<Integer, Import>("maSP"));
		NCC.setCellValueFactory(new PropertyValueFactory<Integer, Import>("tenNCC"));
		amount.setCellValueFactory(new PropertyValueFactory<Integer, Import>("soLuong"));
		ngayNhap.setCellValueFactory(new PropertyValueFactory<String, Import>("ngayNhap"));
		price.setCellValueFactory(new PropertyValueFactory<String, Import>("donGia"));
		money.setCellValueFactory(new PropertyValueFactory<String, Import>("tongTien"));
		
		storage_Table.setItems(oblist);
		
		storage_Table.setOnMousePressed(e -> {
			if(e.isPrimaryButtonDown() && e.getClickCount() == 2) {
				Transfer_Data();
			}
		});
	}
	
	private void Transfer_Data() {
		Import id = storage_Table.getSelectionModel().getSelectedItem();
		if(id != null) {
			maKho_Tf.setText("" + id.getMaKho());
			maSP_Tf.setText("" + id.getMaSP());
			NCC_Tf.setText("" + id.getMaNCC());
			amount_Tf.setText("" + id.getSoLuong());
			price_Tf.setText(id.getDonGia());
			money_Tf.setText(id.getTongTien());
		}
	}
	
	//BACK TO STORAGE MANAGEMENT
		@FXML
		private void Back() {
			Stage stage = (Stage) anchor_Pane.getScene().getWindow();
			stage.close();
		}
	//===============================================================
	
	//EXPORT
		@FXML
		private void Export() throws Exception {
			if(maKho_Tf.getText().equals("")) {
				Error("Lỗi Nhập Liệu", "Bạn Phải Chọn Sản Phẩm Cần Xuất Từ Bảng Chi Tiết Kho", "");
				return;
			}
			
			else if(reason.getText().equals("")) {
				Error("Lỗi Nhập Liệu", "Chưa Nhập Vào Lý Do Xuất Hàng", "");
				reason.requestFocus();
				return;
			}
			
			Import id = storage_Table.getSelectionModel().getSelectedItem();
			if(id != null) {
				exports = eb.getList();
				int j = 1;
				for(Integer i : exports.keySet()) {
					j++;
				}
				int b = Integer.parseInt(maSP_Tf.getText());
				int c = id.getMaNCC();
				int d = Integer.parseInt(amount_Tf.getText());
				
				Export e = new Export(
						j, b, c,
						d, getDate(), reason.getText()
				);
				id.setTongTien(money_Tf.getText());
				
				eb.Insert(e);
				Update_Storage_Detail(id);
				Update_Product(id);
				
				Information("Xuất Hàng", "Xuất Hàng Thành Công", "");
			}
		}
		
		private String getDate() {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = df.format(cal.getTime());
			return date;
		}
		
		private void Update_Storage_Detail(Import id) {
			int a = id.getSoLuong() - Integer.parseInt(amount_Tf.getText());
			
			id.setSoLuong(a);
			id.setTongTien(money_Tf.getText());
			try {
				ib.Update(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(id.getSoLuong() == 0) {
				try {
					ib.Delete(id.getMaPhieu());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Refresh();
		}
		
		private void Update_Product(Import id) {
			int a = id.getMaSP();
			int b = Integer.parseInt(amount_Tf.getText());
			int amount;
			
			products = pb.getAll();
			
			for(Integer i : products.keySet()) {
				if(a == products.get(i).getMaSP()) {
					System.out.println(a);
					amount = products.get(i).getSoLuongConLai();
					products.get(i).setSoLuongConLai(amount + b);
					products.get(i).setTrangThai("Còn Hàng");
					try {
						pb.Update(products.get(i));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
			
			
			
		}
		
		private void Refresh() {
			imports = ib.getList();
			ObservableList<Import> oblist = FXCollections.observableArrayList();
			for(Integer i : imports.keySet()) {
				Import id = imports.get(i);
				oblist.add(id);
			}
			storage_Table.setItems(oblist);
		}
	//====================================================================
	
	private String getSupplierName(int index) {
		suppliers = sb.getList();
		return suppliers.get(index).getTenNCC();
	}
	
	
	//ADJUST AMOUNT & MONEY
		@FXML
		private void Increase() {
			up.setOnMousePressed(e -> {
				int a = Integer.parseInt(amount_Tf.getText());
				a = a + 1;
					
				Import id = storage_Table.getSelectionModel().getSelectedItem();
				if(id != null) {
					if(a == id.getSoLuong()) {
						a = id.getSoLuong();
						amount_Tf.setText("" + a);
					}
					else
						amount_Tf.setText("" + a);
				}
				
				if(!price.getText().equals("")) {
					try {
						money_Tf.setText(Calculate(price_Tf.getText(), a));
					} catch (ParseException e1) {
						
						e1.printStackTrace();
					}
				}
			});
		}
		
		@FXML
		private void Decrease() {
			down.setOnMousePressed(e -> {
				if(!amount_Tf.getText().equals("")) {
					int a = Integer.parseInt(amount_Tf.getText());
					if(a == 1){
						amount_Tf.setText("1");
						try {
							money_Tf.setText(Calculate(price_Tf.getText(), a));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return;
					}
					a = a - 1;
					amount_Tf.setText("" + a);
					if(!price.getText().equals("")) {
						try {
							money_Tf.setText(Calculate(price_Tf.getText(), a));
						} catch (ParseException e1) {
							
							e1.printStackTrace();
						}
					}
				}
			});
		}
		
		private String Calculate(String money, int amount) throws ParseException {
			Locale localeVN = new Locale("vi", "VN");
			money = money.replace(".", "");
			NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
			String str = currencyVN.format(Double.parseDouble(money) * amount);
			str = str.replaceAll(" đ", "");
			return str;
		}
	//=======================================================================
	
	//MESSAGE
		private void Error(String errorName, String text, String contentText) {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(errorName);
			alert.setHeaderText(text);
			alert.setContentText(contentText);
				
			dp = alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("../GUI/CSS/style.css").toExternalForm());
				
			alert.showAndWait();
		}
		
		private void Information(String infoName, String text, String contentText) {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(infoName);
			alert.setHeaderText(text);
			alert.setContentText(contentText);
				
			dp = alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("../GUI/CSS/style.css").toExternalForm());
				
			alert.showAndWait();
		}
	//===========================================================================================================
		
	public void Receiver(int id) {
		this.id = id;
	}
}
