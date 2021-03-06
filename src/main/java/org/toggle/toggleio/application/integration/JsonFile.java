package org.toggle.toggleio.application.integration;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

/**
 * This class handles writing and reading a file of type .json
 */
public class JsonFile {
    final private static String DEVICE_FILE = "devices.json";

    /**
     * Returns a json of type JSONObject if a file exist and contains correctly formated json
     *
     * @return JSONObject
     * @throws ParseException throws ParseException if file is not of json type or incorrectly formated
     */
    private static JSONArray read() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(DEVICE_FILE)) {
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            return jsonArray;
        } catch (IOException | ParseException e) {
            return null;
        }

    }

    /**
     * Writes the json object to a existing json file or creates a new file if none exist
     *
     * @param jsonObject to write in file
     * @throws IOException if
     */
    private static void write(JSONArray jsonObject) throws IOException {
        try (FileWriter file = new FileWriter(DEVICE_FILE)) {

            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Adds a device with supplied id and token to file DEVICE_FILE
     * @param id
     * @param token
     */
    public static void addDevice(int id, String token) {
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = read();
        } catch (Exception e) {
        }
        if (jsonArray == null) jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            jsonObject = (JSONObject) jsonObject.get("device");
            if ((long)jsonObject.get("id") == id){
               return;
            }
        }
        JSONObject deviceDetails = new JSONObject();
        deviceDetails.put("id", id);
        deviceDetails.put("token", token);

        JSONObject deviceObject = new JSONObject();
        deviceObject.put("device", deviceDetails);

        jsonArray.add(deviceObject);

        try {
            write(jsonArray);
        } catch (IOException e) {
            System.out.println("Something went wrong when writing");
        }


    }

    /**
     * Removes device with supplied id from the JSON file defined in DEVICE_FILE
     * @param id Device ID
     */
    public static void removeDevice(int id) {
        JSONArray jsonArray;
        try {
            jsonArray = read();
        } catch (Exception e) {
            return;
        }
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            jsonObject = (JSONObject) jsonObject.get("device");
            if ((long)jsonObject.get("id") == id) {
                jsonArray.remove(i);
                break;
            }
        }

        try {
            write(jsonArray);
        } catch (IOException e) {
            System.out.println("Something went wrong when writing");
        }
    }

    /**
     * Set the device token if the supplied device with id exits
     * @param id
     * @param token
     */
    public static void setToken(int id, String token) {
        JSONArray jsonArray;
        try {
            jsonArray = read();
        } catch (Exception e) {
            return;
        }
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            jsonObject = (JSONObject) jsonObject.get("device");
            if ((long)jsonObject.get("id") == id) {
                jsonObject.put("token", token);
                break;
            }
        }

        try {
            write(jsonArray);
        } catch (IOException e) {
            System.out.println("Something went wrong when writing");
        }
    }

    /**
     * Returns the token of device supplied if it exists, else returns null
     * @param id
     * @return
     */
    public static String getToken(long id) {
        JSONArray jsonArray;
        try {
            jsonArray = read();
        } catch (Exception e) {
            return "-1";
        }
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            jsonObject = (JSONObject) jsonObject.get("device");
            if ((long)jsonObject.get("id") == id) {
                return (String) jsonObject.get("token");
            }
        }
        return "-1";

    }
}
