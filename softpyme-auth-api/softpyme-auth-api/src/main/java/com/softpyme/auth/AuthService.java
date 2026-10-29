
package com.softpyme.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // Almacena temporalmente los usuarios registrados.
    private final Map<String, String> usuarios = new ConcurrentHashMap<>();

    // Registra un usuario nuevo si los datos son válidos.
    public synchronized ApiResponse registrar(AuthRequest solicitud) {
        if (solicitud == null
                || solicitud.usuario() == null
                || solicitud.contrasena() == null) {
            return new ApiResponse(false, "Debe ingresar usuario y contraseña.");
        }

        String usuario = solicitud.usuario().trim();
        String contrasena = solicitud.contrasena();

        if (usuario.length() < 3 || contrasena.length() < 8) {
            return new ApiResponse(false,
                    "El usuario debe tener al menos 3 caracteres y la contraseña 8.");
        }

        if (usuarios.containsKey(usuario)) {
            return new ApiResponse(false, "El usuario ya está registrado.");
        }

        usuarios.put(usuario, contrasena);
        return new ApiResponse(true, "Registro exitoso.");
    }

    // Verifica las credenciales de inicio de sesión.
    public ApiResponse iniciarSesion(AuthRequest solicitud) {
        if (solicitud == null
                || solicitud.usuario() == null
                || solicitud.contrasena() == null) {
            return new ApiResponse(false, "Debe ingresar usuario y contraseña.");
        }

        String contrasenaGuardada = usuarios.get(solicitud.usuario().trim());

        if (contrasenaGuardada != null
                && contrasenaGuardada.equals(solicitud.contrasena())) {
            return new ApiResponse(true, "Autenticación satisfactoria.");
        }

        return new ApiResponse(false, "Error en la autenticación.");
    }
}
