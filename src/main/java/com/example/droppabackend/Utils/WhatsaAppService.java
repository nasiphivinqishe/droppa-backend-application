package com.example.droppabackend.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WhatsaAppService {

        public void sendWhatsApp(String phoneNumber, String recipientName, int orderId, String newStatus) {
            try {
                System.out.println("Phone: " + phoneNumber + ", Name: " + recipientName + ", PackageNo: " + orderId + ", Status: " + newStatus);

                sendMessage(phoneNumber, recipientName, String.valueOf(orderId), newStatus);

                System.out.println("WhatsApp sent successfully!");
            } catch (IOException e) {
                System.out.println("Error in sending WhatsApp: " + e.getMessage());
            }
        }

        private void sendMessage(String phoneNumber, String recipientName, String orderId, String newStatus) throws IOException {
            try {
                URL url = new URL("https://graph.facebook.com/v18.0/243932928801159/messages");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization", "Bearer EAFf4jW7ZCrXABO7ttXi9DBalZBSTH2vjCxcy8MOaKHo8ZAUoTxih65pbSPmZCQQHzIwznfhKx6jC7j3kRqIzRHuJqeqJXNVj4NSdHKGGZC7rZCuThv1K8vWD5t6oWnsUKhxyAYSTPmFYf6cWuxhnkiKH5PFQMAcUTA2w0SyyoOM9snhA3LmtAw0pAXGzOweVNTuWKA9Ua9S7yaIifkdlYj");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                String jsonInputString = "{\"messaging_product\": \"whatsapp\", \"to\": \""+phoneNumber+"\", \"type\": \"template\", \"template\": { \"name\": \"status_updates\", \"language\": { \"code\": \"en_US\" }, \"components\": [ { \"type\": \"body\", \"parameters\": [ { \"type\": \"text\", \"text\": \""+recipientName+"\" }, { \"type\": \"text\", \"text\": \""+orderId+"\" }, { \"type\": \"text\", \"text\": \""+newStatus+"\" } ] } ] } }";

                System.out.println("***********"+jsonInputString);
                try(OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                System.out.println("Response: " + response.toString());
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            http.setRequestMethod("POST");
//            http.setDoOutput(true);
//            http.setRequestProperty("Authorization", "Bearer EAFf4jW7ZCrXABO4aKs5c1ZC2zZAUhGQasgpdeQTFko7hTN5JZAbeQCQqnIRA9DZCx5TLWKRdZAR59xOv1pBGCDT4miMZAkqVOo3yVMY6p31yA0eg0JenVuQG51GjYAqDtBwoq5cJgTzpOyeT1ZCsAXz59TBEFpIcBwqpqnZCq81BDNkz4Mwg2DXOfIxoDIzLbdGLMUZAK7oIrZASYXQct4ZCpoQZD");
//            http.setRequestProperty("Content-Type", "application/json");
//
//            String data = "{\"messaging_product\": \"whatsapp\", \"to\": \"" + phoneNumber + "\", \"type\": \"template\", \"template\": { \"name\": \"status_update\", \"language\": { \"code\": \"en_US\" }, \"params\": { \"1\": \"" + recipientName + "\", \"2\": \"" + orderId + "\", \"3\": \"" + newStatus + "\" } } }";
//
//            byte[] out = data.getBytes(StandardCharsets.UTF_8);
//
//            OutputStream stream = http.getOutputStream();
//            stream.write(out);
//
//            System.out.println("Response code: " + http.getResponseCode() + ", Message: " + http.getResponseMessage());
//            http.disconnect();

        }


}
