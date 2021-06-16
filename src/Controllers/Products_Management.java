package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import BUS.Product_BUS;
import DTO.Product;
import GUI.Frames.Function.Product_Edit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Products_Management implements Initializable {

	@FXML
	public TableView<Product> product_Table;
	
	@FXML
	private TableColumn<Product, Integer> maSP;
	
	@FXML
	private TableColumn<Product, ImageView> hinhAnh;
	
	
	@FXML
	private TableColumn<Product, String> tenSP;
		
	@FXML
	private TableColumn<Product, String> loaiSP;
	
	@FXML
	private TableColumn<Product, String> thuongHieu;
	
	@FXML
	private TableColumn<Product, Integer> soLuong;
	
	@FXML
	private TableColumn<Product, String> donGia;
	
	@FXML
	private TableColumn<Product, Integer> trangThai;
	
	@FXML
	private JFXTextField search_Box;
	
	Product counter;
	private Product_BUS pb = new Product_BUS();
	private ObservableList<Product> oblist = FXCollections.observableArrayList();
	HashMap<Integer, Product> products;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		products = pb.getList();
		for(Integer i : products.keySet()) {
			Product p = products.get(i);
			p.getHinhAnh().setFitWidth(133);
			p.getHinhAnh().setFitHeight(133);
			oblist.add(p);
		}
		
		maSP.setCellValueFactory(new PropertyValueFactory<Product, Integer>("maSP"));
		hinhAnh.setCellValueFactory(new PropertyValueFactory<Product, ImageView>("hinhAnh"));
		tenSP.setCellValueFactory(new PropertyValueFactory<Product, String>("tenSP"));
		loaiSP.setCellValueFactory(new PropertyValueFactory<Product, String>("loaiSP"));
		thuongHieu.setCellValueFactory(new PropertyValueFactory<Product, String>("thuongHieu"));
		soLuong.setCellValueFactory(new PropertyValueFactory<Product, Integer>("soLuongConLai"));
		donGia.setCellValueFactory(new PropertyValueFactory<Product, String>("giaTien"));
		trangThai.setCellValueFactory(new PropertyValueFactory<Product, Integer>("trangThai"));
		
		product_Table.setItems(oblist);
		product_Table.setOnMousePressed(e -> {
			getCounter();
		});
	}
	
	@FXML 
	private void Search(KeyEvent event) {
		
		product_Table.getItems().stream().filter(item -> item.getTenSP().equals(search_Box.getText())).findAny().ifPresent(item -> {
			product_Table.getSelectionModel().select(item);
			product_Table.scrollTo(item);
		});
		
		product_Table.getItems().stream().filter(item -> item.getLoaiSP().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
			product_Table.getSelectionModel().select(item);
			product_Table.scrollTo(item);
		});
		
		product_Table.getItems().stream().filter(item -> item.getThuongHieu().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
			product_Table.getSelectionModel().select(item);
			product_Table.scrollTo(item);
		});
		
		product_Table.getItems().stream().filter(item -> ("" + item.getMaSP()).indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
			product_Table.getSelectionModel().select(item);
			product_Table.scrollTo(item);
		});
	}
	
	private void getCounter() {
		counter = product_Table.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	private void Edit() {
		
		if(counter == null) {
			System.out.println("error");
		}
		else {
			Stage stage = new Stage();
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Product_Edit.fxml"));
				root = loader.load();
				Product_Edit pe = loader.<Product_Edit>getController();
				pe.createProduct(counter);
				
				Scene scene = new Scene(root);
				scene.setFill(Color.TRANSPARENT);
				stage.setScene(scene);

				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(Launch.stage);
				stage.showAndWait();
				
				Refresh();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void Refresh() {
		ObservableList<Product> oblist = FXCollections.observableArrayList();
		products = pb.getList();
		
		
		for(Integer i : products.keySet()) {
			Product p = products.get(i);
			p.getHinhAnh().setFitWidth(133);
			p.getHinhAnh().setFitHeight(133);
			oblist.add(p);
		}
		
		product_Table.setItems(oblist);
	}
	
	@FXML
	private void Insert() {
		Stage stage = new Stage();
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Product_Insert.fxml"));
			root = loader.load();
			
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			stage.setScene(scene);

			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(Launch.stage);
			stage.showAndWait();
			
			Refresh();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
