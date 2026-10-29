
package com.softpyme.auth;

// Estandariza las respuestas del servicio web.
public record ApiResponse(boolean success, String mensaje) {
}
