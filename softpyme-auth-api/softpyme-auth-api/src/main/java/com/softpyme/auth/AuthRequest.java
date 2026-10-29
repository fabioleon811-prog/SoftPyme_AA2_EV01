
package com.softpyme.auth;

// Recibe el usuario y la contraseña enviados por el cliente.
public record AuthRequest(String usuario, String contrasena) {
}
