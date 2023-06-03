package com.example.backendpi.controller;

import com.example.backendpi.dto.PageResponseDTO;
import com.example.backendpi.dto.UserDTO;
import com.example.backendpi.dto.UserPageDTO;
import com.example.backendpi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "UserController (Este es solo para mostrar ejemplo de DTO y paginado)")
public class UserController {

    private final UserService userService;

    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Get page of users",
            parameters = { @Parameter(in = ParameterIn.QUERY, name = "page", description = "Page"),
                    @Parameter(in = ParameterIn.QUERY, name = "size", description = "Size") },
            responses = {
                    @ApiResponse(responseCode = "200",description = "Successful Operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserPageDTO.class)))})
    @GetMapping("/api/users")
    public PageResponseDTO<UserDTO> getUsers(@PageableDefault(size = 10,page = 0) @ParameterObject Pageable pageable) {
        return userService.getUsers(pageable);
    }
}
