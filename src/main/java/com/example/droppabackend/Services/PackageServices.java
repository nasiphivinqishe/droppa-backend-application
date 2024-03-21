package com.example.droppabackend.Services;

import com.example.droppabackend.Models.CheckDeliveryStatus;
import com.example.droppabackend.Models.DeliveryStatus;
import com.example.droppabackend.Models.PackageData;
import com.example.droppabackend.Models.UpdateDeliveryStatusModel;
import com.example.droppabackend.Repositories.PackageRepository;
import com.example.droppabackend.Utils.WhatsaAppService;
import com.example.droppabackend.Utils.SESUtil;
import com.example.droppabackend.Utils.SNSUtil;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
@Service
public class PackageServices {
    PackageRepository packageRepository = new PackageRepository();
    public ResultSet requestPackageDelivery(PackageData event) throws SQLException {
        return packageRepository.requestPackageDelivery(event);
    }


    public ResultSet doDeliveryRequest(DeliveryStatus event) throws SQLException {
        return packageRepository.doDeliveryRequest(event);
    }

    public CheckDeliveryStatus checkDeliveryStatus(int packageId) throws SQLException {
        try (ResultSet resultSet = packageRepository.checkDeliveryStatus(packageId)) {
            if (resultSet != null && resultSet.next()) {
                CheckDeliveryStatus deliveryStatus = new CheckDeliveryStatus();

                deliveryStatus.setStatus(resultSet.getString("status"));
                deliveryStatus.setEstimatedDateOfDelivery(resultSet.getString("estimated_delivery"));

                System.out.println("Delivery status retrieved: " + deliveryStatus);
                return deliveryStatus;
            } else {
                throw new RuntimeException("No delivery status found for packageId: " + packageId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching delivery status", e);
        }
    }

    public void updateDeliveryStatus(UpdateDeliveryStatusModel event) throws Exception {
        UpdateDeliveryStatusModel updateModel = event;
        PackageData packageData = getDeliveryRequestById(updateModel.getDeliveryRequestId());

        if (packageData != null) {
            String recipientEmail = packageData.getRecipientEmail();
            String recepientName = packageData.getRecipientName();
            String companyEmail = packageData.getCompanyEmail();

            String emailBody = "<html><head></head><body><h4>Hello " + recepientName + "</h4>"
                    + "<p>Your delivery status has been updated to: <b>" + updateModel.getNewStatus() + "</b></p>"
                    + "<p>If you need any assistance, please contact our support team.</p>"
                    + "<p><h4>Best Regards,</h4> Droppa</p></body></html>";


// SENDING STATUS UPDATE TO RECIPIENT EMAIL

            SESUtil sesUtil = new SESUtil();
            sesUtil.send(emailBody, recipientEmail);

// SENDING EMAIL TO CUSTOMER/COMPANY WHEN PACKAGE IS DELIVERED

            if ("DELIVERED".equals(updateModel.getNewStatus())) {
                // Send email to company email address
                String companyEmailBody = "<html><head></head><body><h4>Hello,</h4>"
                        + "<p>The delivery for order " + updateModel.getDeliveryRequestId() + " has been completed.</p>"
                        + "<p>If you have any questions or concerns, please contact our support team.</p>"
                        + "<p><h4>Best Regards,</h4> Droppa</p></body></html>";

                sesUtil.send(companyEmailBody, companyEmail);
            }
// SENDING STATUS UPDATE TO RECIPIENT PHONE VIA SMS

            String message = "Hi " + recepientName + ", your order " + updateModel.getDeliveryRequestId() + " status has been updated to: " + updateModel.getNewStatus();
            SNSUtil snsUtil = new SNSUtil();
            String phoneNumber = packageData.getRecipientPhone();
            snsUtil.sendSMS(message, phoneNumber);


//SENDING STATUS UPDATE TO RECIPIENT WHATSAPP

            WhatsaAppService whatsaAppService = new WhatsaAppService();
            int orderId = updateModel.getDeliveryRequestId();
            String newStatus = updateModel.getNewStatus();
            System.out.println(orderId+"  "+newStatus+"   "+recepientName+"   "+phoneNumber);

            whatsaAppService.sendWhatsApp(phoneNumber, recepientName, orderId, newStatus);

            packageRepository.updateDeliveryStatus(event);
        } else {
            System.out.println("PackageData not found for deliveryRequestId: " + updateModel.getDeliveryRequestId());
        }
    }



    public PackageData getDeliveryRequestById(Integer integer) throws SQLException {
        PackageData packageData = new PackageData();
        ResultSet resultSet = packageRepository.getDeliveryRequestById(integer);

        try{
            while(resultSet.next()){
                packageData.setRecipientName(resultSet.getString("recepient_name"));
                packageData.setRecipientPhone(resultSet.getString("recepient_phone"));
                packageData.setDeliveryAddress(resultSet.getString("delivery_address"));
                packageData.setRecipientEmail(resultSet.getString("recepient_email"));
                packageData.setPackageWeight(resultSet.getInt("package_weight"));
                packageData.setCompanyEmail(resultSet.getString("customer_email"));

            }
            return packageData;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
