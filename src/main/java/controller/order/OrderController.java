package controller.order;

import controller.order.OrderService;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderController implements OrderService{
    @Override
    public boolean addOrder(Order order) {

        try {
            String SQL = "INSERT INTO orders values(?,?,?,?,?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,order.getCustId());
            psTm.setObject(3,order.getOrderId());
            psTm.setObject(4,order.getOrderDate());


            return psTm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Order> getAllOrders() {
            String SQL = "SELECT * FROM orders";
        ObservableList<Order> orderObservableList = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement PSTM = connection.prepareStatement(SQL);
            ResultSet resultSet = PSTM.executeQuery();

            while (resultSet.next()){
             orderObservableList.add(new Order(
                     resultSet.getString("OrderID"),
                     resultSet.getDate("OrderDate").toString(),
                     resultSet.getString("CustID")
             ));
            }

            return orderObservableList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateOrder(Order order) {

        String SQL = "UPDATE orders SET OrderID=?,  OrderDate=?, CustID=? WHERE OrderID=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,order.getCustId());
            psTm.setObject(2,order.getOrderDate());
            psTm.setObject(3,order.getOrderDate());
            return psTm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean deleteOrder(String id) {
        try {
            return DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM orders WHERE OrderID='" + id + "'") > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Order searchOrder(String OrderId) {
        return null;
    }
}
