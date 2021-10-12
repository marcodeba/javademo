package com.demo.javademo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyJSONTest {

    public static void main(String[] args) {
        String json = "{\n" +
                "\t\"ResponseStatus\": \n" +
                "\t{\n" +
                "\t\t\n" +
                "\t},\n" +
                "\t\"Data\": \n" +
                "\t{\n" +
                "\t\t\"TourCopyWriterInfo\": \n" +
                "\t\t{\n" +
                "\t\t\t\"DefaultCopyWriter\": \"String\",\n" +
                "\t\t\t\"SearchValue\": \"String\"\n" +
                "\t\t},\n" +
                "\t\t\"ThemeList\": \n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"Key\": \"String\",\n" +
                "\t\t\t\t\"Value\": \"String\",\n" +
                "\t\t\t\t\"PoiId\": \"String\",\n" +
                "\t\t\t\t\"IsJump\": \"False\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"DestinationList\": \n" +
                "\t\t[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"DestName\": \"String\",\n" +
                "\t\t\t\t\"CategoryId\": \"0\",\n" +
                "\t\t\t\t\"SubDestList\": \n" +
                "\t\t\t\t[\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"Key\": \"String\",\n" +
                "\t\t\t\t\t\t\"Value\": \"String\",\n" +
                "\t\t\t\t\t\t\"PoiId\": \"String\",\n" +
                "\t\t\t\t\t\t\"IsJump\": \"False\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t]\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"TourProductList\": \n" +
                "\t\t{\n" +
                "\t\t\t\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        new MyJSONTest().parseResponseData(json);
    }

    public List<TopTripType> parseResponseData(String responseStr) {
        List<TopTripType> result = new ArrayList<>();
        try {
            JSONObject object = JSON.parseObject(responseStr);
            JSONObject data = (JSONObject) object.get("Data");
            JSONArray jsonArray = data.getJSONArray("ThemeList");
            result = JSON.parseArray(jsonArray.toJSONString(), TopTripType.class);
            for (TopTripType trip : result) {
                System.out.println(trip.getKey());
                System.out.println(trip.getPoiId());
                System.out.println(trip.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

class TopTripType {
    String key;
    String value;
    String poiId;
    boolean isJump;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public boolean isJump() {
        return isJump;
    }

    public void setJump(boolean jump) {
        isJump = jump;
    }
}

