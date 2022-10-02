package org.sm.entitygraph.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.sm.entitygraph.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Set;

@OpenAPIDefinition(info = @Info(
        title = "User API",
        version = "1.0.0",
        description = "User Details with Graph Entity ",
        contact= @Contact(
                name = "Sovan Mukherjee",
                email="sovanm10@gmail.com")
))

@RequestMapping(value="users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserAPI {

    @Operation(summary = "Get All Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Details of All the Users",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @GetMapping
    ResponseEntity<List<User>> getUsers();

    @Operation(summary = "Get User By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Details of User by user ID",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @GetMapping("/{userId}")
    ResponseEntity<User> getUserById(int userId);


    @Operation(summary = "Get User By className")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Details of User by className",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @GetMapping("/class")
    ResponseEntity<List<User>> getUserByClassName(@RequestParam("className") String className);

    @Operation(summary = "Get User By Roll number and Class Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Details of User by roll number and Class Name",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @GetMapping("/roll/{rollNumber}")
    ResponseEntity<List<User>> getUserByRollNumberAndClassName(@PathVariable("rollNumber") int rollNumber,
                                                                      @RequestParam("className") Set<String> classNames);
}
