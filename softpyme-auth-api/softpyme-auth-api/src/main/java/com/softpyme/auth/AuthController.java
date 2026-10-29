
package com.softpyme.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    // Inyecta el servicio que procesa el registro y el inicio de sesión.
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Endpoint para registrar un usuario.
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registrar(
            @RequestBody AuthRequest solicitud) {

        ApiResponse respuesta = authService.registrar(solicitud);

        if (respuesta.success()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }

        return ResponseEntity.badRequest().body(respuesta);
    }

    // Endpoint para validar las credenciales del usuario.
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> iniciarSesion(
            @RequestBody AuthRequest solicitud) {

        ApiResponse respuesta = authService.iniciarSesion(solicitud);

        if (respuesta.success()) {
            return ResponseEntity.ok(respuesta);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
    }
}
