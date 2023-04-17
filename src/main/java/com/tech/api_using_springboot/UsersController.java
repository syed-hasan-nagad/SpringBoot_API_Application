package com.tech.api_using_springboot;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.tech.api_using_springboot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    Gson gson;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object>> getUserUsingGET(@RequestParam("id") String[] userId) {
        GetRequests getRequests = new GetRequests();

        List<Object> responses = new ArrayList<>();
        if (userId == null) {
            return ResponseEntity.notFound().build();
        } else {
            for (String i : userId) {

                JsonArray array = gson.fromJson(getRequests.getUsers(i), JsonArray.class);
                responses.add(gson.fromJson(array.get(0), User.class));
            }
        }
        return ResponseEntity.ok(responses);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserUsingPOST(@RequestBody com.tech.api_using_springboot.models.RequestBody requestBody) {
        GetRequests getRequests = new GetRequests();
        return ResponseEntity.ok(getRequests.getUsers(requestBody.getId()));
    }

}
