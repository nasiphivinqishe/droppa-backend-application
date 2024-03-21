package com.example.droppabackend.Repositories;

import com.example.droppabackend.Models.DeliveryStatus;
import com.example.droppabackend.Models.PackageData;
import com.example.droppabackend.Models.UpdateDeliveryStatusModel;
import com.example.droppabackend.Utils.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class PackageRepository {
    private Connection conn;

    public ResultSet requestPackageDelivery(PackageData event) throws SQLException {
        try {
            this.conn = DBUtil.createConnectionViaUserPwd();

            String query = "INSERT INTO delivery_request(recepient_name,recepient_phone,delivery_address,recepient_email,package_weight,customer_email) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, event.getRecipientName());
            preparedStatement.setString(2, event.getRecipientPhone());
            preparedStatement.setString(3, event.getDeliveryAddress());
            preparedStatement.setString(4, event.getRecipientEmail());
            preparedStatement.setInt(5, event.getPackageWeight());
            preparedStatement.setString(6, event.getCompanyEmail());

            return preparedStatement.executeQuery();
        }  catch (Exception e) {
            if (this.conn != null) {
                this.conn.close();
            }
            System.out.println(e);
            throw new SQLException(e);
        }
    }

    public ResultSet getDeliveryRequestById(Integer integer) throws SQLException {
        try{
            this.conn = DBUtil.createConnectionViaUserPwd();

            String query = "SELECT * FROM delivery_request WHERE id =? LIMIT 1";

            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            this.conn.close();
            return resultSet;
        } catch (Exception e) {
            if (this.conn != null) {
                this.conn.close();
            }
            throw new SQLException(e);
        }
    }

    public ResultSet doDeliveryRequest(DeliveryStatus event) throws SQLException {
        try {
            this.conn = DBUtil.createConnectionViaUserPwd();

            String query = "INSERT INTO delivery_status(delivery_request_id,date_received,status,estimated_delivery) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setInt(1, event.getDeliveryRequestId());

            LocalDateTime dateTime = event.getDateReceived();
            Timestamp timestamp = Timestamp.valueOf(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            preparedStatement.setTimestamp(2, timestamp);


            preparedStatement.setString(3, event.getStatus());
            preparedStatement.setString(4, event.getEstimatedDateOfDelivery());

            return preparedStatement.executeQuery();
        }  catch (Exception e) {
            if (this.conn != null) {
                this.conn.close();
            }
            System.out.println(e);
            throw new SQLException(e);
        }
    }

    public ResultSet checkDeliveryStatus(int packageId) throws SQLException {
        try {
            System.out.println(packageId);
            this.conn = DBUtil.createConnectionViaUserPwd();

            String query = "SELECT status, estimated_delivery FROM delivery_status WHERE delivery_request_id =? ORDER BY date_received DESC LIMIT 1";

            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setInt(1, packageId);

            ResultSet resultSet = preparedStatement.executeQuery();
            this.conn.close();
            return resultSet;

        }  catch (Exception e) {
            if (this.conn != null) {
                this.conn.close();
            }
            System.out.println(e);
            throw new SQLException(e);
        }
    }


//    public void updateDeliveryStatus(UpdateDeliveryStatusModel event) throws Exception {
//        this.conn = DBUtil.createConnectionViaUserPwd();
//
//        String currentStatus = null;
//
//        // Retrieve current status from the database
//        String selectSql = "SELECT status FROM delivery_status WHERE id = ?";
//        try (PreparedStatement selectStatement = conn.prepareStatement(selectSql)) {
//            selectStatement.setInt(1, event.getPackageId());
//            try (ResultSet resultSet = selectStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    currentStatus = resultSet.getString("status");
//                }
//            }
//        }
//
//        // Save the previous status before updating
//        String previousStatus = currentStatus;
//
//        // Determine the updated status
//        String updatedStatus;
//        if (currentStatus == null || currentStatus.isEmpty()) {
//            updatedStatus = event.getNewStatus();
//        } else {
//            updatedStatus = currentStatus + "," + event.getNewStatus();
//        }
//
//        // Update the status in the database
//        String updateSql = "UPDATE delivery_status SET status = ? WHERE id = ?";
//        try (PreparedStatement updateStatement = conn.prepareStatement(updateSql)) {
//            updateStatement.setString(1, updatedStatus);
//            updateStatement.setInt(2, event.getPackageId());
//            updateStatement.executeUpdate();
//        }
//
//        // Optionally, you can log or handle the previous status here
//        System.out.println("Previous status: " + previousStatus);
//    }


    public ResultSet updateDeliveryStatus(UpdateDeliveryStatusModel event) throws Exception {
        try{
            this.conn = DBUtil.createConnectionViaUserPwd();

            String query = "INSERT INTO delivery_status(delivery_request_id,date_received,status,estimated_delivery) VALUES (?,?,?,?)";

            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setInt(1, event.getDeliveryRequestId());

            LocalDateTime dateTime = event.getDateUpdated();
            Timestamp timestamp = Timestamp.valueOf(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            preparedStatement.setTimestamp(2, timestamp);

            preparedStatement.setString(3, event.getNewStatus());
            preparedStatement.setString(4, (event.getEstimatedDelivery()));


            return preparedStatement.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
