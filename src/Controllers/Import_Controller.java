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

import BUS.Employee_BUS;
import BUS.Import_BUS;
import BUS.Product_BUS;
import BUS.Supplier_BUS;
import BUS.Warehouse_BUS;
import DTO.Import;
import DTO.Employee;
import DTO.Product;
import DTO.Supplier;
import DTO.Warehouse;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Import_Controller implements Initializable {

	//SCENE BUILDER CONTROLS
		@FXML
		private AnchorPane anchor_Pane;
		
		@FXML
		private ChoiceBox<Integer> maKho_CB;
		
		@FXML
		private ChoiceBox<Integer> maSP_CB;
		
		@FXML
		private ChoiceBox<String> NCC_CB;
		
		@FXML
		private TextField amount;
		
		@FXML
		private FontAwesomeIconView up;
		
		@FXML
		private FontAwesomeIconView down;
		
		@FXML
		private TextField price;
		
		@FXML
		private TextField money;
	//=========================================================
	
	//OTHER VARIABLES
		private Product_BUS pb = new Product_BUS();
		private Import_BUS ib = new Import_BUS();
		private Warehouse_BUS wb = new Warehouse_BUS();
		private Supplier_BUS sb = new Supplier_BUS();
		private Employee_BUS eb = new Employee_BUS();
		
		private ObservableList<Integer> product = FXCollections.observableArrayList();
		private ObservableList<Integer> warehouse = FXCollections.observableArrayList();
		private ObservableList<String> supplier = FXCollections.observableArrayList();
	
		private HashMap<Integer, Product> products;
		private HashMap<Integer, Import> imports;
		private HashMap<Integer, Warehouse> warehouses;
		private HashMap<Integer, Supplier> suppliers;
		private HashMap<Integer, Employee> employees;
		
		private Alert alert;
		private DialogPane dp;
		
		private String name;
		private int id;
	//=========================================================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		products = pb.getAll();
		warehouses = wb.getList();
		suppliers = sb.getList();
		
		for(Integer i : products.keySet()) {
			Product p = products.get(i);
			product.add(p.getMaSP());
		}
		
		for(Integer i : warehouses.keySet()) {
			Warehouse w = warehouses.get(i);
			warehouse.add(w.getMaKho());
		}
		
		for(Integer i : suppliers.keySet()) {
			Supplier s = suppliers.get(i);
			supplier.add(s.getTenNCC());
		}
		
		maKho_CB.setItems(warehouse);
		maKho_CB.getSelectionModel().selectFirst();
		amount.setText("0");
		
		
		
		maSP_CB.setItems(product);
		maSP_CB.getSelectionModel().selectFirst();	
		
		price.setText(products.get(maSP_CB.getSelectionModel().getSelectedItem()).getGiaTien());
		try {
			money.setText(Calculate(price.getText(), Integer.parseInt(amount.getText())));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		maSP_CB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		      @Override
		      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
		        price.setText(products.get(maSP_CB.getItems().get((Integer) number2)).getGiaTien());
		        try {
					money.setText(Calculate(price.getText(), Integer.parseInt(amount.getText())));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		   });
		
		NCC_CB.setItems(supplier);
		NCC_CB.getSelectionModel().selectFirst();
	}
	
	@FXML
	private void Cancle() {
		Stage stage = (Stage) anchor_Pane.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void Import() throws Exception {
		if(!amount.getText().equals("0")) {
			Import i = new Import(
					getMaPhieuNhap(),
					maKho_CB.getSelectionModel().getSelectedItem(),
					maSP_CB.getSelectionModel().getSelectedItem(), 
					getIDNCC(NCC_CB.getSelectionModel().getSelectedItem()),
					this.id,
					Integer.parseInt(amount.getText()),
					getDate(),
					price.getText(),
					money.getText()
			);
			i.setHoTenNV(this.name);
			ib.Insert(i);
			
			Information("Phiếu Nhập Hàng", "Nhập Hàng Thành Công" ,"");
			Stage stage = (Stage) anchor_Pane.getScene().getWindow();
			stage.close();
		}
	}
	
	//GET TODAY'S DATE
		private String getDate() {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = df.format(cal.getTime());
			return date;
		}
	//========================================================================
	
	//CALCULATE MONEY
		private String Calculate(String money, int amount) throws ParseException {
			Locale localeVN = new Locale("vi", "VN");
			money = money.replace(".", "");
			NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
			String str = currencyVN.format(Double.parseDouble(money) * amount);
			str = str.replaceAll(" đ", "");
			return str;
		}
	//===========================================================================
	
	//GET SUPPLIER'S ID
		private int getIDNCC(String text) {
			for(Integer i : suppliers.keySet()) {
				if(text.equals(suppliers.get(i).getTenNCC()))
					return suppliers.get(i).getMaNCC();
			}
			return 1;
		}
	//===========================================================================
	
		
	//GET IMPORT BILL'S ID
		private int getMaPhieuNhap() {
			imports = ib.getList();
			if(imports.size() == 0) {
				System.out.println("abc");
				return 1;
			}
			else {
				int j = 1;
				for(Integer i : imports.keySet()) {
					if(j != imports.get(i).getMaPhieu())
						return j;
					else 
						j++;
				}
				return j;
			}
		}
	//===========================================================================
		
	//AMOUNT
		@FXML
		private void Increase(MouseEvent event) {
			up.setOnMousePressed(e -> {
				if(!amount.getText().equals("")) {
					int a = Integer.parseInt(amount.getText());
					a = a + 1;
					amount.setText("" + a);
					
					try {
						money.setText(Calculate(price.getText(), a));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					return;
				}
			});
		}
		
		@FXML
		private void Decrease(MouseEvent event) {
			down.setOnMousePressed(e -> {
				if(!amount.getText().equals("")) {
					int a = Integer.parseInt(amount.getText());
					if(a == 1){
						amount.setText("1");
						try {
							money.setText(Calculate(price.getText(), a));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						return;
					}
					a = a - 1;
					amount.setText("" + a);
					try {
						money.setText(Calculate(price.getText(), a));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			});

		}
	//========================================================================
	
	
	//MESSAGE
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
		employees = eb.getList();
		this.id = id;
		for(Integer i : employees.keySet()) {
			if(id == employees.get(i).getMaNV()) {
				this.name = employees.get(i).getHoNV() + " " + employees.get(i).getTenNV();
				break;
			}
		}
	}
}
