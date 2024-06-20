package modelo

import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {

    fun cadenaConxion (): Connection? {
        try {
            val url ="jdbc:oracle:thin:@192.168.1.17:1521:xe"
            val usuario = "FERNANDO_DEVELOPER"
            val contrasena = "123456"

            val connection = DriverManager.getConnection(url,usuario,contrasena)
            return connection


        }catch (e: Exception) {
            println ("error: $e")
            return null

        }
    }
}