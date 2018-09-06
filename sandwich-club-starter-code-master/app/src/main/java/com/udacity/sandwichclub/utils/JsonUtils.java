package com.udacity.sandwichclub.utils;

import android.widget.Toast;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {
    private static final String NAME = "name";
    private static final String MAIN_NAME= "mainName";
    private static final String KNOWN_AS= "alsoKnownAs";
    private static final String PLACE_ORIGIN= "placeOfOrigin";
    private static final String DESCRIPTION= "description";
    private static final String IMAGE= "image";
    private static final String INGREDIENTS= "ingredients";
    private static  Sandwich sandObj ;




    public static Sandwich parseSandwichJson(String json)  {
        try {

            JSONObject sandwitch = new JSONObject(json);

            JSONObject name = sandwitch.getJSONObject(NAME);

            String mainName = name.getString(MAIN_NAME);

            String placeOfOrigin = sandwitch.getString(PLACE_ORIGIN);
            String description = sandwitch.getString(DESCRIPTION);
            String image = sandwitch.getString(IMAGE);

            JSONArray knownArr = name.getJSONArray(KNOWN_AS);
            JSONArray ingArr = sandwitch.getJSONArray(INGREDIENTS);

            List <String> ingredients_List = makeList(ingArr);
            List <String> alsoKnownAs_List = makeList(knownArr);


            sandObj = new Sandwich(mainName, alsoKnownAs_List, placeOfOrigin, description, image, ingredients_List);

        }
        catch (Exception e){
            e.printStackTrace();
        }


        return sandObj;
    }




    public  static  ArrayList<String> makeList(JSONArray arr) throws  Exception {

        ArrayList <String> list  = new ArrayList<String>();
        for (int i=0;i<arr.length();i++){
            list.add(arr.getString(i));
        }
        return list;

    }
}
