package controller.order;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private DatePicker dateOrderDate;

    @FXML
    private TableView<Order> tblOrder;

    @FXML
    private JFXTextField txtCustId;

    @FXML
    private JFXTextField txtOrderId;

    OrderService service = new OrderController();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("CustId"));

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null) {
                addValueToText(newVal);
            }
        });

        loadTable();
    }

    private void addValueToText(Order newVal) {
        txtCustId.setText(newVal.getCustId());
        txtOrderId.setText(newVal.getOrderId());
        dateOrderDate.setValue(LocalDate.parse(newVal.getOrderDate()));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void loadTable() {
        tblOrder.setItems(service.getAllOrders());
    }
}
